package com.tianjian.util.comm;

import java.util.*;

public class Pages {

  String filename = "";
  int page = 1;
  long totals = -1;
  int perpagenum = 20;
  int style = 0;
  int allpage = 1;
  int cpage = 1;
  int spage = 1;
  String listPageBreak = "";
  String[] pagesign = null;
  Locale locale = Locale.getDefault();

  public Pages() {
  }

  public Pages(Locale locale) {
    this.locale = locale;
    this.pagesign = Util.getPagesign(locale);
  }

  public String getFileName() {
    return this.filename;
  }

  public void setFileName(String aFileName) {
    this.filename = aFileName;
  }

  public int getPage() {
    return this.page;
  }

  public void setPage(int aPage) {
    this.page = aPage;
  }

  public long getTotals() {
    return this.totals;
  }

  public void setTotals(long aTotals) {
    this.totals = aTotals;
  }

  public int getPerPageNum() {
    return this.perpagenum;
  }

  public void setPerPageNum(int aperpagenum) {
    this.perpagenum = aperpagenum;
  }

  public int getStyle() {
    return this.style;
  }

  public void setStyle(int aStyle) {
    this.style = aStyle;
  }

  public void setPagesign(String[] apagesign) {
    this.pagesign = apagesign;
  }

  public int getSpage() {
    return this.spage;
  }

  public void doPageBreak() {
    this.allpage = (int) Math.ceil( (this.totals + this.perpagenum - 1) /
                                   this.perpagenum);
    int intPage = this.page;
    if (intPage > this.allpage) { // pages == 0
      this.cpage = 1;
    }
    else {
      this.cpage = intPage;
    }
    this.spage = (this.cpage - 1) * this.perpagenum;
    getPageBreakStr();
  }

  public String getListPageBreak() {
    return this.listPageBreak;
  }

