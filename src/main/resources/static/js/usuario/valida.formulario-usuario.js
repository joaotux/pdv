$(function() {

	$("#form_usuario").validate({
		rules : {
			user : {
				required : true,
				minlength : 4,
				maxlength : 20
			},
			
			senha : {
				required : true,
				minlength : 1,
				maxlength : 12
			},
			
			pessoa : {
				required : true
			}
		}
	});
});