$(document).ready(function() {
	
	// Responsável por ativar a opção de selecionar o cadastro de banco quando o 
	// caixa é definido como sendo um banco.
	$('#titulo_tipo').change(function() {
		var tipo = $('#titulo_tipo').val();
		var link = $('#link_maquina').attr('href');
		
		$('.maquina-info select').remove();
		$('.maquina-info label').remove();
		
		//se for cartão crédito ou debito, insere o select dos bancos
		if(tipo == 2 || tipo == 3) {
			$('.maquina-info').append("<label for='maquina'>Máquina</label><select name='maquina' class='form-control select_maquinacartao' th:field='*{maquina}' ></select>");
		}
		
		// Responsável por buscar os registros dos bancos e inseri-los no select
		$.ajax({
			dataType : 'json',
			url : link,
			type : 'get',
			
			beforeSend : function() {
				$(".carrega").html(
				"<p class='carregando'></p>");
			},
			
			success : function(e) {
				$(".carrega").empty();
				
				var $select = $(".select_maquinacartao");
				
				$select.find('option').remove();
				
				var i = 0;
				$select.append("<option value=''></option>");
				$.each(e, function(key, value) {
					$select.append("<option value=" + value['codigo'] + "> " + value['descricao'] + " </option>");
					i++;
				})
			},
			
			error : function(jqXHR, status, error) {
				$(".carrega").empty();
				var err = eval("(" + jqXHR.responseText	+ ")");
				alert(err);
			}
		});
	});
	
	
	$('#body-titulos').on('click', '#btn-excluir-titulo', function(event) {
		event.preventDefault();
		
		var link = $('#btn-excluir-titulo').attr('href');
		console.log(link);
		
		var confirma = confirm("Tem certeza que deseja remover este registro?");

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
				$('.table-titulos').load(" .table-titulos");
			},
			
			error : function(jqXHR, status, error) {
				$(".carrega").empty();
				var err = eval("(" + jqXHR.responseText	+ ")");
				alert(err);
			}
		});
	});
});