function daohang(){
	var url = window.location.href;
	//var selfurl = url.substring(34,url.length);
	//alert(url);
	if(url.indexOf("?")!=-1){
		array1 = url.split("?");
		url2 = array1[1];
		//alert(url2);
		if(url2.indexOf("&")!=-1){
			array2 = url2.split("&");
			//alert(array2.length);
			for(i=0;i<array2.length;i++){
				var canshu = array2[i];
				if(canshu.indexOf("parent")!=-1){
					var dengyu = canshu.indexOf("=");
					//alert(dengyu);								
					var parent = canshu.substring((dengyu+1),canshu.length);
					//alert(parent);
					window.parent.parent.table_index.login_index.document.getElementById("span_parent").innerHTML = "&nbsp;&nbsp;》"+parent;
					if(window.parent.parent.table_index.login_index.document.getElementById("span_parent").innerHTML == "&nbsp;&nbsp;》居民密码管理"){
						//alert("0000");
						window.parent.parent.table_index.login_index.document.getElementById("span_weizhi").innerHTML = "当前位置：居民管理";
					}
				}
				if(canshu.indexOf("self")!=-1){
					var dengyu = canshu.indexOf("=");
					//alert(dengyu);								
					var self = canshu.substring((dengyu+1),canshu.length);
					//alert(self);
					window.parent.parent.table_index.login_index.document.getElementById("span_self").innerHTML = "&nbsp;&nbsp;》"+"<a href='"+url+"' target='main'>"+self+"</a>";
				}
			}
		}
	}
	window.parent.parent.table_index.login_index.document.getElementById("span_son").innerHTML = "";
	window.parent.parent.table_index.login_index.document.getElementById("span_third").innerHTML = "";
}
function secendurl(){
	var url = window.location.href;
	var aHtml = window.parent.parent.table_index.login_index.document.getElementById("span_self").innerHTML;
	//alert(aHtml);
	if(url!=""){
		var yi = aHtml.lastIndexOf("<");
		var er = aHtml.indexOf(">");
		var secondson = aHtml.substring((er+1),yi);
		//alert(secondson);
		//alert(er+"and"+yi);
		window.parent.parent.table_index.login_index.document.getElementById("span_self").innerHTML = "&nbsp;&nbsp;》"+"<a href='"+url+"' target='main'>"+secondson+"</a>";
		window.parent.parent.table_index.login_index.document.getElementById("span_son").innerHTML = "";
		window.parent.parent.table_index.login_index.document.getElementById("span_third").innerHTML = "";
	}
	//window.parent.parent.table_index.login_index.document.getElementById("span_self").innerHTML = "&nbsp;&nbsp;》"+"<a href='"+url+"' target='main'>"+self+"</a>";
}

function url(href,self){
	var son = self.getElementsByTagName("span")[0].innerHTML;	
	window.parent.parent.table_index.login_index.document.getElementById("span_son").innerHTML = "&nbsp;&nbsp;》"+"<a href='"+href+"' target='main'>"+son+"</a>";
	window.location=href;
	window.parent.parent.table_index.login_index.document.getElementById("span_third").innerHTML = "";
}
function thirdurl(href,self){
	var third = self.getElementsByTagName("span")[0].innerHTML;	
	window.parent.parent.table_index.login_index.document.getElementById("span_third").innerHTML = "&nbsp;&nbsp;》"+"<a href='"+href+"' target='main'>"+third+"</a>";
	window.location=href;
}
function clearOne(){
	window.parent.parent.table_index.login_index.document.getElementById("span_parent").innerHTML = "";
	window.parent.parent.table_index.login_index.document.getElementById("span_self").innerHTML = "";
	window.parent.parent.table_index.login_index.document.getElementById("span_son").innerHTML = "";
	window.parent.parent.table_index.login_index.document.getElementById("span_third").innerHTML = "";
	window.parent.parent.table_index.document.getElementById("main").src = "";
}
function clearThird(){
	window.parent.parent.table_index.login_index.document.getElementById("span_third").innerHTML = "";
}