$(document).ready(function() {
	
	// Responsável por realizar o processamento
	$('#body-cartao').on('click', '.btn-processar-cartao', function(event) {
		event.preventDefault();

		var codigo = $(this).data('codlancamento');
		var link = $('.btn-processar-cartao').attr('href') + codigo;
		
		var confirmado = confirm("Tem certeza que deseja processar o registro de código " + codigo + "?");

		if(confirmado == true)
		
		$.ajax({
			url : link,
			type : 'put',
			
			beforeSend : function() {
				$(".carrega").html(
				"<p class='carregando'></p>");
			},
			
			success : function(e) {
				$(".carrega").empty();
				alert(e);
				$('.table-dados-cartoes').load(' .table-dados-cartoes');
			},

			error : function(jqXHR, success, error) {
				$(".carrega").empty();
				var err = eval("(" + jqXHR.responseText + ")");
				alert(err.message);
			}
		});
	});
	
	// Responsável por realizar a antecipação
	$('#body-cartao').on('click', '.btn-antecipar-cartao', function(event) {
		event.preventDefault();
		
		var codigo = $(this).data('codlancamento');
		var link = $('.btn-antecipar-cartao').attr('href') + codigo;
		
		var confirmado = confirm("Tem certeza que deseja antecipar o registro de código " + codigo + "?");
		
		if(confirmado == true)
		
		$.ajax({
			url : link,
			type : 'put',
			
			beforeSend : function() {
				$(".carrega").html(
				"<p class='carregando'></p>");
			},
			
			success : function(e) {
				$(".carrega").empty();
				alert(e);
				$('.table-dados-cartoes').load(' .table-dados-cartoes');
			},
			
			error : function(jqXHR, success, error) {
				$(".carrega").empty();
				var err = eval("(" + jqXHR.responseText + ")");
				alert(err.message);
			}
		});
	});
});