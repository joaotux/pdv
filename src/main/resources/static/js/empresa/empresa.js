$(function() {

	$("#form_empresa").validate({
		rules : {
			nome : {
				required : true,
				minlength : 4,
				maxlength : 255
			},

			nome_fantasia : {
				required : true,
				minlength : 4,
				maxlength : 255
			},

			cnpj : {
				required : true
			},

			regime_tributario : {
				required : true
			},

			cidade : {
				required : true
			},

			rua : {
				required : true
			}
		},

		errorLabelContainer : $('#mensagemErro'),

		submitHandler : function(form) {
			var dados = $(form).serialize();
			var link = $('#link-empresa').attr('href');
			
			$.ajax({
				url : link,
				type : 'post',
				data : dados,
				
				beforeSend : function() {
					$(".carrega").html(
					"<p class='carregando'></p>");
				},

				success : function(data) {
					$(".carrega").empty();
					$('.body_form').load(' .body_form');
				},

				error : function(jqXHR, status, error) {
					$(".carrega").empty();
					var err = eval("(" + jqXHR.responseText + ")");
					alert(err.message);
				}
			});
		}
	});
});