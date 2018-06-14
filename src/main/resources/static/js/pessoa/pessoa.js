$(function() {

	$("#form_pessoa").validate({
		rules : {
			nome : {
				required : true,
				minlength : 5
			},

			cpfcnpj : {
				required : true,
				maxlength : 18
			},

			data_nacimento : {
				required : false
			},

			cidade : {
				required : true
			},

			rua : {
				required : true
			},

			bairro : {
				required : true
			},

			numero : {
				required : true
			},

			fone : {
				required : true
			}
		},

		errorLabelContainer : $('#mensagemErro'),

		submitHandler : function(form) {
			var codendereco = $('#codendereco').val();
			var codfone = $('#codfone').val();
			
			var dados = $(form).serialize();
			console.log(dados);
			dados += '&codfone=' + codfone + '&codendereco=' + codendereco;
			
			var link = $('#link_pessoa').attr('href');

			$.ajax({
				type : 'POST',
				url : link,
				data : dados,

				beforeSend : function() {
					$(".carrega").html("<p class='carregando'></p>");
				},

				success : function(data) {
					$(".carrega").empty();
					alert(data);
				},

				error : function(jqXHR, status, error) {
					$(".carrega").empty();
					var err = eval("(" + jqXHR.responseText + ")");
					alert(err.message);
				}
			});

			return false;
		}
	});
});