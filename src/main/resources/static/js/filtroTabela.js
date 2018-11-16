  var campoFiltro = document.querySelector("#filtrar-tabela");

campoFiltro.addEventListener("input", function() {
    var animais = document.querySelectorAll(".animal");

    if (this.value.length > 0) {
        for (var i = 0; i < animais.length; i++) {
            var animal = animais[i];
            var tdNome = animal.querySelector(".info-nome");
            var nome = tdNome.textContent;        
            
            var tdNumero = animal.querySelector(".info-numero");
            var numero = tdNumero.textContent;
            
            var expressao = new RegExp(this.value, "i");
          
           
            if (expressao.test(nome) || expressao.test(numero) ) {
            	animal.classList.remove("invisivel");                   
            } else {
            	animal.classList.add("invisivel");
            }
                      
            
        }
    } else {
        for (var i = 0; i < animais.length; i++) {
            var animal = animais[i];
            animal.classList.remove("invisivel");
        }
    }
    
});