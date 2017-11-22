<!--
<!--
/**
 *
 * JSCalendar
 *
 * Author           :H.Z. Shang (Jack)
 * Email            :shhongzhang@cntomorrow.com
 * Site             :http://www.cntomorrow.com:3310
 * Version          :1.0.1
 * Finished Date    :2003-3-2
 * Beijing Huasun Mingtian Tech. Co., Ltd.
 * No CopyRight,Can be modified by you if you want improve it's function!!!!
 * LET'S MAKE IT BETTER TOGETHER!
 * HISTORY:
 *  1. [2003-3-4 by Jack] 采用IFRAME修正了会被页面SELECT对象挡住的BUG!
 *  2. [2003-3-4 by Jack] 增加了清空控件值的功能
 *  3. [2003-3-5 by Jack] 修正了当用户翻年、翻月时原输入框值被覆盖的情况
 *  4. [2003-3-5 by Jack] 通过使用DXImageTransform.Microsoft.Shadow来实现IFRAME的阴影效果
 *                        该滤镜只能在IE5.5下使用可加快程序运行速度，在IE5.0中还是使用原来的产生阴影的方法
 *  5. [2003-3-6 by Jack] 增加了另外一种生成该控件的方法：<script>createDateBox("Name", "2003-3-6")</script>
 *                        其中第二个参数为可选参数
 *  6. [2003-8-20 by GM ] 年使用 Select 方式选择，年范围(100年)：当前年前60年，后40年
 *                        月使用 Select 方式选择
 */
 
var _cldTabIFrame = null;
var _cldTabIFrame2 = null;

var _cldTabFrm = null;
var _cldMonthMenuFrm = null;
var _curCldTabRltvObj = null;
var _bCanHide = true;
var _bHaveUpdated = false;
var _bHaveShown = false;

var _bHaveSelectNewValue = false;

var _dCurYear = null;
var _dCurMonth = null;
var _dCurDate = null;

var _curCldTabRltvObjValue = "";
var _selectTimeObj=null;
var _isShowtime=false;

/**
  * 在该日历控件中，我采用了给空间周围加上阴影层来增强控件的视觉感。
  * _nShadowLength 变量设定了阴影层的长度，默认值为 4；
  * 使用阴影层将相应的滞缓程序的运行速度，如果你想得到较快的运行速度，请将该值设为 0
  */
var _nShadowLength = 4;

/**
  * _sNeededFilePath 变量设定了JSCalendar控件所需的所有文件的位置
  * 当你将你的应用部署到应用服务器上后，你需要给改变量设定相应的值。
  * 例如：
  *		_sNeededFilePath = "/MyWebAPP/comm/js/JSCalendar/";
  *
  * 该变量的值必须正确设置，否则程序在运行中可能出现错误！ 
  */
var _sNeededFilePath = "/teapot/script/JSCalendar/";

var _sBrowserVersion = navigator.appVersion.substring(navigator.appVersion.indexOf("MSIE") + 5,navigator.appVersion.indexOf("Windows") - 2);
var _bBrowserVer55	= _sBrowserVersion >= 5.5 ? true : false;

/**
  * 定义所需图片
  * 
  */
var _imgReset1 = new Image();
	_imgReset1.src = _sNeededFilePath + "reset1.gif";
var _imgReset2 = new Image();
	_imgReset2.src = _sNeededFilePath + "reset2.gif";
var _imgBtnYear = new Image();
	_imgBtnYear.src = _sNeededFilePath + "btnYears.gif";
var _imgBgToday1 = new Image();
	_imgBgToday1.src = _sNeededFilePath + "bgToday1.gif";

var _monthDays = new Array(12);
    _monthDays[ 0] = 31;
    _monthDays[ 1] = 28;
    _monthDays[ 2] = 31;
    _monthDays[ 3] = 30;
    _monthDays[ 4] = 31;
    _monthDays[ 5] = 30;
    _monthDays[ 6] = 31;
    _monthDays[ 7] = 31;
    _monthDays[ 8] = 30;
    _monthDays[ 9] = 31;
    _monthDays[10] = 30;
    _monthDays[11] = 31;
//var _weeks = new Array(7);    
//	_weeks[0] = "Sun.";
//	_weeks[1] = "Mon.";
//	_weeks[2] = "Tue.";
//	_weeks[3] = "Wed.";
//	_weeks[4] = "Thu.";
//	_weeks[5] = "Fri.";
//	_weeks[6] = "Sat.";

