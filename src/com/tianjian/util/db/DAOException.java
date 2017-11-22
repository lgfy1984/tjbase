package com.tianjian.util.db;

import java.io.PrintStream;



public class DAOException
    extends Exception {

  /**
	 * 
	 */
	private static final long serialVersionUID = 8121510978615914802L;
protected Exception exception;
  protected boolean fatal;

  public DAOException() {
    super();
  }

  public DAOException(String p0) {
    super(p0);
  }

  public DAOException(Throwable p0) {
    super(p0);
  }

  public DAOException(String p0, Throwable p1) {
    super(p0, p1);
  }

  public DAOException(Exception e) {
    this(e, e.getMessage());
  }

  public DAOException(Exception e, String message) {
    super(message);
    this.exception = e;
  }

  public DAOException(Exception e, String message, boolean fatal) {
    this(e, message);
    setFatal(fatal);
  }

  public boolean isFatal() {
    return this.fatal;
  }

  public void setFatal(boolean fatal) {
    this.fatal = fatal;
  }

  public void printStackTrace() {
    super.printStackTrace();
    if (this.exception != null) {
      System.out.print("%%%% wrapped exception: ");
      this.exception.printStackTrace();
    }
  }

  public void printStackTrace(PrintStream printStream) {
    super.printStackTrace(printStream);
    if (this.exception != null) {
      System.out.print("%%%% wrapped exception: ");
      this.exception.printStackTrace(printStream);
    }
  }

  public String toString() {
    if (exception != null) {
      return super.toString() + " wraps: [" + exception.toString() + "]";
    }
    else {
      return super.toString();
    }
  }

}
