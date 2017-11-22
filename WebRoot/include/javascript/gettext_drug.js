//定义一些全局变量
var XMLHttpReq;
//var completeDiv;
var inputField;
var completeTable;
var completeBody;
var currentLine = -1; 
var item_code;
var item_seqNo;
var item_name;
var item_form;
//创建XMLHttpRequest对象
function createXmlHttpRequest(){
	if(window.ActiveXObject){
		XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
	}else if(window.XMLHttpRequest){
		XMLHttpReq = new XMLHttpRequest(); 
	}
}
//字典库调用入口 方法
/****
调用ajax
*/
function findNames(url, displayInputId, hiddenInputId1, hiddenInputId2, hiddenInputId3){
	var e = window.event || arguments[0];
	switch(e.keyCode){ 
    	case 38: 
       		break; 
    	case 40:
        	break; 
    	default :    
    		currentLine = -1;
	    	item_name = displayInputId;
	    	item_code = hiddenInputId1;
	    	item_seqNo = hiddenInputId2;
	    	item_form = hiddenInputId3;
	    	
	    	var inputTypeFlag = document.getElementById("inputTypeId").options[document.getElementById("inputTypeId").selectedIndex].value;
	    	
	   		inputField = document.getElementById("input_value");            
	   		completeTable = document.getElementById("guoji_table");
	   		completeBody = document.getElementById("guoji_body");
	   		
      		if(completeBody.rows.length>0){	
				for(var i=0;i<completeBody.rows.length;i++){
					completeBody.deleteRow(i);
					i--;
				}
			}      
          	createXmlHttpRequest();    
          	XMLHttpReq.onreadystatechange = function(){processMatchResponse(displayInputId, hiddenInputId1, hiddenInputId2, hiddenInputId3)};//指定对象状态判断的方法
          	
			var para = "inputCode="+inputField.value+"&flag="+inputTypeFlag;

          	XMLHttpReq.open("Post", url, true);
          	XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			XMLHttpReq.send(para);    
     }
}  
//对象状态判断的方法
function processMatchResponse(displayInputId, hiddenInputId1, hiddenInputId2, hiddenInputId3){	
	if(XMLHttpReq.readyState==4){//readyState为正常返回的时候
		if(XMLHttpReq.status == 200){//成功响应 	
			//alert(XMLHttpReq.responseText);		
			setNames(XMLHttpReq.responseXML, displayInputId, hiddenInputId1, hiddenInputId2, hiddenInputId3);                              
        }else{//当为失败的时候
            window.alert("检索数据失败!");
        }
    }    
}   
//解析从action中返回的xml文件
function setNames(names, displayInputId, hiddenInputId1, hiddenInputId2, hiddenInputId3){
	var dataElements = names.getElementsByTagName("data");//获得返回的xml的节点
	var row,cell,txtNode,classCode,className,drugSeqNo,inputCode,drugForm;//定义多个变量
	for(var i=0; i<dataElements.length; i++){
		if(dataElements[i].getElementsByTagName("class_code")[0].firstChild==null){
			classCode = "";
		}else{
			classCode = dataElements[i].getElementsByTagName("class_code")[0].firstChild.data;
		}
		if(dataElements[i].getElementsByTagName("class_name")[0].firstChild==null){
			className = "";
		}else{
			className = dataElements[i].getElementsByTagName("class_name")[0].firstChild.data;
		}		
		if(dataElements[i].getElementsByTagName("drug_seq_no")[0].firstChild==null){
			drugSeqNo = "";
		}else{
			drugSeqNo = dataElements[i].getElementsByTagName("drug_seq_no")[0].firstChild.data;
		}		
		if(dataElements[i].getElementsByTagName("drug_name")[0].firstChild==null){
			drugName = "";
		}else{
			drugName = dataElements[i].getElementsByTagName("drug_name")[0].firstChild.data;
		}		
		if(dataElements[i].getElementsByTagName("input_code")[0].firstChild==null){
			inputCode = "";
		}else{
			inputCode = dataElements[i].getElementsByTagName("input_code")[0].firstChild.data;
		}		
		if(dataElements[i].getElementsByTagName("drug_form")[0].firstChild==null){
			drugForm = "";
		}else{
			drugForm = dataElements[i].getElementsByTagName("drug_form")[0].firstChild.data;
		}
     	row = document.createElement("tr");
     	row.onmouseover = function(){
			var rowNumber = this.rowIndex-1;				
			for(i=0; i<completeBody.rows.length; i++){			
				if(i!=currentLine){
					if(i == rowNumber){									
						completeBody.rows[i].className = "trColor_over";			
					}else{						
						if (completeBody.rows[i].rowIndex % 2 == 1) {
							completeBody.rows[i].className = "trColor_white";
						} else {
							completeBody.rows[i].className = "trColor_defale";
						}																							
					}
				}		
			}				
		}
		row.onclick = function(){
			var rowNumber = this.rowIndex-1;
			currentLine = rowNumber;
			for(i=0; i<completeBody.rows.length; i++){			
				if(i==rowNumber){									
					completeBody.rows[i].className = "trColor";			
				}else{
					if(completeBody.rows[i].rowIndex%2 == 1){
						completeBody.rows[i].className = "trColor_white";
					}else{
						completeBody.rows[i].className = "trColor_defale";
					}											
				}		
			}
			document.getElementById("input_value").value = this.getElementsByTagName('td')[3].innerHTML;
			document.getElementById("hidden_value_1").value = this.getElementsByTagName('td')[0].innerHTML;
			document.getElementById("hidden_value_2").value = this.getElementsByTagName('td')[4].innerHTML;
			document.getElementById("hidden_value_3").value = this.getElementsByTagName('td')[5].innerHTML;
		}
		row.ondblclick = function(){	
			document.getElementById(displayInputId).value = this.getElementsByTagName('td')[3].innerHTML;
			document.getElementById(hiddenInputId1).value = this.getElementsByTagName('td')[0].innerHTML;	
			document.getElementById(hiddenInputId2).value = this.getElementsByTagName('td')[4].innerHTML;	
			document.getElementById(hiddenInputId3).value = this.getElementsByTagName('td')[5].innerHTML;		
			closed();
		}
		
		//处理药物类别代码
        cell = document.createElement("td");
	    txtNode = document.createTextNode(classCode);
	    cell.appendChild(txtNode);
	    cell.style.display = "none";
	    row.appendChild(cell);
     	//处理药物类别名称
        cell = document.createElement("td");
	    txtNode = document.createTextNode(className);
	    cell.appendChild(txtNode);
	    row.appendChild(cell);
		//处理药物拼音码
        cell = document.createElement("td");
	    txtNode = document.createTextNode(inputCode);
	    cell.appendChild(txtNode);
	    row.appendChild(cell);
		//处理药物汉字
        cell = document.createElement("td");
	    txtNode = document.createTextNode(drugName);
	    cell.appendChild(txtNode);
	    row.appendChild(cell);
	    //处理药物序号
        cell = document.createElement("td");
	    txtNode = document.createTextNode(drugSeqNo);
	    cell.appendChild(txtNode);
	    cell.style.display = "none";
	    row.appendChild(cell);
		//处理药物剂型
        cell = document.createElement("td");
	    txtNode = document.createTextNode(drugForm);
	    cell.appendChild(txtNode);
	    row.appendChild(cell);
	    completeBody.appendChild(row);
	   	trColor();
	}
}
function trColor(){
	var TbRow = document.getElementById("guoji_body");
	if(TbRow!=null){
		for(var i=0; i<TbRow.rows.length; i++){
			if(TbRow.rows[i].rowIndex%2 == 1){
				TbRow.rows[i].className = "trColor_white";
			}else{
				TbRow.rows[i].className = "trColor_defale";
			}
		}
	}
}
/***
关闭div显示部分
*/
function closed(){			
	document.getElementById("body_div").parentNode.removeChild(document.getElementById("body_div"));   
	document.getElementById("bg_div").parentNode.removeChild(document.getElementById("bg_div"));
	//将剂型默认
	var vDrugForm = document.getElementById("drugFormHidden").value;
	var oOptions = document.getElementById("drugForm").options;
	if(oOptions!=null && oOptions.length>0){
		for(var i=0; i<oOptions.length; i++){
			if(oOptions[i].value==vDrugForm){		
				oOptions[i].selected=true;
				return;
			}
		}
	}
	//如果在已有的option选项中没有目标选项则新增一个目标选项
	var oOption = document.createElement("OPTION");
	oOptions.add(oOption);
	oOption.innerText = vDrugForm;
	oOption.value = vDrugForm;
}
/**
选中处理
*/
function xuanz(displayInputId, hiddenInputId1, hiddenInputId2, hiddenInputId3){
	if(document.getElementById("input_value").value != "" && currentLine != -1){
		document.getElementById(displayInputId).value = document.getElementById("input_value").value;
		document.getElementById(hiddenInputId1).value = document.getElementById("hidden_value_1").value;
		document.getElementById(hiddenInputId2).value = document.getElementById("hidden_value_2").value;
		document.getElementById(hiddenInputId3).value = document.getElementById("hidden_value_3").value;
	}
	closed();
}


