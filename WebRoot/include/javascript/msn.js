<!-- 
 
/**//*  
 *    消息构造  
 */ 
 
function CLASS_MSN_MESSAGE(id,width,height,caption,title,consultation,target,action){  
    this.id     = id;  
    this.title  = title;  
    this.caption= caption;  
	this.consultation= consultation;
    this.target = target;  
    this.action = action;  
    this.width    = width?width:200;  
    this.height = height?height:120; 
    if(document.all){
    	this.timeout=150;
    }else{
    	this.timeout= 50;
    }
    this.speed    = 5; 
    this.step    = 1; 
    if(document.all){
    	this.right = screen.width -1;
    	this.bottom = screen.height;
    }else{
    	this.right    = document.documentElement.clientWidth -1;  
    	this.bottom = document.documentElement.clientHeight; 
    }
    this.left    = this.right - this.width; 
    this.top    = this.bottom - this.height; 
    this.timer    = 0; 
    this.pause    = false;
    this.close    = false;
    if(document.all){
    	this.autoHide = true;
    } else {
    	this.autoHide    = false;
    }
}  
  
/**//*  
 *    隐藏消息方法  
 */  
CLASS_MSN_MESSAGE.prototype.hide = function(){  
     outid = setTimeout("startRequest()",1000*1200);//隐藏后20分钟再显示 
    if(this.onunload()){  

        var offset  = this.height>this.bottom-this.top?this.height:this.bottom-this.top; 
        var me  = this;  
 
        if(this.timer>0){   
            window.clearInterval(me.timer);  
        }  
 
        var fun = function(){  
            if(me.pause==false||me.close){
                var x  = me.left; 
                var y  = 0; 
                var width = me.width; 
                var height = 0; 
                if(me.offset>0){ 
                    height = me.offset; 
                } 
     
                y  = me.bottom - height; 
     
                if(y>=me.bottom){ 
                    window.clearInterval(me.timer);  
                    me.Pop.hide();  
                } else { 
                    me.offset = me.offset - me.step;  
                } 
                me.Pop.show(x,y,width,height);    
            }             
        }  
 
        this.timer = window.setInterval(fun,this.speed)      
    }  
}  
  
/**//*  
 *    消息卸载事件，可以重写  
 */  
CLASS_MSN_MESSAGE.prototype.onunload = function() {  
    return true;  
}  
/**//*  
 *    咨询消息命令事件，要实现自己的连接，请重写它  
 *  
 */  
CLASS_MSN_MESSAGE.prototype.consulcommand = function(){  
    this.close = true;
    this.hide();  
}  

/**
 * 如果不能使用window.createPopup,自定义该方法
 */
if (!window.createPopup) {
	var __createPopup = function() {
		var SetElementStyles = function( element, styleDict ) {
			var style = element.style ;
			for ( var styleName in styleDict )style[ styleName ] = styleDict[ styleName ] ; 
		}
		var eDiv = document.createElement( 'div' ); 
		SetElementStyles( eDiv, { 'position': 'absolute', 'top': 0 + 'px', 'left': 0 + 'px', 'width': 0 + 'px', 'height': 0 + 'px', 'zIndex': 1000, 'display' : 'none', 'overflow' : 'hidden' } ) ;
		eDiv.body = eDiv ;
		var opened = false ;
		var setOpened = function( b ) {
			opened = b; 
		}
		var getOpened = function() {
			return opened ; 
		}
		var getCoordinates = function( oElement ) {
			var coordinates = {x:0,y:0} ; 
			while( oElement ) {
				coordinates.x += oElement.offsetLeft ;
				coordinates.y += oElement.offsetTop ;
				oElement = oElement.offsetParent ;
			}
			return coordinates ;
		}
		return {htmlTxt : '',
			document : eDiv, 
			isOpen : getOpened(),
			isShow : false,
			hide : function() {
				SetElementStyles( eDiv, { 'top': 0 + 'px',
										'left': 0 + 'px',
										'width': 0 + 'px',
										'height': 0 + 'px',
										'display' : 'none' } ) ;
				eDiv.innerHTML = '' ;
				this.isShow = false ;
			},
			show : function( iX, iY, iWidth, iHeight, oElement ) {
					if (!getOpened()) {
						document.body.appendChild( eDiv ) ;
						setOpened( true ) ;
					} ;
					this.htmlTxt = eDiv.innerHTML ;
					if (this.isShow) {
						this.hide() ;
					} ;
					eDiv.innerHTML = this.htmlTxt ;
					var coordinates = getCoordinates ( oElement ) ;
					eDiv.style.left = ( iX + coordinates.x) + 'px' ;
					eDiv.style.top = ( iY + coordinates.y) + 'px' ;
					eDiv.style.width = iWidth + 'px' ;
					eDiv.style.height = iHeight + 'px' ;
					eDiv.style.display = 'block' ;
					this.isShow = true ; 
			} 
		}
	}
	window.createPopup = function() {
		return __createPopup(); 
	}
}
/**//*  
 *    消息显示方法  
 */  
