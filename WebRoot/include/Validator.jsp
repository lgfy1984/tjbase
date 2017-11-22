<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
 
 <title>表单验证类 Validator v1.05</title>
 <script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
 <style>
	 body,td{font:normal 12px Verdana;color:#333333}
	 input,textarea,select,td{font:normal 12px Verdana;color:#333333;border:1px solid #999999;background:#ffffff}
	 table{border-collapse:collapse;}
	 td{padding:3px}
	 input{height:20;}
	 textarea{width:80%;height:50px;overflow:auto;}
	 form{display:inline}
 </style>
 <script language="javascript">
 	function saveForm(){
		if(!Validator.Validate(document.forms.form,3)){
	      return ;
	   	}
   	}
 	
 </script>
 </head>
 <body>
 	<form name="form" id="demo" method="post" >
 	<table align="center">
	  <tr>
		   <td>身份证号：</td>
		   <td><input name="Card" dataType="IdCard" msg="身份证号错误"></td>
	  </tr>
	  <tr>
		   <td>真实姓名：</td>
		   <td><input name="Name" dataType="Chinese" msg="真实姓名只允许中文"></td>
	  </tr>
	  <tr>
		   <td>ID：</td><td>
		   <input name="username" dataType="Username" msg="ID名不符合规定"></td>
	  </tr>
	  <tr>
		   <td>英文名：</td>
		   <td><input name="Nick" dataType="English" require="false" msg="英文名只允许英文字母"></td>
	  </tr>
	  <tr>
		   <td>主页：</td>
		   <td><input name="Homepage" require="false" dataType="Url"   msg="非法的Url"></td>
	  </tr>
	  <tr>
		   <td>密码：</td>
		   <td><input name="Password" dataType="SafeString"   msg="密码不符合安全规则" type="password"></td>
	  </tr>
	  <tr>
		   <td>重复：</td>
		   <td><input name="Repeat" dataType="Repeat" to="Password" msg="两次输入的密码不一致" type="password"></td>
	  </tr>
	  <tr>
		   <td>信箱：</td>
		   <td><input name="Email" dataType="Email" msg="信箱格式不正确"></td>
	  </tr>
	  <tr>
		   <td>信箱：</td>
		   <td><input name="Email" dataType="Repeat" to="Email" msg="两次输入的信箱不一致"></td>
	  </tr>
	  <tr>
		   <td>QQ：</td>
		   <td><input name="QQ" require="false" dataType="QQ" msg="QQ号码不存在"></td>
	  </tr>
	  <tr>
		   <td>身份证：</td>
		   <td><input name="Card" dataType="IdCard" msg="身份证号码不正确"></td>
	  </tr>
	  <tr>
		   <td>年龄：</td>
		   <td><input name="Year" dataType="Range" msg="年龄必须在18~28之间" min="18" max="28"></td>
	  </tr>
	  <tr>
		   <td>年龄1：</td>
		   <td><input name="Year1" require="false" dataType="Compare" msg="年龄必须在18以上" to="18" operator="GreaterThanEqual"></td>
	  </tr>
	  <tr>
		   <td>电话：</td>
		   <td><input name="Phone" require="false" dataType="Phone" msg="电话号码不正确"></td>
	  </tr>
	  <tr>
	  	 <td>手机：</td>
	   	 <td><input name="Mobile" require="false" dataType="Mobile" msg="手机号码不正确"></td>
	  </tr>
	  <tr>
	  	 <td>生日：</td>
	   	 <td><input name="Birthday" dataType="Date" format="ymd" msg="生日日期不存在"></td>
	  </tr>
	   <tr>
	  	 <td>邮政编码：</td>
	   	<td><input name="Zip" dataType="Custom" regexp="^[1-9]\d{5}$" msg="邮政编码不存在"></td>
	  </tr>
	  <tr>
	  	 <td>邮政编码：</td>
	   	<td><input name="Zip1" dataType="Zip" msg="邮政编码不存在"></td>
	  </tr>
	  <tr>
	  	 <td>操作系统：</td>
	  	 <td><select name="Operation" dataType="Require"  msg="未选择所用操作系统" ><option value="">选择您所用的操作系统</option><option value="Win98">Win98</option><option value="Win2k">Win2k</option><option value="WinXP">WinXP</option></select></td>
	  </tr>
	  <tr>
	  	 <td>所在省份：</td>
	  	 <td>广东<input name="Province" value="1" type="radio">陕西<input name="Province" value="2" type="radio">浙江<input name="Province" value="3" type="radio">江西<input name="Province" value="4" type="radio" dataType="Group"  msg="必须选定一个省份" ></td>
	  </tr>
	  <tr>
	  	 <td>爱好：</td>
	  	 <td>运动<input name="Favorite" value="1" type="checkbox">上网<input name="Favorite" value="2" type="checkbox">听音乐<input name="Favorite" value="3" type="checkbox">看书<input name="Favorite" value="4" type="checkbox"" dataType="Group" min="2" max="3"  msg="必须选择2~3种爱好"></td>
	  </tr>
	  <tr>
	   	 <td>自我介绍：</td>
	  	 <td><textarea name="Description" dataType="Limit" max="10"  msg="自我介绍内容必须在10个字之内">中文是一个字</textarea></td>
	  </tr>
	  <tr>
	     <td>自传：</td>
	     <td><textarea name="History" dataType="LimitB" min="3" max="10"  msg="自传内容必须在[3,10]个字节之内">中文是两个字节t</textarea></td>
	  </tr>
	  <tr>
		   <td>相片上传：</td>
		   <td><input name="up" dataType="Filter" msg="非法的文件格式" type="file" accept="jpg, gif, png"></td>
	  </tr>
	  <tr>
		   <td colspan="2">
			   <input type="button" name="btnSave" id="btnSave" value="提交" onClick="saveForm()" />
		   </td>
	  </tr>
 </table>
 </form>
 </body>
 </html>