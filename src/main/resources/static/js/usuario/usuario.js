$(function() {
	var codigo = $('#codigoUsuario').val();

	if (codigo == null || codigo == "") {
		$("#usuariovalor").prop("disabled", false);
		$("#pessoavalor").prop("disabled", false);

		$("#pesDado").prop("disabled", true);
		$("#userDado").prop("disabled", true);
	} else {
		$("#usuariovalor").prop("disabled", true);
		$("#pessoavalor").prop("disabled", true);

		$("#pesDado").prop("disabled", false);
		$("#userDado").prop("disabled", false);
	}

	// responsável por adicionar um novo grupo ao usuário
	$('.body-usuario').on('click', '.js-add-grupo', function(event) {
		event.preventDefault();

		var codigoUsu = $("#codigoUsuario").val();
		var codigoGru = $("#codigoGrupo").val();
		var link = $(".js-add-grupo").attr("href");
		var linkNovo = link + "=" + codigoGru.toString();

		var response = $.ajax({
			url : linkNovo,
			type : 'POST',
			
			beforeSend : function() {
				$(".carrega").html(
				"<p class='carregando'></p>");
			},
			
			success : function(e) {
				$(".carrega").empty();
				
				if (e == "grupo vazio")
					alert("Favor, selecione um grupo");
				else if (e == "ok") {
					$("#tabela-grupos").load(" #tabela-grupos");
					alert("Grupo adicionado com sucesso");
				}
				else if (e == "ja existe")
					alert("Grupo já esta adicionado a este usuário");
			},
			
			error : function(e) {
				$(".carrega").empty();
				alert("Erro " + e);
			}
		});
	});

	// responsável por remover um grupo do usuário
	$('.body-usuario').on('click', 'a.js-remove-grupo', function(event) {
		event.preventDefault();

		var button = $(event.currentTarget);
		var codigoGru = button.attr('data-codigoGrupo');
		var link = $(".js-remove-grupo").attr("href");
		var linkNovo = link + "=" + codigoGru;

		var response = $.ajax({
			url : linkNovo,
			type : 'PUT',
			
			beforeSend : function() {
				$(".carrega").html(
				"<p class='carregando'></p>");
			},
			
			success : function(e) {
				$(".carrega").empty();
				$("#tabela-grupos").load(" #tabela-grupos");
				
				if (e == "ok") {
					alert("Grupo removido com sucesso");
				}
			},
			
			error : function(e) {
				$(".carrega").empty();
				alert("Erro " + e);
			}
		});
	});
});
