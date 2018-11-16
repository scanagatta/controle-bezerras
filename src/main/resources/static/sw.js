let arquivos = [
	"/",
	"/index",
//	"/offline.html",
//	"/bezerro/listabezerros",
//	"/touro/listatouros",
//	"/vaca/listavacas",
//	"/manejo/listamanejo",
//	"css/style.css",
//	"bootstrap/css/bootstrap.min.css",
//	"bootstrap/js/bootstrap.min.js",
//	"js/filtroTabela.js",
//	"js/jquery.js",
//	"js/alertOffline.js",
]


//now = new Date
//const nomeStaticoDoCache = 'controle-bezerras-'+now.getHours()+'-'+now.getMinutes()+'-'+now.getMilliseconds();
const nomeStaticoDoCache = 'paginas';

// Cache on install
this.addEventListener("install", event => {
  this.skipWaiting();

  event.waitUntil(
    caches.open(nomeStaticoDoCache)
      .then(cache => {
    	  console.log("sw instalado");
        return cache.addAll(arquivos);
    })
  )
});


//Clear cache on activate
this.addEventListener('activate', event => {
  event.waitUntil(
    caches.keys().then(cacheNames => {
		  console.log("sw ativado");
      return Promise.all(
        cacheNames
          //.filter(cacheName => (cacheName.startsWith('controle-bezerras-')))
          .filter(cacheName => (cacheName !== nomeStaticoDoCache))
          .map(cacheName => caches.delete(cacheName))
      );
    })
  );
});



//verifica se tem rede, senão pega do cache, senão vai pra tela de offline
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
