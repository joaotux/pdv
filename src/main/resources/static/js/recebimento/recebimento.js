$(document)
		.ready(
				function() {

					// verifica se o recebimento esta fechado e desbilita os
					// inputs
					processamento = $('#processamento').val();

					if (processamento == '' || processamento == null) {
						
					} else {
						$('#desc').prop('disabled', true);
						$('#acres').prop('disabled', true);
						$('#vlrecebido').prop('disabled', true);
						$('#titulo').prop('disabled', true);
					}

					// responsável pelo recebimento
					$('#body-recebimento').on(
							'click',
							'.btn-receber-titulo',
							function(event) {
								event.preventDefault();

								var total = $('#total').val();
								var desconto = $('#desc').val();
								var acrescimo = $('#acres').val();
								var vlrecebido = $('#vlrecebido').val();
								var codrece = $('#codreceber').val();
								var codtitulo = $('#titulo').val();

								var link = $('.btn-receber-titulo')
										.attr('href')
										+ '?receber='
										+ codrece
										+ "&total="
										+ mascaraValor(total)
										+ "&desconto="
										+ mascaraValor(desconto)
										+ "&acrescimo="
										+ mascaraValor(acrescimo)
										+ "&vlrecebido=" + mascaraValor(vlrecebido) + "&titulo=" + codtitulo;

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
										$('.tab-dados-parcelas').load(
										' .tab-dados-parcelas');
										$('#desc').prop('disabled', true);
										$('#acres').prop('disabled', true);
										$('#titulo').prop('disabled', true);
										$('#vlrecebido').prop('disabled', true);
									},

									error : function(jqXHR, success, error) {
										$(".carrega").empty();
										var err = eval("(" + jqXHR.responseText
												+ ")");
										alert(err.message);
									}
								});
							});

					// responsável pela exclusão do recebimento
					$('#body-recebimento')
							.on(
									'click',
									'.btn-cancelar-receber',
									function(event) {
										event.preventDefault();

										var link = $('.btn-cancelar-receber')
												.attr('href');

										var confirma = confirm("Confirmar cancelamento do receber?")

										if (confirma == true)

											$
													.ajax({
														url : link,
														type : 'PUT',
														
														beforeSend : function() {
															$(".carrega").html(
															"<p class='carregando'></p>");
														},
														
														success : function(e) {
															$(".carrega").empty();
															window.location.href = e;
														},

														error : function(jqXHR,
																success, error) {
															$(".carrega")
																	.empty();
															var err = eval("("
																	+ jqXHR.responseText
																	+ ")");
															alert(err.message);
														}
													});

									});

					function mascaraValor(valor) {
						valor = valor.toString().replace(/\D/g, "");
						valor = valor.toString().replace(/(\d)(\d{2})$/,
								"$1.$2");
						return valor
					}
				});
