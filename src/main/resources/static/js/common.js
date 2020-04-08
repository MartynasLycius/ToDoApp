/**
 * Helper functions & Common codes
 */
// IE doest support string date,need to parse as new Date(y,m,d)//
function getDate(dt) {
	if(dt != null){
		let arry = dt.split("T");
		if (arry.length == 2) {
			let datePart = arry[0].split("-");
			let timePart = arry[1].split(":");
			let dt = new Date(datePart[0], (datePart[1] - 1), datePart[2],
					timePart[0], timePart[1]);
			return dt.getDate() + "-" + (dt.getMonth() + 1) + "-"
					+ dt.getFullYear() + " " + dt.getHours() + ":"
					+ dt.getMinutes();
		}
	}
	return "";
}