function showtime(time){
	//alert(time.value);
	if(time.value!=null&&time.value.length>0){
		//alert(time.value.length);
			var date=time.value;
			var year=date.substring(0,4);
			//alert(year);
			var month=date.substring(5,7);
			//alert(month);
			var day=date.substring(8,10);
			//alert(day);
			
			document.getElementById("birth_date_year").value=year;
			document.getElementById("birth_date_month").value=month;
			document.getElementById("birth_date_day").value=day;
	}
}
function getTime(time,a,b,c){
	if(time.value!=null&&time.value.length>0){
		//alert(time.value.length);
			var date=time.value;
			var year=date.substring(0,4);
			//alert(year);
			var month=date.substring(5,7);
			//alert(month);
			var day=date.substring(8,10);
			//alert(day);
			
			document.getElementById(a).value=year;
			document.getElementById(b).value=month;
			document.getElementById(c).value=day;
	}
}
function cardtime(time){
	if(time.value!=null&&time.value.length>0){
		var date=time.value;
		var year=date.substring(0,4);
		var month=date.substring(5,7);
		var day=date.substring(8,10);
		
		document.getElementById("acceptyear").value=year;
		document.getElementById("acceptmonth").value=month;
		document.getElementById("acceptday").value=day;
	}
}
function losttime(time){
		if(time.value!=null&&time.value.length>0){
		var date=time.value;
		var year=date.substring(0,4);
		var month=date.substring(5,7);
		var day=date.substring(8,10);
		
		document.getElementById("startyear").value=year;
		document.getElementById("startmonth").value=month;
		document.getElementById("startday").value=day;
	}
}