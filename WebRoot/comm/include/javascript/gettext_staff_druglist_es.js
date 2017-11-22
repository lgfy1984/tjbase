	//定义一些全局变量
	var XMLHttpReq;
    //var completeDiv;
    var inputField;
    var completeTable;
    var completeBody;
	var currentLine = -1; 
	var	item_name;
    var item_drugDictId;
    var item_drugCode;
    var item_drguDict_DrugIndicator;
    var item_drguDict_DrugSpec;
    var item_drguDict_Units;
    var item_drguDict_DrugForm;
    var item_drguDict_ToxiProperty;
    var item_drguDict_Administration;
    var item_drguDict_DrugClassDictCode;
    var item_drguDict_Otc;
    var item_drguDict_Concentration
    var item_drguDict_ACTIOAN;
    var item_drguDict_elemt;
    var item_stopFlag;
	var windowstate = false;
	var divtop;
	var divlayer = "";
	var path="";
 	//创建XMLHttpRequest对象
    function createXmlHttpRequest(){
		if (window.ActiveXObject) {
			XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
		}else if (window.XMLHttpRequest) {
			XMLHttpReq = new XMLHttpRequest(); 
		}
	}
	//字典库调用入口 方法
	/****
	调用ajax
	*/
    function findNames(url,itemName,drugDictId,drugCode,drguDict_DrugIndicator,drguDict_DrugSpec,drguDict_Units,drguDict_DrugForm,drguDict_ToxiProperty,
	drguDict_Administration,drguDict_DrugClassDictCode,drguDict_Otc,drguDict_Concentration,drguDict_ACTIOAN,drguDict_elemt,stopFlag) {
    	var e = window.event || arguments[0];
    	switch (e.keyCode)    
   		{ 
        case 38: 
            break; 
        case 40:
            break; 
        default :    
        	currentLine = -1;
			
        	item_name = itemName;
        	item_drugDictId = drugDictId;
        	item_drugCode = drugCode;
        	item_drguDict_DrugIndicator = drguDict_DrugIndicator;
        	item_drguDict_DrugSpec=drguDict_DrugSpec;
        	item_drguDict_Units=drguDict_Units;
        	item_drguDict_DrugForm=drguDict_DrugForm;
        	item_drguDict_ToxiProperty=drguDict_ToxiProperty;
        	item_drguDict_Administration=drguDict_Administration;
        	item_drguDict_DrugClassDictCode=drguDict_DrugClassDictCode;
        	item_drguDict_Otc=drguDict_Otc;
        	item_drguDict_Concentration=drguDict_Concentration;
        	item_drguDict_ACTIOAN=drguDict_ACTIOAN;
        	item_drguDict_elemt=drguDict_elemt;
        	item_stopFlag=stopFlag;
        	
        	var inputTypeFlag = document.getElementById("inputTypeId").options[document.getElementById("inputTypeId").selectedIndex].value;
        	
       		inputField = document.getElementById("input_value");            
       		completeTable = document.getElementById("guoji_table");
       		completeBody = document.getElementById("guoji_body");
			

			/***
			objchinese=inputField.value.match(/^[\u4e00-\u9fa5]*$/g); 
			objenglish=inputField.value.match(/^[A-Za-z]+$/); 
			objnumber=inputField.value.match(/^\d+$/); 
			
			//判断是否输入的是中文
			if(objchinese!=null){
				flag = 3;
			}
			//判断是否输入的是英文
			if(objenglish!=null){ 
				flag = 1;
			}
			//判断是否输入的是数字
			if(objnumber!=null){ 
				flag = 2;
			}
			//当输入的是其他字符
			if(flag == 0){
				return false;
			}
			*/
	        
	        if(completeBody.rows.length>0){
				
				for(var i=0;i<completeBody.rows.length;i++){
					completeBody.deleteRow(i);
					i--;
				}
			}	
	        
	        
	            createXmlHttpRequest();         
	   
	            XMLHttpReq.onreadystatechange = function(){processMatchResponse(itemName,drugDictId,drugCode,drguDict_DrugIndicator,drguDict_DrugSpec,drguDict_Units,drguDict_DrugForm,drguDict_ToxiProperty,
	drguDict_Administration,drguDict_DrugClassDictCode,drguDict_Otc,drguDict_Concentration,drguDict_ACTIOAN,drguDict_elemt,stopFlag)};  //指定对象状态判断的方法
	       //     var url = "ajax/commDictPublicCharViewDemo.do?classCode="+classCode;    //提交查询的url
	            //url = url+"&inputCode="+inputField.value+"&flag="+inputTypeFlag; //传递给Action的参数                   
	        //   	url = url + xmlql;	            
				
				var para = "inputCode="+inputField.value+"&flag="+inputTypeFlag;
	        	//alert(url);
	            XMLHttpReq.open("Post", url, true);
	            XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
				XMLHttpReq.send(para);
	      
        }
    }
  
    
	//对象状态判断的方法
    function processMatchResponse(itemName,drugDictId,drugCode,drguDict_DrugIndicator,drguDict_DrugSpec,drguDict_Units,drguDict_DrugForm,drguDict_ToxiProperty,
	drguDict_Administration,drguDict_DrugClassDictCode,drguDict_Otc,drguDict_Concentration,drguDict_ACTIOAN,drguDict_elemt,stopFlag) {
    	
    	if (XMLHttpReq.readyState == 4) { // readyState　为正常返回的时候
        	if (XMLHttpReq.status == 200) {　　//成功响应 
        		     
				  			
                setNames(XMLHttpReq.responseXML,itemName,drugDictId,drugCode,drguDict_DrugIndicator,drguDict_DrugSpec,drguDict_Units,drguDict_DrugForm,drguDict_ToxiProperty,
	drguDict_Administration,drguDict_DrugClassDictCode,drguDict_Otc,drguDict_Concentration,drguDict_ACTIOAN,drguDict_elemt,stopFlag); 
                                  
            }else { //当为失败的时候
                window.alert("Error al recuperar datos!");
            }
        }
        
    }
    
	//解析从action中返回的xml文件
	/***
	setNames(names,displayInputId,hiddenInputId)把生成的xml的值传递到div显示
	*/
     function setNames(names,itemName,drugDictId,drugCode,drguDict_DrugIndicator,drguDict_DrugSpec,drguDict_Units,drguDict_DrugForm,drguDict_ToxiProperty,
	drguDict_Administration,drguDict_DrugClassDictCode,drguDict_Otc,drguDict_Concentration,drguDict_ACTIOAN,drguDict_elemt,stopFlag) {            
        var elementroot = names.getElementsByTagName("ress");　　　//获得返回的xml的节点
        var row,cell1,cell2,cell3,cell6,cell7,cell8,cell9,cell10,cell11,cell12,cell13,cell14,cell15,cell16,cell17,cell18,txtNode;　　　
        　　　　　　　　　　　 //定义多个变量
        //setOffsets();
       　　　　　　　　　　　　　　　　　　　　//设置显示位置
     
        //叠代出所有节点
        for(var i=0 ;i < elementroot.length;i++){
        	 
        	var ress = elementroot[i];
        	
        	row = document.createElement("tr");
            cell1 = document.createElement("td");
			cell2 = document.createElement("td");
			cell3 = document.createElement("td");	
			cell6 = document.createElement("td");		
			cell7 = document.createElement("td");
			cell8 = document.createElement("td");
			cell9 = document.createElement("td");
			cell10 = document.createElement("td");
			cell11 = document.createElement("td");
			cell12 = document.createElement("td");
			cell13 = document.createElement("td");
			cell14 = document.createElement("td");
			cell15 = document.createElement("td");
			cell16 = document.createElement("td");
			cell17 = document.createElement("td");
			cell18 = document.createElement("td");
        	var resInputCode = ress.getElementsByTagName("resInputCode")[0].firstChild.data;
        	if(resInputCode==""||resInputCode=="null"){
        		resInputCode="";
        	}
        	var resDrugCode = ress.getElementsByTagName("resDrugCode")[0].firstChild.data;
        	var resDrugName = ress.getElementsByTagName("resDrugName")[0].firstChild.data;
        	var resItemId = ress.getElementsByTagName("resItemId")[0].firstChild.data;
        	var resDrugIndicator = ress.getElementsByTagName("resDrugIndicator")[0].firstChild.data;
			var resDrugSpec = ress.getElementsByTagName("resDrugSpec")[0].firstChild.data;
			var resDictUnits = ress.getElementsByTagName("resDictUnits")[0].firstChild.data;
			var resDrugForm = ress.getElementsByTagName("resDrugForm")[0].firstChild.data;
			var resToxiProperty = ress.getElementsByTagName("resToxiProperty")[0].firstChild.data;
			var resAdministration = ress.getElementsByTagName("resAdministration")[0].firstChild.data;
			var resDrugClassDictCode = ress.getElementsByTagName("resDrugClassDictCode")[0].firstChild.data;
			var resDictOtc = ress.getElementsByTagName("resDictOtc")[0].firstChild.data;
			var resConcentration = ress.getElementsByTagName("resConcentration")[0].firstChild.data;
			var resActioan = ress.getElementsByTagName("resActioan")[0].firstChild.data;
			var resElemt = ress.getElementsByTagName("resElemt")[0].firstChild.data;
			var resStopFlag = ress.getElementsByTagName("resStopFlag")[0].firstChild.data;
        	
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
					if(i == rowNumber){									
						completeBody.rows[i].className = "trColor";			
					}else{
						if (completeBody.rows[i].rowIndex % 2 == 1) {
							completeBody.rows[i].className = "trColor_white";
						} else {
							completeBody.rows[i].className = "trColor_defale";
						}											
					}		
				}
				document.getElementById("input_value").value = this.getElementsByTagName('td')[2].innerHTML;
				document.getElementById("hidden_value").value = this.getElementsByTagName('td')[3].innerHTML;
				document.getElementById("drugCode").value = this.getElementsByTagName('td')[1].innerHTML;
				document.getElementById("drguDict_DrugIndicator").value = this.getElementsByTagName('td')[4].innerHTML;
			    document.getElementById("drguDict_DrugSpec").value = this.getElementsByTagName('td')[5].innerHTML;
			    document.getElementById("drguDict_Units").value =this.getElementsByTagName('td')[6].innerHTML;
		     	document.getElementById("drguDict_DrugForm").value = this.getElementsByTagName('td')[7].innerHTML;
			    document.getElementById("drguDict_ToxiProperty").value = this.getElementsByTagName('td')[8].innerHTML;
			    document.getElementById("drguDict_Administration").value = this.getElementsByTagName('td')[9].innerHTML;
			   document.getElementById("drguDict_DrugClassDictCode").value = this.getElementsByTagName('td')[10].innerHTML;
			   document.getElementById("drguDict_Otc").value =this.getElementsByTagName('td')[11].innerHTML;
			  document.getElementById("drguDict_Concentration").value = this.getElementsByTagName('td')[12].innerHTML;
			  document.getElementById("drguDict_ACTIOAN").value = this.getElementsByTagName('td')[13].innerHTML;
			  document.getElementById("drguDict_elemt").value = this.getElementsByTagName('td')[14].innerHTML;
			  document.getElementById("stopFlag").value = this.getElementsByTagName('td')[15].innerHTML;
			}
			
			row.ondblclick = function(){
			document.getElementById(itemName).value = this.getElementsByTagName('td')[2].innerHTML;
			document.getElementById(drugDictId).value = this.getElementsByTagName('td')[3].innerHTML;
			document.getElementById(drugCode).value = this.getElementsByTagName('td')[0].innerHTML;
			document.getElementById(drguDict_DrugIndicator).value = this.getElementsByTagName('td')[4].innerHTML;
			    document.getElementById(drguDict_DrugSpec).value = this.getElementsByTagName('td')[5].innerHTML;
			    document.getElementById(drguDict_Units).value =this.getElementsByTagName('td')[6].innerHTML;
		     	document.getElementById(drguDict_DrugForm).value = this.getElementsByTagName('td')[7].innerHTML;
			    document.getElementById(drguDict_ToxiProperty).value = this.getElementsByTagName('td')[8].innerHTML;
			    document.getElementById(drguDict_Administration).value = this.getElementsByTagName('td')[9].innerHTML;
			   document.getElementById(drguDict_DrugClassDictCode).value = this.getElementsByTagName('td')[10].innerHTML;
			   document.getElementById(drguDict_Otc).value =this.getElementsByTagName('td')[11].innerHTML;
			  document.getElementById(drguDict_Concentration).value = this.getElementsByTagName('td')[12].innerHTML;
			  document.getElementById(drguDict_ACTIOAN).value = this.getElementsByTagName('td')[13].innerHTML;
			  document.getElementById(drguDict_elemt).value = this.getElementsByTagName('td')[14].innerHTML;
			  document.getElementById(stopFlag).value = this.getElementsByTagName('td')[15].innerHTML;
			 
				closed();
				//document.getElementById(displayInputId).focus();
				
			}
			var resDrugCode = ress.getElementsByTagName("resDrugCode")[0].firstChild.data;
        	var resDrugName = ress.getElementsByTagName("resDrugName")[0].firstChild.data;
        	var resItemId = ress.getElementsByTagName("resItemId")[0].firstChild.data;
        	var resDrugIndicator = ress.getElementsByTagName("resDrugIndicator")[0].firstChild.data;
			var resDrugSpec = ress.getElementsByTagName("resDrugSpec")[0].firstChild.data;
			var resDictUnits = ress.getElementsByTagName("resDictUnits")[0].firstChild.data;
			var resDrugForm = ress.getElementsByTagName("resDrugForm")[0].firstChild.data;
			var resToxiProperty = ress.getElementsByTagName("resToxiProperty")[0].firstChild.data;
			var resAdministration = ress.getElementsByTagName("resAdministration")[0].firstChild.data;
			var resDrugClassDictCode = ress.getElementsByTagName("resDrugClassDictCode")[0].firstChild.data;
			var resDictOtc = ress.getElementsByTagName("resDictOtc")[0].firstChild.data;
			var resConcentration = ress.getElementsByTagName("resConcentration")[0].firstChild.data;
			var resActioan = ress.getElementsByTagName("resActioan")[0].firstChild.data;
			var resElemt = ress.getElementsByTagName("resElemt")[0].firstChild.data;
			var resStopFlag = ress.getElementsByTagName("resStopFlag")[0].firstChild.data;
            //----代码----
            txtNode = document.createTextNode(resDrugCode);
            cell1.appendChild(txtNode);
            row.appendChild(cell1);
			//----拼音码----
            txtNode = document.createTextNode(resInputCode);
            cell2.appendChild(txtNode);
            row.appendChild(cell2);
			//----汉字----------
            txtNode = document.createTextNode(resDrugName);
            cell3.appendChild(txtNode);
            row.appendChild(cell3);
            //----ID----------
            txtNode = document.createTextNode(resItemId);
            cell6.appendChild(txtNode);
            cell6.style.display = "none";
            row.appendChild(cell6);
            
             txtNode = document.createTextNode(resDrugIndicator);
            cell7.appendChild(txtNode);
            cell7.style.display = "none";
            row.appendChild(cell7);
            
             txtNode = document.createTextNode(resDrugSpec);
            cell8.appendChild(txtNode);
            cell8.style.display = "none";
            row.appendChild(cell8);
            
             txtNode = document.createTextNode(resDictUnits);
            cell9.appendChild(txtNode);
            cell9.style.display = "none";
            row.appendChild(cell9);
            
             txtNode = document.createTextNode(resDrugForm);
            cell10.appendChild(txtNode);
            cell10.style.display = "none";
            row.appendChild(cell10);
            
             txtNode = document.createTextNode(resToxiProperty);
            cell11.appendChild(txtNode);
            cell11.style.display = "none";
            row.appendChild(cell11);
            
             txtNode = document.createTextNode(resAdministration);
            cell12.appendChild(txtNode);
            cell12.style.display = "none";
            row.appendChild(cell12);
            
             txtNode = document.createTextNode(resDrugClassDictCode);
            cell13.appendChild(txtNode);
            cell13.style.display = "none";
            row.appendChild(cell13);
            
             txtNode = document.createTextNode(resDictOtc);
            cell14.appendChild(txtNode);
            cell14.style.display = "none";
            row.appendChild(cell14);
            
             txtNode = document.createTextNode(resConcentration);
            cell15.appendChild(txtNode);
            cell15.style.display = "none";
            row.appendChild(cell15);
            
             txtNode = document.createTextNode(resActioan);
            cell16.appendChild(txtNode);
            cell16.style.display = "none";
            row.appendChild(cell16);
            
             txtNode = document.createTextNode(resElemt);
            cell17.appendChild(txtNode);
            cell17.style.display = "none";
            row.appendChild(cell17);
            
             txtNode = document.createTextNode(resStopFlag);
            cell18.appendChild(txtNode);
            cell18.style.display = "none";
            row.appendChild(cell18);
            completeBody.appendChild(row);
           	trColor();
        }
    }
	
	function trColor(){
		var TbRow = document.getElementById("guoji_body");
		if (TbRow != null) {
			for (var i = 0; i < TbRow.rows.length; i++) {
				if (TbRow.rows[i].rowIndex % 2 == 1) {
					TbRow.rows[i].className = "trColor_white";
				} else {
					TbRow.rows[i].className = "trColor_defale";
				}
			}
		}
	}
