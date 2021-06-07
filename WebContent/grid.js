function sendWidthAndHeight(width, height){
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	let reqs = this.responseText;
	         reqs = JSON.parse(reqs);
	         console.log(reqs);
	         constructGrid(reqs);
	    }
	  };
	  xhttp.open("POST", "GetMazeServlet", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("gridWidth="+width+"&gridHeight="+height);
}
function getWidthAndHeight(){
	const width = document.getElementById("gridWidth").value;
	const height = document.getElementById("gridHeight").value;
	sendWidthAndHeight(width, height);
}

function constructGrid(reqs){
	var grid = document.getElementById("grid");
	console.log(reqs[0].length);
	for(var row=0; row < reqs.length; row++){
		var gridRow = document.createElement("tr");
		gridRow.id = "row"+row;
		grid.appendChild(gridRow);
		console.log(reqs[row]);
		for(var column = 0; column< reqs[row].length; column++){
			var gridData = document.createElement("td");
			gridData.id = "column"+column;
			gridData.style.border = "1px solid black";
			gridData.style.width = "50px";
			gridData.style.height = "50px";
			gridRow.appendChild(gridData);
			console.log(reqs[row][column]);
		}
	}
}

function setToWall(){
	
}

function setToStart(){
	
}

function setToEnd(){
	
}

function changeGrid(){
	
}