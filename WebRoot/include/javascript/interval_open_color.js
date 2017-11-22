// JavaScript Document
var trOn = -1;
function defauleColor(){

	var TbRow = document.getElementById("interval_row_id");
	if (TbRow != null) {
		for (var i = 0; i < TbRow.rows.length; i++) {
		//alert(1);
			if(trOn != i){
				if (TbRow.rows[i].rowIndex % 2 == 1) {
					TbRow.rows[i].className="trColor_white";
					//alert(3);
				} else {
					TbRow.rows[i].className="trColor_defale";
					
				}
			}else{
				self.className="trColor_on";
			}			
		}
	}
}
function overColor(self){
	
	defauleColor();
	if((self.rowIndex - 1) != trOn){
	//alert(0)
		self.className="trColor";
	}			
}

function tresOn(self){
	
	trOn = self.rowIndex - 1;
	defauleColor();
	self.className="trColor_on";		
}