/***

关闭div显示部分
*/
	function closed(){
		document.body.style.overflowY = "visible";
		document.documentElement.scrollTop = divtop;		
		document.getElementById("full_div").parentNode.removeChild(document.getElementById("full_div"));   
		document.getElementById("bg_div").parentNode.removeChild(document.getElementById("bg_div"));		
		windowstate = false;
		if(divlayer != ""){
			document.getElementById(divlayer).style.display = "block";
		}
		
	}
/**
选中处理
*/
	function xuanz(itemName,drugDictId,drugCode,drguDict_DrugIndicator,drguDict_DrugSpec,drguDict_Units,drguDict_DrugForm,drguDict_ToxiProperty,drguDict_Administration,drguDict_DrugClassDictCode,drguDict_Otc,drguDict_Concentration,drguDict_ACTIOAN,drguDict_elemt,stopFlag){
		if(document.getElementById("input_value").value != "" && currentLine != -1){
			document.getElementById(itemName).value = document.getElementById("input_value").value;
			document.getElementById(drugDictId).value = document.getElementById("hidden_value").value;
			document.getElementById(drugCode).value = document.getElementById("drugCode").value;
			document.getElementById(drguDict_DrugIndicator).value = document.getElementById("drguDict_DrugIndicator").value;
			document.getElementById(drguDict_DrugSpec).value = document.getElementById("drguDict_DrugSpec").value;
			document.getElementById(drguDict_Units).value = document.getElementById("drguDict_Units").value;
			document.getElementById(drguDict_DrugForm).value = document.getElementById("drguDict_DrugForm").value;
			document.getElementById(drguDict_ToxiProperty).value = document.getElementById("drguDict_ToxiProperty").value;
			document.getElementById(drguDict_Administration).value = document.getElementById("drguDict_Administration").value;
			document.getElementById(drguDict_DrugClassDictCode).value = document.getElementById("drguDict_DrugClassDictCode").value;
			document.getElementById(drguDict_Otc).value = document.getElementById("drguDict_Otc").value;
			document.getElementById(drguDict_Concentration).value = document.getElementById("drguDict_Concentration").value;
			document.getElementById(drguDict_ACTIOAN).value = document.getElementById("drguDict_ACTIOAN").value;
			document.getElementById(drguDict_elemt).value = document.getElementById("drguDict_elemt").value;
			document.getElementById(stopFlag).value = document.getElementById("stopFlag").value;
			
		}		
		
		closed();
	}
	
	
	document.onkeydown = function (e){
	    e = window.event || e; 
	    switch (e.keyCode){ 
	        case 38: //--上箭头
	        	//document.getElementById("guoji").blur();
	            currentLine--; 
	            changeItem();
	            
	            break; 
	        case 40: //--下箭头
	        	//document.getElementById("guoji").blur();
	            currentLine++; 
	            changeItem(); 
	            break; 
	        case 13:
	        	if(windowstate == true){
	        		if(currentLine == -1){
						return;
					}			 
			        document.getElementById(item_drugDictId).value = completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;
			        document.getElementById(item_drugCode).value = completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;
					document.getElementById(item_name).value = completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;							
		        	document.getElementById(item_drguDict_DrugIndicator).value = completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;
			document.getElementById(item_drguDict_DrugSpec).value =completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;
			document.getElementById(item_drguDict_Units).value = completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;
			document.getElementById(item_drguDict_DrugForm).value = completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;
			document.getElementById(item_drguDict_ToxiProperty).value = completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;
			document.getElementById(item_drguDict_Administration).value = completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;
			document.getElementById(item_drguDict_DrugClassDictCode).value = completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;
			document.getElementById(item_drguDict_Otc).value = completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;
			document.getElementById(item_drguDict_Concentration).value = completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;
			document.getElementById(item_drguDict_ACTIOAN).value = completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;
			document.getElementById(item_drguDict_elemt).value = completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;
			document.getElementById(item_stopFlag).value =completeBody.rows[currentLine].getElementsByTagName("td")[0].innerHTML;
		        	currentLine = -1;
		        	closed();
	        	}else{
	        		break;
	        	}
	        	break;        	
	        default : 
	            break; 
	    } 
	} 
    
	//当按上下键的时候　调用的方法
	function changeItem() 
	{
	   
	    var it = completeBody; 
		    
	    if(currentLine < 0){
	        currentLine = it.rows.length - 1; 
	    }
	    if(currentLine == it.rows.length){ 
	        currentLine = 0; 
	    }
	    for(i=0;i<it.rows.length;i++){ 
	        if(i == currentLine){
				it.rows[currentLine].className = "trColor"; 
			}else{
				if (it.rows[i].rowIndex % 2 == 1) {
					it.rows[i].className = "trColor_white";
				} else {
					it.rows[i].className = "trColor_defale";
				}				
			} 
	    } 	         
	}
	
	
	
	/**
	add(url,displayInputId,hiddenInputId):动态生成弹出div
	url:代表完整的调用.do及检索参数： 如：url = "ajax/commDictPublicCharViewDemo.do?classCode=123"
	displayInputId代表基本录入框的id，
	hiddenInputId代表保存存储到数据库字段对应的input的id
	*****/
	function add(url,itemName,drugDictId,drugCode,drguDict_DrugIndicator,drguDict_DrugSpec,drguDict_Units,drguDict_DrugForm,drguDict_ToxiProperty,
	drguDict_Administration,drguDict_DrugClassDictCode,drguDict_Otc,drguDict_Concentration,drguDict_ACTIOAN,drguDict_elemt,stopFlag,divlevel){	
			path=url;
			if(divlevel != null){
				document.getElementById(divlevel).style.display = "none";
				divlayer = divlevel;
			}
			windowstate = true;
			var csdnScrollTop=function(){
		        return document.documentElement.scrollTop?document.documentElement.scrollTop:document.body.scrollTop;
		    };
		    divtop = csdnScrollTop();		        
			document.body.style.overflowY = "hidden";			
			
			var fulldiv = document.createElement("div");
			fulldiv.id = "full_div";
			fulldiv.style.top="0";
			fulldiv.style.left="0";
			fulldiv.style.position="absolute";
			
			fulldiv.innerHTML = '<iframe frameborder="0" style="" id="bgiframe"></iframe>';
			document.body.appendChild(fulldiv);			
			
			var newele = document.createElement("div");
			newele.id = "body_div";
			newele.style.display = "block";			
			newele.style.background = "#000000";					
			newele.style.top="0";
			newele.style.left="0";
			newele.style.position="absolute";
			if(document.all){
				newele.style.filter = "alpha(Opacity=30)";	
			}else{
				newele.style.opacity="0.3";
			}					
			fulldiv.appendChild(newele);		
			if(document.body.scrollHeight<462){
				fulldiv.style.width= "100%";
				fulldiv.style.height= "100%";
				newele.style.width= "100%";
				newele.style.height= "100%";
				document.getElementById("bgiframe").style.width= "100%";
				document.getElementById("bgiframe").style.height= "100%";
				
			}else{
				fulldiv.style.width= document.body.offsetWidth + "px";
				fulldiv.style.height= document.body.offsetHeight + "px";
				newele.style.width= document.body.offsetWidth + "px";
				newele.style.height= document.body.offsetHeight + "px";
				document.getElementById("bgiframe").style.width= document.body.offsetWidth + "px";
				document.getElementById("bgiframe").style.height= document.body.offsetHeight + "px";				
			}
			
			
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
			nav_div.onclick = function() {
				closed();
			}
			bgdiv.appendChild(nav_div);	
			
			//弹出窗口div框架
			var divList = document.createElement("div");
			divList.id = "div_pinyin";						
			bgdiv.appendChild(divList);
			
			document.body.appendChild(bgdiv);
			
			//---此处判断需要弹出显示的字典库类型-------------------------
			/*****
			if (id == displayInputId_1){
		 	
			}
			*/
			var check=path.substring((path.length-9),path.length);
				document.getElementById("div_pinyin").innerHTML = '<div class="writediv"><select id="inputTypeId" style="width:50px;margin-right:10px;"><option value="1">código fonético</option><option value="2">código</option><option value="3">nombre</option></select>pregunta:<input type="hidden" id="hidden_value" value="" /><input type="hidden" id="drugCode" value="" /><input type="hidden" id="drguDict_DrugIndicator" value="" /><input type="hidden" id="drguDict_DrugSpec" value="" /><input type="hidden" id="drguDict_Units" value="" /><input type="hidden" id="drguDict_DrugForm" value="" /><input type="hidden" id="drguDict_ToxiProperty" value="" /><input type="hidden" id="drguDict_Administration" value="" /><input type="hidden" id="drguDict_DrugClassDictCode" value="" /><input type="hidden" id="drguDict_Otc" value="" /><input type="hidden" id="drguDict_Concentration" value="" /><input type="hidden" id="drguDict_ACTIOAN" value="" /><input type="hidden" id="drguDict_elemt" value="" /><input type="hidden" id="stopFlag" value="" /><input type="text" name="divInputName" id="input_value" class="write_input" value="" onkeyup="findNames(\''+url+'\',\''+itemName+'\',\''+drugDictId+'\',\''+drugCode+'\',\''+drguDict_DrugIndicator+'\',\''+drguDict_DrugSpec+'\',\''+drguDict_Units+'\',\''+drguDict_DrugForm+'\',\''+drguDict_ToxiProperty+'\',\''+drguDict_Administration+'\',\''+drguDict_DrugClassDictCode+'\',\''+drguDict_Otc+'\',\''+drguDict_Concentration+'\',\''+drguDict_ACTIOAN+'\',\''+drguDict_elemt+'\',\''+stopFlag+'\');" maxlength="30" /><input type="button" class="button" value="Selecciona" onclick="xuanz(\''+itemName+'\',\''+drugDictId+'\',\''+drugCode+'\',\''+drguDict_DrugIndicator+'\',\''+drguDict_DrugSpec+'\',\''+drguDict_Units+'\',\''+drguDict_DrugForm+'\',\''+drguDict_ToxiProperty+'\',\''+drguDict_Administration+'\',\''+drguDict_DrugClassDictCode+'\',\''+drguDict_Otc+'\',\''+drguDict_Concentration+'\',\''+drguDict_ACTIOAN+'\',\''+drguDict_elemt+'\',\''+stopFlag+'\');" /></div><div id="guoji_div"><table id="guoji_table" border="0" cellspacing="1" cellpadding="0"><tr class="pinyin_nav"><td width="110">nombre</td><td width="160">Medida unidad minima</td><td width="286">Medida unidad</td></tr><tbody id="guoji_body"></tbody></table></div>';		
			//fade("body_div");//调用该函数作用是背景渐变透明	
			findNames(url,itemName,drugDictId,drugCode,drguDict_DrugIndicator,drguDict_DrugSpec,drguDict_Units,drguDict_DrugForm,drguDict_ToxiProperty,
	drguDict_Administration,drguDict_DrugClassDictCode,drguDict_Otc,drguDict_Concentration,drguDict_ACTIOAN,drguDict_elemt,stopFlag);
			document.getElementById("input_value").focus();	
						
	} 