var grid;
function sendWidthAndHeight(width, height){
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	let reqs = this.responseText;
	         reqs = JSON.parse(reqs);
	         grid = reqs;
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
	for(var row=0; row < reqs.length; row++){
		var gridRow = document.createElement("tr");
		gridRow.id = "row"+row;
		grid.appendChild(gridRow);
		for(var column = 0; column< reqs[row].length; column++){
			var gridData = document.createElement("td");
			gridData.id = "column"+column;
			gridData.style.border = "1px solid black";
			gridData.style.width = "50px";
			gridData.style.height = "50px";
			gridData.onclick = function(){changeGrid()};
			gridRow.appendChild(gridData);
		}
	}
}

var wall = false;
var start = false;
var end = false;
var path = true;

function setToWall(){
	wall = true;
	start = false;
	end = false;
	path = false;
}
function setToStart(){
	wall = false;
	start = true;
	end = false;
	path = false;
}
function setToEnd(){
	wall = false;
	start = false;
	end = true;
	path = false;
}
function setToPath(){
	wall = false;
	start = false;
	end = false;
	path = true;
}

function changeGrid(){
	console.log(event.target);
	if(path){
		addPathToGrid(event.target);
	}else if(wall){
		addWallToGrid(event.target);
	}else if(start){
		addStartToGrid(event.target);
	}else if(end){
		addEndToGrid(event.target);
	}
}
function addPathToGrid(target){
	column = target.id.replace("column", "");
	row = target.parentElement.id.replace("row", "");
	grid[row][column] = 0;
	target.style.backgroundColor = "white";
}
function addWallToGrid(target){
	column = target.id.replace("column", "");
	row = target.parentElement.id.replace("row","");
	grid[row][column] = 1;
	target.style.backgroundColor = "black";
	console.log(grid);
}
function addStartToGrid(target){
	if(checkGrid(2)){
		console.log("There is already a start");
	}else{
		column = target.id.replace("column", "");
		row = target.parentElement.id.replace("row","");
		grid[row][column] = 2;
		target.style.backgroundColor = "green";
	}
}
function addEndToGrid(target){
	if(checkGrid(3)){
		console.log("There is already an end");
	}else{
		column = target.id.replace("column", "");
		row = target.parentElement.id.replace("row","");
		grid[row][column] = 3;
		target.style.backgroundColor = "red"
	}
}
function checkGrid(value){
	for(var row = 0; row < grid.length; row++){
		for(var column = 0; column < grid[row].length; column++){
			console.log(grid[row][column] == value);
			if(grid[row][column]==value){
				return true;
			}
		}
	}
	return false;
}
