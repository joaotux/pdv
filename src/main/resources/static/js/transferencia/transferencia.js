$(document).ready(
		function() {

			origem = 0;

			$('#btnTransferencia').on('click', function(event) {
				event.preventDefault();

				origem = $('#codigoCaixa').val();

				$("input[name='origem']").val(origem);
				$("input[name='valor']").val("");
				$("option[name='destino']").val("");
				$("textarea[name='observacao']").val("");
			});

			$('.btn-transferencia').on(
					'click',
					function(event) {
						event.preventDefault();

						var valor = $('#vltotal').val();
						var destino = $('#iddestino').val();
						var obs = $('#idobservacao').val();
						var link = $('.btn-transferencia').attr('href')
								+ "?valor=" + mascaraValor(valor) + "&origem="
								+ origem + "&destino=" + destino + "&obs="
								+ obs;

						$.ajax({
							url : link,
							type : 'POST',
							
							beforeSend : function() {
								$(".carrega").html(
								"<p class='carregando'></p>");
							},
							

							success : function(e) {
								$(".carrega").empty();
								alert(e);
								$('.modalTransferencia').modal('toggle');
							},

							complete : function(e) {
								$('#tabLancamentos').load(' #tabLancamentos');
								$('.saldosCaixa').load(" .saldosCaixa");
							},

							error : function(jqXHR, status, error) {
								$(".carrega").empty();
								var err = eval("(" + jqXHR.responseText + ")");
								alert(err.message);
							}
						});
					});

			function mascaraValor(valor) {
				valor = valor.toString().replace(/\D/g, "");
				valor = valor.toString().replace(/(\d)(\d{2})$/, "$1.$2");
				return valor
			}
		});