var _weeks = new Array(7);    
	_weeks[0] = "Sunday";
	_weeks[1] = "Monday";
	_weeks[2] = "Tuesday";
	_weeks[3] = "Wednesday";
	_weeks[4] = "Thursday";
	_weeks[5] = "Friday";
	_weeks[6] = "Saturday";
var _months = new Array(12);
	_months[ 0] = "1";
	_months[ 1] = "2";
	_months[ 2] = "3";
	_months[ 3] = "4";
	_months[ 4] = "5";
	_months[ 5] = "6";
	_months[ 6] = "7";
	_months[ 7] = "8";
	_months[ 8] = "9";
	_months[ 9] = "10";
	_months[10] = "11";
	_months[11] = "12";
	
var _dftD = new Date(); 
document.write("<IFRAME id=frm frameborder='0' name=frm style='display:none;'></IFRAME>");
//document.write("<IFRAME id=frm name=frm style='display:'></IFRAME>");

function JSCalendar(rltvO,year,month,date,isShowtime){

	_isShowtime=isShowtime;
	if(_bHaveUpdated && rltvO == _curCldTabRltvObj) return;
	if(rltvO.tagName != "INPUT"){alert("JSCalendar 1.0 alerts you:\n\n INPUT element(object) must be required!");return};
	
	hideCldTabFrm();
	
	rltvO.readOnly = true;
	rltvO.style.cursor = "default";
	rltvO.style.textAlign = "center";
	_curCldTabRltvObjValue = rltvO.value;
	_bHaveSelectNewValue = false;
	//------------------------------------------------------------------------------
	this.createCldTabFrm = createCldTabFrm;	
	this.fillCldTabFrm = fillCldTabFrm;
	this.placeCldTabFrm = placeCldTabFrm;
	//------------------------------------------------------------------------------
	
	this._rltvO = (rltvO == null ? document.body : rltvO);
	var _oldDate = rltvO.value;
	//alert(_oldDate)
	var _oldDateType = "From Input!";
	try{
		if(_oldDate == "") throw "";
		//var d = _oldDate.split("-");
		var _tmpDate = getValidateDate(_oldDate);
		//alert(_tmpDate);
		this._year  = _tmpDate.getFullYear();
		this._month = _tmpDate.getMonth();
		this._date  = _tmpDate.getDate();
		this._hours =_tmpDate.getHours();
		this._minutes =_tmpDate.getMinutes();
	}catch(e){
		_debug("Error was be catched:" + e.description);
		_oldDateType = "From default or current date!";
		this._year = ((year == null || year=="") ? _dftD.getFullYear() : year);
		this._month =((month == null || month=="") ? _dftD.getMonth() : month - 1);;
		this._date = ((date == null || month=="")? _dftD.getDate() : date);
		this._hours =_dftD.getHours();
		this._minutes =_dftD.getMinutes();
	}
	//alert(this._year + "-" + (this._month + 1) + "-" + this._date );
	_dCurYear = this._year;
	_dCurMonth = this._month;
	_dCurDate = this._date;	
	//_dCurHours = this._hours;
	//_dCurMinutes = this._minutes;
		

	if(_cldTabIFrame == null){
		this.createCldTabFrm();
	}
	//alert(_cldTabIFrame2)	
	
	_debug(_oldDateType + "[]" + this._year + "-" + (this._month + 1) + "-" + this._date);
	
	this.fillCldTabFrm(this._year, this._month, this._date,this._hours,this._minutes);	
	_cldTabIFrame2.style.display = "";	
	if(!_bHaveShown || rltvO != _curCldTabRltvObj) this.placeCldTabFrm();
	if(!_bBrowserVer55){
		removeShadowDiv();
		MakeDivShadowEffect(_cldTabFrm, '#aaaaaa', _nShadowLength);
	}	
	//alert("aaaa");
	//_cldTabIFrame2.style.display = "inline";
	_curCldTabRltvObj = this._rltvO;
	_bHaveUpdated = true;
	_bHaveShown = true;
	
}
function createCldTabFrm(){
	var _sz = "<HTML>"
			+ "<HEAD><link href='" +_sNeededFilePath + "JSCalendar.css' rel=stylesheet type='text/css'></HEAD>"
			+ "<BODY onselectstart='return false;' leftmargin=0 topmargin=0 rightmargin=0 bottommargin=0 style='cursor:default;background-color:transparent;border:0px solid black;scroll:no'>"
			+ "</BODY></HTML>";
	_cldTabIFrame = frm;//document.all("frm");//document.createElement("IFRAME");
	_cldTabIFrame2 = document.all("frm");	
	_cldTabIFrame2.style.position = "absolute";
	if(_bBrowserVer55){
		_cldTabIFrame2.style.filter = "progid:DXImageTransform.Microsoft.Shadow(direction=135,color=#aaaaaa,strength=" + _nShadowLength + ")";
	}
	_cldTabIFrame.document.open("text/html","replace");
	_cldTabIFrame.document.write(_sz);	
	_cldTabIFrame.document.close();
	_cldTabFrm = _cldTabIFrame.document.createElement("TABLE");
	_cldTabFrm.id = "JACKSHANGJIELOVEFEIFEI"
	_cldTabFrm.style.position = "absolute";
	_cldTabFrm.className = "calendar";
	_cldTabFrm.border = 0;
	_cldTabFrm.height = 165;
	_cldTabFrm.style.pixelWidth = 350;
	
	_cldTabFrm.cellSpacing =1;
	_cldTabFrm.cellPadding = 1;
	_cldTabFrm.bgColor = "#ffffff";
	_cldTabFrm.attachEvent("onmouseover", whenMouseOverCldTabFrm);
	_cldTabFrm.attachEvent("onmouseout", whenMouseOutCldTabFrm);
	// Create Calendar Control header
	var _TR = _cldTabFrm.insertRow();	
	var _TD = _TR.insertCell();
	_TD.colSpan = 7;
	_TD.align = "center";
	_TD.innerHTML = "&nbsp;";//"<b>JS Calendar 1.0 by Jack</b>";		
	// Create main block
	for(var i = 0; i < 7; i++){
		_TR = _cldTabFrm.insertRow();
		for(var j = 0; j < 7; j++){
			_TD = _TR.insertCell();
			_TD.style.cursor = "default";
			_TD.align = "center";
			_TD.width = 50;
			//_TD.style.border = "1px solid black";
			_TD.innerHTML = "*";
			if(i != 0){
				_TD.style.cursor = "hand";
				_TD.attachEvent("onmouseover", whenMouseOverDateItem);
				_TD.attachEvent("onmouseout", whenMouseOutDateItem);
				_TD.attachEvent("onclick", whenClickDateItem);
			}	
			if(i == 0) _TD.innerHTML = "<b>" + _weeks[j] + "</b>";
			if(i == 0 && (j == 0 || j == 6)) _TD.className = "tdHoliday";
			
		}		
	}
	// Create footer
	_TR = _cldTabFrm.insertRow(2);
	_TD = _TR.insertCell();
	_TD.colSpan = 7;
	_TD.height = 1;
	_TD.bgColor = "black";
	_TR = _cldTabFrm.insertRow();
	_TD = _TR.insertCell();
	_TD.colSpan = 7;
	_TD.innerHTML = "<table cellspacing=0 cellpadding=0 class=calendar style='border:0px solid;width:100%'>"
		+ "<tr><td title=   'Today's Date'style='cursor:hand'onclick=\"parent._bHaveSelectNewValue=true;parent.setTargetFormaValue(" + _dftD.getFullYear() + "," + (_dftD.getMonth() + 1) + "," + _dftD.getDate() + ");parent.hideCldTabFrm();return;parent.fillCldTabFrm(" + _dftD.getFullYear() + "," + _dftD.getMonth() + "," + _dftD.getDate() + ");\">"
		+ "<b>&nbsp;<img src='" + _imgBgToday1.src + "' width=30px>今天 : " + _dftD.getFullYear() + "-" + (_dftD.getMonth() + 1) + "-" + _dftD.getDate()
		+ "</td>"
		+ "<td width=30px>"
		+ "<img style='cursor:hand' onclick=parent.resetTargetValue() title='Clear Date' src='" + _imgReset1.src + "' onmouseover=this.src='" + _imgReset2.src + "' onmouseout=this.src='" + _imgReset1.src + "'></td>"
		+ "<td align=right class=smallFont></td></tr></table>";
	_cldTabIFrame.document.body.insertBefore(_cldTabFrm);
	//alert(_cldTabIFrame.document.body.innerHTML);
}