document.onkeydown = function(e){
    e = window.event || e; 
    switch(e.keyCode){ 
        case 38://--上箭头
            currentLine--; 
            changeItem();            
            break; 
        case 40://--下箭头
            currentLine++; 
            changeItem(); 
            break; 
        case 13:
        	if(event.srcElement.tagName == "TD"){
        		if(currentLine == -1){
					return;
				}		 
		        document.getElementById(item_code).value = completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;				
				document.getElementById(item_name).value = completeBody.rows[currentLine].getElementsByTagName("td")[3].innerHTML;				
				document.getElementById(item_seqNo).value = completeBody.rows[currentLine].getElementsByTagName("td")[4].innerHTML;				
				document.getElementById(item_form).value = completeBody.rows[currentLine].getElementsByTagName("td")[5].innerHTML;								
	        	currentLine = -1;
	        	closed();
        	}        	
        default : 
            break; 
    } 
} 
    
//当按上下键的时候　调用的方法
function changeItem(){   
    var it = completeBody; 	    
    if(currentLine<0){
        currentLine = it.rows.length - 1; 
    }
    if(currentLine == it.rows.length){ 
        currentLine = 0; 
    }
    for(i=0;i<it.rows.length;i++){ 
        if(i == currentLine){
			it.rows[currentLine].className = "trColor"; 
		}else{
			if(it.rows[i].rowIndex%2 == 1){
				it.rows[i].className = "trColor_white";
			}else{
				it.rows[i].className = "trColor_defale";
			}				
		} 
    } 	         
}

