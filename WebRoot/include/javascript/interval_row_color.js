// JavaScript Document
var TbRow = document.getElementById("interval_row_id");
if (TbRow != null) {
	for (var i = 0; i < TbRow.rows.length; i++) {
		if (TbRow.rows[i].rowIndex % 2 == 1) {
			TbRow.rows[i].style.backgroundColor = "#ffffff";
		} else {
			TbRow.rows[i].style.backgroundColor = "#F1F7FC";
		}
	}
}

