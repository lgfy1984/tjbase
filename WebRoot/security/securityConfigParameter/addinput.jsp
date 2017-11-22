<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html public "-//W3C//DTD HTML 4.0 Transitional//EN">    
<html>    
<head>    
<title> 表格操作 </title>    
<meta name="Generator" content="EditPlus">    
<meta name="Author" content="">    
<meta name="Keywords" content="">    
<meta name="Description" content="">    
</head>    
   
<body> 
<form align="center">   
   <input  type="button" name="baocun" value="保存" onclick="saveCells();">
  </form> 
</body>    
<script language="javascript">    
//添加表格    
function loadTable()    
{    
    var tb = document.createElement("table");    
    var rowTitle = tb.insertRow();    
    var cellTitle1= rowTitle.insertCell();    
    var cellTitle2= rowTitle.insertCell();    
    cellTitle2.colSpan = 2;    
    cellTitle2.align = "right";    
    cellTitle2.innerHTML = "<input type = 'button' value= '确定' onclick='addRow(this.parentElement)'>";    
    cellTitle1.innerHTML = "<span>添加</span>";    
    loadSelect(cellTitle1);    
   
      
    var rowObj = tb.insertRow();//添加行    
    var cell0 = rowObj.insertCell();//添加单元格    
    var cell1 = rowObj.insertCell();//添加第二个单元格    
    var cell2 = rowObj.insertCell();
    var cell3 = rowObj.insertCell();    
   
    cell0.innerHTML = "ID";    
    cell1.innerHTML = "NAME";    
    cell2.innerHTML = "AGE";
    cell3.innerHTML = "删除";    
        
       
    //设置属性    
    tb.id = "tb";    
    tb.align="center";    
    tb.cellPadding = 1;    
    tb.bgColor="#ffffee";    
    tb.style.borderWidth = "1px";    
    tb.style.borderCollapse = "collapse";    
    tb.style.borderStyle = "solid";    
    tb.rules = "ALL";//应用全部    
    tb.borderColor = "#000000";    
    document.body.appendChild(tb);    
} 
function saveCells(){
}   
//添加单元格    
function loadCells(rowObj) 
{    
    var cell0 = rowObj.insertCell();//添加单元格    
    var cell1 = rowObj.insertCell();//添加第二个单元格    
    var cell2 = rowObj.insertCell();
    var cell3 = rowObj.insertCell();    
   
    cell0.innerHTML = "<input type='text' name='id' value=''>";       
    cell1.innerHTML = "<input type='text' name='name' value=''>";    
    cell2.innerHTML = "<input type='text' name='age' value=''>";
    cell3.innerHTML = "<span style='cursor:hand;' onclick='delRow(this)'>&nbsp;×&nbsp;</span>";    
    
}    
//添加select框    
function loadSelect(obj)    
{    
    var s = document.createElement("select");    
    s.id = "sel_count";    
    s.name = s.id;    
    for(var i = 1; i <= 10; i++){    
        var opt = new Option(i+"行",i);    
        s.options.add(opt);    
    }    
    obj.appendChild(s);    
}    
//删除行    
function delRow(obj)    
{    
    var tr = obj.parentElement.parentElement;    
    var tb = tr.parentElement.parentElement;//table标签和tr标签隐式存在一个tbody    
    tb.deleteRow(tr.rowIndex);    
}    
//选择好的个数的行，传入单元格    
function addRow(obj)    
{    
    var tr = obj.parentElement;    
    var cnt = (tr.cells[0]).children[1].value;//个数    
    var tb = tr.parentElement.parentElement;    
   
    var lastRow = tb.rows[tb.rows.length - 1];//获得当前最后一行    
    var maxIndex= parseInt(lastRow.cells[0].innerText);//获得当前表格中显示的最好编号    
   
    for(var i = 0; i < cnt; i++){    
        var newRow = tb.insertRow();    
        loadCells(newRow,parseInt(maxIndex + i));    
    }    
        
}    
   
loadTable();    
</script>    
<html>   