function fillCldTabFrm(year, month, date, hours, minutes){	
	var dCurDate = 0;
	var dNextMonthDate = 1;
	var iDateStartRow = 3;
	//var _d = new Date(this._year, this._month, 1);
	var _d = new Date(year, month, 1);
	var _day = _d.getDay();
	var _td = null;
	
	_dCurYear = year;
	_dCurMonth = month;
	_dCurDate = date;

	if (((_dCurYear % 4 == 0) && !(_dCurYear % 100 == 0))
		||(_dCurYear % 400 == 0)) _monthDays[1] = 29;
	else _monthDays[1] = 28;


	// 年列表 -------- GM
	//setTimeout("",5000);
	var _sYearList = "<select name='_JSCYear' onchange='parent.setYearValue(this.value)' style='width:55;height:16;font-size:9pt'>"
	var _targetYear = parseInt(_dCurYear) + 40
	    for( i=_dCurYear-60; i<_targetYear; i++){
	      if(i == _dCurYear)
	        _sYearList += "<option value="+ i +" selected>" + i + "</option>"
	      else
		    _sYearList += "<option value="+ i +">" + i + "</option>"
		}
		_sYearList += "</select>"
		
	var _sMonthList = "<select name='_JSCMonth' onchange='parent.setMonthValue(this.value)' style='width:55;height:16;font-size:9pt'>"
	    for( i=0; i<12; i++){
	      if(i == _dCurMonth){
	        _sMonthList += "<option value="+ i +" selected>"+ (i+1) +"</option>";
	      }else{
	        _sMonthList += "<option value="+ i +" >"+ (i+1) +"</option>";
	      }
	    }
	    _sMonthList += "</select>"
	  //alert(_sMonthList);
//_months[_dCurMonth]
	_sNowTime=new Date();
	_sHours=(hours==null||hours=="")?_sNowTime.getHours():hours;
	_sMinute=(minutes==null||minutes=="")?_sNowTime.getMinutes():minutes;	
	_sTimes="<table  border='0' cellpadding='0' cellspacing='0'>"
		+"<tr>"
		+"<td rowspan='2' ><input name='hours' type='text' style='width:24px;hight:20px' value='"+_sHours+"' onclick=parent.setobj(document.all['hours']) onBlur=parent.checkTime(document.all['hours'])></td>"
		+"<td rowspan='2' align='center' class='title'>时</td>"
		+"<td rowspan='2' ><input name='minute' type='text' style='width:24px;hight:20px' value='"+_sMinute+"' onclick=parent.setobj(document.all['minute']) onBlur=parent.checkTime(document.all['minute'])></td>"
		+"<td rowspan='2' align='center'  class='title'>分</td>"
		+"<td align='left' class='title' valign='bottom'><img src='"+_sNeededFilePath+"throw2.gif' style='cursor:hand' onclick=parent.changeTimes(1)></td>"
		+"</tr><tr>"
		+"<td align='left' valign='top'><img src='"+_sNeededFilePath+"throw1.gif' style='cursor:hand' onclick=parent.changeTimes(0) ></td>"
		+"</tr></table>";
	_cldTabFrm.rows(0).cells(0).innerHTML
	 = "<table class='title_bar' style=\"color:white;font-weight:bolder;border:0px solid;width:100%;height:20px;\" cellspacing=2 cellpadding=0>"
		+ "<tr><td>"
		+ "</td>"
		+ "<td align=center vAlign=middle>"
		+ "" + _sYearList + " - " + _sMonthList
		+ "</td>"
		+ "<td align=right>"
		+ _sTimes		
		+ "</td></tr></table>"
		//+ "<map name=mapForBtnYears><area title='上一年' onclick=parent.switchNextYear() shape=rect coords=0,0,10,5><area title='下一年' onclick=parent.switchLastYear() shape=rect coords=0,5,10,15></map>"
		//+ "&nbsp;<img border=0 align=absMiddle src='" + _imgBtnYear.src + "' style='height:12px;width:10px' usemap=#mapForBtnYears>&nbsp;"
		//+ "&nbsp;<a style='cursor:hand' title='上一月' onclick=\"parent.switchLastMonth()\"><<</a>"
		//+ "<a style='cursor:hand' title='下一月' onclick=\"parent.switchNextMonth()\">>></a>&nbsp"
		//alert(_cldTabFrm.rows(0).cells(0).innerHTML);
	//changeTimes();
	//alert("aaaaaaaaaaa")
			
	_day = (_day == 0 ? 7 : _day);
	
	for(var i = _day - 1, dlt = 0; i >= 0; i--){
		_td = _cldTabFrm.rows(iDateStartRow).cells(i);
		//_td.disabled = true;
		_td.className = "lastMonth";
		_td.title = "";
		_td.name = "LASTMONTH";
		_td.style.backgroundColor = "transparent";
		_td.style.border = "0px solid";
		var _nextMonth = _dCurMonth - 1;
		if(_nextMonth < 0) _nextMonth = 11;
		_td.innerText = (_monthDays[_nextMonth] - (dlt++));
	}
	
	i = _day;
	for(var d = 1, iRow = iDateStartRow; d <= _monthDays[_dCurMonth] || iRow < 9; ){
		for(; i < 7; i++){
			dCurDate = d++;
			_td = _cldTabFrm.rows(iRow).cells(i);
			_td.disabled = false;
			_td.className = "normal";
			_td.name = "CURRENTMONTH";
			_td.style.backgroundColor = "transparent";
			_td.style.border = "0px solid";
			if(i == 0 || i == 6) _td.className = "tdHoliday";
			//if(i == 0) _td.className = "tdHoliday";
			//if(i == 6) _td.className = "tdSat";
			
			if(d - 1 > _monthDays[_dCurMonth]){
				dCurDate = dNextMonthDate++;
				//_td.disabled = true;
				_td.className = "nextMonth";
				_td.name = "NEXTMONTH";
			}
			//if(dCurDate < 10) dCurDate = "0" + dCurDate;
			_td.innerHTML = dCurDate;

			if(dCurDate == _dCurDate && _td.name == "CURRENTMONTH"){
				//_cldTabFrm.rows(iRow).cells(i).style.backgroundColor = "620662";
				//_cldTabFrm.rows(iRow).cells(i).background = "bgCurDate.gif";
				_cldTabFrm.rows(iRow).cells(i).className = "tdCurDate";
			}	
			if(dCurDate == _dftD.getDate() 
				&& _dCurMonth == _dftD.getMonth() 
				&& _dCurYear == _dftD.getFullYear()
				&& _td.name == "CURRENTMONTH"){
				_cldTabFrm.rows(iRow).cells(i).className = "tdToday";
				if(dCurDate == _dCurDate)
					_cldTabFrm.rows(iRow).cells(i).className = "tdTodayCurDate";
			}	
			if(dCurDate == _dftD.getDate() && dCurDate == _dCurDate && _td.name == "CURRENTMONTH"){
				//_td.style.backgroundColor = "#663366";
				//_td.style.border = "1px solid gray";
				//_cldTabFrm.rows(iRow).cells(i).className = "tdTodayCurDate";
			}	
			
		}
		i = 0;
		iRow++;
		//alert(_cldTabFrm.rows.length);
	}
}
function placeCldTabFrm(){
	var _rect = this._rltvO.getBoundingClientRect();
	var _bodyWidth = document.body.clientWidth;
	var _bodyHeight = document.body.clientHeight;
	
	var _tmp = _cldTabIFrame;
	//alert(_bodyHeight);
	_cldTabIFrame = _cldTabIFrame2;

	var _innerTabFrmRect = _cldTabFrm.getBoundingClientRect();
	//_debug(_innerTabFrmRect.right + "," + _innerTabFrmRect.left)

	_cldTabIFrame2.style.pixelWidth = /*_innerTabFrmRect.right - _innerTabFrmRect.left*/  350 + (_bBrowserVer55 ? 0 : _nShadowLength);
	_cldTabIFrame2.style.pixelHeight = _innerTabFrmRect.bottom - _innerTabFrmRect.top + (_bBrowserVer55 ? 0 : _nShadowLength);	
	_cldTabIFrame2.style.pixelHeight = _innerTabFrmRect.bottom - _innerTabFrmRect.top + (_bBrowserVer55 ? 0 : _nShadowLength);	
	
	_cldTabIFrame.style.pixelLeft = _rect.left - 0 + document.body.scrollLeft;
	_cldTabIFrame.style.pixelTop = _rect.bottom - 0 + document.body.scrollTop;
	
	var _cldTabFrmRect = _cldTabIFrame.getBoundingClientRect();
		
	if(_cldTabFrmRect.right > _bodyWidth){
		_cldTabIFrame.style.pixelLeft -= (_cldTabFrmRect.right - _bodyWidth + _nShadowLength);
	}
	
	if(_cldTabFrmRect.bottom > _bodyHeight){
		//alert("dd");
		_cldTabIFrame.style.pixelTop = _rect.top - _nShadowLength;
		_cldTabIFrame.style.pixelTop -= (_cldTabFrmRect.bottom - _cldTabFrmRect.top - document.body.scrollTop);
	}
	
	//alert(_cldTabIFrame.style.pixelWidth);
	_cldTabIFrame = _tmp;
	//if(
}

