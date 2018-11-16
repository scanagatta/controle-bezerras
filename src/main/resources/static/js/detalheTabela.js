 function exibeFilhos(elemento) {
      	var elementoPai = elemento.parentNode;
      	
      	var filhos = elemento.childNodes[1];
      	var icone = filhos.childNodes[1];
      	
      	
      	var x = document.getElementsByClassName(elementoPai.id);
      	var i;
      	for (i = 0; i < x.length; i++) {
      	    var bezerro = x[i];
  	      if(bezerro.style.display == "none"){
	    	  bezerro.style.display = "";
	    	  icone.className = 'ace-icon fa fa-angle-double-up';
	      }
	      else{
	      	bezerro.style.display = "none";
	      	icone.className = 'ace-icon fa fa-angle-double-down';
	      }
      	}


      }