CLASS_MSN_MESSAGE.prototype.show = function(){
    var oPopup = window.createPopup(); //IE5.5+  
        var me  = this;  

    this.Pop = oPopup;  
  
    var w = this.width;  
    var h = this.height;
//    var div1 = document.createElement("div");
//    div1.style='BORDER-RIGHT: #455690 1px solid; BORDER-TOP: #a6b4cf 1px solid; Z-INDEX: 99999; LEFT: 0px; BORDER-LEFT: #a6b4cf 1px solid; WIDTH: " + w + "px; BORDER-BOTTOM: #455690 1px solid; POSITION: absolute; TOP: 0px; HEIGHT: " + h + "px; BACKGROUND-COLOR: #c9d3f3';
//    var table1 = document.createElement("table");
//    table1.style='BORDER-TOP: #ffffff 1px solid; BORDER-LEFT: #ffffff 1px solid';
    var str = "<DIV style='BORDER-RIGHT: #455690 1px solid; BORDER-TOP: #a6b4cf 1px solid; Z-INDEX: 99999; LEFT: 0px; BORDER-LEFT: #a6b4cf 1px solid; WIDTH: " + w + "px; BORDER-BOTTOM: #455690 1px solid; POSITION: absolute; TOP: 0px; HEIGHT: " + h + "px; BACKGROUND-COLOR: #c9d3f3'>"  
        str += "<TABLE style='BORDER-TOP: #ffffff 1px solid; BORDER-LEFT: #ffffff 1px solid' cellSpacing=0 cellPadding=0 width='100%' bgColor=#cfdef4 border=0>"  
        str += "<TR>"  
        str += "<TD style='FONT-SIZE: 12px;COLOR: #0f2c8c' width=30 height=24></TD>"  
        str += "<TD style='PADDING-LEFT: 4px; FONT-WEIGHT: normal; FONT-SIZE: 14px; COLOR: #1f336b; PADDING-TOP: 4px' vAlign=center width='100%;font-family:arial;'>" + this.caption + "</TD>"  
        str += "<TD style='PADDING-RIGHT: 2px; PADDING-TOP: 2px' vAlign=center align=right width=19>"  
        str += "<SPAN title='关闭' style='FONT-WEIGHT: bold; FONT-SIZE: 12px; CURSOR: pointer; COLOR: red; MARGIN-RIGHT: 4px' id='btSysClose'>×</SPAN></TD>"  
        str += "</TR>"  
        str += "<TR>"  
        str += "<TD style='PADDING-RIGHT: 1px;PADDING-BOTTOM: 1px' colSpan=3 height=" + (h-28) + ">"  
        str += "<DIV style='BORDER-RIGHT: #b9c9ef 1px solid; PADDING-RIGHT: 8px; BORDER-TOP: #728eb8 1px solid; PADDING-LEFT: 8px; FONT-SIZE: 14px; PADDING-BOTTOM: 8px; BORDER-LEFT: #728eb8 1px solid; WIDTH: 100%; COLOR: #1f336b; PADDING-TOP: 8px; BORDER-BOTTOM: #b9c9ef 1px solid; HEIGHT: 100%;font-family:arial;'>" + this.title + "<BR><BR>"  
        str += "<DIV style='overflow:hidden;word-wrap:break-word; height:25px;' align=left><A href='javascript:void(0)' hidefocus=true id='consulCommand'><FONT color=#ff0000;FONT-SIZE: 14px;font-family:arial;>" +this.consultation + "</FONT></A></DIV>" 
        str += "</DIV>"  
        str += "</TD>"  
        str += "</TR>"  
        str += "</TABLE>"  
        str += "</DIV>"  
  
    oPopup.document.body.innerHTML = str; 
    
  
    this.offset  = 0; 

    oPopup.document.body.onmouseover = function(){me.pause=true;}
    oPopup.document.body.onmouseout = function(){me.pause=false;}

    var fun = function(){  
        var x  = me.left; 
        var y  = 0; 
        var width    = me.width; 
        var height    = me.height; 
 
            if(me.offset>me.height){ 
                height = me.height; 
            } else { 
                height = me.offset; 
            } 
 
        y  = me.bottom - me.offset; 
        if(y<=me.top){ 
            me.timeout--; 
            if(me.timeout==0){ 
                window.clearInterval(me.timer);  
                if(me.autoHide){
                    me.hide(); 
                }
            } 
        } else { 
            me.offset = me.offset + me.step; 
        } 
        me.Pop.show(x,y,width,height);
        
       oPopup.document.getElementsByTagName("SPAN")[0].onclick = function(){ 
        	me.close = true;
        	me.hide();  
    	}  
  
  		oPopup.document.getElementsByTagName("A")[0].onclick = function(){  
        	me.consulcommand();  
   		 }
    }  
        this.timer = window.setInterval(fun,this.speed)      

}  
/**//* 
** 设置速度方法 
**/ 
CLASS_MSN_MESSAGE.prototype.speed = function(s){ 
    var t = 20; 
    try { 
        t = praseInt(s); 
    } catch(e){} 
    this.speed = t; 
} 
/**//* 
** 设置步长方法 
**/ 
CLASS_MSN_MESSAGE.prototype.step = function(s){ 
    var t = 1; 
    try { 
        t = praseInt(s); 
    } catch(e){} 
    this.step = t; 
} 
  
CLASS_MSN_MESSAGE.prototype.rect = function(left,right,top,bottom){ 
    try { 
        this.left        = left    !=null?left:this.right-this.width; 
        this.right        = right    !=null?right:this.left +this.width; 
        if(document.all){
        	this.bottom = bottom!=null?(bottom>screen.height?screen.height:bottom):screen.height;
        }else{
       		this.bottom        = bottom!=null?(bottom>document.documentElement.clientHeight?document.documentElement.clientHeight:bottom):document.documentElement.clientHeight; 
        }
        this.top        = top    !=null?top:(this.bottom - this.height); 
    } catch(e){} 
} 