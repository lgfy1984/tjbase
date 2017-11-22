<%@page contentType="text/html; charset=gbk"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommConfigGbDrugclassForm" />
<jsp:useBean id="ls" scope="request" class="com.tianjian.comm.struts.form.CommConfigGbDrugclassForm" />
<html>
<head>
<%if(request.getServerPort() == 80) {%>
  <base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
  <%} else {%>
  <base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
<%}%>
<title><bean:message key="comm.jsp.common.text42" bundle="conf.comm.comm"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script language="javascript" src="<%=request.getContextPath() %>/<bean:message key="comm.js.CommMessage.path" bundle="conf.comm.comm"/>"></script>
<script language="javascript" src="<%=request.getContextPath() %>/include/javascript/eventOnKeyPress.js"></script>
<script language="javascript" src="<%=request.getContextPath() %>/include/javascript/utrim.js"></script>
<script language="javascript" src="<%=request.getContextPath() %>/include/javascript/treeView12/MzTreeView12.js"></script>
<script language="javascript">
function saveForm(){
	if(trim(document.form.itemCode.value)==""){
	 	alert("<bean:message key="comm.jsp.common.text31" bundle="conf.comm.comm"/>");
	 	return false;
	}
	if(trim(document.form.itemName.value)==""){
	 	alert("<bean:message key="comm.jsp.common.text32" bundle="conf.comm.comm"/>");
	 	return false;
	}
	if(parseInt(trim(document.form.levelFlag.value),10)>=10){
	   alert("<bean:message key="comm.jsp.different.text22" bundle="conf.comm.comm"/>!")
	   return false;
	}
	
	document.form.verbId.value = "add";
	document.charset='utf-8';
	if(confirm("确定要保存吗？")){
	document.form.submit();
	}
	
}

function sendval(a,b,c){
   document.form.parentId.value = a;
   document.form.parentName.value = b;
   document.form.levelFlag.value = c;
   document.form.itemCode.value = a;
}
function isNUM(val) {
    var rexPatn = /^[0-9]{0,11}?$/;
    var res = rexPatn.exec(val); 
    return res;
}
function goback(url){
	location.href=url;
}
</script>


<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
</head>
<body onload="showCommMessage('','<%=data.getMessage() %>','1')" >
<form name="form" method="post" action="<%=request.getContextPath()%>/comm/commConfigGbDrugclass.do" >
<input type="hidden" name="verbId" value="add" />
<input type="hidden" name="levelFlag" value="1" />
 <table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill">
    <tr >
         <td height="30px" class="tblTitle" colspan="4"><span>※</span><bean:message key="comm.jsp.common.text42" bundle="conf.comm.comm"/> <span>※</span>
	</td>
    </tr>
 </table>
 <table border="0" align="center" style="font-size: 14px" cellspacing="0" cellpadding="0" class="table2" width="600" style="border-right:1px solid #BFBFBF;border-left:1px solid #BFBFBF;border-bottom:1px solid #BFBFBF;" >   
   <tr>
     <td  class="tblLable">
     <table  border="0" cellspacing="1" cellpadding="0" class="table2" >
								<tr style="background-color:#ffffff;">
									<td align="left" width="40%" nowrap>
									</td>
									<td align="left" width="60%" id="shuxing" nowrap>

										<SCRIPT LANGUAGE="JavaScript">

	var tree = new MzTreeView("tree");
	tree.setIconPath("include/javascript/treeView12/images/");
	with(tree){
		N["0_999999"] = "T:;C:";
     <%
			if (ls.getItemCodeList() != null
					&& ls.getItemCodeList().length > 0) {

				%>
							
<%	
	//System.out.println("len=" + securityRoleVsMenus.getMenuId().length);
	for(int i = 0; i < ls.getItemCodeList().length; i++){
		String itemCode = ls.getItemCodeList()[i];
		String itemName = ls.getItemNameList()[i];
		String parentId = ls.getParentIdList()[i];
		if(parentId == ""){
		  parentId = "999999";
		}
		String levelFlag = ls.getLevelFlagList()[i];
		long templevel = Long.parseLong(levelFlag);
		long level = templevel+1;
		//String seqNo = ls.getSeqNoList()[i];	
		String temp =  "T:" + itemCode+ " " +itemName + ";"+"C:sendval('"+itemCode+"','"+itemName+"','"+level+"');";
	    
%>   	
		N["<%=parentId %>_<%=itemCode %>"]="<%=temp %>";		
<%	        
		        
	}
	}
%>
	}
	
	document.write(tree.toString()); 


