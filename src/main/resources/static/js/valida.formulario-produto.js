$(function() {

	$("#form_produto").validate({
		rules : {
			descricao : {
				required : true,
				minlength : 4,
				maxlength : 250
			},
			
			valor_venda : {
				required : true
			}
		}
	});
});