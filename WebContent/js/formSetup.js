	$(document).ready(function(){		
		$("td").addClass("tdBlue");
		$("table").addClass("tableNoRowcolBorder");
		setInputBGColor();
		loadCSS();
	});

	function setInputBGColor(){
		$('form input[type="text"]').each(function(){
	       $(this).attr("class","inputBackground");
		});
	}
	
	var siteRoot = '';
	function loadCSS(){
		var fullPath = window.location.pathname;
		fullPath = fullPath.replace(/^.*\\/, '');
		var start = fullPath.indexOf(siteRoot);
		if(-1 != start){
			var relevantPath = fullPath.slice(start+siteRoot.length);
			var tokens = relevantPath.split("/");
			var rootDir = tokens[1];
			switch(rootDir){
//				case 'index.html':
//				case 'pages':{
//					var fileName = tokens[2];	
//					if(null != fileName && 'undefined' != fileName && '' != fileName){
//						if(fileName == 'changePassword.jsp'){
//							
//						}
//					}
//				}
//				break;	
				default:{
					loadcssfile('main.css','css');
				}
				break;
			}
		}
	}
	
	function loadcssfile(filename,relativePath){
		  var relPath = '';
		  if(null != relativePath && 'undefined' != relativePath && '' != relativePath){
			  relPath = relativePath;
		  }
		  var fileref=document.createElement("link");
		  fileref.setAttribute("rel", "stylesheet");
		  fileref.setAttribute("type", "text/css");
		  fileref.setAttribute("href", relPath + '/' + filename);
		  if (typeof fileref!="undefined"){
			  document.getElementsByTagName("head")[0].appendChild(fileref);
		  }
	}