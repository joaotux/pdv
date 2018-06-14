$(function() {

	// Responsável por cadastrar a nota fiscal
	$('.btn-cria-nota').on('click', function(event) {
		event.preventDefault();

		var dados = $('#form_notafiscal').serialize();

		var link = $('#link-notafiscal').attr('href');

		$.ajax({
			url : link,
			type : 'post',
			data : dados,
			
			beforeSend : function() {
				$(".carrega").html(
				"<p class='carregando'></p>");
			},
			
			success : function(e) {
				$(".carrega").empty();
				var a = e.replace('{Location=[', '');
				var b = a.replace(']}', '');
				console.log(b);
				window.location.href = b;
			},

			error : function(jqXHR, status, error) {
				$(".carrega").empty();
				var err = eval("(" + jqXHR.responseText + ")");
				alert(err.message);
			}
		});
	});

	// Responsável por inserir itens na nota
	$('.body-itens-nota').on(
			'click',
			'.btn-addnota-produto',
			function(event) {
				event.preventDefault();

				var codpro = $('#codigoProduto').val();
				var nota = $('#codnota').val();
				var tipo = $('#tipo').val();

				var qtdItem = prompt("Informe a quantidade");

				var link = $('#link-nota-item').attr('href') + "?codprod=" + codpro + "&nota="
						+ nota + "&qtd=" + qtdItem + "&tipo=" + tipo;

				$.ajax({
					url : link,
					type : 'post',
					
					beforeSend : function() {
						$(".carrega").html(
						"<p class='carregando'></p>");
					},
					
					success : function(e) {
						$(".carrega").empty();
						$('.produtos-nota').load(' .produtos-nota');
						$('.totais_nota').load(' .totais_nota');
					},

					error : function(jqXHR, status, error) {
						$(".carrega").empty();
						var err = eval("(" + jqXHR.responseText + ")");
						alert(err.message);
					}
				});
			});

	$('.body-itens-nota').on('click', '.btn-remove-item', function(event) {
		event.preventDefault();

		var notaitem = $(this).data('notaitem');
		var nota = $(this).data('nota');
		var link = $('#link-nota-item').attr('href') + "?notaitem=" + notaitem + "&nota=" + nota;

		var acao = confirm("Deseja remover o item da nota?");

		if (acao == true)

			$.ajax({
				url : link,
				type : 'DELETE',
				
				beforeSend : function() {
					$(".carrega").html(
					"<p class='carregando'></p>");
				},
				

				success : function(e) {
					$(".carrega").empty();
					$('.produtos-nota').load(' .produtos-nota');
					$('.totais_nota').load(' .totais_nota');
				},

				error : function(jqXHR, status, error) {
					$(".carrega").empty();
					var err = eval("(" + jqXHR.responseText + ")");
					alert(err.message);
				}
			});
	});

	// Responsável pela emissão da nfe
	$('.btn-emitir-nfe').on('click', function(event) {
		event.preventDefault();

		var link = $('.btn-emitir-nfe').attr('href');

		$.ajax({
			url : link,
			type : 'post',
			
			beforeSend : function() {
				$(".carrega").html(
				"<p class='carregando'></p>");
			},
			
			success : function(e) {
				$(".carrega").empty();
				alert(e);
			},

			error : function(jqXHR, status, error) {
				$(".carrega").empty();
				var err = eval("(" + jqXHR.responseText + ")");
				alert(err.message);
			}
		});
	});

});