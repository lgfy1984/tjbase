<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CurrentPriceListForm" />
<html>
<head>
<%if(request.getServerPort() == 80) {%>
  <base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
  <%} else {%>
  <base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
<%}%>
<title> <bean:message  key="comm.jsp.drugpricelist.detail.title" bundle="confcommdrugmessageInit"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_detail.css" />
</head>

<body>
<form name="form" method="post">

<input type="hidden" name="verbId" value="detail">

 <table border="0" cellspacing="1" cellpadding="0" class="table">
 	<tr>
        <td class="biaoti" colspan="4" nowrap >
              <bean:message  key="comm.jsp.drugpricelist.detail.toptitle" bundle="confcommdrugmessageInit"/>
        </td>
    </tr>
   <tr>
					<td class="qian" nowrap>
						<font color="red">*</font>  <bean:message  key="comm.jsp.drugpricelist.add.eancode" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getEanCode()%>
					</td>
					
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message  key="comm.jsp.drugpricelist.add.DRUG_CODE" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getDrugName()%>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.DRUG_C" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getDrugCode()%>
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.MIN_SPEC" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getMinSpec()%>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.MIN_UNITS" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getMinUnits()%>
					</td>
					<td class="qian" nowrap>
						<bean:message  key="comm.jsp.drugpricelist.add.GMP" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getGmp()%>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.DRUG_SPEC" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getDrugSpec()%>
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.UNITS" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getUnits()%>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.AMOUNT_PER_PACKAGE" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getAmountPerPackage()%>
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.FIRM_ID" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getFirmId() %>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.TRADE_PRICE" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getTradePrice()%>
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.RETAIL_PRICE" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getRetailPrice()%>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.HLIMIT_PRICE" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getHlimitPrice()%>
					</td>
					<td class="qian" nowrap>
						<bean:message  key="comm.jsp.drugpricelist.add.PRICE_CLASS" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getPriceClass()%>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.START_DATE" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getStartDate()%>
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.STOP_DATE" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getStopDate()%>
					</td>
				</tr>
				<tr>
				<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.PASS_NO" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getPassNo()%>
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.MEMOS" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<%=data.getMemos()%>
					</td>
				</tr>
   
  </table>


  <!-- Sheet operation button area -->
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
          <td height="30" align="center">
          
          	<input type="button" style="cursor:hand; background:url(include/images/tong.gif) no-repeat; border:0; width:50px; height:23px; font-size:12px;" name="btnBack" value='<bean:message  key="comm.jsp.drugpricelist.fanhui" bundle="confcommdrugmessageInit"/>' onClick="history.go(-1);" />
           
          </td>
      </tr>
  </table>
</form>
</body>
</html>