function whenMouseOverCldTabFrm(){
	_bCanHide = false;
}

function whenMouseOutCldTabFrm(){
	_bCanHide = true;
}

function getNextDate(year, month, date){
	if(date == null) date = 1;
	if(date > _monthDays[month + 1]) date = _monthDays[month + 1];
	return new Date(year, month + 1, date);
}

function getLastDate(year, month, date){
	if(date == null) date = 1;
	if(date > _monthDays[month - 1]) date = _monthDays[month - 1];
	return new Date(year, month - 1, date);
}
function switchLastMonth(bLast){
	if(bLast == null) bLast = true;
	var _tmpdate = null;
	_tmpdate = bLast ? getLastDate(_dCurYear, _dCurMonth, _dCurDate) : getNextDate(_dCurYear, _dCurMonth, _dCurDate);
	//_curCldTabRltvObj.value = _tmpdate.getFullYear() + "-" + (_tmpdate.getMonth() + 1) + "-" + _tmpdate.getDate();
	setTargetFormaValue(_tmpdate.getFullYear(), _tmpdate.getMonth() + 1, _tmpdate.getDate());
	_bHaveUpdated = false;
	//JSCalendar(_curCldTabRltvObj);	
	fillCldTabFrm(_tmpdate.getFullYear(), _tmpdate.getMonth(), _tmpdate.getDate());;
}
function switchNextMonth(){
	switchLastMonth(false);
}
function switchLastYear(){
	//_curCldTabRltvObj.value = (_dCurYear * 1 + 1) + "-" + (_dCurMonth + 1) + "-" + _dCurDate;
	setTargetFormaValue(_dCurYear * 1 + 1, _dCurMonth * 1 + 1, _dCurDate);
	_bHaveUpdated = false;
	//JSCalendar(_curCldTabRltvObj);	
	fillCldTabFrm(_dCurYear * 1 + 1, _dCurMonth * 1, _dCurDate);
}
function switchNextYear(){
	//_curCldTabRltvObj.value = (_dCurYear - 1) + "-" + (_dCurMonth + 1) + "-" + _dCurDate;
	setTargetFormaValue(_dCurYear * 1 - 1, _dCurMonth * 1 + 1, _dCurDate);
	_bHaveUpdated = false;
	//JSCalendar(_curCldTabRltvObj);	
	fillCldTabFrm(_dCurYear * 1 - 1, _dCurMonth * 1, _dCurDate);
}
function whenMouseOverDateItem(){
	var e = _cldTabIFrame.event.srcElement;
	var _tmpdate = null;
	if(e.tagName == "TD"){
		if(e.name == "LASTMONTH"){
			_tmpdate = getLastDate(_dCurYear, _dCurMonth);
			e.title = "Last : " + _tmpdate.getFullYear() + "-" + (_tmpdate.getMonth() + 1) + "-" + e.innerText;
			return;
		}
		if(e.name == "NEXTMONTH"){
			_tmpdate = getNextDate(_dCurYear, _dCurMonth);
			e.title = "Next : " + _tmpdate.getFullYear() + "-" + (_tmpdate.getMonth() + 1) + "-" + e.innerText;
			return;
		}
	
		var sCurDate = _dCurYear + "-" + (_dCurMonth + 1) + "-" + e.innerText;
		e.title = "Current date: " + sCurDate;
		
		e.style.backgroundColor = "#EFEFEF";
		setTargetFormaValue(_dCurYear, _dCurMonth + 1, e.innerText);
	}	
}

