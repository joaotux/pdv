$(function() {

	$("#form_categoria").validate({
		rules : {
			descricao : {
				required : true,
				minlength : 4,
				maxlength : 50
			}
		}
	});
});