var inputobj;			
			function sendData(){
				var divdisplay = document.getElementById("displaydiv");						
				inputobj.value=document.getElementById("textareaForm").value;
				divdisplay.style.display = "none";
				document.getElementById("textareaForm").value = "Por favor, introduzca el contenido";
			}
			//获取元素的纵坐标
			function getTopp(arg){
				var offset=arg.offsetTop;
				if(arg.offsetParent!=null) offset+=getTopp(arg.offsetParent);
				return offset;
			}
			//获取元素的横坐标
			function getLeftt(arg){
				var offset=arg.offsetLeft;
				if(arg.offsetParent!=null) offset+=getLeftt(arg.offsetParent);
				return offset;
			}
			function opendiv(inputarg){				
				var inputobject = document.getElementById(inputarg);
				var divdisplay = document.getElementById("displaydiv");
				inputobj = inputobject;				
				var inputLeft = getLeftt(inputobject);
				var inputTop = getTopp(inputobject);
				divdisplay.style.left = inputLeft + "px";
				divdisplay.style.top = inputTop + inputobject.offsetHeight + "px";
				divdisplay.style.display = "block";
				document.getElementById("textareaForm").value = inputobject.value;
			}
			function closediv(){
				var divdisplay = document.getElementById("displaydiv");	
				divdisplay.style.display = "none";
				document.getElementById("textareaForm").value = "Por favor, introduzca el contenido";
			}