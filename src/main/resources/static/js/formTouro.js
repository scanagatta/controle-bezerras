$("#formTouro").submit(function(e) {

	var form = $(this);
	var url = form.attr('action');

	$.ajax({
		type : "POST",
		url : url,
		data : form.serialize(), // serializes the form's elements.
		beforeSend : function(data){
		//isso aqui bloqueia todos os campos do formulario para o usuario nao clicar
		    $('#formTouro').find('button, a').attr('disabled', 'disabled');
		},
		success : function(data) {
			
			
			document.getElementById('idtouro').value = data["id"];
			document.getElementById('autocompleteTouros').value = data["nome"];


            //mensagem de alerta é exibida por 3 segundos e se fecha sozinha
			$('#msg-sucesso1').show();
			setTimeout(function(){
			    $('#modalTouro').modal('hide'); //escondo o modal
			    $('#formTouro input').val(""); //limpa os inputs
                $('#msg-sucesso1').hide(); //esconde o alert
                $('#formTouro').find('button, a').removeAttr('disabled'); // desbloqueia os botoes
			}, 3000);


		}
	});
	e.stopPropagation();
	e.preventDefault(); // avoid to execute the actual submit of the form.
});