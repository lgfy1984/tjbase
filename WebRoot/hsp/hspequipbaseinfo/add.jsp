<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.tianjian.comm.bean.CommEquipUnit"%>
<%@page import="com.tianjian.comm.bean.CommEquipState"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.tianjian.comm.bean.CommEquipUseage"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.hsp.struts.form.HspEquipBaseinfoForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%
		boolean useForTree = "1".equals(request.getParameter("useForTree"));
	%>
		<%if(request.getServerPort() == 80) {%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%} else {%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%}%>
		<title>添加卫生机构设备</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<script language="javascript" src="<%=request.getContextPath() %>/comm/include/javascript/CommMessage.js"></script>
		<script language="javascript" src="<%=request.getContextPath() %>/include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>

	    <script src="/include/javascript/searchsuggest.js"></script>
	    <link rel="stylesheet" rev="stylesheet" href="include/css/form.css" />
	    <link rel="stylesheet" href="/include/css/searchsuggest.css" />
	    <script src="/include/javascript/searchsuggest.js"></script>
	    	<link rel="stylesheet"
			href="<%=request.getContextPath()%>/include/css/open.css" />
	  <script type="text/javascript"
			src="<%=request.getContextPath()%>/include/javascript/jianbian.js"
			defer="defer"></script>
		<script language="javascript"
			src="<%=request.getContextPath()%>/hsp/include/javascript/gettext_staff.js"
			defer="defer"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/My97DatePicker/WdatePicker.js"
			defer="defer"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/jquery-1.4.4.min.js"
			defer="defer"></script>
		
		<script>
			function saveForm(){
			      
				 if(!Validator.Validate(document.forms.form,3)){
      				return ;
   				 }
   			      var priceObj = document.getElementById("price");
			      var value= priceObj.value.indexOf('.')<0 ? priceObj.value+'.0' : priceObj.value;
		           priceObj.value =value;
		           var nums = value.split(".");
		           if(nums[0].length>10){
		               document.getElementById("priceMessage").innerHTML="<span style=\"color:red\">*整数部分不能大于10位</span>";
		               obj.select();
		               return false;
		           }
		           if(nums[1].length>2){
		               document.getElementById("priceMessage").innerHTML="<span style=\"color:red\">*小数部分不能长于2位</span>";
		               obj.select();
		               return false;
		           }
		           
		           if(document.getElementById("purchaseDate").value < document.getElementById("productDate").value){
		              alert("生产日期不得大于购入日期！");
		              return false;
		           }
		           
		            if(document.getElementById("instrumentUseStartDate").value < document.getElementById("purchaseDate").value){
		              alert("购入日期不得大于开始使用日期！");
		              return false;
		           }
		           
		            if(document.getElementById("instrumentUseEndDate").value!=""&& document.getElementById("instrumentUseEndDate").value < document.getElementById("instrumentUseStartDate").value){
		              alert("开始使用日期不得大于终止使用日期！");
		              return false;
		           }
   			    
				document.form.verbId.value = "add";
				if(confirm("确定要保存吗？")){
				<%
					if(useForTree){
				%>
				  var formData = $("#form").serialize();
				    $.ajax({
				    	dataType: "text/html",
				    	type:"POST",
				    	url:"<%=request.getContextPath()%>/hsp/hspEquipBaseinfo.do",
				    	processData:true,
				    	data:formData,
				    	error: function(a, b, c){
			        		alert(a + "-" + b + "-" + c);
			        	},
				    	success:function(data){
				    		if(data != null){
				    			try{
				    				var json = eval(data);
				    			}catch(e){
				    				alert("请重新登录！");
				    				return;
				    			}
				    			if(json[0].flag == '1'){
				      				var hspId = $("#orgId").val();
				      				var deptCode = $("#deptCode").val();
				      				if(hspId != null && hspId.length > 0){
					      				if(deptCode != null && deptCode.length > 0){
					      					parent.frames["treeFrame"].refreshByIdAndType("dept", hspId, null, deptCode);
					      				}else{
					      					parent.frames["treeFrame"].refreshByIdAndType("otherdept", hspId);
				      					}
				      				}
				      				alert(json[0].msg);
				    				location.reload(false);	
				    			}else{
				    				alert(json[0].msg);
				    			}
				    		}
				    	}
				    });
				<%
					}else{
				%>
				document.form.submit();
				<%
					}
				%>
				}
				
			}
			
			  function goBack(){
			       location.href="<%=request.getContextPath()%>/hsp/hspEquipBaseinfo.do?verbId=init";
			     }
				function huiche(){
						    if(event.keyCode==13){
								event.keyCode=9
						    }				
			}