  private void getPageBreakStr() {

    if (this.filename.indexOf("?") == -1) {
      this.filename = this.filename + "?";
    }
    else {
      if (!this.filename.endsWith("&")) {
        this.filename = this.filename + "&";
      }
    }

    StringBuffer sb = new StringBuffer();

    if (this.style == 0) {
      if (this.cpage > 1) {
        sb.append("[<a href='");
        sb.append(this.filename);
        sb.append("page=1' >");
        sb.append(pagesign[0]);
        sb.append("</a>] [<a href='");
        sb.append(this.filename);
        sb.append("page=");
        sb.append( (cpage - 1));
        //new
        sb.append("&t=");
        sb.append(this.totals);
        //new
        sb.append("'>");
        sb.append(pagesign[1]);
        sb.append("</a>] ");
        this.listPageBreak = sb.toString();
      }
      if (this.cpage < this.allpage) {
        sb.append("[<a href='");
        sb.append(this.filename);
        sb.append("page=");
        sb.append( (cpage + 1));
        sb.append("' >");
        sb.append(pagesign[2]);
        sb.append("</a>] [<a href='");
        sb.append(this.filename);
        sb.append("page=");
        sb.append(this.allpage);
        //new
        sb.append("&t=");
        sb.append(this.totals);
        //new

        sb.append("' >");
        sb.append(pagesign[3]);
        sb.append("</a>] ");
        this.listPageBreak = sb.toString();
      }
      return;
    }
    if (this.style == 1) {
      if (this.cpage > 1) {
        sb.append("[<a href='");
        sb.append(this.filename);
        sb.append("page=1");
        //new
        sb.append("&t=");
        sb.append(this.totals);
        //new
        sb.append("'>");
        sb.append(pagesign[0]);
        sb.append("</a>] [<a href='");
        sb.append(this.filename);
        sb.append("page=");
        sb.append( (cpage - 1));
        //new
        sb.append("&t=");
        sb.append(this.totals);
        //new
        sb.append("'>");
        sb.append(pagesign[1]);
        sb.append("</a>] ");
      }
      if (this.cpage < this.allpage) {
        sb.append("[<a href='");
        sb.append(this.filename);
        sb.append("page=");
        sb.append( (cpage + 1));
        //new
        sb.append("&t=");
        sb.append(this.totals);
        //new
        sb.append("'>");
        sb.append(pagesign[2]);
        sb.append("</a>] [<a href='");
        sb.append(this.filename);
        sb.append("page=");
        sb.append(this.allpage);
        //new
        sb.append("&t=");
        sb.append(this.totals);
        //new

        sb.append("'>");
        sb.append(pagesign[3]);
        sb.append("</a>] ");
      }
      int _cpage = 0;
      if (this.allpage == 0) {
        _cpage = 0;
      }
      else {
        _cpage = cpage;
      }
      sb.append(Messages.getMessage(this.locale, "pages.str",
                                    String.valueOf(this.totals),
                                    String.valueOf(_cpage),
                                    String.valueOf(this.allpage)));
      this.listPageBreak = sb.toString();
      return;
    }
    if (this.style == 2) {
      if (this.cpage > 1) {
        sb.append("[<a href='");
        sb.append(this.filename);
        sb.append("inpages=1");
        //new
        sb.append("&t=");
        sb.append(this.totals);
        //new
        sb.append("'>");
        sb.append(pagesign[0]);
        sb.append("</a>] [<a href='");
        sb.append(this.filename);
        sb.append("inpages=");
        sb.append( (cpage - 1));
        //new
        sb.append("&t=");
        sb.append(this.totals);
        //new
        sb.append("'>");
        sb.append(pagesign[1]);
        sb.append("</a>] ");

      }
      if (this.cpage < this.allpage) {
        sb.append("[<a href='");
        sb.append(this.filename);
        sb.append("inpages=");
        sb.append( (cpage + 1));
        //new
        sb.append("&t=");
        sb.append(this.totals);
        //new
        sb.append("'>");
        sb.append(pagesign[2]);
        sb.append("</a>] [<a href='");
        sb.append(this.filename);
        sb.append("inpages=");
        sb.append(this.allpage);
        //new
        sb.append("&t=");
        sb.append(this.totals);
        //new
        sb.append("'>");
        sb.append(pagesign[3]);
        sb.append("</a>] ");

      }
      int _cpage = 0;
      if (this.allpage == 0) {
        _cpage = 0;
      }
      else {
        _cpage = cpage;
      }
      sb.append(Messages.getMessage(this.locale, "pages.str",
                                    String.valueOf(this.totals),
                                    String.valueOf(_cpage),
                                    String.valueOf(this.allpage)));
      this.listPageBreak = sb.toString();
      return;
    }

    if (this.style == 3) {
      String postto;
      if (this.filename != null && this.filename.length() > 0) {

        sb.append(
            "<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n");
        filename = filename.toLowerCase();
        int index = filename.indexOf("?");
        if (index != -1) {
          postto = filename.substring(0, index);
          filename = filename.substring(index + 1, filename.length());
        }
        else {
          postto = filename;
          filename = "";
        }
        sb.append("<FORM METHOD=POST ACTION=\"" + postto + "\">\n");
        sb.append("<tr><td><div align=\"right\">\n");
        //System.out.println(filename);
        if (filename != null && filename.length() > 0) {
          String[] ss = filename.split("&");
          if (ss != null) {
            //System.out.println(ss.length);
            for (int i = 0; i < ss.length; i++) {
              String[] p = ss[i].split("=");
              if (p != null && p.length == 2) {
                sb.append("<INPUT TYPE=\"hidden\" name=\"" + p[0] +
                          "\" value=\"" +
                          p[1] + "\">\n");

              }
            }
          }
        }

        if (filename != null && filename.length() > 0) {
          filename = postto + "?" + filename;
        }
        else {
          filename = postto;
        }

        if (this.cpage > 1) {
          sb.append("[<a href='");
          sb.append(this.filename);
          sb.append("page=1");
          //new
          sb.append("&t=");
          sb.append(this.totals);
          //new
          sb.append("'>");
          sb.append(pagesign[0]);
          sb.append("</a>] [<a href='");
          sb.append(this.filename);
          sb.append("page=");
          sb.append( (cpage - 1));
          //new
          sb.append("&t=");
          sb.append(this.totals);
          //new
          sb.append("'>");
          sb.append(pagesign[1]);
          sb.append("</a>] ");

        }
        if (this.cpage < this.allpage) {
          sb.append("[<a href='");
          sb.append(this.filename);
          sb.append("page=");
          sb.append( (cpage + 1));
          //new
          sb.append("&t=");
          sb.append(this.totals);
          //new
          sb.append("'>");
          sb.append(pagesign[2]);
          sb.append("</a>] [<a href='");
          sb.append(this.filename);
          sb.append("page=");
          sb.append(this.allpage);
          //new
          sb.append("&t=");
          sb.append(this.totals);
          //new
          sb.append("'>");
          sb.append(pagesign[3]);
          sb.append("</a>] ");

        }
        int _cpage = 0;
        if (this.allpage == 0) {
          _cpage = 0;
        }
        else {
          _cpage = cpage;
        }
        sb.append(Messages.getMessage(this.locale, "pages.str",
                                      String.valueOf(this.totals),
                                      String.valueOf(_cpage),
                                      String.valueOf(this.allpage)));
        sb.append(" ");
        sb.append(Messages.getMessage(this.locale, "pages.post"));
        sb.append("</div></td></tr>\n");
        sb.append("</FORM>\n");
        sb.append("</table>\n");

        this.listPageBreak = sb.toString();
      }
      return;
    }

  }

}
