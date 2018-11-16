$("#formDiaBezerro").submit(function(e) {

	var form = $(this);
	var url = form.attr('action');

	$.ajax({
		type : "POST",
		url : url,
		data : form.serialize(), // serializes the form's elements.
		beforeSend : function(data){
		//isso aqui bloqueia todos os campos do formulario para o usuario nao clicar
		    $('#formDiaBezerro').find('button, a').attr('disabled', 'disabled');
		},
		success : function(data) {
			
			var dataId = data["id"];
			
			var idTr = document.getElementById(dataId);
			
			idTr.getElementsByClassName('pesoNoDia')[0].innerHTML = data["pesoNoDia"];
			idTr.getElementsByClassName('alturaNoDia')[0].innerHTML = data["alturaNoDia"];

            //mensagem de alerta é exibida por 3 segundos e se fecha sozinha
			$('#msg-sucesso').show();
			setTimeout(function(){
			    $('#modalDiaBezerro').modal('hide'); //escondo o modal
			    $('#formDiaBezerro input').val(""); //limpa os inputs
                $('#msg-sucesso').hide(); //esconde o alert
                $('#formDiaBezerro').find('button, a').removeAttr('disabled'); // desbloqueia os botoes
			}, 3000);


		}
	});
	e.stopPropagation();
	e.preventDefault(); // avoid to execute the actual submit of the form.
});