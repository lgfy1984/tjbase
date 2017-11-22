/*******************
公用的JavaScript类库
********************/

/*
提交表单的方法
参数：
FormName:Form的名称,默认为第一个form
Action:Form的Action路径
Op:From中Op的值
flag:表示是否充许选择多值ID
*/
function tjsubmit(formName,action,op,flag)
{
	if(formName==null)
		form=document.forms[0];
	else	
		form=document.forms(formName);
	if(flag==null)//flag为空的时候清除checkbox的状态
		new FormatForm(form).clear();
	if(flag!=null && typeof(flag)=="boolean")
	{
		var df=new FormatForm(form);
		var num=df.selectedCheckboxNumber();
		if(num<1)
		{
			alert("请先选择要操作的记录！");
			return false;
		}
		if(!flag && num>1)
		{
			alert("此操作只充许选择一条记录！");
			return false;
		}

		if(op=="del")
		{
			if(!window.confirm("确定你要删除所选的"+num+"条记录吗？"))
				return false;
		}

	}

	if(action!=null)
		form.action=action;
	if(op!=null)
		form.op.value=op;
	form.submit();
}

function selectAll(ob){
	ob.checked=true;
	new FormatForm(ob.form).selectAllCheckbox();
}
////////////////////////

/*
表单类，封装了一些对表单的操作
*/
function FormatForm(form)
{
	this.form=form;  //操做针对的表单

	this.$element=new Array();  //操作针对的元素的集合

	//遍历表单，找到全部某个类型的元素
	this.$findElement=function fun(obj,type)
	{
		if(obj!=null)
		{
			if(obj.type==type)
				{
					this.$element.push(obj);
				}
			var objs=obj.childNodes;
			if(objs!=null && objs.length>0)
			{
				for(var i=0;i<objs.length;i++)
				{
					this.$findElement(objs[i],type);
				}
			}
		}
	}

	//选择所有的单选框
	this.selectAllCheckbox=function fun()
	{
		var stat=0;  //统计操作过的数目
		this.$element=new Array();  //清空元素列表
		this.$findElement(this.form,"checkbox");  //找到所有的复选框
		for(var i=0;i<this.$element.length;i++)
		{
			if(this.$element[i].checked!=true)
			{
				this.$element[i].checked=true;
				stat++;
			}
		}

		//如果已经全选
		if(stat<1)
		{
			for(var i=0;i<this.$element.length;i++)
			{
				this.$element[i].checked=false;
			}
		}
	}

	//检查是否有复选框被选择
	this.checkAllCheckbox=function fun()
	{
		this.$element=new Array();
		this.$findElement(this.form,"checkbox");

		for(var i=0;i<this.$element.length;i++)
		{
			if(this.$element[i].checked==true)
				return true;
		}
		return false;
	}

	//返回被选择的idList复选框的数目
	this.selectedCheckboxNumber=function fun()
	{
		var returnNum=0;
		this.$element=new Array();
		this.$findElement(this.form,"checkbox");

		for(var i=0;i<this.$element.length;i++)
		{
			if(this.$element[i].checked==true && this.$element[i].name=="idList")
				returnNum++;
		}
		return returnNum;
	}
	
	//清除复选框的选择
	this.clear=function fun()
	{
		var stat=0;  //统计操作过的数目
		this.$element=new Array();  //清空元素列表
		this.$findElement(this.form,"checkbox");  //找到所有的复选框
		for(var i=0;i<this.$element.length;i++)
		{
			this.$element[i].checked=false;
		}
	}
}

