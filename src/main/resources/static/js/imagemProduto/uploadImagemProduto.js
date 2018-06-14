$(document).ready(function() {

	$("#btnSubmit").click(function(event) {
		event.preventDefault();

		// get form
		var form = $('#imageProdutoUpload')[0];

		var codigo = $('#codigoProduto').val();
		var link = $('.btn-upload-imagem').attr('href');

		// cria um objeto formData
		var data = new FormData(form);

		// exemplo de como add campos extrás no formulário
		data.append("codigo", codigo);

		// disabilita o submit do botão
		$('#btnSubmit').prop("disabled", false);

		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : link,
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,

			beforeSend : function() {
				$(".carrega").html("<p class='carregando'></p>");
			},

			success : function(data) {
				$(".carrega").empty();
				
				$("#resultado").text(data);
				console.log("SUCCESS : ", data);
				$("#btnSubmit").prop("disabled", false);
				$(".upload-imagem-modal").modal('toggle');
				$("#imagemDoProduto").load(" #imagemDoProduto");

			},
			error : function(e) {
				$(".carrega").empty();
				
				$("#resultado").text(e.responseText);
				console.log("ERROR : ", e);
				$("#btnSubmit").prop("disabled", false);

			}
		});
	});
});