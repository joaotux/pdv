$(function() {

	$("#form-grupusu").validate({
		rules : {
			nome : {
				required : true,
				minlength : 4,
				maxlength : 15
			},
			
			descricao : {
				required : true,
				minlength : 4,
				maxlength : 100
			}
		}
	});
});