function resetTargetValue(){
	_curCldTabRltvObj.value = "";
	_bHaveSelectNewValue = true;
	hideCldTabFrm();	
}

function setTargetFormaValue(year, month, date){
	var _year, _month, _date;
	_year = year;
	_month = month * 1;
	_date = date * 1;
	if(_month < 10) _month = "0" + _month;
	if(_date < 10) _date = "0" + _date;
	_hours=_cldTabIFrame.document.all["hours"].value;
	if(_hours<10) _hours="0"+_hours;
	_minute=_cldTabIFrame.document.all["minute"].value;
	if(_minute<10)_minute="0"+_minute;
	if(!_isShowtime){
		_curCldTabRltvObj.value = _year + "-" + _month + "-" + _date;
	}else{
		_curCldTabRltvObj.value = _year + "-" + _month + "-" + _date+" "+_hours+":"+_minute;
		
	}
	//_curCldTabRltvObj.value="how are you!";
}

function whenMouseOutDateItem(){
	var e = _cldTabIFrame.event.srcElement;
	if(e.tagName == "TD")
		e.style.backgroundColor = "";
}
function whenClickDateItem(){
	var e = _cldTabIFrame.event.srcElement;
	var _tmpdate = null;
	var _month = null;
	var _date = null;
	if(e.tagName == "TD"){
		_bHaveUpdated = false;
		if(e.name == "LASTMONTH"){
			_tmpdate = getLastDate(_dCurYear, _dCurMonth);
			setTargetFormaValue(_tmpdate.getFullYear(), _tmpdate.getMonth() + 1, e.innerText);
			fillCldTabFrm(_tmpdate.getFullYear(), _tmpdate.getMonth(), e.innerText);
			/* new */ //JSCalendar(_curCldTabRltvObj);
			return;
		}
		if(e.name == "NEXTMONTH"){
			_tmpdate = getNextDate(_dCurYear, _dCurMonth);
			setTargetFormaValue(_tmpdate.getFullYear(), _tmpdate.getMonth() + 1, e.innerText);
			/* new */ //JSCalendar(_curCldTabRltvObj);
			fillCldTabFrm(_tmpdate.getFullYear(), _tmpdate.getMonth(), e.innerText);
			return;
		}
		_bHaveSelectNewValue = true;
		hideCldTabFrm();
	}
}

