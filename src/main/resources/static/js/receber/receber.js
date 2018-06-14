$(document).ready(
		function() {
			// responsável por realizar o recebimento da parcela

			receber = 0;

			$(".listaParcelas")
					.on(
							'click',
							'.btnRecebeParcela',
							function(event) {
								event.preventDefault();

								receber = $(this).data("codreceber");

								var total = $(this).data("vlreceber");

								$("input[name='valor_acrescimo']").val("");

								$("input[name='valor_desconto']").val("");

								$("input[name='valor_total']").val(
										parseFloat(total).toFixed(2).replace(
												".", ","));
							});

			// Responsável por finalizar o recebimento
			$(".btn-recebimento").on(
					'click',
					function(event) {
						event.preventDefault();

						var vltotalPago = $("#vltotal").val();
						var vlacrescimo = $("#acre").val();
						var vldesconto = $("#desc").val();

						var link = $("#btnRecebeParcela").attr("href")
								+ "?receber=" + receber + "&vltotalPago="
								+ mascaraValor(vltotalPago) + "&vlacre="
								+ mascaraValor(vlacrescimo) + "&vldesc="
								+ mascaraValor(vldesconto);

						var response = $.ajax({
							url : link,
							type : 'post',
							
							beforeSend : function() {
								$(".carrega").html(
								"<p class='carregando'></p>");
							},
							
							success : function(e) {
								$(".carrega").empty();
								if (e == "ok")
									alert("Recebimento realizado com sucesso");
								else if (e == "titulo quitado")
									alert("Este titulo já esta quitado");
								else if (e == "valor invalido")
									alert("O valor de recebimento é inválido");
								else if (e == "caixa fechado")
									alert("Nenhum caixa aberto");

								$(".modalReceber").modal('toggle');
								$(".table-receber").load(" .table-receber");	
							},
							
							error : function(jqXHR, status, error) {
								$(".carrega").empty();
								alert("Falha ao tentar receber, chame o suporte");
								
							}
						});
					});

			// pega os receber checked e redireciona para a tela do recebimento
			$('.btn-receber-varios').on(
					'click',
					function(e) {
						e.preventDefault();

						var codpessoa = $("#codpessoa").val();

						var marcados = $('input:checked').map(function() {
							return this.value;
						}).get().join(', ');

						var link = $('.btn-receber-varios').attr("href")
								+ "?codpessoa=" + codpessoa + "&parcelas="
								+ marcados;

						$.ajax({
							url : link,
							type : 'GET',
							
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

			// Responsável por listar os titulos do clinte selecionado
			$('.btn-busca-cliente').on(
					'click',
					function(e) {
						var codpessoa = $('.codcliente').val();

						$('#formbuscacliente').append(
								'<input type="hidden" name="codigo" value="'
										+ codpessoa + '" />');

						$('#formbuscacliente').submit();
					});

			function mascaraValor(valor) {
				valor = valor.toString().replace(/\D/g, "");
				valor = valor.toString().replace(/(\d)(\d{2})$/, "$1.$2");
				return valor
			}
		});
