var argArray;
function searchMore(args) {
	if(args!=null&&args!=""){
		argArray = args.split("#");
	}
	
	var newele = document.createElement("div");
	newele.id = "searchbody_div";
	newele.style.display = "block";
	newele.style.background = "#000000";
	newele.style.width = "100%";
	newele.style.height = "100%";
	newele.style.top = "0";
	newele.style.left = "0";
	newele.style.margin = "0";
	newele.style.padding = "0";
	newele.style.position = "absolute";
	if(document.all){
				newele.style.filter = "alpha(Opacity=30)";	
			}else{
				newele.style.opacity="0.3";
			}
	document.form.appendChild(newele);
				
	//弹出窗口背景
	var bgdiv = document.createElement("div");
	bgdiv.id = "searchbg_div";
	bgdiv.style.visibility = "";
	bgdiv.style.position = "absolute";
	var bodywidth = document.body.clientWidth;
	//var bodyheight = document.body.clientHeight;
	bgdiv.style.top = "70px";
	bgdiv.style.left = (bodywidth - 600) / 2 + "px";	
						
	//弹出窗口div框架
	var divList = document.getElementById("searchDiv");
	bgdiv.appendChild(divList);
	document.form.appendChild(bgdiv);
	document.getElementById("searchDiv").style.display = "block";
	
	//把默认主要的查询条件值赋值给高级查询条件的文本框值
	if(argArray!=null&&argArray!=""){
		for(i=0; i<argArray.length; i++){			
			document.getElementById(argArray[i]+"2").value = document.getElementById(argArray[i]).value;			
		}
	}
	fade("body_div");//调用该函数作用是背景渐变透明
}
function createtable(){
	//searchclosed();
	//取的所有选择的checkbox的值,
	var checklist=[];
	var count=-1;
	var size;
	var table=document.getElementById("maintable");
	var searchdiv = document.getElementById("searchDiv");
	//var table=searchdiv.getElementsByTagName("table");
	var divinput = searchdiv.getElementsByTagName("input");
	for(i=0;i<divinput.length;i++){
		if(divinput[i].type=="checkbox"){
			if(divinput[i].checked==true){
				count++;
				checklist[count]=divinput[i].value;
			}
		}

	} 
	//依据所选的条件判断需要构建的td,tr并构建,将隐藏的元素放入tr中,从而的要新的一个table弹出框
	if(checklist.length%2==0){
		size=checklist.length/2;
	}else if(checklist.length%2!=0){
		size=Math.floor(checklist.length/2)+1;
	}
	var tbody=document.createElement("tbody");
	for(j=0;j<size;j++){
		var tr=document.createElement("tr");
		for(k=j*2;k<(j+1)*2;k++){
			if(checklist[k]!=null&&checklist[k].length>0){
				var td1=document.createElement("td");
				var comment1=document.getElementById(checklist[k]+"1");
				var td2=document.createElement("td");
				var comment2=document.getElementById(checklist[k]+"2");
				//var inputs=comment.getElementsByTagName("input");
				td1.appendChild(comment1);
				td2.appendChild(comment2);
				td1.align="center";
				td2.align="left";
				comment1.style.display="block";
				comment2.style.display="block";
				tr.appendChild(td1);
				tr.appendChild(td2);
			}
		}
		tbody.appendChild(tr);
		table.appendChild(tbody);
	}
	var checktr=searchdiv.getElementsByTagName("tr");
	for(m=0;m<checktr.length;m++){
		if(checktr[m].id=="checktr"){
			checktr[m].style.display = "none";
		}
	}
	//searchMore(many);
}
function searchclosed() {
	change();
	document.getElementById("searchbody_div").parentNode.removeChild(document.getElementById("searchbody_div"));
	var divList = document.getElementById("searchDiv");
	document.form.appendChild(divList);
	document.getElementById("searchbg_div").parentNode.removeChild(document.getElementById("searchbg_div"));
	document.getElementById("searchDiv").style.display = "none";	
}
function change(){
	//var checklist;
	var searchdiv = document.getElementById("searchDiv");
	var divinput = searchdiv.getElementsByTagName("input");
	for(i=0;i<divinput.length;i++){
		//if(divinput[i].checked=true){
		if(divinput[i].type=="checkbox"){
			var name=divinput[i].value;
			divinput[i].checked=false;
			var reset1=document.getElementById(name+"1");
			var reset2=document.getElementById(name+"2");
			reset1.style.display="none";
			reset2.style.display="none";
			var resetValue = name.substring(5,name.length);
			if(!resetValue ||!document.getElementById(resetValue))return;
			document.getElementById(resetValue).value="";
		}else{
			if(divinput[i].type!="button")
			divinput[i].value="";
		}
		//}
	}
	var divselect=searchdiv.getElementsByTagName("select");
	for(i=0;i<divselect.length;i++){
		divselect[i].value="";
	}
	var checktr=searchdiv.getElementsByTagName("tr");
	for(m=0;m<checktr.length;m++){
		if(checktr[m].id=="checktr"){
			checktr[m].style.display = "block";
		}
	}
}