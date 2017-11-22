<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommConfigHospitalTypeForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%if(request.getServerPort() == 80) {%>
<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
<%} else {%>
<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<%}%>
<title><bean:message key="comm.jsp.common.text44" bundle="conf.comm.comm"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="conf.comm.comm"/>"></script>
<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
<script language="javascript" src="<bean:message key="comm.js.CommMessage.path" bundle="conf.comm.comm"/>"></script>
<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
<script language="javascript">
	function saveForm(){
        if(!Validator.Validate(document.forms.form,3)){
         return ;
         }
		if(isNaN(document.form.seqNo.value)){
			alert('<bean:message key="comm.jsp.common.text49" bundle="conf.comm.comm"/>'); 
			return;
		}
		if(isNaN(document.form.principal_phone.value)){
			alert('<bean:message key="comm.jsp.common.text49" bundle="conf.comm.comm"/>');
			return true;
			    }
		if(document.form.itemCode.value == ""){
			alert('<bean:message key="security.jsp.securityConfigParamClass1.update.warn" bundle="security"/>');
		 	return ;
		}
		if(isNaN(document.form.itemCode.value)){
			alert('<bean:message key="comm.jsp.commconfigtrue.add.sc" bundle="conf.comm.CommMessageguoh"/>');
			return ; 
		}
		if(document.form.itemName.value == ""){
			alert('<bean:message key="security.jsp.securityConfigParamClass1.update.warn1" bundle="security"/>');
		 	return ;
		}
		if (confirmMessage("<bean:message key='security.jsp.commom.save' bundle='security'/>")){     
		    document.form.idHidden.value = id;  
		    document.form.verbId.value = "add";    
		    document.form.submit(); 
		    }   
		}
	function newXMLHttpRequest() {
		var xmlreq = false;
		if (window.XMLHttpRequest) {
			xmlreq = new XMLHttpRequest();		
		} else if (window.ActiveXObject) {
		    try {
	      		xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e1) {
				try {
	        		xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e2) {
				xmlreq = false;
		    	}
	    	}
		}	
		return xmlreq;
	}

	function getReadyStateHandler(req, responseXmlHandler) {
		return function () {
			if (req.readyState == 4) {
	      		if (req.status == 200) {
	      			//alert(req.responseText); 
				    responseXmlHandler(req.responseXML);
				} else {
				    alert("HTTP error: " + req.status);
	      		}
	    	}
	  	}
	}
	
	
	function setLevel2(url){
		var levelCode_1 = document.getElementById("levelCode_1").value;
		var xmlHttp = newXMLHttpRequest();
		var sendTo = url + "?verbId=setLevel2&levelCode1=" + levelCode_1;
		//alert(sendTo);
		xmlHttp.open("GET", sendTo, true);
		var handlerFunction = getReadyStateHandler(xmlHttp, updateLevel2);
		xmlHttp.onreadystatechange = handlerFunction;
		xmlHttp.send(null);
	}
	function updateLevel2(level2XML) {
		//alert();
	   	var level2 = document.getElementById("levelCode_2");
	    while (level2.options.length) {
	        level2.remove(0);
	    }
	   	var indexObj = level2XML.getElementsByTagName("index")[0];
		var index = indexObj.childNodes[0].nodeValue;
		for (var i = 0; i < index; i++) {
			var keyObj = level2XML.getElementsByTagName("key")[i];
			var valueObj = level2XML.getElementsByTagName("value")[i];
			var newElem = document.createElement("option");
			if(valueObj.childNodes[0] == null){
				newElem.text = "" ;
			} else {
				newElem.text = valueObj.childNodes[0].nodeValue ;
			}
			if(keyObj.childNodes[0] == null){
				newElem.value = "" ;
			} else {
				newElem.value = keyObj.childNodes[0].nodeValue ;
			}
			level2.add(newElem);
		}
	}
	function showHspMessage(message){
				if(message != ''&& message != null){
					alert(message);
					return;
				}
			}
</script>
<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
</head>

