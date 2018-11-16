    	if ('serviceWorker' in navigator) {
    		console.log('ServiceWorker é suportado, vamos usar!');
    	} else {
    		console.log('ServiceWorker não é suportado.');
    	}
    if ('serviceWorker' in navigator) {
    	navigator.serviceWorker.register('sw.js')
    	.then(reg => console.log('sw registrado'))
    	.catch(err => console.error('erro ao registrar sw', err));
    }