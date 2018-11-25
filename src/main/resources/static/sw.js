let arquivos = [
	"/",
	"/index",
	"/offline",
	"bezerro/listabezerros",
	"touro/listatouros",
	"vaca/listavacas",
	"manejo/listamanejo",
	"css/style.css",
	"bootstrap/css/bootstrap.min.css",
	"bootstrap/js/bootstrap.min.js",
	"js/filtroTabela.js",
	"js/jquery.js",
	"js/alertOffline.js",
]

const nomeCache = 'paginas';

// Instalando
this.addEventListener("install", event => {
  this.skipWaiting();
  event.waitUntil(
    caches.open(nomeCache)
      .then(cache => {
    	  console.log("sw instalado");
        return cache.addAll(arquivos);
    })
  )
});

// Ativando
this.addEventListener('activate', event => {
  event.waitUntil(
    caches.keys().then(cacheNames => {
		  console.log("sw ativado");
      return Promise.all(
        cacheNames
          .filter(cacheName => (cacheName !== nomeCache))
          .map(cacheName => caches.delete(cacheName))
      );
    })
  );
});

// evento de Fetch
// verifica se tem rede, senão tem pega do cache, senão existir vai pra tela de offline
self.addEventListener('fetch', function(event) {
	  event.respondWith(
	    fetch(event.request).catch(function() {
		    	return caches.match(event.request)
		    .then(response => {
		    	return response || fetch(event.request);
		    })
		    .catch(() => {
		    	return caches.match('/offline');
		    })
	    })
	  );
});
