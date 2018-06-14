$(function() {

	// responsável por desabilitar os componentes da tela de caixa
	var codigo = $('#dataFechamantoCaixa').val();

	if (codigo == null || codigo == "") {
		$("#btnSuprimento").prop("disabled", false);
		$("#btnSangria").prop("disabled", false);
		$("#btnTransferencia").prop("disabled", false);
		$("#btnfechacaixa").prop("disabled", false);
	} else {
		$("#btnSuprimento").prop("disabled", true);
		$("#btnSangria").prop("disabled", true);
		$("#btnTransferencia").prop("disabled", true);
		$("#btnfechacaixa").prop("disabled", true);
	}

	// limpa dados do modal de suprimento
	$('#btnSuprimento').on('click', function(e) {
		e.preventDefault();

		$("input[name='valor']").val("");
		$("input[name='observacao']").val("");
	});

	// Responsável por abrir o caixa
	$('.btn-abrir-caixa').on('click', function(event) {
		event.preventDefault();

		var link = $('.btn-abrir-caixa').attr('href');
		var form = $('#form_caixa').serialize();
		
		$.ajax({
			url : link,
			type : 'post',
			data : form,
			
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

	// responsavel pelo suprimento
	$('#secGerenciar').on(
			'click',
			'.btn-suprimento-caixa',
			function(event) {
				event.preventDefault();

				var valor = $('#idvalor').val();
				var obs = $('#idObs').val();
				var caixa = $(this).data('codcaixa');
				var link = $('.btn-suprimento-caixa').attr('href') + "?caixa="
						+ caixa + "&valor=" + mascaraValor(valor) + "&obs="
						+ obs;

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
						$('.modalSuprimento').modal('toggle');
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

	// limpa os inputs do modal da samgria quando o mesmo é aberto
	$('#btnSangria').on('click', function(event) {
		event.preventDefault();

		$("input[name='valor']").val("");
		$("input[name='observacao']").val("");
	});

	// responsável pela sangria
	$('#secGerenciar').on(
			'click',
			'.btn-Sangria-Sangria',
			function(event) {
				event.preventDefault();

				var valor = $('#idvl').val();
				var obs = $('#idobs').val();
				var caixa = $(this).data('codcaixa');
				var link = $('.btn-Sangria-Sangria').attr('href') + "?caixa="
						+ caixa + "&obs=" + obs + "&valor="
						+ mascaraValor(valor);

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
					},

					complete : function(e) {
						$('.modalSangria').modal('toggle');
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

	// responsável pelo fechamento do caixa
	$('.btn-fechar-caixa').on(
			'click',
			function(event) {
				event.preventDefault();

				var caixa = $(this).data("codcaixa");
				var senha = $('#admsenha').val();
				var link = $('.btn-fechar-caixa').attr("href") + "?caixa="
						+ caixa + "&senha=" + senha;

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

						if (e == 'Caixa fechado com sucesso') {
							$('#recarregastatus').load(' #recarregastatus');
							$("#btnSuprimento").prop("disabled", true);
							$("#btnSangria").prop("disabled", true);
							$("#btnTransferencia").prop("disabled", true);
							$("#btnfechacaixa").prop("disabled", true);
						}
					},

					complete : function(e) {
						$(".modalFechaCaixa").modal("toggle");
					},

					error : function(jqXHR, status, error) {
						$(".carrega").empty();
						var err = eval("(" + jqXHR.responseText + ")");
						alert(err.message);
					}
				});
			});

	// Responsável por adiconar os inputs de agência e conta quando o tipo BANCO
	// é selecionado
	$('#caixatipo')
			.change(
					function() {
						var tipo = $('#caixatipo').val();

						$('.conta-banco-info input').remove();
						$('.conta-banco-info label').remove();

						if (tipo == 'BANCO')
							$('.conta-banco-info')
									.append(
											"<label>Agência</label> <input type='text' name='agencia' id='agencia' class='form-control' th:field='*{agencia}' />"
													+ "<label>Conta</label> <input type='text' name='conta' id='conta' class='form-control' th:field='*{conta}' />");

						$('#agencia').inputmask({
							mask : [ '99999' ],
							keepStatic : true
						});

						$('#conta').inputmask({
							mask : [ '99999-9' ],
							keepStatic : true
						});
					});

	function mascaraValor(valor) {
		valor = valor.toString().replace(/\D/g, "");
		valor = valor.toString().replace(/(\d)(\d{2})$/, "$1.$2");
		return valor
	}
});