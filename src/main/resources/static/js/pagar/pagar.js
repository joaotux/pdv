$(document).ready(
		function() {

			$('.btnAbreModal').on('click', function(e) {
				// limpa os campos do modal
				$("input[name='valor_total']").val("");
				$("input[name='data_vencimento']").val("");
				$("textarea[name='observacao']").val("");
				$("option[name='fornecedor']").val("");
			});

			// cadastra despesa
			$('.secDespesas').on(
					'click',
					'.btn-despesa',
					function(event) {
						event.preventDefault();

						var codFornecedor = $('#codFornecedor').val();
						var valor = $('#vltotalDespesa').val();
						var dataVenciemnto = $("#dataVencimento").val();
						var obs = $('#obs').val();
						var tipo = $('#despesatipo').val();

						var link = $('.btn-despesa').attr('href') + "?fornecedor=" + codFornecedor
								+ "&valor=" + mascaraValor(valor)
								+ "&vencimento=" + dataVenciemnto + "&obs="
								+ obs + "&tipo=" + tipo;

						var response = $.ajax({
							url : link,
							type : 'POST',
							
							beforeSend : function() {
								$(".carrega").html(
								"<p class='carregando'></p>");
							},
							

							success : function(e) {
								$(".carrega").empty();
								alert(e);
							},

							complete : function(e) {
								$(".modalDespesa").modal('toggle');
								$('.tab-despesas').load(' .tab-despesas');
							},

							error : function(jqXHR, status, error) {
								$(".carrega").empty();
								var err = eval("(" + jqXHR.responseText + ")");
								alert(err.message);
							}
						});
					});

			// váriaveis globais utilizadas para o pagamento
			valor = 0;
			codpagar = 0;
			$('.bodypagar')
					.on(
							'click',
							'.btn-modal-paga',
							function(event) {
								event.preventDefault();

								valor = $(this).data("vlrestante");
								codpagar = $(this).data("parcela");

								$("input[name='valorTotal']").val(
										parseFloat(valor).toFixed(2).replace(
												".", ","));

								$("input[name='valorpago']").val(
										parseFloat(valor).toFixed(2).replace(
												".", ","));

								$("input[name='acrescimo']").val("");
								$("input[name='desconto']").val("");

							});

			// Responsável por realizar o pagamento da despesa
			$('.bodypagar').on(
					'click',
					'.btn-pag-despesa',
					function(event) {
						event.preventDefault();

						var vlpago = $('#valorpago').val();
						var desc = $('#desc').val();
						var acre = $('#acres').val();
						var caixa = $('#caixa').val();

						var link = $('.btn-pag-despesa').attr('href')
								+ "?parcela=" + codpagar + "&vlpago="
								+ mascaraValor(vlpago) + "&desconto="
								+ mascaraValor(desc) + "&acrescimo="
								+ mascaraValor(acre) + "&caixa=" + caixa;

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
								$('.modalpagdespesa').modal('toggle');
							},

							complete : function(e) {
								$('.tab-despesas').load(" .tab-despesas");
							},

							error : function(jqXHR, status, error) {
								$(".carrega").empty();
								var err = eval("(" + jqXHR.responseText + ")");
								alert(err.message);
								console.log(status);
								console.log(error);
							}
						});
					});

			function mascaraValor(valor) {
				valor = valor.toString().replace(/\D/g, "");
				valor = valor.toString().replace(/(\d)(\d{2})$/, "$1.$2");
				return valor
			}
		});