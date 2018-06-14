$(function() {

	// responsável por limpar os dados do modalregratributaria
	$('.btn-nova-regra').on('click', function(e) {
		document.getElementById("tipo").value = '';
		document.getElementById("uf").value = '';
		document.getElementById("cfop").value = '';
		document.getElementById("cst_csosn").value = '';
		document.getElementById("cst_pis").value = '';
		document.getElementById("cst_cofins").value = '';

		$("input[name='pis']").val("");
		$("input[name='cofins']").val("");
		$("input[name='aliq_ipi']").val("");
		$("input[name='aliq_icms']").val("");
	});

	// responsável por cadastrar nova regra
	$('.body-regras-fiscais').on('click', '.btn-salva-regra', function(event) {
		event.preventDefault();

		var dados = $('#form_regra').serialize();
		var link = $('.btn-salva-regra').attr('href');

		$.ajax({
			type : 'POST',
			url : link,
			data : dados,
			
			beforeSend : function() {
				$(".carrega").html(
				"<p class='carregando'></p>");
			},
			

			success : function(e) {
				$(".carrega").empty();
				alert(e);
			},

			complete : function(e) {
				$('.fiscal-dados-regras').load(' .fiscal-dados-regras');
				$('.modalregratributaria').modal('toggle');

				$("input[name='pis']").val("");
				$("input[name='cofins']").val("");
				$("input[name='aliq_ipi']").val("");
				$("input[name='aliq_icms']").val("");
			},

			error : function(jqXHR, status, error) {
				$(".carrega").empty();
				var err = eval("(" + jqXHR.responseText + ")");
				alert(err.message);
			}
		})
	});

	// responsável por deletar regra
	$('.fiscal-dados-regras').on('click', '.btn-delete-regra', function(event) {
		event.preventDefault();

		var codigo = $(this).data('codigo');
		var link = $('.btn-delete-regra').attr('href') + codigo;

		var confirma = confirm("Deseja mesmo remover esta regra?");

		if (confirma == true)

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
					$('.fiscal-dados-regras').load(' .fiscal-dados-regras');
				},

				error : function(jqXHR, status, error) {
					$(".carrega").empty();
					var err = eval("(" + jqXHR.responseText + ")");
					alert(err.message);
				}
			});
	});

	// responsável por editar regra
	$('.fiscal-dados-regras')
			.on(
					'click',
					'.btn-edita-regra',
					function(event) {
						event.preventDefault();

						var codigo = $(this).data('codregra');
						var link = $('.btn-edita-regra').attr('href') + codigo;
						
						
						$.ajax({
									url : link,
									type : "PUT",
									
									beforeSend : function() {
										$(".carrega").html(
										"<p class='carregando'></p>");
									},
									

									success : function(e) {
										$(".carrega").empty();
										
										$('.modalregratributaria')
										.modal('show');
										
										document.getElementById("uf").value=e.uf.codigo;
										document.getElementById("cfop").value=e.cfop.codigo;
										document.getElementById("tipo").value = e.tipo;
										document.getElementById("cst_csosn").value=e.cst_csosn.codigo;
										document.getElementById("cst_pis").value = e.cst_pis.codigo;
										document.getElementById("cst_cofins").value = e.cst_cofins.codigo;
										document.getElementById("cst_ipi").value = e.cst_ipi.codigo;
										
										$('input[name="codigo_regra"]').val(e.codigo);
										$('input[name="cofins"]').val(e.cofins);
										$('input[name="aliq_ipi"]').val(e.aliq_ipi);
										$('input[name="aliq_icms"]').val(e.aliq_icms);
										$('input[name="pis"]').val(e.pis);
										
									},

									error : function(jqXHR, status, error) {
										$(".carrega").empty();
										var err = eval("(" + jqXHR.responseText	+ ")");
										alert(err.message);
									}
								});
					});
});
