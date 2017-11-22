<%@ page language="java" pageEncoding="UTF-8" import="com.tianjian.comm.bean.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request"
	class="com.tianjian.hsp.struts.form.HspConfigBaseinfoForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<script language="javascript" src="<%=request.getContextPath()%>/include/javascript/eventOnKeyPress.js"></script>
	<script language="javascript" src="<%=request.getContextPath()%>/<bean:message  key="comm.js.includeTJMessage.path"  bundle="comm.commLocale"/>"></script>
	<script language="javascript">
		function saveForm(){
			if (confirmMessage("0-000008")){ 
		       document.form.verbId.value = "addLogOut";
		       document.form.submit();
		    }
		}
	</script>
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="<%=request.getContextPath()%>/include/css/form.css" />
  </head>
  <body onload="showCommMessage('','${message }','1'),load()">
  	 <form name="form" action="<%=request.getContextPath()%>/hsp/hspLogoutRecord.do" method="post"> 
  	 	<input type="hidden" id="verbId" name="verbId" value="addLogOut" />
  	 	<input type="hidden" id="idHidden" name="idHidden" value="<%=data.getId()%>" />
  		 <table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
  				 <tr>
					<td class="tblTitle" colspan="6"><span>※</span> 
						卫生机构注销
					 <span>※</span></td>
				</tr>
	
	<tr>
		<td class="tblLable">
			上级医疗机构：
		</td>
		<td class="tdRight" colspan="3">
			<%=data.getParentItemName()%>
		</td>	
		<td class="tblLable"></td>
  		<td class="tdRight">
  		</td>
  	</tr>
	<tr>
		<td class="tblLable" >
			<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemCode"/>：
		</td>
		<td class="tdRight">
			<%=data.getItemCode()%>
		</td>	
		<td class="tblLable">
			<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemName"/>：
		</td>
		<td class="tdRight" >
			<%=data.getItemName()%>
		</td>
		<td class="tblLable">
		</td>
		<td class="tdRight">
		</td>
	</tr>
	<tr> 
		<td class="tblLable">
			<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemSort"/>：
		</td>
		<td class="tdRight">
			<%=data.getCchtName()%>
		</td>
		<td class="tblLable">
			机构分类：
		</td>
		<td class="tdRight">
			<%=data.getCommConfigFtManageName()%>
		</td> 
		
		<td class="tblLable" width="108">
			机构等级：
		</td>
		<td class="tdRight" width="158">
			
						<%
										if (data.getC010501aList().size() > 0
												&& data.getC010501aList() != null) {
											for (int i = 0; i < data.getC010501aList().size(); i++) {
												String veryId = "";
												if (data.getCommConfigUnittypeId() != null) {
													veryId = data.getCommConfigUnittypeId();
												}
												String tempId = ((CommConfigUnittype) data
														.getC010501aList().get(i)).getSeqNo()
														+ "";
												String tempName = ((CommConfigUnittype) data
														.getC010501aList().get(i)).getItemName();
												if (veryId.equalsIgnoreCase(tempId)) {
									%>
						  <%=((CommConfigUnittype) data.getC010501aList()
										.get(i)).getItemName()%>
						<%
							}
						%>
					<%
						}

						}
					%>  
						
						<%
  													if (data.getC010501bList().size() > 0
  															&& data.getC010501bList() != null) {
  														for (int i = 0; i < data.getC010501bList().size(); i++) {
  															String veryId = "";
  															if (data.getCommConfigUnitgradeId() != null) {
  																veryId = data.getCommConfigUnitgradeId();
  															}
  															String tempId = ((CommConfigUnitgrade) data
  																	.getC010501bList().get(i)).getSeqNo()
  																	+ "";
  															String tempName = ((CommConfigUnitgrade) data
  																	.getC010501bList().get(i)).getItemName();
  															if (veryId.equalsIgnoreCase(tempId)) {
  												%>
						 <%=((CommConfigUnitgrade) data.getC010501bList()
										.get(i)).getItemName()%>
						<%
							}
						%> 
						<%
 							}
 							}
 						%> 
			
			
			
		</td>
		</tr>
	<tr>
		<td class="tblLable" width="106">
			经济性质：
		</td>
		<td class="tdRight" width="160">
			<%=data.getCommConfigEconkindName()%>
		</td>
		<td class="tblLable" width="106">
			省（市）：
		</td>
		<td class="tdRight"width="160">
			<%=data.getCommConfigLocationName1()%>
		</td>	 
		
		<td class="tblLable">
			市（地区）：
		</td>
		<td class="tdRight">
			<%=data.getCommConfigLocationName2()%>
		</td>
		</tr>
    <tr>
		<td class="tblLable">
			县（区）：
		</td>
		<td class="tdRight">
			<%=data.getCommConfigLocationName3()%>
		</td>	
		<td class="tblLable">
			乡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;镇 ：
		</td>
		<td class="tdRight">
			<%=data.getCcltName()%>
		</td>	
		<td class="tblLable">
			村 ：
		</td>
		<td class="tdRight">
			<%=data.getCommClvName()%>
		</td>
	</tr>
	<tr>
		<td class="tblLable">
            <bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfodetail.detailAddress"/>：
        </td>
        <td  class="tdRight"  colspan="5">
            <%=data.getAddress()%>
        </td>  
        
    </tr>


	
		<tr>
	        <td class="tblLable">
           		 <bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfodetail.contactPerson"/>：
        	</td>
       	 	<td class="tdRight" >
           		 <%=data.getContactPersonName()%>
       		 </td>
          <td class="tblLable">
            <bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfodetail.contactPhone"/>：
        </td>
        <td class="tdRight">
            <%=data.getPhone()%>
        </td>
        <td class="tblLable">
            <bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.zipCode"/>：
        </td>
        <td class="tdRight">
            <%=data.getZipcode()%>
        </td>
    </tr>
	   <tr>
		 <td class="tblLable">
            <bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.email"/>：
        </td>
        <td class="tdRight">
            <%=data.getEMail()%>
        </td>
         <td class="tblLable">
            <bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.domainName"/>：
        </td>
        <td class="tdRight">
            <%=data.getDomainName()%>
        </td>
		 <td class="tblLable">
            设置主办单位：
        </td>
        <td class="tdRight">
			<%
				if (data.getCommConfigSetTypeId() != null
						&& data.getCommConfigSetTypeId().length > 0) {
					for (int i = 0; i < data.getCommConfigSetTypeId().length; i++) {
			%>
				<%=data.getCommConfigSetTypeId()[i].equals(data
									.getCommConfigSetTypes()) ? data
							.getCommConfigSetType()[i] : ""%>
			<%
				}
				}
			%>
        </td>
	</tr>
	
	<tr>
          <td class="tblLable">
            政府办卫生机构隶属关系：
        </td>
        <td class="tdRight">
          <%
          	if (data.getCommConfigCovaffrsId() != null
          			&& data.getCommConfigCovaffrsId().length > 0) {
          		for (int i = 0; i < data.getCommConfigCovaffrsId().length; i++) {
          %>
				<%=data.getCommConfigCovaffrsId()[i].equals(data
									.getCommConfigCovaffrss()) ? data
							.getCommConfigCovaffrs()[i] : ""%>
		  <%
		  	}
		  	}
		  %>
        </td>
        <td class="tblLable">
           第一级行政管理机构（省）：
        </td>
        <td class="tdRight">
        <%=data.getHspConfigBaseInfoId1Name() %>
					</td>
		 <td class="tblLable">
          第二级行政管理机构（市）：
        </td>
        <td class="tdRight">
        <%=data.getHspConfigBaseInfoId2Name() %>
        </td>
	</tr>
	<tr>
          <td class="tblLable">
            第三级行政管理机构（县）：
        </td>
        <td class="tdRight">
        <%=data.getHspConfigBaseInfoId3Name() %>
        </td>
        <td class="tblLable">
          第四级行政管理机构（乡镇）：
        </td>
        <td class="tdRight">
        <%=data.getHspConfigBaseInfoId4Name() %>
					</td>
		<td class="tblLable">
          第五级行政管理机构（村）：
        </td>
        <td class="tdRight">
        <%=data.getHspConfigBaseInfoId5Name() %>
					</td>
	</tr>
	
	<tr>
	   <td class="tblLable">
            <bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.seqNo"/>：
		</td>				
		<td class="tdRight">
            <%=data.getSeqNo()%>
         </td>
					
					<td class="tdLeft_L">
						编制床位数：
					</td>
					
					<td class="tdRight">
                          <%=data.getAuthorizedBedNum()%>
                     </td>
					
					<td>
					   实有床位数：
					</td>
					<td>
					    <%=data.getOutspreadBedNum()%>
					</td>
	</tr>	
	<tr>
		<td class="tblLable">
			<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfodetail.comments"/>：
		</td>
		<td colspan="5" >
			 <%=data.getComments()%>
		</td>
	<tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.createUserName"/>：
					</td>
					<td class="hou">
						${data.createUserName}
					</td>
					<td class="tblLable" >
					    <bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.createDate"/>：
					</td>
					<td class="hou"  colspan="3">
					    ${data.createDate}
					</td>
				</tr>
				<tr>
        <td class="tblLable"   >
            <bean:message  bundle="comm.comIHE" key="comm.jsp.segment9"/>：
        </td>
        <td class="hou"  >
            <input type="hidden" name="logoutTime" value="<%=data.getLogoutTime() %>" />
            <%=data.getLogoutTime() %>
        </td>
        <td class="tblLable"   >
           <bean:message  bundle="comm.comIHE" key="comm.jsp.segment14"/>：
        </td>
        <td  colspan="3">
			<input type="text" class="kuandu" name="logoutReason" id="logoutReason" size="50" maxlength="50" value="<%=data.getLogoutReason()%>" onkeypress="eventOnKeyPress('btnSave')"/>
		</td>   
    </tr>
    <tr>
        <td class="tblLable"   >
            <bean:message  bundle="comm.comIHE" key="comm.jsp.segment15"/>：
        </td>
        <td class="hou"  >
             <input type="hidden" name="createUserId1" value="<%=data.getCreateUserId1() %>" />
            <%=data.getCreateUserId1() %>
        </td>
        <td class="tblLable"   >
           <bean:message  bundle="comm.comIHE" key="comm.jsp.segment16"/>：
        </td>
        <td class="hou"  colspan="3">
            <input type="hidden" name="createUserName1" value="<%=data.getCreateUserName1() %>" />
        	<%=data.getCreateUserName1() %>
        </td>        
    </tr>
  		</table>
  			<!-- Sheet operation button area -->
  			<div class="btnSave" id="budiv">
	        	<input type="button" name="btnSaveForm" id="btnSave" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoupdate.save1"/>" onclick="saveForm()" />
	        	<input type="button" name="btnBack" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoupdate.back"/>" onclick="history.go(-1)" />
	     	</div>   
  	</form>
  </body>
</html>
