function addRow(id){   
	//var t = document.getElementById(id);
	//alert(id);
	tdy = document.getElementById(id);
	var len = tdy.rows.length;  
	var last_row = tdy.rows[len-1];   
	var clone_node = last_row.cloneNode(true);   
	
	tdy.appendChild(clone_node);   
}

function appendRow(tbodyid){
	if(tbodyid==null) {
		alert("parameter wrong");   
		return ;   
	}   
	var tbody = document.getElementById(tbodyid); 
	var trtemplate = tbody.childNodes[0];   
	var tds = trtemplate.childNodes;   
	var trlength = tds.length;   
	var tr = document.createElement("tr");   
	for(var i=0; i<trlength; i++){   
		var td = document.createElement("td");   
		td.className = tds[i].className; 
		var content = tds[i].innerHTML; 
		td.innerHTML = content;
		
		var tdDetails = td.childNodes;   
		var tdlength = tdDetails.length;
		if(tdDetails != null){
			for(var j=0; j<tdlength; j++){
				var tdDetail = tdDetails[j];
				if(tdDetail.type == "text" || tdDetail.type == "TEXT"){
					tdDetail.value = "";
				} if(tdDetail.type == "hidden" || tdDetail.type == "HIDDEN"){
					tdDetail.value = "";
				} else if(tdDetail.type == "checkbox" || tdDetail.type == "CHECKBOX"){
					tdDetail.checked = false;
				} 
			}
		}
		
		tr.appendChild(td);    
	}   
	tbody.appendChild(tr);   
}

function selectAll(id,name){
	var tdy = document.getElementById(id);
	var checkbox = document.getElementsByName(name);
	var len = tdy.rows.length;
	var isexit = false;
	for(var i=0; i<len; i++){
		if(checkbox[i].checked==true){
			isexit = true;
		}else{
			isexit = false;
			break;
		}
	}
	return isexit;
}
function delRow(id,name){
	var tdy = document.getElementById(id);
	var checkbox = document.getElementsByName(name);
	var len = tdy.rows.length;
	if(selectAll(id,name)){
		for(i=len-1; i>0; i--){
			if(checkbox[i].checked){
				//alert(i);			
				tdy.deleteRow(i);
				//alert(len);
			}		
		}
		var firstTr = tdy.rows[0].getElementsByTagName("input");
		var firstTrLength = firstTr.length;
		if(firstTrLength!=0){			
			firstTr[0].checked = false;
			for(var i=1; i<firstTrLength; i++){
				firstTr[i].value = "";
			}
		}
		var firstTrSelect = tdy.rows[0].getElementsByTagName("select");
		var firstTrSelectLength = firstTrSelect.length;
		if(firstTrSelectLength!=0){			
			for(var i=0; i<firstTrSelectLength; i++){
				firstTrSelect[i].value = "";
			}
		}
		
	}else{		
		//alert(len);
		for(i=len-1; i>-1; i--){
			if(checkbox[i].checked){
				//alert(i);			
				tdy.deleteRow(i);
				//alert(len);
			}		
		}
	}   
}
function displayRow(id){
	tbody_display = document.getElementById(id);
	if(tbody_display.style.display=="block"){
		tbody_display.style.display="none";
	}else{
		tbody_display.style.display="block";
	}
}