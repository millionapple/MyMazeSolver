function createGrid(){
	alert("Hello");
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	let reqs = this.responseText;
	         reqs = JSON.parse(reqs);
	    }
	  };
	  xhttp.open("POST", "GetMazeServlet", true);
	  xhttp.send();
}
function hello(){
	alert("hello");
	return false;
}