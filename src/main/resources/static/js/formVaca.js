$("#formVaca").submit(function(e) {

	var form = $(this);
	var url = form.attr('action');

	$.ajax({
		type : "POST",
		url : url,
		data : form.serialize(), // serializes the form's elements.
		beforeSend : function(data){
		//isso aqui bloqueia todos os campos do formulario para o usuario nao clicar
		    $('#formVaca').find('button, a').attr('disabled', 'disabled');
		},
		success : function(data) {
			
			
			document.getElementById('idvaca').value = data["id"];
			document.getElementById('autocompleteVacas').value = data["nome"];


            //mensagem de alerta é exibida por 3 segundos e se fecha sozinha
			$('#msg-sucesso').show();
			setTimeout(function(){
			    $('#modalVaca').modal('hide'); //escondo o modal
			    $('#formVaca input').val(""); //limpa os inputs
                $('#msg-sucesso').hide(); //esconde o alert
                $('#formVaca').find('button, a').removeAttr('disabled'); // desbloqueia os botoes
			}, 3000);


		}
	});
	e.stopPropagation();
	e.preventDefault(); // avoid to execute the actual submit of the form.
});