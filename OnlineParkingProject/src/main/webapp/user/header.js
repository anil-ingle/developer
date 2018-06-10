/*
	 * Developer:Anil D. Ingle
	 * Date:26/05/2018
	 * -----------------------------------Description-----------------
	 * Here user.main.html control dom and make ajax call as per requirement and 
	 * give performance of do unwanted data not load and rendor
	 * -----------------------End--------------------------------------
	 */
	var jq=$;
	
	var script = document.createElement('script');
	script.src = 'jquery.min.js';
	script.type = 'text/javascript';
	document.getElementsByTagName('head')[0].appendChild(script);
	
	// Store
	
	jq(document).ready(function() {
		let name="Anil";
		logo(name);
		
		

		});
	/*window.onload = function what(){
		logo("anil");
		};*/
	
	window.onload =function logo(name){
		let ur="<div class='user-r-page'><a class='hy-user' href='user_main.jsp'>Home</a></div>"
		let uh="<div class='user-r-history'><a class='hy-history' href='order.html'>Order</a></div>"
		let p="<div class='wolet-page'><a class='hy-wpage' href='../input.html'>My Wolet</a></div>"
		let info="<div id='header-nav-right' class='header-nav-right'><div class='header-user-name'>Welcome "+name+"</div><div class='header-log-out-logo'><button class='logout-btn'>logout</button></div></div>";
		if(ur||uh||p||info)
		 document.getElementById("hdr").innerHTML =ur+p+uh+info;
	}

	