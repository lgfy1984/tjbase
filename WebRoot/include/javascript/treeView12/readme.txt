主要修改：（由于是js请注意严格大小写）
◆为了在大量数据时节省文本大小，修改了接口：
原来： nodes["pnt_cur"]="text:结点文字;method:js函数()";
改为： N["pnt_cur"]="T:结点文字;C:js函数()";
其中pnt为父结点的ID，cur为当前结点ID

◆增加了复选框(CheckBox)，用法示例：
N["pnt_cur"] = "ctrl:sel;checked:1;T:结点文字;"
N["pnt_cur"] = "ctrl:sel;checked:0;T:结点文字;"
其中“ctrl:sel;”的冒号后面的是复选框的名称(name)属性
其中“checked:1;”的冒号后面的是0或1，表明复选框是否选中(1为选中,0为不选)
如果这两个参数均不出现，则不会有复选框显示

◆增加了外部方法：tree.Click(id) 用于虚拟鼠标单击某ID的结点所产生的动作，例如自动聚焦并执行相应的js函数

◆增加了两个用于自定义表格的公共变量： MzTreeViewTH , MzTreeViewTD 

可以加自定义的HTML表格到每一项，这是表头部分，在外部定义方法如下：
var MzTreeViewTH="<table class='MzTreeViewRow'><tr><td class='MzTreeViewCell0'>";

可以加自定义的HTML表格到每一项，这是单元格部分，在外部定义方法如下：
var MzTreeViewTD="\"</td><td class='MzTreeViewCell1'>\"+ sid +\"</td></tr></table>\"";
要与前面的表头对应，注意这个是用于eval的嵌套字符串格式，其中的sid为结点ID
（如果这两个变量没有出现则不会有自定义表格的效果，要出现就有两个一起出现）
