$(document).ready(function() {
	
	$('.body-maquinacartao').on('click','.btn-remove-maquina', function(event) {
		event.preventDefault();
		
		var codmaquina = $(this).data('codmaquina');
		var link = $('.btn-remove-maquina').attr('href') + codmaquina;
		
		var confirmar = confirm("Tem certeza que deseja excluir esta máquina?");
		
		if(confirmar == true)
		
		$.ajax({
			url : link,
			type : 'delete',
			
			beforeSend : function() {
				$(".carrega").html(
				"<p class='carregando'></p>");
			},
			
			success : function(e) {
				$(".carrega").empty();
				alert(e);
				$('.table-maquina').load(' .table-maquina');
			},
			
			error : function(jqXHR, status, error) {
				$(".carrega").empty();
				var err = eval("(" + jqXHR.responseText
						+ ")");
				alert(err.message);
			}
		});
	});
});