/**
 * gettext_drug(url,displayInputId,hiddenInputId):动态生成弹出div
 * url:代表完整的调用.do及检索参数： 如：url = "ajax/commDictPublicCharViewDemo.do?classCode=123"
 * displayInputId代表基本录入框的id，
 * hiddenInputId1代表保存存储到数据库字段对应的input的id
 * hiddenInputId2代表保存存储到数据库字段对应的input的id
 */
function gettext_drug(url, displayInputId, hiddenInputId1, hiddenInputId2, hiddenInputId3){		
	var newele = document.createElement("div");
	newele.id = "body_div";
	newele.style.display = "block";			
	newele.style.background = "#000000";		
	
	if(document.body.scrollHeight<462){
		newele.style.width= "100%";
		newele.style.height= "100%";
	}else{
		newele.style.width= document.body.scrollWidth + "px";
		newele.style.height= document.body.scrollHeight + "px";
	}
			
	newele.style.top="0";
	newele.style.left="0";
	newele.style.position="absolute";
	if(document.all){
				newele.style.filter = "alpha(Opacity=30)";	
			}else{
				newele.style.opacity="0.3";
			}		
	document.body.appendChild(newele);
	
	//弹出窗口背景
	var bgdiv = document.createElement("div");
	bgdiv.id = "bg_div";
	bgdiv.style.visibility="";
	bgdiv.style.position="absolute";
	var bodywidth = document.body.clientWidth;
	//var bodyheight = document.body.clientHeight;
	bgdiv.style.top="70px";
	bgdiv.style.left=(bodywidth-385)/2 + "px";
	
	//弹出窗口标题
	var nav_div = document.createElement("div");
	nav_div.id = "div_title";
	nav_div.onclick = function(){
		body_div.parentNode.removeChild(body_div);   
		bg_div.parentNode.removeChild(bg_div);
	}
	bgdiv.appendChild(nav_div);	
	
	//弹出窗口div框架
	var divList = document.createElement("div");
	divList.id = "div_pinyin";						
	bgdiv.appendChild(divList);
	
	document.body.appendChild(bgdiv);
	div_pinyin.innerHTML = '<div class="writediv"><input type="hidden" id="hidden_value_1" value="" /><input type="hidden" id="hidden_value_2" value="" /><input type="hidden" id="hidden_value_3" value="" />'
		+'<select id="inputTypeId" style="width:70px;margin-right:10px;">'
		+'<option value="1">拼音码</option>'
		+'<option value="2">类别</option>'
		+'<option value="3">汉字</option>'
		+'<option value="4">剂型</option>'
		+'</select>'
		+'请输入查询条件：<input type="text" name="divInputName" id="input_value" class="write_input" style="width:90px;" value="" onkeyup="findNames(\''+url+'\',\''+displayInputId+'\',\''+hiddenInputId1+'\', \''+hiddenInputId2+'\', \''+hiddenInputId3+'\');" maxlength="30" /><input type="button" class="button" value="选中" onclick="xuanz(\''+displayInputId+'\',\''+hiddenInputId1+'\', \''+hiddenInputId2+'\', \''+hiddenInputId3+'\');" />'
		+'</div>'
		+'<div id="guoji_div">'
		+'<table id="guoji_table" border="0" cellspacing="1" cellpadding="0">'
		+'<tr class="pinyin_nav">'
		+'<td width="40">类别</td>'
		+'<td width="50">拼音码</td>'
		+'<td width="116">名称</td>'
		+'<td width="50">剂型</td>'
		+'</tr>'
		+'<tbody id="guoji_body"></tbody>'
		+'</table></div>';
	
	fade("body_div");//调用该函数作用是背景渐变透明	
	
	findNames(url,displayInputId,hiddenInputId1, hiddenInputId2, hiddenInputId3);
	document.getElementById("input_value").focus();			
} 