<body onload="showHspMessage('<%=data.getMessage() %>')" >
<form name="form" method="post" action= "comm/commConfigHospitalType.do" >
<input type="hidden" name="verbId" value="add" />
<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
    <tr>
        <td class="tblTitle" colspan="4"><span>※</span><bean:message key="comm.jsp.different.text40" bundle="conf.comm.comm"/><span>※</span></td>
    </tr>
    <tr>
        <td class="tblLable">
            <bean:message key="comm.jsp.common.text48" bundle="conf.comm.comm"/>:
        </td>
        <td>
            <input type="text"  name="seqNo" id="principal_phone" size="30" maxlength="11"
               onkeypress="eventOnKeyPress('levelCode_1')" max="11" dataType="LimitB" msg="序号输入过长"
              value="<%=data.getSeqNo() %>"  />
        </td>
        <td class="tblLable">
            <bean:message key="comm.jsp.common.text9" bundle="conf.comm.comm"/>:
        </td>
        <td>
            <select name="levelCode_1" 
				id="levelCode_1" onchange="setLevel2('comm/commConfigHospitalType.do')"
				onkeypress="eventOnKeyPress('levelCode_2')">
				<%
					if (data.getLevelCode_1List() != null
						&& data.getLevelCode_1List().length > 0) {
						for (int i = 0; i < data.getLevelCode_1List().length; i++) {
							String tempId = data.getLevelCode_1List()[i];
							String tempName = data.getLevelName_1List()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data.getLevelCode_1()) ? "selected": ""%>>
								<%=tempName%>
							</option>
							<%
						}
					}
				%>
			</select>
        </td>
    </tr>
    
    <tr>
    	<td class="tblLable">
            <bean:message key="comm.jsp.common.text58" bundle="conf.comm.comm"/>:
        </td>
        <td>
            <select name="levelCode_2" 
				id="levelCode_2"
				onkeypress="eventOnKeyPress('itemCode')">
				<%
					if (data.getLevelCode_2List() != null
						&& data.getLevelCode_2List().length > 0) {
						for (int i = 0; i < data.getLevelCode_2List().length; i++) {
							String tempId = data.getLevelCode_2List()[i];
							String tempName = data.getLevelName_2List()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data.getLevelCode_2()) ? "selected": ""%>>
								<%=tempName%>
							</option>
							<%
						}
					}
				%>
			</select>
        </td>
        
    	<td class="tblLable">
         	<span>*</span> <bean:message key="comm.jsp.common.text10" bundle="conf.comm.comm"/>:
        </td>
        <td>
            <input type="text" class="kuandu" name="itemCode" id="itemCode" size="20" maxlength="8"
            	onkeypress="eventOnKeyPress('itemName')"  max="32" dataType="LimitB" msg="代码输入过长"
             	value="<%=data.getItemCode() %>" />
        </td>  
    </tr>
    <tr>
         <td class="tblLable">
         	 <span>*</span> <bean:message key="comm.jsp.common.text29" bundle="conf.comm.comm"/>:
        </td>
        <td>
            <input type="text" name="itemName" id="itemName" size="50" maxlength="50"
              onkeypress="eventOnKeyPress('comments')"  max="100" dataType="LimitB" msg="名称输入过长"
              value="<%=data.getItemName() %>" />
        </td>
        <td class="tblLable">
            <bean:message key="comm.jsp.common.text4" bundle="conf.comm.comm"/>:
        </td>
        <td>
            <input type="text" name="comments" id="comments" size="30" maxlength="50"
           onkeypress="eventOnKeyPress('btnSaveForm')"  max="40" dataType="LimitB" msg="备注输入过长"
           value="<%=data.getComments() %>"  />
        </td>
    </tr>
  </table>


  <!-- Sheet operation button area -->
  	<div class="btnSave">
		<input type="button" name="btnSaveForm" value="<bean:message key="comm.jsp.common.text3" bundle="conf.comm.comm"/>" onclick="saveForm()" />
        <input type="button"  name="btnBack" value="<bean:message key="comm.jsp.common.text19" bundle="conf.comm.comm"/>" onclick="history.go(-1);" /> 
         
	</div>
</form>
</body>
</html>
