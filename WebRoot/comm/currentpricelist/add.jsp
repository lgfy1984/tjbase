<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CurrentPriceListForm" />
<html>
	<head>
		<%
		if (request.getServerPort() == 80) {
		%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
		<%
		} else {
		%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
		<%
		}
		%>
		<title><bean:message  key="comm.jsp.drugpricelist.add.title" bundle="confcommdrugmessageInit"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="include/javascript/TJMessage.js"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript"
			src="comm/include/javascript/gettext_staff.js"></script>
		<script src="include/calendar/calendar.js"></script>
		<script src="include/calendar/calendar-setup.js"></script>
		<script src="include/calendar/calendar-zh.js"></script>
		<link rel="StyleSheet" href="<%=request.getContextPath()%>/include/calendar/theme.css" type="text/css"/>
		<link rel="stylesheet" href="security/include/css/open.css" />
		<script language="javascript">
		function trim(str){  //删除左右两端的空格
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
		function yznumber(id,itermname,integer,decimal){
		var inputvalue= (document.getElementById(id)).value;
		if(inputvalue != ""){
			var reg = /^[0-9]+(\.[0-9]+)?$/;
			if(reg.test(inputvalue)){
				if(inputvalue.indexOf(".")!=-1){
					array = inputvalue.split(".");
					arg1 = array[0];
					arg2 = array[1];										
					if(arg1.length > integer){
						alert("\""+itermname+"\""+document.form.yzzs.value+integer+document.form.ws.value);
						return false;
					}
					if(arg2.length > decimal){
						alert("\""+itermname+"\""+document.form.yzxs.value+decimal+document.form.ws.value);
						return false;
					}				
				}else{
					if(inputvalue.length > integer){
						alert("\""+itermname+"\""+document.form.yzzs.value+integer+document.form.ws.value);						
						return false;
					}
				}			
			}else{
				alert(document.form.bxsz.value);
				return false;
				}
			}		
		}
function saveForm(){
	var itemCode = trim(document.form.eanCode.value);
	if(itemCode == ""){
	 	alertMessage("0-000001");
	 	return ;
	}
	if(itemCode.length>13){
		alert(document.form.zdgz.value);
	}
	var itemName = trim(document.form.itemName.value);
	if(itemName == ""){
	 	alertMessage("0-000002");
	 	return ;
	}
if (confirmMessage("0-000003")){ 
	document.form.verbId.value = "add";
	document.form.submit();
	}
}

</script>
		<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_add.css" />
	</head>
	<body>
		<form name="form" method="post" action="comm/currentpricelist.do">
		<!-- jsguojih -->
		<input type="hidden" id="cwdzs" name="zdgz" value='<bean:message  key="comm.jsp.drugpricelist.shuruguoz" bundle="confcommdrugmessageInit"/>'>
		<input type="hidden" id="bxsz" name="bxsz" value='<bean:message  key="comm.jsp.drugpricelist.bxzs" bundle="confcommdrugmessageInit"/>'>
		<input type="hidden" id="yzzs" name="yzzs" value='<bean:message  key="comm.jsp.drugpricelist.shuziyanzhengzs" bundle="confcommdrugmessageInit"/>'>
		<input type="hidden" id="yzxs" name="yzxs" value='<bean:message  key="comm.jsp.drugpricelist.shuziyanzhengxs" bundle="confcommdrugmessageInit"/>'>
		<input type="hidden" id="ws" name="ws" value='<bean:message  key="comm.jsp.drugpricelist.ws" bundle="confcommdrugmessageInit"/>'>
		<!--  -->
			<input type="hidden" name="verbId" value="add" />
			<table border="0" cellspacing="1" cellpadding="0" class="table">
				<tr>
					<td height="30px" class="biaoti" colspan="4">
						<font color="red">※</font><bean:message  key="comm.jsp.drugpricelist.add.toptitle" bundle="confcommdrugmessageInit"/>
						<font color="red">※</font>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message  key="comm.jsp.drugpricelist.add.eancode" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="eanCode" size="13" maxlength="13" onkeypress="eventOnKeyPress('itemName')" value="<%=data.getEanCode()%>" />
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message  key="comm.jsp.drugpricelist.add.DRUG_CODE" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="itemName" size="20" maxlength="20" onkeypress="eventOnKeyPress('gmp')" value="<%=data.getDrugName()%>" />
					<input type="hidden" class="kuandu" name="drugDictId" value="<%=data.getDrugDictId()%>" />
					<img src="security/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/comm/drugpricelist.do?verbId=getdrugname','itemName','drugDictId','drugCode','minSpec','minUnits')" />
					
					</td>
					
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.DRUG_C" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="drugCode"  size="10" maxlength="10"  value="<%=data.getDrugCode()%>" readonly />
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.MIN_SPEC" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="minSpec"  size="4" maxlength="4" value="<%=data.getMinSpec()%>" readonly/>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.MIN_UNITS" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="minUnits"  size="10" maxlength="10" value="<%=data.getMinUnits()%>"  readonly/>
					</td>
					<td class="qian" nowrap>
						<bean:message  key="comm.jsp.drugpricelist.add.GMP" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="gmp"  size="1" maxlength="1" value="<%=data.getGmp()%>" onkeypress="eventOnKeyPress('drugSpec')"/>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.DRUG_SPEC" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="drugSpec"  size="10" maxlength="10" onkeypress="eventOnKeyPress('units')" value="<%=data.getDrugSpec()%>" />
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.UNITS" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="units"  size="4" maxlength="4" value="<%=data.getUnits()%>" onkeypress="eventOnKeyPress('amountPerPackage')"/>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.AMOUNT_PER_PACKAGE" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="amountPerPackage"  size="5" maxlength="5" onkeypress="eventOnKeyPress('firmId')" value="<%=data.getAmountPerPackage()%>"  onchange="yznumber('amountPerPackage','<bean:message  key="comm.jsp.drugpricelist.add.AMOUNT_PER_PACKAGE" bundle="confcommdrugmessageInit"/>',5,0)" />
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.FIRM_ID" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
							<select name="firmId" style="width:162px" onkeypress="eventOnKeyPress('tradePrice')">
							<option></option>
							<%
									if (data.getFrimidlist()!= null && data.getFrimidlist().length>0 ) {
									for (int i = 0; i < data.getFrimidlist().length; i++) {
										String tempId = data.getFrimidlist()[i].toString();
										String tempName = data.getFrimnamelist()[i];
							%>
							<option value="<%=tempId%>" title="<%=tempName%>">
									<%=tempName %>
							</option>
							<%
								}
								}
							%>
						</select>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.TRADE_PRICE" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="tradePrice"  size="10" maxlength="10" onkeypress="eventOnKeyPress('retailPrice')" value="<%=data.getTradePrice()%>"  onchange="yznumber('tradePrice','<bean:message  key="comm.jsp.drugpricelist.add.TRADE_PRICE" bundle="confcommdrugmessageInit"/>',5,4)"/>
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.RETAIL_PRICE" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="retailPrice"  size="10" maxlength="10" value="<%=data.getRetailPrice()%>" onkeypress="eventOnKeyPress('hlimitPrice')" onchange="yznumber('retailPrice','<bean:message  key="comm.jsp.drugpricelist.add.RETAIL_PRICE" bundle="confcommdrugmessageInit"/>',5,4)"/>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.HLIMIT_PRICE" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="hlimitPrice"  size="10" maxlength="10" onkeypress="eventOnKeyPress('priceClass')" value="<%=data.getHlimitPrice()%>" onchange="yznumber('hlimitPrice','<bean:message  key="comm.jsp.drugpricelist.add.HLIMIT_PRICE" bundle="confcommdrugmessageInit"/>',5,4)" />
					</td>
					<td class="qian" nowrap>
						<bean:message  key="comm.jsp.drugpricelist.add.PRICE_CLASS" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="priceClass"  size="2" maxlength="2" value="<%=data.getPriceClass()%>" onkeypress="eventOnKeyPress('startDate')"/>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.START_DATE" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="startDate"  size="10" maxlength="20" onkeypress="eventOnKeyPress('stopDate')" value="<%=data.getStartDate()%>" />
					<img id="startDateButton" src="include/calendar/calendar.gif" style="cursor:hand;" align="middle" />
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.STOP_DATE" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="stopDate"  size="4" maxlength="20" value="<%=data.getStopDate()%>" onkeypress="eventOnKeyPress('memos')"/>
					 <img id="stopDateButton" src="include/calendar/calendar.gif" style="cursor:hand;" align="middle" />
					</td>
				</tr>
				<tr>
				<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.PASS_NO" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="passNo"  size="15" maxlength="15" onkeypress="eventOnKeyPress('memos')" value="<%=data.getPassNo()%>" />
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  key="comm.jsp.drugpricelist.add.MEMOS" bundle="confcommdrugmessageInit"/>：
					</td>
					<td class="hou" >
						<input type="text" class="kuandu" name="memos"  size="20" maxlength="20" onkeypress="eventOnKeyPress('btnSave')" value="<%=data.getMemos()%>" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" align="center">
						<input type="button" name="btnSave" id="btnSave" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="btnSaveForm" value='<bean:message  key="comm.jsp.drugpricelist.baocun" bundle="confcommdrugmessageInit"/>' onClick="saveForm()" />
						<input type="button" name="btnHistory" id="btnHistory" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="btnBack" value='<bean:message  key="comm.jsp.drugpricelist.fanhui" bundle="confcommdrugmessageInit"/>' onClick="history.go(-1);" />
					</td>
				</tr>
			</table>
		</form>
		<script>	
    Calendar.setup({
	  inputField    : "startDate",
	  button        : "startDateButton",
	  timeFormat    : "24",
	  ifFormat      : "%Y-%m-%d %H:%M:%S",
	  showsTime     :  false
	});
</script>
<script>	
    Calendar.setup({
	  inputField    : "stopDate",
	  button        : "stopDateButton",
	  timeFormat    : "24",
	  ifFormat      : "%Y-%m-%d %H:%M:%S",
	  showsTime     :  false
	});
</script>
	</body>
</html>
