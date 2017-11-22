<script language="javascript" src="include/javascript/jquery-1.4.2.min.js"></script>
<script language="javascript">
function changePageSize(){
	var pageId = document.getElementById("pageId").value;
	var pageSize = document.getElementById("pageSize").value;
	var reHref = document.getElementById("reHref").value;
	$.ajax({
		url:'SercurityConfigParameterServlet?method=changePageSize&pageId='+pageId+'&pageSize='+pageSize,
		type: 'GET',
		dataType: 'xml',
		timeout: 10000,
		cache:false ,
		success: function(xml){
			//location.href=reHref;
			var params = reHref.split("verbId=");
		  	document.form.method = "POST";
		  	document.form.verbId.value = params[1].replace(/[^\w\.\/]/ig,'');
		  	document.form.submit();
		}
	});
}
</script>
<bean:message  bundle="conf.comm.Comm" key="comm.jsp.common.page5"/>
	<select id="pageSize" name="pageSize" onchange="changePageSize();">
		<option value="10" <%=(pageSize==10)?"selected":"" %>>10</option>
		<option value="20" <%=(pageSize==20)?"selected":"" %>>20</option>
		<option value="50" <%=(pageSize==50)?"selected":"" %>>50</option>
		<option value="100" <%=(pageSize==100)?"selected":"" %>>100</option>
		<option value="<%=totalNum %>" <%=(pageSize==totalNum)?"selected":"" %>><bean:message  bundle="conf.comm.Comm" key="comm.jsp.common.page7"/></option>
	</select>
<bean:message  bundle="conf.comm.Comm" key="comm.jsp.common.page6"/>&nbsp;
