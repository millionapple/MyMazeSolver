function createGrid(width, height){
	console.log(width);
	console.log(height);
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	let reqs = this.responseText;
	         reqs = JSON.parse(reqs);
	         console.log(reqs);
	    }
	  };
	  xhttp.open("POST", "GetMazeServlet", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("gridWidth="+width+"&gridHeight="+height);
}
function getWidthAndHeight(){
	const width = document.getElementById("gridWidth").value;
	const height = document.getElementById("gridHeight").value;
	console.log("Height "+height);
	createGrid(width, height);
}

function createGridFetch(width, height){
	const params = {
		    gridWidth: width,
		    gridHeight: height 
		};
	const options = {
		    method: 'POST',
		    body: JSON.stringify( params )  
		};
	fetch( 'GetMazeServlet', options )
    .then( response => response.json() )
    .then( response => {
        console.log(response);
    } );
}