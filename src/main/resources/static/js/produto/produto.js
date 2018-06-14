$(document).ready(function() {

	$("#form_produto").validate({

		rules : {
			descricao : {
				required : true,
				minlength : 3,
				maxlength : 150
			},
			fornecedor : {
				required : true
			},
			categoria : {
				required : true
			},
			grupo : {
				required : true
			},
			valor_venda : {
				required : true
			}
		},

		errorLabelContainer : $('#mensagemErro'),

		submitHandler : function(form) {
			var dados = $(form).serialize();
			var link = $('#link-produto').attr('href');
			
			$.ajax({
				type : 'POST',
				url : link,
				data : dados,
				
				beforeSend : function() {
					$(".carrega").html(
					"<p class='carregando'></p>");
				},
				
				success : function(data) {
					$(".carrega").empty();
					alert(data);
				}
			});
			
			return false;
		}
	});

});