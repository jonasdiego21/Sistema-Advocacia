var DP = DP || {};

DP.ADD = (function() {
		
		function ADD() {
			this.checkboxDocumento = $('.js-checkbox-documento');
			this.checkboxProva = $('.js-checkbox-prova');
			this.documento = $('.js-chip-documento');
			this.prova = $('.js-chip-prova');
		}
		
		ADD.prototype.iniciar = function() {
			this.checkboxDocumento.on('click', onClicadoDocumento.bind(this));
			this.checkboxProva.on('click', onClicadoProva.bind(this));
		}
		
		function onClicadoDocumento(e) {
			if($('.js-checkbox-documento').is(':checked'))      
				this.documento.append('<div class=\"chip\">' + $(e.currentTarget.attributes[2]).val() + '</div>');
			else       
				$('.js-checkbox-documento').remove($(e.currentTarget.attributes[3]).val());
		}
		
		function onClicadoProva(e) {
			console.log($(e.currentTarget.attributes[2]).val());
			this.prova.append('<div class=\"chip\">' + $(e.currentTarget.attributes[2]).val() + '</div>');
		}
		
		return ADD;
		
}());

$(function() {
	var dp = new DP.ADD();
	dp.iniciar();
});