var Upload = Upload || {};

Upload.Arquivo = (function() {
	
	function Arquivo() {
		
	}
	
	Arquivo.prototype.iniciar = function() {
		var settings = {
				type: 'json',
				filelimit: 1,
				allow: '*.(.jpg|jpeg|png|doc|docx|xls|xlsx|ppt|pptx|avi|mp3|mp4|odt|ods|pdf)',
				action: '/arquivos',
				complete: function(resposta) {
					$('input[name=arquivo]').val(resposta.nome);
					$('input[name=contentType]').val(resposta.contentType);
					
					console.log('Nome: ' + resposta.nome + ' - Tipo: ' + resposta.contentType);
					/*
					$.ajax({
						url: '/arquivos/novo',
						method: 'POST',
						data: {
							'arquivo': resposta.nome,
							'content_type': resposta.contentType
							'processo_codigo': $('codigoProcesso');
						},
						success: function(r) {
							alert('Informações salvas com sucesso!');
						},
						error: function() {
							alert('Erro ao salvar o arquivo no banco');
						}
					});
					*/
				}
		};
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop($('#upload-drop'), settings);

	}
	
	return Arquivo;
	
})();

$(function(){
	var upload = new Upload.Arquivo();
    upload.iniciar();
});