function huiche(){
	if(event.keyCode==13){
		event.keyCode=9
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
		
		function getReadyStateHandler(req, responseXmlHandler,current) {
			return function () {
				if (req.readyState == 4) {
		      		if (req.status == 200) {
		      			//alert(req.responseText); 
		      			if(current==""){
					    	responseXmlHandler(req.responseXML);
					    }else{
					    	responseXmlHandler(req.responseXML,current);
					    }
					} else {
					    alert("HTTP error: " + req.status);
		      		}
		    	}
		  	}
		}
		
		
		
		function getBdept(url){
		   //取消异步操作
		   return;
			var current="";
			var hspCode = document.getElementById("hspCode").value;
			var xmlHttp = newXMLHttpRequest();
			var sendTo = url + "?verbId=getBdept&hspCode=" + hspCode;
			
			xmlHttp.open("GET", sendTo, true);
			var handlerFunction = getReadyStateHandler(xmlHttp, setBdept,current);
			xmlHttp.onreadystatechange = handlerFunction;
			xmlHttp.send(null);
		}

		function setBdept(dataXml) {
		    //setBdept2(dataXml);
			var bdept = document.getElementById("bdeptCode");
		    while (bdept.options.length) {
		        bdept.remove(0);
		    }
		    
		    var dept = document.getElementById("deptCode");
		    var deptElem = document.createElement("option");
		    deptElem.text = dept.options[0].text;
		    deptElem.value = dept.options[0].value;
		    while (dept.options.length) {
		        dept.remove(0);
		    }
		    dept.add(deptElem);
		    
			var indexObj = dataXml.getElementsByTagName("index")[0];
			var index = indexObj.childNodes[0].nodeValue;
			for (var i = 0; i < index; i++) {
				var keyObj = dataXml.getElementsByTagName("key")[i];
				var valueObj = dataXml.getElementsByTagName("value")[i];
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
				bdept.add(newElem);
			}
		}
		
		
		
		function getDept(url){
			//取消异步操作
			return;
			var current="";
			var hspCode = document.getElementById("hspCode").value;
			var bdeptCode = document.getElementById("bdeptCode").value;
			var xmlHttp = newXMLHttpRequest();
			var sendTo = url + "?verbId=getDept&hspCode="+hspCode+"&bdeptCode="+bdeptCode;
			xmlHttp.open("GET", sendTo, true);
			var handlerFunction = getReadyStateHandler(xmlHttp, setDept,current);
			xmlHttp.onreadystatechange = handlerFunction;
			xmlHttp.send(null);
		}

		function setDept(dataXml) {
		     //setDept2(dataXml);
			var dept = document.getElementById("deptCode");
		    while (dept.options.length) {
		        dept.remove(0);
		    }
			var indexObj = dataXml.getElementsByTagName("index")[0];
			var index = indexObj.childNodes[0].nodeValue;
			for (var i = 0; i < index; i++) {
				var keyObj = dataXml.getElementsByTagName("key")[i];
				var valueObj = dataXml.getElementsByTagName("value")[i];
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
				dept.add(newElem);
			}
		}
		
    function checkDate(id){
    var day = new Date();
    var year = day.getFullYear();
    var  month = day.getMonth()+1;
    if(month<10){
        month="0"+month;
    }
    var day = day.getDate();
    if(day<10){
        day = "0"+day;
    }
    var currTime= year+"-"+month+"-"+day; //获得系统日期的文本值
   var valiTime=document.getElementById(id).value; //获得用户选择的日期文本值
   var cStart=currTime.split('-'); //转成成数组，分别为年，月，日，下同
   var vEnd=valiTime.split('-');
   var currDate = cStart[0]+"/" + cStart[1]+ "/" + cStart[2];
   var valiDate = vEnd[0] + "/" + vEnd[1] + "/" + vEnd[2];
   if (currDate < valiDate) {
    alert("输入日期不得超过当前系统日期，请重新输入");
    document.getElementById(id).value="";
    return false;
   }
    return true;
  }	  
    function selectDeptByHspCode(hspCodeInput, deptNameInput, deptCodeInput){
		var hspCode = $("#"+hspCodeInput).val();
		if($.trim(hspCode) == ''){
			alert("请先选择机构！");
			return;
		}
		add("<%=request.getContextPath()%>/hsp/healthRegisterTree.do?verbId=getDeptByHspCode&hspCode="+hspCode, deptNameInput,deptCodeInput);
	}
    function clearDept(_hspCode){
    	if(_hspCode.value == ""){
    		document.form.deptCode.value = "";
    		document.form.deptName.value = "";
    	}
    }
		</script>
	</head>

	<body onload="showCommMessage('','<%=data.getMessage() %>','1')">
		<form name="form" id="form" method="post" action="<%=request.getContextPath()%>/hsp/hspEquipBaseinfo.do" enctype="multipart/form-data">
			<input type="hidden" name="verbId" value="add" />
			<input type="hidden" name="useForTree" value="<%=request.getParameter("useForTree")%>"/>
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill">
				<tr>
					<td height="30px" class="tblTitle" colspan="4">
						<span>※</span>&nbsp;&nbsp;添加卫生机构设备&nbsp;&nbsp;
						<span>※</span>
					</td>
				</tr>
				<tr>
					<td class="tblLable" style="width:20%">
						<span>*</span>医疗机构：
					</td>
					<td  style="width:30%">
						<span id="spanOutput" class="spanTextDropdown" style="display: none;"></span> 
                        <input type="text" id="hspName" name="hspName" value="${data.hspName}" 
	                        onkeyup="GiveOptions(event, '<%=request.getContextPath()%>/searchSuggest.do', 'getHspName_Code_ID', new Array('hspCode', 'orgId'))"
							onkeydown="huanhang(event)" dataType="Require" msg="请输入医疗机构"/>
						<input type="hidden" id="hspCode" name="hspCode" value="${data.hspCode}" 
							onpropertychange="clearDept(this)"
                        	dataType="Require" msg="请在下拉列表中选中医疗机构"/>
                        <input type="hidden" id="orgId" name="orgId" value="${data.orgId}"/>
					</td>
				
					<td class="tblLable"  style="width:20%">
						科室属性：
					</td>
					<td  style="width:30%">
                        <input type="text" id="bdeptName" name="bdeptName" value="${data.bdeptName}" readonly="readonly"/>
                        <input type="hidden" id="bdeptCode" name="bdeptCode" value="${data.bdeptCode}"/>
					</td>
					
				</tr>
				<tr>   
				    <td class="tblLable">
						科室：
					</td>
					<td style="padding-right:40px;">
                        <input type="text" id="deptName" name="deptName" value="${data.deptName}" readonly="readonly"/>
                        <input type="hidden" id="deptCode" name="deptCode" value="${data.deptCode}"/>
                        <img src="<%=request.getContextPath()%>/include/images/select.gif" style="cursor: pointer; position: absolute;"
								onclick="selectDeptByHspCode('hspCode', 'deptName', 'deptCode')" />
					</td>
					
				   <td class="tblLable" >
					<span>*</span>设备名称：
					</td>
					<td>
						<input name="equipName" id="equipName" dataType="Require" msg="请输入设备名称"
							 maxlength="9" type="text"
							 value="${data.equipName }"
							onkeydown="huiche();if(event.keyCode==32) return false" />
							
                       </td>
				   
                       
				</tr>
				<tr>
				         <td class="tblLable" >
						<span>*</span>设备型号：
						</td>
						<td >
                           <input onkeydown="huiche();if(event.keyCode==32) return false" 
                            require="true" dataType="LimitB" max="32" msg="设备型号长度不能大于32字节"
							name="equipType" id="equipType"
							maxlength="32" type="text" value="${data.equipType}"  />
						</td>
						<td class="tblLable" >
						   <span>*</span>出厂编号：
						</td>
						<td >
                           <input onkeydown="huiche();if(event.keyCode==32) return false"
                            require="true" dataType="LimitB" max="40" msg="出厂编号长度不能大于40字节"
							name="equipCode" id="equipCode"
							maxlength="50" type="text" value="${data.equipCode}"  />
						</td>
						
				</tr>	
				<tr>   
				        <td class="tblLable" >
						  计算单位：
						</td>
						<td >
                             <select  id="unit" name="unit" 
								onkeydown="huiche();if(event.keyCode==32) return false">
							<option value=""></option>
							<%
									if (data.getUnitList().size()>0) {
									for (int i = 0; i < data.getUnitList().size(); i++) {
										String selected = "";
										CommEquipUnit cgc = (CommEquipUnit)(data.getUnitList().get(i));
										if (cgc.getItemCode().equals(data.getUnit())) {
										selected = "selected";
										}
							%>
							<option value="<%=cgc.getItemCode()%>" <%=selected%>> <%=cgc.getItemName()%></option>
							<%
									
									}
								}
							%>
						</select>
						</td>
				   
                        <td class="tblLable" >
							 <span>*</span>供货单位：
						</td>
						<td >
                          <input onkeydown="huiche();if(event.keyCode==32) return false"
                        	  require="true" dataType="LimitB" max="60" msg="供货单位长度不能大于60字节"
											name="supplier" id="supplier"
										    maxlength="20" type="text" value="${data.supplier}"  />
						</td>
						
				</tr>
				<tr>
				        <td class="tblLable" >
						   	 <span>*</span>供货单位联系方式：
						</td>
						<td >
                            <input onkeydown="huiche();if(event.keyCode==32) return false"
                                require="true" dataType="Require" msg="请输入供货单位联系方式"
										 name="supplierTel" id="supplierTel"
										 maxlength="20" type="text" value="${data.supplierTel}"  />
						</td>
						 <td class="tblLable" >
							 <span>*</span>生产厂家：
						</td>
						<td >
                          <input onkeydown="huiche();if(event.keyCode==32) return false"
                        	  require="true" dataType="LimitB" max="60" min="1" msg="生产厂家长度应为1-60字节"
											name="productor" id="productor"
											maxlength="20" type="text" value="${data.productor}"  />
						</td>
					
				</tr>
					<tr>
					     	<td class="tblLable" >
						   	 <span>*</span>生产厂家联系方式：
						</td>
						<td >
                            <input onkeydown="huiche();if(event.keyCode==32) return false"
                            	 require="true" dataType="Require" msg="请输入生产厂家联系方式"
											name="productorTel" id="productorTel"
											 maxlength="20" type="text" value="${data.productorTel}"  />
						</td>
						 <td class="tblLable" >
							 <span>*</span>生产日期：
						</td>
						<td>
                              <input type="text" require="true"  dataType="Date" format="ymd" msg="请输入正确的生产日期" 
	                              onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"  
	                              name="productDate" onkeydown="huiche();if(event.keyCode==32) return false" readonly="readonly"  
	                              id="productDate"  value="${data.productDate}" onchange="checkDate('productDate')"/>
                              
						</td>
						
				</tr>
				<tr>   
				        <td class="tblLable" >
						   	 <span>*</span>购入日期：
						</td>
						<td>
                              <input type="text" onchange="checkDate('purchaseDate')" require="true"  dataType="Date" format="ymd" msg="请输入正确的购入日期"
                              	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"   
                               name="purchaseDate" onkeydown="huiche();if(event.keyCode==32) return false" readonly="readonly"  
                               id="purchaseDate"  value="${data.purchaseDate}"/>
						</td>
						 <td class="tblLable" >
						   	  <span>*</span>开始使用日期：
						</td>
						<td>
                              <input type="text"  require="true"  dataType="Date" format="ymd" msg="请输入正确的开始使用日期"      name="instrumentUseStartDate" onkeydown="huiche();if(event.keyCode==32) return false" readonly="readonly"  id="instrumentUseStartDate"  value="${data.instrumentUseStartDate}"
                              	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
						</td>
						
				</tr>
				<tr>   
				        <td class="tblLable" >
						   	    终止使用日期：
						</td>
						<td>
                              <input type="text" name="instrumentUseEndDate" onkeydown="huiche();if(event.keyCode==32) return false" readonly="readonly"  id="instrumentUseEndDate"  value="${data.instrumentUseEndDate}"
                              	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
						</td>
						 <td class="tblLable" >
							<span>*</span>购买单价（万元）：
						</td>
						<td >
                          <input onkeydown="huiche();if(event.keyCode==32) return false"
											 name="price" id="price" require="true" dataType="Double" msg="请输入正确的购买单价"
											 maxlength="10" type="text" value="${data.price}"  />
						   <span id="priceMessage"></span>
						</td>
						
				</tr>
				<tr>
				         <td class="tblLable" >
						   <span>*</span>	购买时新旧状态：
						</td>
						<td >
                            <select  id="purchaseState" name="purchaseState" dataType="Require" msg="请选择购买时新旧状态" 
								onkeydown="huiche();if(event.keyCode==32) return false">
							<option value=""></option>
							<%
									if (data.getStateList().size()>0) {
									for (int i = 0; i < data.getStateList().size(); i++) {
										String selected = "";
										CommEquipState cgc = (CommEquipState)(data.getStateList().get(i));
										if (cgc.getItemCode().equals(data.getPurchaseState())) {
										selected = "selected";
										}
							%>
							<option value="<%=cgc.getItemCode()%>" <%=selected%>> <%=cgc.getItemName()%></option>
							<%
									
									}
								}
							%>
						</select>
						</td>
						 <td class="tblLable" >
							<span>*</span>设计寿命（年）：
						</td>
						<td >
                          <input onkeydown="huiche();if(event.keyCode==32) return false" require="true" dataType="Integer" msg="请输入正确的年数" 
											name="designLifetime" id="designLifetime"
											maxlength="3" type="text" value="${data.designLifetime}"  />
						</td>
						
				</tr>
				<tr>      
				        <td class="tblLable" >
						   	<span>*</span>使用情况：
						</td>
						<td >
                           <select id="usage" name="usage" dataType="Require" msg="请选择使用情况" 
								onkeydown="huiche();if(event.keyCode==32) return false">
							<option value=""></option>
							<%
									if (data.getUseageList().size()>0) {
									for (int i = 0; i < data.getUseageList().size(); i++) {
										String selected = "";
										CommEquipUseage cgc = (CommEquipUseage)(data.getUseageList().get(i));
										if (cgc.getItemCode().equals(data.getUsage())) {
										selected = "selected";
										}
							%>
							<option value="<%=cgc.getItemCode()%>" <%=selected%>> <%=cgc.getItemName()%></option>
							<%
									
									}
								}
							%>
						</select>
						</td>
						 <td class="tblLable" >
							<span>*</span>产地（国别）：
						</td>
						<td style="padding-right:40px">
                          	<input type="text" id="displayInputId_3" 
								value="<%=data.getSourceName()%>"
                          		 dataType="Require" msg="请选择产地" 
								onKeyDown="huiche()" name="sourceName"
								readonly="readonly" />
							<input type="hidden" id="hiddenInputId_3"
								name="source"
								value="<%=data.getSource()%>" />
							<img src="<%=request.getContextPath()%>/include/images/select.gif" style="cursor: pointer; position: absolute;"
								onclick="add('<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?verbId=country','displayInputId_3','hiddenInputId_3','','source')" />
						</td>
				</tr>
				<tr>
						 <td class="tblLable" >
							<span>*</span>用途：
						</td>
						<td  colspan="3">
                          <textarea  name="application" rows="3" id="application" require="true" dataType="LimitB" max="200" min="1" msg="用途长度应在1-200字节"
                           onkeydown="if(event.keyCode==32) return false;huiche();" 
                          >${data.application}</textarea>
						</td>
				</tr>
				<tr>
						 <td class="tblLable" >
							备注：
						</td>
						<td  colspan="3">
                          <input onkeydown="huiche();if(event.keyCode==32) return false"
                          		dataType="LimitB" max="40" msg="备注长度应小于40字节"
								name="comments" id="comments"
								 maxlength="10" type="text" value="${data.comments}"  />
						</td>
						
				</tr>
			</table>
             <div class="btnSave">
			<!-- Sheet operation button area -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" align="center">
						<input type="button"
							name="btnSaveForm" value="保&nbsp&nbsp存" onclick="saveForm()" />
					<%
						if(!useForTree){
					%>
						<input type="button"
							name="btnBack" value="返&nbsp&nbsp回"
							onclick="goBack()" />
					<%
						}
					%>
					</td>
				</tr>
			</table>
			</div>
		</form>
	</body>
</html>