/*
动态变化表单数目的类
已知问题：在FF上不可以上移，下移，因为FF不支持SwapNode方法
*/
function DynamicFormTable()
{
	this.formT;  //存放表单的表格
	this.demoT;  //存放表单示例的表格
	this.count=0;  //共有多少个纪录
	this.base=0;  //从第几个Tbody开始计算
	this.countInput;
    this.pre=0;

	this.init=function fun(formTableId,demoTableId,count,countId)
	{
		this.formT=document.getElementById(formTableId);  //初始化存放表单的Table
		this.demoT=document.getElementById(demoTableId);  //初始化存放表单示例的Table
		if(count!=null) this.count=count;

		if(this.formT!=null)
		{
			var nodes=this.formT.childNodes;
			var i=nodes.length-1;
			for(;i>-1;i--)
			{
				if(count<1) break;
				if(nodes[i].nodeName=="TBODY") count--;
			}
			this.base=i+1;
		}

		if(countId!=null)
		{
			this.countInput=document.getElementById(countId);
		}

		if(this.count>0) this.index();
	}
	

	/*
	在尾部增加一个记录
	*/
	this.append=function fun()
	{
		if(this._usable())
		{
			var tbody=this._getDemoTbody().cloneNode(true);
			this.formT.appendChild(tbody);
			this.count++;
			this.index();
		}
	}

	/*
	在当前记录前插入一个记录
	*/
	this.insert=function fun(obj)
	{
		if(this._usable())
		{
			var tbody=this._getDemoTbody().cloneNode(true);
			this.formT.insertBefore(tbody,this._ownTbody(obj));
			this.count++;
			this.index();
		}
	}

	/*
	删除一个Tbody，obj是Tbody中的一个元素
	*/
	this.remove=function fun(obj)
	{
		if(this._usable() && obj!=null)
		{
			this.formT.removeChild(this._ownTbody(obj));
			this.count--;
			this.index();
		}
	}
	
	/*  清除所有Tbody */
	this.clean=function fun()
	{
		if(this.formT!=null)
		{
			var nodes=this.formT.childNodes;
			var i=nodes.length-1;
			for(;i>0;i--)
			{
				if(nodes[i].nodeName=="TBODY")
				{
					this.formT.removeChild(nodes[i]);
				} 
			}
			
		}
		this.count=0;
	}

	/*
	上移
	*/
	this.moveUp=function fun(obj)
	{
		obj=this._ownTbody(obj);
		if(!this._isFirst(obj))
		{
			obj.swapNode(this._previousTbody(obj));
			this.index();
		}
	}

	/*
	下移
	*/
	this.moveDown=function fun(obj)
	{
		obj=this._ownTbody(obj);
		if(!this._isEnd(obj))
		{
			obj.swapNode(this._nextTbody(obj));
			this.index();
		}
	}

	/*
	找下一个Tbody
	*/
	this._nextTbody=function fun(obj)
	{
		var obj=obj.nextSibling;
		if(obj!=null)
		{
			if(obj.nodeName=="TBODY") return obj;
			else return this._nextTbody(obj);
		}
	}

	/*
	找上一个Tbody
	*/
	this._previousTbody=function fun(obj)
	{
		var obj=obj.previousSibling;
		if(obj!=null)
		{
			if(obj.nodeName=="TBODY") return obj;
			else return this._previousTbody(obj);
		}
	}

	/*
	检查Obj是否是最后一个存放表单的Tbody。
	*/
	this._isEnd=function fun(obj)
	{
		if(this.count<2) return true;
		else
		{
			var nodes=this.formT.childNodes;
			for(var i=nodes.length-1;i>-1;i--)
			{
				if(nodes[i].nodeName=="TBODY")
				{
					if(nodes[i]==obj) return true;
					else return false;
				}
			}
		}
	}

	/*
	检查Obj是否是第一个存放表单的Tbody。
	*/
	this._isFirst=function fun(obj)
	{
		if(this.count<2) return true;
		else
		{
			var nodes=this.formT.childNodes;
			for(var i=this.base;i<nodes.length;i++)
			{
				if(nodes[i].nodeName=="TBODY")
				{
					if(nodes[i]==obj) return true;
					else return false;
				}
			}
		}
	}

	/*
	重新把表中的Form进行排序
	*/
	this.index=function fun()
	{
		var nodes=this.formT.childNodes;
		var index=0;
		for(var i=this.base;i<nodes.length;i++)
		{
			if(nodes[i].nodeName=="TBODY")
			{
				this._indexChildNodes(nodes[i],index);
				index++;
			}
		}

		if(this.countInput!=null) this.countInput.value=this.count;
	}

	/*
	检查操作是否可用。
	*/
	this._usable=function fun()
	{
		if(this.demoT!=null && this.formT!=null)
		{
			return true;
		}
		else
		{
			alert('Please init this object [DynamicFormTable] at first ! ');
			return false;
		}
	}

	/*
	把所有子节点的Name都按Index编好号，用于递归。
	*/
	this._indexChildNodes=function fun(obj,index)
	{
		if(obj.hasChildNodes())
		{
			var nodes=obj.childNodes;
			for(var i=0;i<nodes.length;i++)
			{
				if(nodes[i].nodeType==1)
				{
					if(nodes[i].name!=null && nodes[i].name!="")
					{
						nodes[i].name=this._indexName(nodes[i].name,index);
					}
					this._indexChildNodes(nodes[i],index);
				}
			}
		}
	}

	/*
	给一个Name编号。
	(name的值,要编的号)
	*/
	this._indexName=function fun(name,index)
	{
		if(name.lastIndexOf("[")<0)
			return name+"["+index+"]";
		else
			return name.substr(0,name.lastIndexOf("["))+"["+index+name.substr(name.lastIndexOf("]"),name.length);
	}

	/*
	根据Tbody中的一个元素，取出这个Tbody对象
	*/
	this._ownTbody=function fun(obj)
	{
		while(obj.nodeName!="TBODY")
		{
			obj=obj.parentNode;
		}
		return obj;
	}

	/*
	取得Demo表中的Tbody对象
	*/
	this._getDemoTbody=function fun()
	{
		var nodes=this.demoT.childNodes;
		for(var i=0;i<nodes.length;i++)
		{
			if(nodes[i].nodeName=="TBODY") return nodes[i];
		}
		return null;
	}
}