function hideCldTabFrm(){
	//try{
	//alert(_curCldTabRltvObj);
	if(!_bHaveSelectNewValue)
		if(_cldTabIFrame == null || !_bCanHide) return;
	//alert("functionhidecldtabfrm");
	//alert(_cldTabIFrame);		
	var oFiredObj = null;
	try{
		oFiredObj = event.srcElement;
	}catch(e){
		oFiredObj = _cldTabIFrame.event.srcElement;
	}
	if(_curCldTabRltvObj!=null){
		if(oFiredObj == _curCldTabRltvObj) return;
	//alert(_curCldTabRltvObj);//
	
		_cldTabIFrame2.style.display = "none";
		_bHaveUpdated = false;
		_bHaveShown = false;
		removeShadowDiv();
	//alert(_bHaveSelectNewValue);
		if(!_bHaveSelectNewValue)
		_curCldTabRltvObj.value = _curCldTabRltvObjValue;
	//}catch(e){_debug(e.description);}
		_selectTimeObj=null;	
	}
}

function removeShadowDiv(){
	try{
		var arrShadowDiv = eval("window.document.arr" + _cldTabFrm.id);
		for(var i = 0; i < arrShadowDiv.length; i++)
			arrShadowDiv[i].removeNode(true);
	}catch(e){_debug(e.description);}	
}

