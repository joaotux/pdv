$(function() {

	$("#form_grupo").validate({
		rules : {
			descricao : {
				required : true,
				minlength : 4,
				maxlength : 50
			}
		}
	});
});