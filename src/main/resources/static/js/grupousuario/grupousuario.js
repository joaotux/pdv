$(document).ready(
		function() {

			$('#body-form-grup-usu')
					.on(
							'click',
							'.btn-add-permissao',
							function(event) {
								event.preventDefault();

								var grupo = $('#codgrupo').val();
								var permissao = $('#codigoPermissao').val();
								var link = $('.btn-add-permissao').attr('href')
										+ '?grupo=' + grupo + '&permissao='
										+ permissao;

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
										$('#body-dados-permissoes').load(
												' #body-dados-permissoes');
									},

									error : function(jqXHR, status, error) {
										$(".carrega").empty();
										var err = eval("(" + jqXHR.responseText
												+ ")");
										alert(err.message);
									}
								});
							});

			$('#body-form-grup-usu').on(
					'click',
					'.btn-remove-permissao',
					function(event) {
						event.preventDefault();

						var grupo = $('#codgrupo').val();
						var codigo = $(this).data('codigo');
						var link = $('.btn-remove-permissao').attr('href')
								+ "?codigo=" + codigo + "&codgrupo=" + grupo;

						$.ajax({
							url : link,
							type : 'delete',

							beforeSend : function() {
								$(".carrega")
										.html("<p class='carregando'></p>");
							},

							success : function(e) {
								$(".carrega").empty();
								alert(e);
								$('#body-dados-permissoes').load(
										' #body-dados-permissoes');
							},

							error : function(jqXHR, status, error) {
								$(".carrega").empty();
								var err = eval("(" + jqXHR.responseText + ")");
								alert(err.message);
							}
						});
					});
		});