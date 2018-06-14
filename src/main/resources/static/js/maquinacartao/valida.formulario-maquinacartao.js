$(function() {

	$("#form_maquinacartao").validate({
		rules : {
			descricao : {
				required : true,
				minlength : 4,
				maxlength : 50
			},

			banco : {
				required : true
			}
		}
	});
});