function getValidateDate(sDate){
	//alert(sDate == null);
	if(sDate == null) return new Date();
	if(sDate.indexOf("-") == -1){
		if(isNaN(sDate)) return new Date();
		if(sDate * 1 > 9999 || sDate * 1 < 1000) return new Date();
		return new Date(sDate, 0, 1);
	}	
		
	var tmp = sDate.split("-");
	var btnH=tmp[2].indexOf(" ");
	var btnT=tmp[2].indexOf(":");
	//var time
	var time=new Array('','');
	if(btnH>0&&btnT>0){
		time=tmp[2].split(" ");
		tmp[2]=time[0];
		time=time[1].split(":")
		//alert("time="+time);
		}
	//alert(tmp[2]);
	if(isNaN(tmp[0]) || isNaN(tmp[1]) || isNaN(tmp[2])
		|| tmp[0] > 9999 || tmp[0] < 1000
		|| tmp[1] > 12 || tmp[1] < 1		
		|| tmp[2] > 31 || tmp[2] < 1 )
		return new Date();
	if(isNaN(time[0]) || isNaN(time[1])){
		time[0]=0;
		time[1]=0;
		}
	return new Date(tmp[0], tmp[1] - 1, tmp[2],time[0],time[1],0);
}

//var tst = getValidateDate("2000-7-310");
//alert(tst.getFullYear() + "-" + (tst.getMonth() + 1) + "-" + tst.getDate());

function createDateBox(sBoxName, sDfltValue,isShowTime){
	_isShowTime=isShowTime
	var _d = getValidateDate(sDfltValue);
	var _month = _d.getMonth() + 1;
	var _date = _d.getDate();
	if(_month * 1 < 10) _month = "0" + _month;
	if(_date * 1 < 10) _date = "0" + _date;
	sDfltValue = _d.getFullYear() + "-" + _month + "-" + _date;
	if(!_isShowTime){
		_str = "<INPUT READONLY STYLE='text-align:center;cursor:default;' NAME='" + sBoxName + "' VALUE='" + sDfltValue + "' ONCLICK=JSCalendar(this)>";
	}else{
		_str = "<INPUT READONLY STYLE='text-align:center;cursor:default;' NAME='" + sBoxName + "' VALUE='" + sDfltValue +" 00:00"+ "' ONCLICK=JSCalendar(this,'','','',true)>";
	}
	document.write(_str);
}

