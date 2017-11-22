<%@ page contentType="text/html; charset=UTF-8" %>
<script language="javascript" src="<%=request.getContextPath()%>/ciha/include/javascript/gettext_staff.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ciha/include/javascript/jianbian.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/open.css" />
			<td class="tdRight_R" colspan="3">
				<!--以下是显示基本录入框的input-->
				<input type="text" class="input" id="displayInputId_1" name="11" style="width:100px;" value="" readonly="true"  onkeydown="huiche()" />
		 	   <!--这是准备存储到数据的字段-->
				<input type="hidden" id="hiddenInputId_1" value="" name="22" />
				<!--弹出的选择按钮，add(arg1,arg2,arg3)其中arg1代表基本录入框的id，arg2代表保存存储到数据库字段对应的input的id
				arg3代表需要传递到.do的数据库检索参数
				-->
				<!-- hspType 1为除去卫生服务站2为只包括服务站和服务中心3为所有 -->
				<img src="<%=request.getContextPath()%>/ciha/include/images/select.gif" style="cursor:pointer;" onclick="add('<%=request.getContextPath()%>/hsp/hspConfigBaseinfo.do?verbId=getHsp&hspType=2','displayInputId_1','hiddenInputId_1')" />
		 	</td>