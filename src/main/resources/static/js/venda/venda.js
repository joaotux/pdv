$(function() {

	// responsável por adicionar um novo produto á venda
	$(".formdavenda").on(
			"click",
			".js-addvenda-produto",
			function(event) {
				event.preventDefault();

				var codigopro = $("#codigoProduto").val();
				var link = $(".js-addvenda-produto").attr("href");

				var balanca = $("#codigoProduto option:selected").attr(
						'data-balanca');

				var valor = 0;

				// verifica se tem balanca
				if (balanca == 'SIM') {
					valor = prompt("Informe o valor");
				} 

				var linkNovo = link + "=" + codigopro.toString()
						+ "&valorBalanca=" + valor;

				var response = $.ajax({
					url : linkNovo,
					type : 'POST',
					
					beforeSend : function() {
						$(".carrega").html(
						"<p class='carregando'></p>");
					},
					
					success : function(e) {
						$(".carrega").empty();
						
						$("#tabProdutoVenda").load(" #tabProdutoVenda");
						$("#total_produtos").load(" #total_produtos");
						
						if (e == "Venda fechada")
							alert("Venda Fechada");
						
					}
				});

				response.fail(function(e) {
					$(".carrega").empty();
					alert("Falha no processo");
				});
			});

	// responsável por remover um produto da venda
	$('#tabProdutoVenda').on(
			'click',
			'a.js-removeproduto-venda',
			function(event) {
				event.preventDefault();

				var button = $(event.currentTarget);
				var posicaoProd = button.attr('data-posicaoProd');

				var link = $(".js-removeproduto-venda").attr("href") + "="
						+ posicaoProd;

				var confirma = confirm("Remover produto?");

				if (confirma == true)

					var response = $.ajax({
						url : link,
						type : 'POST',
						
						beforeSend : function() {
							$(".carrega").html(
							"<p class='carregando'></p>");
						},
						
						
						success : function(e) {
							$(".carrega").empty();
							$("#tabProdutoVenda").load(" #tabProdutoVenda");
							$("#total_produtos").load(" #total_produtos");
							
							if (e == "Venda fechada")
								alert("Venda Fechada");
						}
					});

				response.fail(function(e) {
					$(".carrega").empty();
					alert(e);
				});
			});

	// responsável por realizar o fechamanto da venda
	$('.formdavenda').on(
			'click',
			'.btn-pagamento',
			function(event) {
				event.preventDefault();

				var codvenda = $('#codigoVenda').val();

				var valores = new Array();
				$('.dadoslinha input').each(function() {
					var dados = $(this).val();
					valores.push(mascaraValor(dados));
				});

				var titulos = new Array();
				$('.dadoslinha select').each(function() {
					var titulo = $(this).val();
					titulos.push(titulo);
				});

				var form = $('#form_pagamento').serialize() + "&valores="
						+ valores + "&titulos=" + titulos + "&venda="
						+ codvenda;
				
				var link = $('.btn-pagamento').attr('href');

				$.ajax({
					url : link,
					type : 'POST',
					data : form,
					
					beforeSend : function() {
						$(".carrega").html(
						"<p class='carregando'></p>");
					},
					
					success : function(e) {
						$(".carrega").empty();
						alert(e);
					},

					complete : function() {
						$(".modalPagamento").modal('toggle');
						location.reload(true);
					},

					error : function(jqXHR, status, error) {
						$(".carrega").empty();
						var err = eval("(" + jqXHR.responseText + ")");
						alert(err.message);
					}
				});
			});

	// Responsável por identificar a forma de pagamento e disponibilizar os
	// inputs para os valores
	$('#pagamento')
			.change(
					function() {

						var formPagamento = $("#pagamento").val();
						var link = $('#link-pagamento-venda').attr('href');

						$
								.ajax({
									url : link
											+ formPagamento,
									type : 'get',

									beforeSend : function() {
										$(".carrega").html(
												"<p class='carregando'></p>");
									},

									success : function(e) {
										$(".carrega").empty();

										$("#tipopagamento .registrolinha")
												.remove();

										// verifico a quantidade de vezes do
										// plano e adiciono um input para cada
										var i;
										for (i = 0; i < e; i++) {
											$("#tipopagamento")
													.append(
															"<div class='registrolinha' id="
																	+ i
																	+ "><div class='row'><div class='col-md-7 dadoslinha'><div class='form-group col-md-3'>"
																	+ "<label for='valor'>Valor</label><input type='text' name='valor_parcela[]' class='form-control vlparcela' id=" + i + "/></div>"
																	+ "<div class='form-group col-md-4'>Titulo<select name='titulo[]' id='titulo' class='form-control selectnovo'></select></div>"
																	+ "</div></div>");

											// adiciono a mascara de R$ para o
											// input adicionado
											$(".vlparcela").maskMoney({
												prefix : 'R$ ',
												allowNegative : true,
												thousands : '.',
												decimal : ',',
												affixesStay : false
											});

											var total = $('#totalid').val();

											var vl1 = parseFloat(mascaraValor(total));

											// incremento +1 porque o contador
											// inicia em zero
											i = i + 1;

											vl1 = (vl1 / i).toFixed(2).replace(
													'.', ',');

											// passo o valor dividido pela
											// quantidade de parcelas
											$("input[name='valor_parcela[]']")
													.val(vl1);
											i--; // volto o valor atual dele
											// para não dar problema na
											// sequência
										}
									},

									error : function(jqXHR, status, error) {
										$(".carrega").empty();

										var err = eval("(" + jqXHR.responseText
												+ ")");
										alert(err.message);
									}
								});

						// busco os estilos DIN, CARTÃO... e os recebo em
						// formato JSON, assim munto o select no front-end
						
						var link_titulos = $('#link-titulos').attr('href');
						$.ajax({
							dataType : 'json',
							url : link_titulos,

							success : function(e) {
								var $select = $(".selectnovo");
								$select.find('option').remove();
								$.each(e, function(key, value) {
									$select.append("<option value="
											+ value['codigo'] + ">"
											+ value['descricao'] + "</option>");
								})
							}
						});
					});

	// Responsável por calcular o desconto
	$(".valorpago").blur(function() {
		var total = $('#vlprod').val();
		var recebido = $('.valorpago').val();

		var troco = mascaraValor(recebido) - mascaraValor(total);

		if (troco < 0)
			troco = 0;

		$(".vltroco").maskMoney({
			prefix : 'R$ ',
			allowNegative : true,
			thousands : '.',
			decimal : ',',
			affixesStay : false
		});

		$("input[name='troco']").val(troco.toFixed(2).replace('.', ','));
	});

	// responsável por adicionar valor dos campos do modal de pagamento
	$('#btn-venda').on('click', function(e) {

		// $("input[name='valor_pago']").val("");

		var total = $('#totalid').val();
		
		console.log("valor " + total);

		$("input[name='valor_produtos']").val(total);
		// $("input[name='valor_pago']").val(total);
	});

	// responsável por limpar o campo de valor recebido do modal de pagar
	$('.btn-limpa-valor').on('click', function(event) {
		event.preventDefault();

		// $('input[name="valor_pago"]').val("");
	});

	$(function() {
		var status = $("#statusPedido").val();

		if (status == 'FECHADA') {
			$("#btn-salva").prop("disabled", true);
			$("#btn-venda").prop("disabled", true);
			$("#codigoProduto").prop("disabled", true);
			$("#cliente").prop("disabled", true);
			$("#observacao").prop("disabled", true);
		}
	});

	function mascaraValor(valor) {
		valor = valor.toString().replace(/\D/g, "");
		valor = valor.toString().replace(/(\d)(\d{2})$/, "$1.$2");
		return valor
	}
});