function MakeDivShadowEffect(divObj, color, nLength)
{
	//alert(divObj);
	var tmpstr = "window.document.arr" + divObj.id + " = new Array();";
	eval(tmpstr);
	//alert( tmpstr );
	var arrShadowDiv = eval("window.document.arr" + divObj.id);
	//window.document.arrJACKSHANGJIELOVEFEIFEI = new Array();
	//var arrShadowDiv = window.document.arrJACKSHANGJIELOVEFEIFEI;
	//alert(arrShadowDiv.length);
	var _rect = divObj.getBoundingClientRect();
	for( i = nLength; i > 0; i --)
	{
		var rect = _cldTabIFrame.document.createElement( "DIV" );
		rect.style.position = "absolute";
		rect.style.left = (divObj.style.posLeft + i ) + "px";
		rect.style.top = (divObj.style.posTop + i ) + "px";
		rect.style.width = divObj.offsetWidth + "px";
		
		rect.style.height = divObj.offsetHeight + "px";
		rect.style.backgroundColor = color;
		var opacity = 1 - i / (i + 1);
		//alert(rect.style.width);
		rect.style.filter = 'alpha(opacity=' + (100 * opacity) + ')';
		rect.style.zIndex = divObj.style.zIndex - 1;
		//alert(divObj.tagName);
		//divObj.insertAdjacentElement("beforeEnd", rect);
		_cldTabIFrame.document.body.insertBefore(rect);
		arrShadowDiv[arrShadowDiv.length] = rect;
		//alert( i );
	}	
}

function setYearValue(year){
	setTargetFormaValue(year, _dCurMonth * 1 + 1  , _dCurDate);
	fillCldTabFrm(year, _dCurMonth * 1 , _dCurDate);
}

function setMonthValue(month){
	setTargetFormaValue(_dCurYear, month * 1 + 1 , _dCurDate);
	fillCldTabFrm(_dCurYear, month * 1 , _dCurDate);
}

function _debug(s){
	return;
	//var o = new Option(s, s);
	//_debugLst.add(o);
	form1._debugLst.options[form1._debugLst.length] = new Option(s, s, 0, 0);
	form1._debugLst.options[form1._debugLst.length - 1].selected = true;
	//_debugLst.item(_debugLst.length - 1).selected = true;
}

function changeTimes(s){
	//tHour=new Object();
	//tMin=new Object();
	tHour=_cldTabIFrame.document.all["hours"];
	tMin=_cldTabIFrame.document.all["minute"];	
	//tValue=_selectTimeObj.value;
	//alert(_selectTimeObj.value);
	if(_selectTimeObj==null) {
		_selectTimeObj=tHour;		
	}
	if(s==1)_selectTimeObj.value++;
	if(s==0)_selectTimeObj.value--;	
		if((_selectTimeObj.value>59&&_selectTimeObj==tMin) ||( _selectTimeObj.value>23 &&_selectTimeObj==tHour) ){
			if(_selectTimeObj==tMin)tHour.value++;			
			_selectTimeObj.value=0;
		}else if(_selectTimeObj.value<0){
			if(_selectTimeObj==tMin){
				tHour.value--;
				_selectTimeObj.value=59;
				}
			if(_selectTimeObj==tHour){
				_selectTimeObj.value=23;
				}			
		}
	_selectTimeObj.focus();
	
}

function setobj(id){
	_selectTimeObj=id;
	}
function checkTime(obj){
	tValue=obj.value;
	//alert(tValue);
	tHour=_cldTabIFrame.document.all["hours"];
	tMin=_cldTabIFrame.document.all["minute"];
	if((tValue>59 || tValue<0 || tValue=="" || !isNum(tValue))&& obj== tMin  ){
			alert("The minute you enter the format does not, check");
			tMin.focus();
	}
	if((tValue>23 || tValue<0 || tValue=="" || !isNum(tValue))&& obj== tHour ){
			alert("Hour format that you entered does not, check")
			tHour.focus();
			}	
	}

function isNum(inputVal){	
  inputStr=inputVal.toString();
  for(var i=0;i<inputStr.length;i++) {
    var oneChar=inputStr.charAt(i);
//    if(i==0&&oneChar=="0")
//      return false;
    if(oneChar<"0"||oneChar>"9"){
      //alert("栏目‘"+strName+"’只能填写大于0的整数");
      return false;
    }  
  }
  return true;
}

document.onclick = hideCldTabFrm;
document.onload= createCldTabFrm();
//createCldTabFrm();
//-->