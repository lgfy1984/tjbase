package com.tianjian.util.comm;


public class PageList {

  private String pageShowString;
  private java.util.List<?> objectList;
  private Pages pages;

  public PageList() {
  }

  public PageList(Pages pages) {
    this.pages = pages;
    this.pageShowString = pages.getListPageBreak();
  }

  public String getPageShowString() {
    return pageShowString;
  }

  public void setPageShowString(String pageShowString) {
    this.pageShowString = pageShowString;
  }

  public java.util.List<?> getObjectList() {
    return objectList;
  }

  public void setObjectList(java.util.List<?> objectList) {
    this.objectList = objectList;
  }

  public Pages getPages() {
    return pages;
  }

  public void setPages(Pages pages) {
    this.pages = pages;
  }

}
