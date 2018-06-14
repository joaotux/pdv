$(document).ready(
		function() {
			
			var status = $('#statusAjuste').val();
			
			if(status == 'PROCESSADO') {
				$('#obs').prop('disabled', true);
				$('#codigoProduto').prop('disabled', true);
			}

			$('.btn-novo-ajuste').on('click', function(event) {
				event.preventDefault();

				var link = $('.btn-novo-ajuste').attr('href');

				$.ajax({
					url : link,
					type : 'post',

					beforeSend : function() {
						$(".carrega").html("<p class='carregando'></p>");
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

			// Responsável por adicionar os produtos no ajuste
			$('.body_ajuste').on(
					'click', '.btn-addajuste-produto',
					function(event) {
						event.preventDefault();

						var codprod = $('#codigoProduto').val();
						var codajuste = $('#codajuste').val();
						
						var qtd_alterar = prompt("Informe a quantidade que deseja alterar");
						
						var link = $('.btn-addajuste-produto').attr('href')
								+ '?codprod=' + codprod + '&codajuste='
								+ codajuste + "&qtd_alterar=" + qtd_alterar;
						
						$.ajax({
							url : link,
							type : 'post',

							beforeSend : function() {
								$(".carrega")
										.html("<p class='carregando'></p>");
							},

							success : function(e) {
								$(".carrega").empty();
								$('#tabela-produtos').load(' #tabela-produtos');
							},

							error : function(jqXHR, status, error) {
								$(".carrega").empty();
								var err = eval("(" + jqXHR.responseText + ")");
								alert(err.message);
							}
						});
					});
			
			// Responsável por processar o ajuste
			$('.body_ajuste').on('click', '.btn-ajuste-processar', function(event) {
				event.preventDefault();
				
				var codajuste = $('#codajuste').val();
				var obs = $('#obs').val();
				
				var link = $('.btn-ajuste-processar').attr('href') + '?codajuste=' + codajuste + '&obs=' + obs;
				
				var confirmado = confirm("Tem certeza que deseja processar este ajuste?");
				
				if(confirmado == true)
				
				$.ajax({
					url : link,
					type : 'post',
					
					beforeSend : function() {
						$(".carrega")
						.html("<p class='carregando'></p>");
					},
					
					success : function(e) {
						$(".carrega").empty();
						
						alert(e);
						
						$('.body_ajuste').load(' .body_ajuste');
						$('#obs').prop('disabled', true);
						$('#codigoProduto').prop('disabled', true);
					},
					
					error : function(jqXHR, status, error) {
						$(".carrega").empty();
						var err = eval("(" + jqXHR.responseText + ")");
						alert(err.message);
					}
				});
			});
			
			// Responsável por cancelar um ajuste
			$('.body_ajuste').on('click', '.btn-ajuste-cancelar', function(event) {
				event.preventDefault();
				
				var codajuste = $('#codajuste').val();
				var link = $('.btn-ajuste-cancelar').attr('href') + codajuste;
				
				var confirmado = confirm("Tem certeza que deseja cancelar este ajuste?");
				
				if(confirmado == true)
				
				$.ajax({
					url : link,
					type : 'delete',
					
					beforeSend : function() {
						$(".carrega")
						.html("<p class='carregando'></p>");
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
			
			$('.body_ajuste').on('click', '.btn-remove-item-ajuste', function(event) {
				event.preventDefault();
				
				var codajuste = $('#codajuste').val();
				var coditem = $(this).data('coditem');
				
				var link = $('.btn-remove-item-ajuste').attr('href') + '?codajuste=' + codajuste + '&coditem=' + coditem;
				
				var confirmado = confirm("Tem certeza que deseja remover este item?");
				
				if(confirmado == true)
				
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
						$('#tabela-produtos').load(' #tabela-produtos');
					},
					
					error : function(jqXHR, status, error) {
						$(".carrega").empty();
						var err = eval("(" + jqXHR.responseText + ")");
						alert(err.message);
					}
				});
			});
		});