function ocxwaibo(){
	var incomingTelegramNo=$("#pn").html();//来电号码赋值
	incomingTelegramNo=$.trim(incomingTelegramNo);
	getHospitalName();//得到医院名称
	findjbxx(incomingTelegramNo);
}
function youlaidian(){
	var incomingTelegramNo=$("#calledno").html();//来电号码赋值
	incomingTelegramNo=$.trim(incomingTelegramNo);
	getHospitalName();//得到医院名称
	findjbxx(incomingTelegramNo);//查询患者基本信息
}
function getHospitalName(){//查询医院名称,全局用
	var path=$("#zycPath").val();
	$.ajax({
		type:"post",
		async:false,//由于异步给全局变量赋值行不通，所以这里要用同步
		url:path+"/tab/TabDetailAction.do?verbId=getHospitalName",
		data:{},
		dataType: "json",
		success:function(data){
			window.hospitalName=data[0].hospitalName;//由于是给全局变量赋值，ajax异步加载无法赋值，需要改为同步执行async:false，这样就能给全局变量赋值了。
		},
		error:function(data){
			$.messager.alert('提示',"查询医院名称失败!","info");
		}
	});
}
	function findjbxx(incomingTelegramNo){
		var path=$("#zycPath").val();
		$.ajax({//基本信息查询
			type:"post",
			url:path+"/main/TabAction.do?verbId=jbxx",
			data:{incomingTelegramNo:incomingTelegramNo},
			dataType:"json",
			success:function(data){
				if(data.state=="yes"){
					$("#huanzhexingming").html(data.huanzhexingming);
					$("#xingbie").html(data.xingbie);
					$("#nianling").html(data.nianling);
					$("#guoji").html(data.guoji);
					$("#shenfenzhenghao").html(data.shenfenzhenghao);
					$("#hunyinzhuangtai").html(data.hunyinzhuangtai);
					$("#zhiye").html(data.zhiye);
					//$("#juminjiankangka").html(data.juminjiankangka);
					$("#jaoyuchengdu").html(data.jaoyuchengdu);
					//$("#zhiwei").html(data.zhiwei);
					$("#jiatingzhuzhi").html(data.jiatingzhuzhi);
					$("#youzhengbianma").html(data.youzhengbianma);
					$("#dianhua").html(data.dianhua);
					$("#securityUserBaseinfoId").val(data.securityUserBaseinfoId);
					var securityUserBaseinfoId=$("#securityUserBaseinfoId").val();
					var oldSecurityUserBaseinfoId=$("#oldSecurityUserBaseinfoId").val();
					if(oldSecurityUserBaseinfoId!=securityUserBaseinfoId){
						$("#checkSecurityUserBaseinfoId").val("yes");
						$("#oldSecurityUserBaseinfoId").val(securityUserBaseinfoId);
						$("#checkzjzd").val("yes");
						$("#checkzjss").val("yes");
						$("#checkzjyy").val("yes");
						$("#checkjc").val("yes");
						$("#checkjy").val("yes");
						$("#checkfy").val("yes");
						$("#checktj").val("yes");
					}else{
						$("#checkSecurityUserBaseinfoId").val("no");
					}
					if($("#f_button").hasClass("footer_hidden")){//判断tap页是否隐藏
						toggle_footer("fbody");
					}
				}else{
					$.messager.alert('提示',"没有与该电话关联的患者!","info");
				}
			},
			error:function(data){
				$.messager.alert('提示',"查询基本信息失败!","info");
			}
		});
	}
	function tabChange(id){
		var hideId=$("#tabId").val();
		if(id!=hideId){
			$("#"+hideId).removeClass("tool_tab_active");
			$("#"+id).addClass("tool_tab_active");
			$("#tabId").val(id);
		}
		var divId=id.substring(0,id.length-3);//去除id的后3为就是Div的id
		var hideDivId=$("#tabDivId").val();
		if(divId!=hideDivId){
			$("#"+hideDivId).addClass("tab_div_hide");
			$("#"+divId).removeClass("tab_div_hide");
			$("#tabDivId").val(divId);
		}
	}

	function zjzdGetData() {
		if ($("#checkSecurityUserBaseinfoId").val() == "yes" && $("#checkzjzd").val() == "yes") {
			var securityUserBaseinfoId=$("#securityUserBaseinfoId").val();
			var path = $("#zycPath").val();
			if (typeof (securityUserBaseinfoId) != "undefind") {
				$.ajax({
					type : "post",
					url : path + "/main/TabAction.do?verbId=zjzd",
					data : {securityUserBaseinfoId : securityUserBaseinfoId},
					dataType : "json",
					success : function(data) {
						if (data[0].state == "yes") {
							var map = data[0];
							for ( var key in map) {
								if (key == "row1") {
									var rowData = map[key];
									$("#authorityId1").val(rowData[0].authorityId);
									$("#pid1").val(rowData[0].pid);
									$("#clinicId1").val(rowData[0].clinicId);
									$("#zdsj1").html(rowData[0].zdsj);
									$("#zdmc1").html(rowData[0].zdmc);
									$("#jzks1").html(rowData[0].jzks);
									var lb = rowData[0].lb;
									if (lb == "m") {
										lb = "门诊";
									} else {
										lb = "住院";
									}
									$("#lb1").html(lb);
									$("#zjzdtr1").removeClass("table_tr_hide");
								}
								if (key == "row2") {
									var rowData = map[key];
									$("#authorityId2").val(rowData[0].authorityId);
									$("#pid2").val(rowData[0].pid);
									$("#clinicId2").val(rowData[0].clinicId);									
									$("#zdsj2").html(rowData[0].zdsj);
									$("#zdmc2").html(rowData[0].zdmc);
									$("#jzks2").html(rowData[0].jzks);
									var lb = rowData[0].lb;
									if (lb == "m") {
										lb = "门诊";
									} else {
										lb = "住院";
									}
									$("#lb2").html(lb);
									$("#zjzdtr2").removeClass("table_tr_hide");
								}
								if (key == "row3") {
									var rowData = map[key];
									$("#authorityId3").val(rowData[0].authorityId);
									$("#pid3").val(rowData[0].pid);
									$("#clinicId3").val(rowData[0].clinicId);									
									$("#zdsj3").html(rowData[0].zdsj);
									$("#zdmc3").html(rowData[0].zdmc);
									$("#jzks3").html(rowData[0].jzks);
									var lb = rowData[0].lb;
									if (lb == "m") {
										lb = "门诊";
									} else {
										lb = "住院";
									}
									$("#lb3").html(lb);
									$("#zjzdtr3").removeClass("table_tr_hide");
								}
							}
						} else {
							$.messager.alert('提示',"该患者没有就诊记录!","info");
						}
					},
					error : function(data) {
						$.messager.alert('提示',"查询最近诊断记录失败!","info");
					}
				});
			}
			$("#checkzjzd").val("no");
		}
	}
	function zjzdDetail(num){
		var path = $("#zycPath").val();
		var authorityId=$("#authorityId"+num).val()
		var patientId=$("#pid"+num).val();
		var clinicId=$("#clinicId"+num).val();
		var clinicType=$("#lb"+num).html();
		if(clinicType=="门诊"){
			$.ajax({
				type:"post",
				url:path+"/tab/TabDetailAction.do?verbId=getOutpClinic",
				data:{authorityId:authorityId,patientId:patientId,clinicId:clinicId},
				dataType:"json",
				success:function(data){
					if(data[0].state=="yes"){
						$("#hsopital_zjzdmzxx").html(hospitalName);
						$("#mzjzh").html(data[0].jzh);
						$("#mzjzsj").html(data[0].jzsj);
						$("#mzjzks").html(data[0].jzks);
						$("#mzzdms").html(data[0].zdms);
						$("#zjzdmzxx").window("open");
					}else{
						$.messager.alert('提示',"查询详细信息失败!","info");
					}
				},
				error:function(data){
					$.messager.alert('提示',"查询详细信息错误!","info");
				}
			});
		}
		if(clinicType=="住院"){
			$.ajax({
				type:"post",
				url:path+"/tab/TabDetailAction.do?verbId=getInpClinic",
				data:{authorityId:authorityId,patientId:patientId,clinicId:clinicId},
				dataType:"json",
				success:function(data){
					if(data[0].state=="yes"){
						$("#hsopital_zjzdzyxx").html(hospitalName);
						$("#zyjzh").html(data[0].jzh);
						$("#zyjzsj").html(data[0].jzsj);
						$("#zycysj").html(data[0].cysj);
						$("#zyjzks").html(data[0].jzks);
						$("#zyzdms").html(data[0].zdms);
						$("#zjzdzyxx").window("open");
					}else{
						$.messager.alert('提示',"查询详细信息失败!","info");
					}					
				},
				error:function(data){
					$.messager.alert('提示',"查询详细信息错误!","info");
				}
			});			
		}		
		
	}
	function zjzdLieBiao(){
		var securityUserBaseinfoId=$("#securityUserBaseinfoId").val();
		openOtherWindow("19850014","诊断列表","hsp/patientClinicRecord.do?verbId=electronicArchives&securityUserBaseinfoId="+securityUserBaseinfoId);
	}
	function zjssGetData() {
		if ($("#checkSecurityUserBaseinfoId").val() == "yes" && $("#checkzjss").val() == "yes") {
			var securityUserBaseinfoId = $("#securityUserBaseinfoId").val();
			var path = $("#zycPath").val();
			if (typeof (securityUserBaseinfoId) != "undefind") {
				$.ajax({
					type : "post",
					url : path + "/main/TabAction.do?verbId=zjss",
					data : {securityUserBaseinfoId : securityUserBaseinfoId},
					dataType : "json",
					success : function(data) {
						if (data[0].state == "yes") {
							var map = data[0];
							for ( var key in map) {
								if (key == "row1") {
									var rowData = map[key];
									$("#sssj1").html(rowData[0].sssj);
									$("#ssmc1").html(rowData[0].ssmc);
									$("#ssks1").html(rowData[0].ssks);
									$("#zjsstr1").removeClass("table_tr_hide");
								}
								if (key == "row2") {
									var rowData = map[key];
									$("#sssj2").html(rowData[0].sssj);
									$("#ssmc2").html(rowData[0].ssmc);
									$("#ssks2").html(rowData[0].ssks);
									$("#zjsstr2").removeClass("table_tr_hide");
								}
								if (key == "row3") {
									var rowData = map[key];
									$("#sssj3").html(rowData[0].sssj);
									$("#ssmc3").html(rowData[0].ssmc);
									$("#ssks3").html(rowData[0].ssks);
									$("#zjsstr3").removeClass("table_tr_hide");
								}
							}
						} else {
							$.messager.alert('提示',"该患者没有手术记录!","info");
						}
					},
					error : function(data) {
						$.messager.alert('提示',"查询最近手术记录失败!","info");
					}
				});
			}
			$("#checkzjss").val("no");
		}
	}
	function zjssLieBiao(){
		var securityUserBaseinfoId=$("#securityUserBaseinfoId").val();
		openOtherWindow("19850034","手术列表","hsp/patientClinicRecord.do?verbId=electronicArchives&securityUserBaseinfoId="+securityUserBaseinfoId);
	}
	function zjyyGetData() {
		if ($("#checkSecurityUserBaseinfoId").val() == "yes" && $("#checkzjyy").val() == "yes") {
			var securityUserBaseinfoId = $("#securityUserBaseinfoId").val();
			var path = $("#zycPath").val();
			if (typeof (securityUserBaseinfoId) != "undefind") {
				$.ajax({
					type : "post",
					url : path + "/main/TabAction.do?verbId=zjyy",
					data : {securityUserBaseinfoId : securityUserBaseinfoId},
					dataType : "json",
					success : function(data) {
						if (data[0].state == "yes") {
							var map = data[0];
							for ( var key in map) {
								if (key == "row1") {
									var rowData = map[key];
									var yptype1=rowData[0].yptype;
									if(yptype1=="0"){
										$("#kysj1t").html("开药时间");
										$("#ypmc1t").html("药品名称(西药)");
										$("#gg1t").html("规格");
										$("#yf1t").html("用法");
										$("#yl1t").html("用量");
										$("#sl1t").html("数量");
										$("#zjyytr1a").removeClass("table_tr_hide");
									}else if(yptype1=="1"){
										$("#kysj1t").html("开药时间");
										$("#ypmc1t").html("药品名称(中草药)");
										$("#gg1t").html("剂数");
										$("#yf1t").html("用药途径");
										$("#yl1t").html("用药方法");
										$("#sl1t").html("数量");
										$("#zjyytr1a").removeClass("table_tr_hide");
									}else{
										$("#kysj1t").html("开药时间");
										$("#ypmc1t").html("药品名称(中成药)");
										$("#gg1t").html("剂数");
										$("#yf1t").html("用药途径");
										$("#yl1t").html("用药方法");
										$("#sl1t").html("数量");
										$("#zjyytr1a").removeClass("table_tr_hide");
									}
									$("#kysj1").html(rowData[0].kysj);
									$("#ypmc1").html(rowData[0].ypmc);
									$("#gg1").html(rowData[0].gg);
									$("#yf1").html(rowData[0].yf);
									$("#yl1").html(rowData[0].yl);
									$("#sl1").html(rowData[0].sl);
									$("#yptype1").val(yptype1);
									$("#zjyyauthorityId1").val(rowData[0].authorityId);
									$("#zjyypid1").val(rowData[0].pid);
									$("#zjyyprescriptionId1").val(rowData[0].prescriptionId);
									$("#zjyyclinicId1").val(rowData[0].clinicId);
									$("#zjyytr1b").removeClass("table_tr_hide");
								}
								if (key == "row2") {
									var rowData = map[key];
									var yptype1=$("#yptype1").val();
									
									var kysj2=rowData[0].kysj;
									var ypmc2=rowData[0].ypmc;
									var gg2=rowData[0].gg;
									var yf2=rowData[0].yf;
									var yl2=rowData[0].yl;
									var sl2=rowData[0].sl;
									var yptype2=rowData[0].yptype;
									var zjyyauthorityId2=rowData[0].authorityId;
									var zjyypid2=rowData[0].pid;
									var zjyypid2=rowData[0].pid;
									var zjyyprescriptionId2=rowData[0].prescriptionId;
									var zjyyclinicId2=rowData[0].clinicId;
									
									var str2b="<tr id=\"zjyytr2b\">" +
									"<td>" +
									"<input id=\"yptype2\" type=\"hidden\" value=\""+yptype2+"\"/>" +
									"<input id=\"zjyyauthorityId2\" type=\"hidden\" value=\""+zjyyauthorityId2+"\"/>" +
									"<input id=\"zjyypid2\" type=\"hidden\" value=\""+zjyypid2+"\"/>" +
									"<input id=\"zjyyprescriptionId2\" type=\"hidden\" value=\""+zjyyprescriptionId2+"\"/>" +
									"<input id=\"zjyyclinicId2\" type=\"hidden\" value=\""+zjyyclinicId2+"\"/>" +
									"<span id=\"kysj2\" class=\"blue_font_tab\" onmouseover=\"addUnderLine(this);\" onmouseout=\"removeUnderLine(this);\" onclick=\"zjyyDetail('2');\">"+kysj2+"</span>" +
									"</td>" +
									"<td id=\"ypmc2\">"+ypmc2+"</td>" +
									"<td id=\"gg2\">"+gg2+"</td>" +
									"<td id=\"yf2\">"+yf2+"</td>" +
									"<td id=\"yl2\">"+yl2+"</td>" +
									"<td id=\"sl2\">"+sl2+"</td>" +
									"</tr>";
									if(yptype2==yptype1){
										$("#zjyytr1b").after(str2b);
									}else if(yptype2=="0"&&yptype2!=yptype1){
										var str2a="<tr id=\"zjyytr2a\">" +
												"<td>开药时间</td>" +
												"<td>药品名称(西药)</td>" +
												"<td>规格</td>" +
												"<td>用法</td>" +
												"<td>用量</td>" +
												"<td>数量</td>" +
												"</tr>";
										$("#zjyytr1b").after(str2a);
										$("#zjyytr2a").after(str2b);
									}else if(yptype2=="1"&&yptype2!=yptype1){
										var str2a="<tr id=\"zjyytr2a\">" +
												"<td>开药时间</td>" +
												"<td>药品名称(中草药)</td>" +
												"<td>剂数</td>" +
												"<td>用药途径</td>" +
												"<td>用药方法</td>" +
												"<td>数量</td>" +
												"</tr>";
										$("#zjyytr1b").after(str2a);
										$("#zjyytr2a").after(str2b);
									}else if(yptype2=="2"&&yptype2!=yptype1){
										var str2a="<tr id=\"zjyytr2a\">" +
												"<td>开药时间</td>" +
												"<td>药品名称(中草药)</td>" +
												"<td>剂数</td>" +
												"<td>用药途径</td>" +
												"<td>用药方法</td>" +
												"<td>数量</td>" +
												"</tr>";
										$("#zjyytr1b").after(str2a);
										$("#zjyytr2a").after(str2b);
									}
								}
								if (key == "row3") {
									var rowData = map[key];
									var yptype1=$("#yptype1").val();
									var yptype2=$("#yptype2").val();

									var kysj3=rowData[0].kysj;
									var ypmc3=rowData[0].ypmc;
									var gg3=rowData[0].gg;
									var yf3=rowData[0].yf;
									var yl3=rowData[0].yl;
									var sl3=rowData[0].sl;
									var yptype3=rowData[0].yptype;
									var zjyyauthorityId3=rowData[0].authorityId;
									var zjyypid3=rowData[0].pid;
									var zjyypid3=rowData[0].pid;
									var zjyyprescriptionId3=rowData[0].prescriptionId;
									var zjyyclinicId3=rowData[0].clinicId;
									
									var str3b="<tr id=\"zjyytr3b\">" +
									"<td>" +
									"<input id=\"yptype3\" type=\"hidden\" value=\""+yptype3+"\"/>" +
									"<input id=\"zjyyauthorityId3\" type=\"hidden\" value=\""+zjyyauthorityId3+"\"/>" +
									"<input id=\"zjyypid3\" type=\"hidden\" value=\""+zjyypid3+"\"/>" +
									"<input id=\"zjyyprescriptionId3\" type=\"hidden\" value=\""+zjyyprescriptionId3+"\"/>" +
									"<input id=\"zjyyclinicId3\" type=\"hidden\" value=\""+zjyyclinicId3+"\"/>" +
									"<span id=\"kysj3\" class=\"blue_font_tab\" onmouseover=\"addUnderLine(this);\" onmouseout=\"removeUnderLine(this);\" onclick=\"zjyyDetail('3');\">"+kysj3+"</span>" +
									"</td>" +
									"<td id=\"ypmc3\">"+ypmc3+"</td>" +
									"<td id=\"gg3\">"+gg3+"</td>" +
									"<td id=\"yf3\">"+yf3+"</td>" +
									"<td id=\"yl3\">"+yl3+"</td>" +
									"<td id=\"sl3\">"+sl3+"</td>" +
									"</tr>";
									if(yptype3==yptype1&&yptype3==yptype2){
										$("#zjyytr2b").after(str3b);
									}
									if(yptype3==yptype1&&yptype3!=yptype2){
										$("#zjyytr1b").after(str3b);
									}
									if(yptype3!=yptype1&&yptype3==yptype2){
										$("#zjyytr2b").after(str3b);
									}
									if(yptype3=="0"&&yptype3!=yptype1&&yptype3!=yptype2){
										var str3a="<tr id=\"zjyytr3a\">" +
												"<td>开药时间</td>" +
												"<td>药品名称(西药)</td>" +
												"<td>规格</td>" +
												"<td>用法</td>" +
												"<td>用量</td>" +
												"<td>数量</td>" +
												"</tr>";
										$("#zjyytr2b").after(str3a);
										$("#zjyytr3a").after(str3b);
									}else if(yptype3=="1"&&yptype3!=yptype1&&yptype3!=yptype2){
										var str3a="<tr id=\"zjyytr3a\">" +
												"<td>开药时间</td>" +
												"<td>药品名称(中草药)</td>" +
												"<td>剂数</td>" +
												"<td>用药途径</td>" +
												"<td>用药方法</td>" +
												"<td>数量</td>" +
												"</tr>";
										$("#zjyytr2b").after(str3a);
										$("#zjyytr3a").after(str3b);
									}else if(yptype3=="2"&&yptype3!=yptype1&&yptype3!=yptype2){
										var str3a="<tr id=\"zjyytr3a\">" +
												"<td>开药时间</td>" +
												"<td>药品名称(中草药)</td>" +
												"<td>剂数</td>" +
												"<td>用药途径</td>" +
												"<td>用药方法</td>" +
												"<td>数量</td>" +
												"</tr>";
										$("#zjyytr2b").after(str3a);
										$("#zjyytr3a").after(str3b);
									}
								}
							}
						} else {
							$.messager.alert('提示',"该患者没有用药记录!","info");
						}
						var aaa=$("#zjyy").html();
					},
					error : function(data) {
						$.messager.alert('提示',"查询最近用药记录失败!","info");
					}
				});
			}
			$("#checkzjyy").val("no");
		}
	}
	function zjyyDetail(num){
		var path = $("#zycPath").val();
		var authorityId=$("#zjyyauthorityId"+num).val()
		var patientId=$("#zjyypid"+num).val();
		var clinicId=$("#zjyyclinicId"+num).val();
		var prescriptionId=$("#zjyyprescriptionId"+num).val();
		$.ajax({
			type:"post",
			url:path+"/tab/TabDetailAction.do?verbId=getOutpPresc",
				data:{authorityId:authorityId,patientId:patientId,clinicId:clinicId,prescriptionId:prescriptionId},
				dataType:"json",
				success:function(data){
					if(data){
						$("#hospital_zjyyxx").html(data[0].hospitalName);
						$("#cfyljg").html(data[0].hospitalName);
						$("#cfsj").html(getSmpFormatDateByLong(data[0].prescriptionDate.time,false));
						$("#cfbh").html(data[0].prescriptionId);
						$("#cfkb").html(data[0].prescriptionDept);
						$("#cffb").html(data[0].feeType);
						$("#cfxm").html(data[0].patientName);
						$("#cfxb").html(data[0].sexName);
						$("#cfnl").html(data[0].age+"岁");
						$("#cfblh").html(data[0].outpClinicNo);
						$("#cfjbzd").html(data[0].clinicDescprition);
						var prescDetails=data[0].prescDetails;
						if(prescDetails!=null&&prescDetails.length>0){
							var trs="";
							if(data[0].prescriptionType=="0"){//是西药
								for(var i=0;i<prescDetails.length;i++){
									trs +=
										"<tr>" +
										"<td>" +
										"<span class=\"lab_item_name\" onclick=\"toDrugDetail(this)\">"+prescDetails[i].drugName+"</span>" +
										"</td>" +
										"<td>"+prescDetails[i].drugSpecification+"*"+prescDetails[i].totalDosage+"</td>" +
										"<td>"+prescDetails[i].drugUseMeansName+"</td>" +
										"<td>"+prescDetails[i].singleDose+prescDetails[i].dosageUnit+"</td>" +
										"<td>"+prescDetails[i].drugUsePrequency+"</td>" +
										"</tr>";
								}
								//$("#cfxxbq").remove();
								//var str1a="<span id=\"cffysl\">服用天数："+data[0].useTimes+" 天</span>";
								var str2a="<span id=\"cfysqz\" style=\"margin-left: 0px;\">医生签章："+data[0].prescriptionDoctor+"</span>";
								$("#cfxxbq").html(str2a);
								//$("#cffysl").html("服用天数："+data[0].useTimes+" 天");
								//$("#cfysqz").html("医生签章："+data[0].prescriptionDoctor);
							}else{//中草药或者中成药
								for(var i=0;i<prescDetails.length;i+=2){
									trs +="<tr>" +
										"<td>" +
										"<span class=\"lab_item_name\" onclick=\"toChineseMed(this)\">"+prescDetails[i].drugName+"</span>" +
										"</td>" +
										"<td>"+prescDetails[i].singleDose+prescDetails[i].dosageUnit+"</td>" ;
									if(i<prescDetails.length){
										trs +="<td>" +
											"<span class=\"lab_item_name\" onclick=\"toChineseMed(this)\">"+prescDetails[i+1].drugName+"</span>" +
											"</td>" +
											"<td>"+prescDetails[i+1].singleDose+prescDetails[i+1].dosageUnit+"</td>";
									}else{
										trs +="<td></td><td></td>";
									}
									trs +="</tr>";
								}
								//$("#cfxxbq").remove();
								var str1b="<span id=\"zyyf\" style=\"margin-left: 0px;\">用法："+prescDetails[0].drugUseMeansName+"</span>";
								var str2b="<span id=\"cffysl\">服用剂数："+data[0].useTimes+" 剂</span>";
								var str3b="<span id=\"cfysqz\">医生签章："+data[0].prescriptionDoctor+"</span>";
								$("#cfxxbq").html(str1b+str2b+str3b);
								//$("#cffysl").html("服用剂数："+data[0].useTimes+" 剂");
								//$("#cfysqz").html("医生签章："+data[0].prescriptionDoctor);
							}
							$("#cfxx").html(trs);
						}
						$("#cfsm").html("过敏试验："+data[0].allergicTest);
						
						$("#zjyyxx").window("open");
					}else{
						$.messager.alert('提示',"查询详细信息失败!","info");
					}
				},
				error:function(data){
					$.messager.alert('提示',"查询详细信息错误!","info");
				}
			});
	}
	function zjyyLieBiao(){
		var securityUserBaseinfoId=$("#securityUserBaseinfoId").val();
		openOtherWindow("19850044","药品列表","hsp/patientClinicRecord.do?verbId=electronicArchives&securityUserBaseinfoId="+securityUserBaseinfoId);
	}
	function jcGetData() {
		if ($("#checkSecurityUserBaseinfoId").val() == "yes" && $("#checkjc").val() == "yes") {
			var securityUserBaseinfoId = $("#securityUserBaseinfoId").val();
			var path = $("#zycPath").val();
			if (typeof (securityUserBaseinfoId) != "undefind") {
				$.ajax({
					type : "post",
					url : path + "/main/TabAction.do?verbId=jiancha",
					data : {securityUserBaseinfoId: securityUserBaseinfoId},
					dataType : "json",
					success : function(data) {
						if (data[0].state == "yes") {
							var map = data[0];
							for ( var key in map) {
								if (key == "row1") {
									var rowData = map[key];
									$("#jcsj1").html(rowData[0].jcsj);
									$("#jcks1").html(rowData[0].jcks);
									$("#jcxm1").html(rowData[0].jcxm);
									$("#jcexaminationId1").val(rowData[0].examinationId);
									$("#jcauthorityId1").val(rowData[0].authorityId);
									$("#jcpid1").val(rowData[0].pid);
									$("#jcclinicId1").val(rowData[0].clinicId);
									$("#jcclinicType1").val(rowData[0].clinicType);
									$("#jctr1").removeClass("table_tr_hide");
								}
								if (key == "row2") {
									var rowData = map[key];
									$("#jcsj2").html(rowData[0].jcsj);
									$("#jcks2").html(rowData[0].jcks);
									$("#jcxm2").html(rowData[0].jcxm);
									$("#jcexaminationId2").val(rowData[0].examinationId);
									$("#jcauthorityId2").val(rowData[0].authorityId);
									$("#jcpid2").val(rowData[0].pid);
									$("#jcclinicId2").val(rowData[0].clinicId);
									$("#jcclinicType2").val(rowData[0].clinicType);
									$("#jctr2").removeClass("table_tr_hide");
								}
								if (key == "row3") {
									var rowData = map[key];
									$("#jcsj3").html(rowData[0].jcsj);
									$("#jcks3").html(rowData[0].jcks);
									$("#jcxm3").html(rowData[0].jcxm);
									$("#jcexaminationId3").val(rowData[0].examinationId);
									$("#jcauthorityId3").val(rowData[0].authorityId);
									$("#jcpid3").val(rowData[0].pid);
									$("#jcclinicId3").val(rowData[0].clinicId);
									$("#jcclinicType3").val(rowData[0].clinicType);
									$("#jctr3").removeClass("table_tr_hide");
								}
							}
						} else {
							$.messager.alert('提示',"该患者没有检查记录!","info");
						}
					},
					error : function(data) {
						$.messager.alert('提示',"查询检查记录失败!","info");
					}
				});
			}
			$("#checkjc").val("no");
		}
	}
	function jcDetail(num){
		var path = $("#zycPath").val();
		var authorityId=$("#jcauthorityId"+num).val();
		var patientId=$("#jcpid"+num).val();
		var clinicId=$("#jcclinicId"+num).val();
		var clinicType=$("#jcclinicType"+num).val();
		var examinationId=$("#jcexaminationId"+num).val();
		$.ajax({
			type : "post",
			url : path + "/tab/TabDetailAction.do?verbId=getOutpExam",
			data : {authorityId : authorityId,patientId:patientId,clinicId:clinicId,clinicType:clinicType,examinationId:examinationId},
			dataType : "json",
			success : function(data) {
				if (data) {
					$("#jcjcyy").html(data[0].hospitalName);
					$("#jcjclb").html(data[0].examinationTypeName+"检查报告单");
					$("#jcjch").html(data[0].examinationItemCode);
					$("#jcjcsj").html(getSmpFormatDateByLong(data[0].examinationDate.time,false));
					$("#jcxm").html(data[0].patientName);
					$("#jcxb").html(data[0].sexName);
					$("#jcnl").html(data[0].age);
					$("#jcmzh").html(data[0].outpClinicNo);
					$("#jckb").html(data[0].dept);
					$("#jcsqys").html(data[0].applicationDoctorName);
					$("#jcjcbw").html(data[0].checkParks);
					$("#jcjccs").html(" ");
					$("#jcjianchasuojian").html(data[0].examinationResult);
					$("#zjjcxx").window("open");
				} else {
					$.messager.alert('提示',"该患者没有检查记录!","info");
				}
			},
			error : function(data) {
				$.messager.alert('提示',"查询检查记录失败!","info");
			}
		});		
	}
	function jcLieBiao(){
		var securityUserBaseinfoId=$("#securityUserBaseinfoId").val();
		openOtherWindow("19850054","检查列表","hsp/patientClinicRecord.do?verbId=electronicArchives&securityUserBaseinfoId="+securityUserBaseinfoId);
	}
	function jyGetData() {
		if ($("#checkSecurityUserBaseinfoId").val() == "yes" && $("#checkjy").val() == "yes") {
			var securityUserBaseinfoId = $("#securityUserBaseinfoId").val();
			var path = $("#zycPath").val();
			if (typeof (securityUserBaseinfoId) != "undefind") {
				$.ajax({
					type : "post",
					url : path + "/main/TabAction.do?verbId=jianyan",
					data : {securityUserBaseinfoId : securityUserBaseinfoId},
					dataType : "json",
					success : function(data) {
						if (data[0].state == "yes") {
							var map = data[0];
							for ( var key in map) {
								if (key == "row1") {
									var rowData = map[key];
									$("#jysj1").html(rowData[0].jysj);
									$("#jyks1").html(rowData[0].jyks);
									$("#jyxm1").html(rowData[0].jyxm);
									$("#jylabId1").val(rowData[0].labId);
									$("#jyauthorityId1").val(rowData[0].authorityId);
									$("#jypid1").val(rowData[0].pid);
									$("#jyclinicId1").val(rowData[0].clinicId);
									$("#jyclinicType1").val(rowData[0].clinicType);
									$("#jytr1").removeClass("table_tr_hide");
								}
								if (key == "row2") {
									var rowData = map[key];
									$("#jysj2").html(rowData[0].jysj);
									$("#jyks2").html(rowData[0].jyks);
									$("#jyxm2").html(rowData[0].jyxm);
									$("#jylabId2").val(rowData[0].labId);
									$("#jyauthorityId2").val(rowData[0].authorityId);
									$("#jypid2").val(rowData[0].pid);
									$("#jyclinicId2").val(rowData[0].clinicId);
									$("#jyclinicType2").val(rowData[0].clinicType);
									$("#jytr2").removeClass("table_tr_hide");
								}
								if (key == "row3") {
									var rowData = map[key];
									$("#jysj3").html(rowData[0].jysj);
									$("#jyks3").html(rowData[0].jyks);
									$("#jyxm3").html(rowData[0].jyxm);
									$("#jylabId3").val(rowData[0].labId);
									$("#jyauthorityId3").val(rowData[0].authorityId);
									$("#jypid3").val(rowData[0].pid);
									$("#jyclinicId3").val(rowData[0].clinicId);
									$("#jyclinicType3").val(rowData[0].clinicType);
									$("#jytr3").removeClass("table_tr_hide");
								}
							}
						} else {
							$.messager.alert('提示',"该患者没有检验记录!","info");
						}
					},
					error : function(data) {
						$.messager.alert('提示',"查询检验记录失败!","info");
					}
				});
			}
			$("#checkjy").val("no");
		}
	}
	function jyDetail(num){
		var path = $("#zycPath").val();
		var authorityId=$("#jyauthorityId"+num).val();
		var patientId=$("#jypid"+num).val();
		var clinicId=$("#jyclinicId"+num).val();
		var clinicType=$("#jyclinicType"+num).val();
		var labId=$("#jylabId"+num).val();
		$.ajax({
			type : "post",
			url : path + "/tab/TabDetailAction.do?verbId=getOutpLab",
			data : {authorityId:authorityId,patientId:patientId,clinicId:clinicId,clinicType:clinicType,labId:labId},
			dataType : "json",
			success : function(data) {
				if (data) {
					$("#jybt").html(data[0].hospitalName);
					$("#jyybh").html(data[0].specimenCode);
					$("#jyxm").html(data[0].patientName);
					$("#jyks").html(data[0].applicationDepartmentName);
					$("#jybb").html(data[0].specimenName);
					if(data[0].specimenReceiveTime!=null&&data[0].specimenReceiveTime!="")
						$("#jysjsj").html(getSmpFormatDateByLong(data[0].specimenReceiveTime.time,false));
					$("#jymzh").html(data[0].outpClinicNo);
					$("#jych").html(data[0].bedNo);
					$("#jybrid").html(data[0].patientId);
					if(data[0].collectionDate!=null&&data[0].collectionDate!="")
						$("#jycysj").html(getSmpFormatDateByLong(data[0].collectionDate.time,false));
					$("#jyxb").html(data[0].sexName);
					$("#jynl").html(data[0].age+"岁");
					$("#jyzd").html(data[0].diagnosis);
					$("#jybz").html(data[0].reportNotes);
					
					var labDetails=data[0].labDetails;
					var le=labDetails.length;
					var length;
					if((le%2)==0)
						length=(le/2);
					if((le%2)==1)
						length=(le+1)/2;
					var str1="<tr><th style=\"text-align:center;padding-top: 3px;padding-bottom: 5px;width: 12%;\">代号</th>" +
								"<th style=\"text-align:center;padding-top: 3px;padding-bottom: 5px;width: 35%;\">项目名称</th>" +
								"<th style=\"text-align:center;padding-top: 3px;padding-bottom: 5px;width: 20%;\">结果</th>" +
								"<th style=\"text-align:center;padding-top: 3px;padding-bottom: 5px;width: 33%;\">参考范围</th></tr>"
					if(labDetails!=null&&labDetails.length>0){
						for(var i=0;i<length;i++){
							str1+=
							"<tr>" +
								"<td>"+labDetails[i].dlabSubItemCode+"</td>" +
								"<td>" +
								"<span class=\"lab_item_name\" onclick=\"toItemDetail(this);\">"+labDetails[i].dlabSubItemName+"</span>" +
								"</td>" +
								"<td>" ;
							if(labDetails[i].labQuantitativelyResults!=null){
								str1+="<span>"+labDetails[i].labQuantitativelyResults+"</span>"
							}else if(labDetails[i].labQualitativeResults!=null){
								str1+="<span>"+labDetails[i].labQualitativeResults+"</span>"
							}
							str1+="<span>"+labDetails[i].labResultCode+"</span>" +
								"</td>"+
								"<td>" +
									"<span>"+labDetails[i].referenceRangeUpperLimit+"</span>" +
									"<span>"+labDetails[i].labMeasurementUnits+"</span>" +
								"</td>" +
							"</tr>";
						}
					}
					$("#labDetails1").html(str1);
					
					var str2="<tr><th style=\"text-align:center;padding-top: 3px;padding-bottom: 5px;width: 12%;\">代号</th>" +
								"<th style=\"text-align:center;padding-top: 3px;padding-bottom: 5px;width: 35%;\">项目名称</th>" +
								"<th style=\"text-align:center;padding-top: 3px;padding-bottom: 5px;width: 20%;\">结果</th>" +
								"<th style=\"text-align:center;padding-top: 3px;padding-bottom: 5px;width: 33%;\">参考范围</th></tr>"
					if(labDetails!=null&&labDetails.length>length){
						for(var i=length;i<labDetails.length;i++){
							str2+=
							  "<tr>" +
									"<td>"+labDetails[i].dlabSubItemCode+"</td>" +
									"<td>" +
									"<span class=\"lab_item_name\" onclick=\"toItemDetail(this);\">"+labDetails[i].dlabSubItemName+"</span>" +
									"</td>" +
									"<td>" ;
								if(labDetails[i].labQuantitativelyResults!=null){
									str2+="<span>"+labDetails[i].labQuantitativelyResults+"</span>"
								}else if(labDetails[i].labQualitativeResults!=null){
									str2+="<span>"+labDetails[i].labQualitativeResults+"</span>"
								}
								str2+="<span>"+labDetails[i].labResultCode+"</span>" +
									"</td>"+
									"<td>" +
										"<span>"+labDetails[i].referenceRangeUpperLimit+"</span>" +
										"<span>"+labDetails[i].labMeasurementUnits+"</span>" +
									"</td>" +
								"</tr>";
							}
					}
					$("#labDetails2").html(str2);
					
					$("#jysqys").html(data[0].applicationDoctorName);
					if(data[0].reportDate!=null&&data[0].reportDate!="")
						$("#jybgsj").html(getSmpFormatDateByLong(data[0].reportDate.time,false));
					$("#jyjyz").html(data[0].labDoctorName);
					$("#jyshz").html(data[0].checkDoctorName);
					
					$("#zjjyxx").window("open");
				} else {
					$.messager.alert('提示',"该患者没有检验记录!","info");
				}
			},
			error : function(data) {
				$.messager.alert('提示',"查询检验记录失败!","info");
			}
		});
	}
	function jyLieBiao(){
		var securityUserBaseinfoId=$("#securityUserBaseinfoId").val();
		openOtherWindow("19850064","检验列表","hsp/patientClinicRecord.do?verbId=electronicArchives&securityUserBaseinfoId="+securityUserBaseinfoId);
	}
	function fyGetData() {
		if ($("#checkSecurityUserBaseinfoId").val() == "yes" && $("#checkfy").val() == "yes") {
			var securityUserBaseinfoId = $("#securityUserBaseinfoId").val();
			var path = $("#zycPath").val();
			if (typeof (securityUserBaseinfoId) != "undefind") {
				$.ajax({
					type : "post",
					url : path + "/main/TabAction.do?verbId=feiyong",
					data : {securityUserBaseinfoId : securityUserBaseinfoId},
					dataType : "json",
					success : function(data) {
						if (data[0].state == "yes") {
							var map = data[0];
							for ( var key in map) {
								if (key == "row1") {
									var rowData = map[key];
									$("#fysj1").html(rowData[0].fysj);
									$("#fyks1").html(rowData[0].fyks);
									$("#fyfp1").html(rowData[0].fyfp);
									$("#fyje1").html(rowData[0].fyje);
									$("#fyreceiptNumber1").val(rowData[0].receiptNumber);
									$("#fyauthorityId1").val(rowData[0].authorityId);
									$("#fypid1").val(rowData[0].pid);
									$("#fyclinicId1").val(rowData[0].clinicId);
									$("#fyclinicType1").val(rowData[0].clinicType);
									$("#fytr1").removeClass("table_tr_hide");
								}
								if (key == "row2") {
									var rowData = map[key];
									$("#fysj2").html(rowData[0].fysj);
									$("#fyks2").html(rowData[0].fyks);
									$("#fyfp2").html(rowData[0].fyfp);
									$("#fyje2").html(rowData[0].fyje);
									$("#fyreceiptNumber2").val(rowData[0].receiptNumber);
									$("#fyauthorityId2").val(rowData[0].authorityId);
									$("#fypid2").val(rowData[0].pid);
									$("#fyclinicId2").val(rowData[0].clinicId);
									$("#fyclinicType2").val(rowData[0].clinicType);
									$("#fytr2").removeClass("table_tr_hide");
								}
								if (key == "row3") {
									var rowData = map[key];
									$("#fysj3").html(rowData[0].fysj);
									$("#fyks3").html(rowData[0].fyks);
									$("#fyfp3").html(rowData[0].fyfp);
									$("#fyje3").html(rowData[0].fyje);
									$("#fyreceiptNumber3").val(rowData[0].receiptNumber);
									$("#fyauthorityId3").val(rowData[0].authorityId);
									$("#fypid3").val(rowData[0].pid);
									$("#fyclinicId3").val(rowData[0].clinicId);
									$("#fyclinicType3").val(rowData[0].clinicType);
									$("#fytr3").removeClass("table_tr_hide");
								}
							}
						} else {
							$.messager.alert('提示',"该患者没有费用记录!","info");
						}
					},
					error : function(data) {
						$.messager.alert('提示',"查询费用失败!","info");
					}
				});
			}
			$("#checkfy").val("no");
		}
	}
	function fyDetail(num){
		var path = $("#zycPath").val();
		var authorityId=$("#fyauthorityId"+num).val();
		var patientId=$("#fypid"+num).val();
		var clinicId=$("#fyclinicId"+num).val();
		var clinicType=$("#fyclinicType"+num).val();
		var receiptNumber=$("#fyreceiptNumber"+num).val();
		/*if(clinicType=="outp"){
		$("#zjfyxx").window("refresh", path+"/hsp/patientClinicRecord.do?verbId=getOutpBill" +
			"&authorityId="+authorityId+"&pid="+patientId+"&clinicId="+clinicId+"&clinicType="+clinicType+"&receiptNumber="+receiptNumber);
		$("#zjfyxx").window("open");
		}
		if(clinicType=="inp"){
		$("#zjfyxx").window("refresh", path+"/hsp/patientClinicRecord.do?verbId=getInpBill" +
			"&authorityId="+authorityId+"&pid="+patientId+"&clinicId="+clinicId+"&clinicType="+clinicType+"&receiptNumber="+receiptNumber);
		$("#zjfyxx").window("open");
		}*/
		if(clinicType=="outp"){
			$.ajax({
				type : "post",
				url : path + "/tab/TabDetailAction.do?verbId=getOutpBill",
				data : {authorityId:authorityId,patientId:patientId,clinicId:clinicId,clinicType:clinicType,receiptNumber:receiptNumber},
				dataType : "json",
				success : function(data) {
					if(data){//注意hospitalName是从全局变量中取的
						var str1="<br>" +
								"<h5 style=\"text-align: center;\">"+hospitalName+"</h5>" +
								"<br>" +
								"<h5 style=\"text-align: center;\">费用详细信息</h5>" +
								"<br>";
						for(var i=0;i<data.length;i++){
							str1+="<div id=\"fee_info_div_"+data[i].classOnRcptCode+"\" class=\"div_width_100\">";
							str1+="<div style=\"width: 98%;margin-left: 1%;margin-top: 5px;\">";
							var billMasterFormats=data[i].billMasterFormats;
							if(billMasterFormats!=null&&billMasterFormats.length>0){
								for(var j=0;j<billMasterFormats.length;j++){
									str1+="<div class=\"div_width_100\" style=\"border: solid 1px #000000;\">" +
									"<table class=\"div_width_100 table_td_interval3\" style=\"text-align: center;\">" +
									"<tr>" +
									"<td width=\"25%\">费用类型：</td>" +
									"<td width=\"25%\">"+data[i].classOnRcptName+"</td>" +
									"<td width=\"25%\">收据号：</td>" +
									"<td width=\"25%\">"+billMasterFormats[j].receiptNumber+"</td>" +
									"</tr>" +
									"<tr>" +
									"<td>交费时间：</td>";
									if(billMasterFormats[j].invoiceDate!=null){
										str1+="<td>"+getSmpFormatDateByLong(billMasterFormats[j].invoiceDate.time,false)+"</td>";
									}else{
										str1+="<td></td>";
									}
									str1+="<td>收费员：</td>" +
									"<td>"+billMasterFormats[j].tollName+"</td>" +
									"</tr>" +
									"</table>" +
									"<div class=\"horizontal_line_3\"></div>" +
									"<hr/>" +
									"<table class=\"div_width_100 table_td_interval3\" style=\"text-align: center;\">" +
									"<tr>" +
									"<td width=\"25%\">名称</td>" +
									"<td width=\"25%\">数量</td>" +
									"<td width=\"25%\">单位</td>" +
									"<td width=\"25%\">金额</td>" +
									"</tr>";
									var billItemsFormats=billMasterFormats[j].billItemsFormats;
									if(billItemsFormats!=null&&billItemsFormats.length>0){
										for(var k=0;k<billItemsFormats.length;k++){
											str1+="<tr>" +
											"<td>"+billItemsFormats[k].itemName+"</td>" +
											"<td>"+billItemsFormats[k].num+"</td>" +
											"<td>"+billItemsFormats[k].uint+"</td>" +
											"<td>"+billItemsFormats[k].money+"</td>" +
											"</tr>";
										}									
									}
									str1+="<tr>" +
											"<td colspan=\"3\"></td>" +
											"<td>合计："+billMasterFormats[j].totalFee+"</td>" +
										"</tr>" +
									"</table>" +
									"</div>"
								}
							}
							str1+="</div>" +
									"<div class=\"horizontal_line_3\"></div>" +
							"</div>"		
						}
						$("#else_fee_mz").html(str1);
						$("#zjmzfyxx").window("open");
					} else {
						$.messager.alert('提示',"该患者没有费用记录!","info");
					}
				},
				error : function(data) {
					$.messager.alert('提示',"查询费用记录失败!","info");
				}
			});
		}
		if(clinicType=="inp"){
			$.ajax({
				type : "post",
				url : path + "/tab/TabDetailAction.do?verbId=getInpBill",
				data : {authorityId:authorityId,patientId:patientId,clinicId:clinicId,clinicType:clinicType,receiptNumber:receiptNumber},
				dataType : "json",
				success : function(data) {
					if(data){
						var str1="<br>" +
								"<h5 style=\"text-align: center;\">"+hospitalName+"</h5>" +
								"<br>" +
								"<h5 style=\"text-align: center;\">费用详细信息</h5>" +
								"<br>";
						for(var i=0;i<data.length;i++){
							str1+="<div id=\"fee_info_div_"+data[i].classOnRcptCode+"\" class=\"div_width_100\">";
							str1+="<div style=\"width: 98%;margin-left: 1%;margin-top: 5px;\">";
							var billMasterFormats=data[i].billMasterFormats;
							if(billMasterFormats!=null&&billMasterFormats.length>0){
								for(var j=0;j<billMasterFormats.length;j++){
									str1+="<div class=\"div_width_100\" style=\"border: solid 1px #000000;\">" +
									"<table class=\"div_width_100 table_td_interval3\" style=\"text-align: center;\">" +
									"<tr>" +
									"<td width=\"25%\">费用类型：</td>" +
									"<td width=\"25%\">"+data[i].classOnRcptName+"</td>" +
									"<td width=\"25%\">收据号：</td>" +
									"<td width=\"25%\">"+billMasterFormats[j].receiptNumber+"</td>" +
									"</tr>" +
									"<tr>" +
									"<td>交费时间：</td>";
									if(billMasterFormats[j].invoiceDate!=null){
										str1+="<td>"+getSmpFormatDateByLong(billMasterFormats[j].invoiceDate.time,false)+"</td>";
									}else{
										str1+="<td></td>";
									}
									str1+="<td>收费员：</td>" +
									"<td>"+billMasterFormats[j].tollName+"</td>" +
									"</tr>" +
									"</table>" +
									"<div class=\"horizontal_line_3\"></div>" +
									"<hr/>" +
									"<table class=\"div_width_100 table_td_interval3\" style=\"text-align: center;\">" +
									"<tr>" +
									"<td width=\"25%\">名称</td>" +
									"<td width=\"25%\">数量</td>" +
									"<td width=\"25%\">单位</td>" +
									"<td width=\"25%\">金额</td>" +
									"</tr>" ;		
									var billItemsFormats=billMasterFormats[j].billItemsFormats;
									if(billItemsFormats!=null&&billItemsFormats.length>0){
										for(var k=0;k<billItemsFormats.length;k++){
											str1+="<tr>" +
											"<td>"+billItemsFormats[k].itemName+"</td>" +
											"<td>"+billItemsFormats[k].num+"</td>" +
											"<td>"+billItemsFormats[k].uint+"</td>" +
											"<td>"+billItemsFormats[k].money+"</td>" +
											"</tr>";
										}									
									}
									str1+="<tr>" +
											"<td colspan=\"3\"></td>" +
											"<td>合计："+billMasterFormats[j].totalFee+"</td>" +
										"</tr>" +
									"</table>" +
									"</div>"
								}
							}
							str1+="</div>" +
								"<div class=\"horizontal_line_3\"></div>" +
							"</div>"		
						}
						$("#else_fee_zy").html(str1);
						$("#zjzyfyxx").window("open");
					} else {
						$.messager.alert('提示',"该患者没有费用记录!","info");
					}
				},
				error : function(data) {
					$.messager.alert('提示',"查询费用记录失败!","info");
				}
			});
		}		
	}
	function fyLieBiao(){
		var securityUserBaseinfoId=$("#securityUserBaseinfoId").val();
		openOtherWindow("19850074","费用列表","hsp/patientClinicRecord.do?verbId=electronicArchives&securityUserBaseinfoId="+securityUserBaseinfoId);
	}
	function tjGetData() {
		if ($("#checkSecurityUserBaseinfoId").val() == "yes" && $("#checktj").val() == "yes") {
			var securityUserBaseinfoId = $("#securityUserBaseinfoId").val();
			var path = $("#zycPath").val();
			if (typeof (securityUserBaseinfoId) != "undefind") {
				$.ajax({
					type : "post",
					url : path + "/main/TabAction.do?verbId=tijian",
					data : {securityUserBaseinfoId : securityUserBaseinfoId},
					dataType : "json",
					success : function(data) {
						if (data[0].state == "yes") {
							var map = data[0];
							for ( var key in map) {
								if (key == "row1") {
									var rowData = map[key];
									$("#tjsj1").html(rowData[0].tjsj);
									$("#tjpj1").html(rowData[0].tjpj);
									$("#tjauthorityId1").val(rowData[0].authorityId);
									$("#tjpid1").val(rowData[0].pid);
									$("#tjpeId1").val(rowData[0].peId);
									$("#tjpeVisitId1").val(rowData[0].peVisitId);
									$("#tjtr1").removeClass("table_tr_hide");
								}
								if (key == "row2") {
									var rowData = map[key];
									$("#tjsj2").html(rowData[0].tjsj);
									$("#tjpj2").html(rowData[0].tjpj);
									$("#tjauthorityId2").val(rowData[0].authorityId);
									$("#tjpid2").val(rowData[0].pid);
									$("#tjpeId2").val(rowData[0].peId);
									$("#tjpeVisitId2").val(rowData[0].peVisitId);
									$("#tjtr2").removeClass("table_tr_hide");
								}
								if (key == "row3") {
									var rowData = map[key];
									$("#tjsj3").html(rowData[0].tjsj);
									$("#tjpj3").html(rowData[0].tjpj);
									$("#tjauthorityId3").val(rowData[0].authorityId);
									$("#tjpid3").val(rowData[0].pid);
									$("#tjpeId3").val(rowData[0].peId);
									$("#tjpeVisitId3").val(rowData[0].peVisitId);
									$("#tjtr3").removeClass("table_tr_hide");
								}
							}
						} else {
							$.messager.alert('提示',"该患者没有体检记录!","info");
						}
					},
					error : function(data) {
						$.messager.alert('提示',"查询体检记录失败!","info");
					}
				});
			}
			$("#checktj").val("no");
		}
	}
	function tjDetail(num){
		var path = $("#zycPath").val();
		var authorityId=$("#tjauthorityId"+num).val();
		var patientId=$("#tjpid"+num).val();
		var peId=$("#tjpeId"+num).val();
		var peVisitId=$("#tjpeVisitId"+num).val();
		$("#zjtjxx").window("refresh", path+"/tab/TabDetailAction.do?verbId=getPeDetail" +
				"&authorityId="+authorityId+"&patientId="+patientId+"&peId="+peId+"&peVisitId="+peVisitId);
		$("#zjtjxx").window("open");
	}
	function tjLieBiao(){
		var securityUserBaseinfoId=$("#securityUserBaseinfoId").val();
		openOtherWindow("19850084","体检列表","hsp/patientClinicRecord.do?verbId=electronicArchives&securityUserBaseinfoId="+securityUserBaseinfoId);
	}
	function addUnderLine(obj){
		id=$(obj).attr("id");
		$("#"+id).addClass("span_under_line");
	}
	function removeUnderLine(obj){
		id=$(obj).attr("id");
		$("#"+id).removeClass("span_under_line");
	} 
	/**
	 * js将long日期格式转换为标准日期格式
	 */
    //扩展Date的format方法 
    Date.prototype.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S": this.getMilliseconds()
        }
        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    }
    /**  
    *转换日期对象为日期字符串  
    * @param date 日期对象  
    * @param isFull 是否为完整的日期数据,  
    *               为true时, 格式如"2000-03-05 01:05:04"  
    *               为false时, 格式如 "2000-03-05"  
    * @return 符合要求的日期字符串  
    */  
    function getSmpFormatDate(date, isFull) {
        var pattern = "";
        if (isFull == true || isFull == undefined) {
            pattern = "yyyy-MM-dd hh:mm:ss";
        } else {
            pattern = "yyyy-MM-dd";
        }
        return getFormatDate(date, pattern);
    }
    /**  
    *转换当前日期对象为日期字符串  
    * @param date 日期对象  
    * @param isFull 是否为完整的日期数据,  
    *               为true时, 格式如"2000-03-05 01:05:04"  
    *               为false时, 格式如 "2000-03-05"  
    * @return 符合要求的日期字符串  
    */  

    function getSmpFormatNowDate(isFull) {
        return getSmpFormatDate(new Date(), isFull);
    }
    /**  
    *转换long值为日期字符串  
    * @param l long值  
    * @param isFull 是否为完整的日期数据,  
    *               为true时, 格式如"2000-03-05 01:05:04"  
    *               为false时, 格式如 "2000-03-05"  
    * @return 符合要求的日期字符串  
    */  

    function getSmpFormatDateByLong(l, isFull) {
        return getSmpFormatDate(new Date(l), isFull);
    }
    /**  
    *转换long值为日期字符串  
    * @param l long值  
    * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss  
    * @return 符合要求的日期字符串  
    */  

    function getFormatDateByLong(l, pattern) {
        return getFormatDate(new Date(l), pattern);
    }
    /**  
    *转换日期对象为日期字符串  
    * @param l long值  
    * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss  
    * @return 符合要求的日期字符串  
    */  
    function getFormatDate(date, pattern) {
        if (date == undefined) {
            date = new Date();
        }
        if (pattern == undefined) {
            pattern = "yyyy-MM-dd hh:mm:ss";
        }
        return date.format(pattern);
    }   

  //点击检验项名称
    function toItemDetail(obj){
    	var itemName=obj.innerHTML;
    	$.ajax({
    		type:"post",
    		url:"hsp/patientClinicRecord.do?verbId=getKbsIdByName",
    		data:{itemName:itemName},
    		dataType:"json",
    		success:function(data){
    			if(data[0].state=="yes"){
    				$("#jyxmmc").html(data[0].itemName);
    				$("#jyflmc").html(data[0].parentName);
    				$("#jybt").html(data[0].itemName);
    				$("#jygjz").html(data[0].itemName);
    				$("#jyckz").html(data[0].referenceValueRange);
    				$("#jycjjb").html(data[0].relatedDiseases);
    				$("#jylcyy").html(data[0].clinicalSignificance);
    				$("#jyjyjs").html(data[0].summary);
    				$("#jyxxxxxx").window("open");
    			}else{
    				$.messager.alert('提示',"无相关数据!","info");
    			}
    		},
    		error:function(data){
    			$.messager.alert('提示',"查询出错!","info");
    		}
    	}); 
    }

    function toItemDiseaseDetail(obj){
    	var path = $("#zycPath").val();
    	var itemName=obj.innerHTML;
    	$.ajax({
    		type:"post",
    		//async:false,//同步执行
    		url:path+"/kbs/Disease/DiseaseAction.do?verbId=getDiseaseId",
    		data:{diseaseName:itemName},
    		dataType: "text",
    		success:function(data){
    			if(data.length>0){
    				showDisease(data);
    			}else{
    				$.messager.alert('提示',"知识库暂无数据！!","info");
    			}
    		},
    		error:function(data){
    			$.messager.alert('提示',"查询失败!","info");
    		}
    	}); 
    }
    function showDisease(diseaseid){
    	var path = $("#zycPath").val();
    	$.ajax({
    		type:"post",
    		//async:false,//同步执行
    		url:path+"/kbs/Disease/DiseaseAction.do?verbId=detail",
    		data:{diseaseid:diseaseid},
    		dataType: "json",
    		success:function(data){
    			$("#jbmc").html(data[0].diseasename);
    			$("#flmc").html(data[0].parentName);
    			$("#bt").html(data[0].title);
    			$("#gjz").html(data[0].keyword);
    			$("#jbms").html(data[0].diseasedesc);
    			$("#lczd").html(data[0].clinicalrepresent);
    			$("#zdyj").html(data[0].diagnosepoint);
    			$("#zlff").html(data[0].treatprescription);
    			$("#jy").html(data[0].doctorsadvice);
    			$("#bz").html(data[0].comments);
    			$('#diseaseWin').window('open');
    		},
    		error:function(data){
    			$.messager.alert('提示',"查询失败!","info");
    		}
    	}); 
    }
    function toDrugDetail(obj){
    	var path = $("#zycPath").val();
    	var itemName=obj.innerHTML;
    	$.ajax({
    		type:"post",
    		//async:false,//同步执行
    		url:path+"/kbs/Ethicals/EthicalsAction.do?verbId=getItemId",
    		data:{itemName:itemName},
    		dataType: "text",
    		success:function(data){
    			if(data.length>0){
    				showEthical(data);
    			}else{
    				$.messager.alert('提示','暂无数据！','info');
    			}
    		},
    		error:function(data){
    			$.messager.alert('提示',"查询失败!","info");
    		}
    	}); 
    }
    function showEthical(itemCode){
    	var path = $("#zycPath").val();
    	$.ajax({
    		type:"post",
    		//async:false,//同步执行
    		url:path+"/kbs/Ethicals/EthicalsAction.do?verbId=detail",
    		data:{itemCode:itemCode},
    		dataType: "json",
    		success:function(data){
    			$("#ypmc_ethical").html(data[0].itemName);
    			$("#flmc_ethical").html(data[0].parentName);
    			$("#bt_ethical").html(data[0].title);
    			$("#gjz_ethical").html(data[0].keyword);
    			$("#ylx_ethical").html(data[0].ylx);
    			$("#syz_ethical").html(data[0].syz);
    			$("#jj_ethical").html(data[0].jj);
    			$("#blfy_ethical").html(data[0].blfy);
    			$("#zysx_ethical").html(data[0].zysx);
    			$("#ywxhzy_ethical").html(data[0].ywxhzy);
    			$("#yfhyl_ethical").html(data[0].yfhyl);
    			$("#zjhgg_ethical").html(data[0].zjhgg);
    			$("#bz_ethical").html(data[0].comments);
    			$('#ethicalWin').window('open');
    		},
    		error:function(data){
    			$.messager.alert('提示',"查询失败!","info");
    		}
    	}); 
    }
    function toChineseMed(obj){
    	var path = $("#zycPath").val();
    	var itemName=obj.innerHTML;
    	$.ajax({
    		type:"post",
    		//async:false,//同步执行
    		url:path+"/kbs/ChineseMedicinalMaterials/ChineseMedicinalMaterialsAction.do?verbId=getItemId",
    		data:{itemName:itemName},
    		dataType: "text",
    		success:function(data){
    			if(data.length>0){
    				showChineseMed(data);
    			}else{
    				$.messager.alert('提示','暂无数据！','info');
    			}
    		},
    		error:function(data){
    			$.messager.alert('提示',"查询失败!","info");
    		}
    	}); 
    }
    function showChineseMed(itemId){
    	var path = $("#zycPath").val();
    	$.ajax({
    		type:"post",
    		//async:false,//同步执行
    		url:path+"/kbs/ChineseMedicinalMaterials/ChineseMedicinalMaterialsAction.do?verbId=detail",
    		data:{itemId:itemId},
    		dataType: "json",
    		success:function(data){
    			$("#ycmc_cMedical").html(data[0].itemName);
    			$("#flmc_cMedical").html(data[0].parentName);
    			$("#bt_cMedical").html(data[0].itemName);
    			$("#gjz_cMedical").html(data[0].itemName);
    			$("#bm_cMedical").html(data[0].aliases);
    			$("#gx_cMedical").html(data[0].effect);
    			$("#zz_cMedical").html(data[0].indications);
    			$("#xw_cMedical").html(data[0].taste);
    			$("#ksfl_cMedical").html(data[0].genus);
    			$("#zyfb_cMedical").html(data[0].resources);
    			$("#sycjb_cMedical").html(data[0].identification);
    			$("#mjls_cMedical").html(data[0].famousDemonstration);
    			$("#cc_cMedical").html(data[0].provenance);
    			$('#chineseMedWin').window('open');
    		},
    		error:function(data){
    			$.messager.alert('提示',"查询失败!","info");
    		}
    	});
    }
    