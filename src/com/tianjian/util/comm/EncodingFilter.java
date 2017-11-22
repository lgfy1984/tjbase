package com.tianjian.util.comm;

import javax.servlet.http.HttpServlet;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.*;
import java.io.IOException;



public class EncodingFilter
    extends HttpServlet
    implements Filter {

  /**
	 * 
	 */
	private static final long serialVersionUID = 6562165861718929910L;
private FilterConfig filterConfig;
  private String targetEncoding = "ASCII";

  /**
   * Called by the web container to indicate to a filter that it is being placed
   * into service.
   *
   * @param filterConfig FilterConfig
   * @throws ServletException
   * @todo Implement this javax.servlet.Filter method
   */
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
    this.targetEncoding = filterConfig.getInitParameter("encoding");
  }

  /**
   * The <code>doFilter</code> method of the Filter is called by the container
   * each time a request/response pair is passed through the chain due to a
   * client request for a resource at the end of the chain.
   *
   * @param request ServletRequest
   * @param response ServletResponse
   * @param chain FilterChain
   * @throws IOException
   * @throws ServletException
   * @todo Implement this javax.servlet.Filter method
   */
  public void doFilter(ServletRequest srequest, ServletResponse sresponse,
                       FilterChain chain) throws IOException, ServletException {
    try {
      HttpServletRequest request = (HttpServletRequest) srequest;
      request.setCharacterEncoding(targetEncoding);
      chain.doFilter(srequest, sresponse);
    }
    catch (ServletException sx) {
      filterConfig.getServletContext().log(sx.getMessage());
    }
    catch (IOException iox) {
      filterConfig.getServletContext().log(iox.getMessage());
    }
  }

  /**
   * Called by the web container to indicate to a filter that it is being taken
   * out of service.
   *
   * @todo Implement this javax.servlet.Filter method
   */
  public void destroy() {
    filterConfig = null;
    targetEncoding = null;
  }

}
