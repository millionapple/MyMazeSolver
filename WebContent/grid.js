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
	showButtons();
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
function showButtons(){
	document.getElementById("setWall").style.display = "inline-block";
	document.getElementById("setStart").style.display = "inline-block";
	document.getElementById("setEnd").style.display = "inline-block";
	document.getElementById("setPath").style.display = "inline-block";
	document.getElementById("solveMaze").style.display = "inline-block";
	document.getElementById("resetGrid").style.display = "inline-block";
	document.getElementById("reloadGrid").style.display = "inline-block";
	document.getElementById("gridSetup").style.display = "none";
}

var path = true;
var wall = false;
var start = false;
var end = false;

function setToPath(){
	event.target.style.backgroundColor ="#999999";
	document.getElementById("setWall").style.backgroundColor = "#efefef";
	document.getElementById("setStart").style.backgroundColor = "#efefef";
	document.getElementById("setEnd").style.backgroundColor = "#efefef";
	wall = false;
	start = false;
	end = false;
	path = true;
}
function setToWall(){
	event.target.style.backgroundColor ="#999999";
	document.getElementById("setStart").style.backgroundColor = "#efefef";
	document.getElementById("setEnd").style.backgroundColor = "#efefef";
	document.getElementById("setPath").style.backgroundColor = "#efefef";
	wall = true;
	start = false;
	end = false;
	path = false;
}
function setToStart(){
	event.target.style.backgroundColor ="#999999";
	document.getElementById("setWall").style.backgroundColor = "#efefef";
	document.getElementById("setEnd").style.backgroundColor = "#efefef";
	document.getElementById("setPath").style.backgroundColor = "#efefef";
	wall = false;
	start = true;
	end = false;
	path = false;
}
function setToEnd(){
	event.target.style.backgroundColor ="#999999";
	document.getElementById("setWall").style.backgroundColor = "#efefef";
	document.getElementById("setStart").style.backgroundColor = "#efefef";
	document.getElementById("setPath").style.backgroundColor = "#efefef";
	wall = false;
	start = false;
	end = true;
	path = false;
}


function changeGrid(){
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
function resetGrid(){
	for(var row = 0; row < grid.length; row++){
		for(var column = 0; column < grid[row].length; column++){
			var block = document.getElementById("row"+row).childNodes[column];
			block.style.backgroundColor = "white";
			grid[row][column] = 0;
		}
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

function solveGrid(){
	console.log("sending to solve the maze");
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	let reqs = this.responseText;
	         reqs = JSON.parse(reqs);
	         console.log(reqs);
	         fillPath(reqs);
	    }
	  };
	  xhttp.open("POST", "SolveMazeServlet", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("grid="+JSON.stringify(grid));
}

function fillPath(grid){
	for(var row = 0; row < grid.length; row++){
		for(var column = 0; column < grid[row].length; column++){
			if(grid[row][column] == 4){
				var rowElement = document.getElementById("row"+row);
				var columnElement = rowElement.childNodes[column];
				columnElement.style.backgroundColor = "blue";
			}
		}
	}
}