<%@ page contentType="text/html; charset=UTF-8" %> 
<%@page import="com.tianjian.comm.bean.CommConfigUnitgrade"%>
<%@page import="com.tianjian.comm.bean.CommConfigUnittype"%> 
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.hsp.struts.form.HspConfigBaseinfoForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	if (request.getServerPort() == 80) {
%>
<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
<%
	} else {
%>
<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<%
	}
%>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
</head>
<script language="javascript">
function printTure() 
	{
		var budivList = document.getElementsByName("budiv");
		for(var i=0; i<budivList.length; i++){
			budivList[i].style.display="none";
		}
		window.print();
		for(var i=0; i<budivList.length; i++){
			budivList[i].style.display="";
		}
	}
function goBack(url){
			window.location=url;
		}
</script>
<body>
<form name="form" method="post">
<input type="hidden" name="verbId" value="add"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tblView" align="center">
	<tr>
		<td class="tblTitle" colspan="4">
			<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfodetail.itemDetailInfo"/>
		</td>
	</tr>
	<tr>
		<td class="tblLable">
			上级医疗机构：
		</td>
		<td class="tdRight" colspan="3">
			<%=data.getParentItemName()%>
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
	</tr>
	<tr>
		<td class="tblLable">
			机构级：
		</td>
		<td class="tdRight">
			<%=data.getCommConfigUnitgradeName() %>
		</td>
		<td class="tblLable">
			机构等：
		</td>
		<td class="tdRight">
			<%=data.getCommConfigUnittypeName() %>
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
	</tr>
	<tr>	
		<td class="tblLable">
			市（地区）：
		</td>
		<td class="tdRight">
			<%=data.getCommConfigLocationName2()%>
		</td>
		<td class="tblLable">
			县（区）：
		</td>
		<td class="tdRight">
			<%=data.getCommConfigLocationName3()%>
		</td>
	</tr>
    <tr>	
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
        <td  class="tdRight" colspan="3">
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
	</tr>
	<tr>
        <td class="tblLable">
            <bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.zipCode"/>：
        </td>
        <td class="tdRight">
            <%=data.getZipcode()%>
        </td>
   
		 <td class="tblLable">
            <bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.email"/>：
        </td>
        <td class="tdRight">
            <%=data.getEMail()%>
        </td>
    </tr>
	<tr>
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
	</tr>
	<tr>
		 <td class="tblLable">
          第二级行政管理机构（市）：
        </td>
        <td class="tdRight">
        <%=data.getHspConfigBaseInfoId2Name() %>
        </td>
          <td class="tblLable">
            第三级行政管理机构（县）：
        </td>
        <td class="tdRight">
        <%=data.getHspConfigBaseInfoId3Name() %>
        </td>
      </tr>
	<tr>
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
					
		<td class="tblLable">
			编制床位数：
		</td>
		<td>
                       <%=data.getAuthorizedBedNum()%>
        </td>
	</tr>
	<tr>
		<td class="tblLable">
		   实有床位数：
		</td>
		<td  class="tdRight" colspan="3">
		    <%=data.getOutspreadBedNum()%>
		</td>
	</tr>	
	<tr>
		<td class="tblLable">
			<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfodetail.comments"/>：
		</td>
		<td colspan="3" >
			 <%=data.getComments()%>
		</td>
	<tr>
	
		
</table>
<div style="width:803px;padding-top:5px; padding-right:5px; line-height:30px; text-align:right;">
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.createUserName"/>：<font color="#0060BF"><%=data.getCreateUserName()%></font>&nbsp;&nbsp;&nbsp;&nbsp;
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.createDate"/>：<font color="#0060BF"><%=data.getCreateDateYear()%></font>	
			    <bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.year"/><font color="#0060BF"><%=data.getCreateDateMonth()%></font>
	            <bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.month"/><font color="#0060BF"><%=data.getCreateDateDay()%></font><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.day"/>
</div>
  <!-- Sheet operation button area -->
    <div  class="btnSave">
    	<%
			if(!"1".equals(request.getParameter("useForTree"))){
		%>
		<input type="button"  value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoupdate.back"/>" onclick="history.go(-1)" />
		<%
			}
		%>   
		<input type="button"  value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfodetail.print"/>" onclick="printTure();" />   	
    </div>
</form>
</body>
</html>

