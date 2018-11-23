        function codeAddress() {
		    caches.open("paginas")
		      .then(cache => { 
		    	  console.log("pagina adicionada no cache");
		        return cache.add(window.location.href);
		    })	  
}
window.onload = codeAddress;


