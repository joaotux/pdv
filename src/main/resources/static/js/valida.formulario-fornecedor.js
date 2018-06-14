$(function() {
			
			$("#form_fornecedor").validate({
				rules : {
					nome : {
						required : true,
						minlength : 5
					},

					nome_fantasia : {
						required : true,
						minlength : 5
					},
					
					cnpj : {
						required : true,
						maxlength : 18
					},
					
					inscricao_estadual : {
						required : false
					},
					
					ativo : {
						required : true
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
				}
			});
		});