</SCRIPT>
	</td>
 </tr>
</table>
							
  </td>

<td  class="tblLable">
     <table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill">  
    <tr>
    
	<td  class="tblLable" nowrap >
         <bean:message key="comm.jsp.different.text12" bundle="conf.comm.comm"/>： 
        </td>
        <td class="hou" nowrap >
            <input type="text" style="height:20px"  name="parentId" size="20" maxlength="4" value="<%=data.getParentId() %>" readonly />
        </td>
     </tr>
     <tr>   
     	<td  class="tblLable" nowrap >
         <bean:message key="comm.jsp.different.text13" bundle="conf.comm.comm"/>： 
        </td>
        <td class="hou" nowrap >
          
          <input type="text" style="height:20px"  name="parentName" size="50" maxlength="50" value="<bean:message key="comm.jsp.different.text59" bundle="conf.comm.comm"/>" readonly/>
           
            

        </td>
    </tr>         
    <tr>
    
	<td  class="tblLable" nowrap >
         <span>*</span> <bean:message key="comm.jsp.common.text10" bundle="conf.comm.comm"/>： 
        </td>
        <td class="hou" nowrap >
            <input type="text" style="height:20px" name="itemCode" size="20" maxlength="10"
            	onkeypress="eventOnKeyPress('itemName')"
              value="<%=data.getItemCode() %>" />
        </td>
     </tr>
     <tr>   
     	<td  class="tblLable" nowrap >
         <span>*</span><bean:message key="comm.jsp.common.text29" bundle="conf.comm.comm"/>： 
        </td>
        <td class="hou" nowrap >
            <input type="text" style="height:20px" name="itemName" size="50" maxlength="50"
              onkeypress="eventOnKeyPress('seqNo')"
              value="<%=data.getItemName() %>" />
        </td>
    </tr>
    <tr>
    	<td  class="tblLable" nowrap >
          <bean:message key="comm.jsp.common.text48" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" nowrap >
           <input type="text" style="height:20px" name="seqNo" id="seqNo" size="30" maxlength="11"
         	onkeypress="eventOnKeyPress('comments')"
              value="<%=data.getSeqNo() %>" /> 
        </td>
     </tr>
     <tr>
        <td  class="tblLable" nowrap >
            <bean:message key="comm.jsp.common.text4" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" nowrap >
            <input type="text" style="height:20px"  name="comments" id="comments" size="30" maxlength="20"
         	onkeypress="eventOnKeyPress('btnSaveForm')"
              value="<%=data.getComments() %>" />
        </td>
    </tr>
    <tr>
          
   
    </tr>
  </table>
 </td>
 
  </tr>
  </table>
 <!-- Sheet operation button area -->
       <div class="btnSave">
          <input type="button" style="cursor:hand; background:url(include/images/tong.gif) no-repeat; border:0; width:50px; height:23px; font-size:12px;" name="btnSaveForm" value="<bean:message key="comm.jsp.common.text3" bundle="conf.comm.comm"/>" onClick="saveForm()" />
          <input type="button" style="cursor:hand; background:url(include/images/tong.gif) no-repeat; border:0; width:50px; height:23px; font-size:12px;" name="btnBack" value="<bean:message key="comm.jsp.common.text19" bundle="conf.comm.comm"/>" onClick="goback('<%=request.getContextPath()%>/comm/commConfigGbDrugclass.do?verbId=query')" />
           
        </div>

</form>
</body>
</html>
