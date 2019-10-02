var Advocacia = Advocacia || {};
	
Advocacia.Global = (function(){
	
	function Global() {		
		
	}
	
	Global.prototype.iniciar = function() {		
		mensagemSucesso();
		mensagemErro();
		removePropriedadeSelected();	
	}
	
	function mensagemSucesso() {		
		var mensagemSucesso = document.getElementById('mensagem-sucesso');
		
		if (mensagemSucesso !== null) {			
			M.toast({
				html: mensagemSucesso.text, 
				classes: 'green rounded'
			});		
		}	
	}
	
	function mensagemErro() {	
		var mensagemErro = document.getElementsByClassName('mensagem-erro');
		
		if (mensagemErro !== null) {	
			for(var i = 0; i < mensagemErro.length; i++) {		
				M.toast({
					html: mensagemErro[i].text, 
					classes: 'red rounded'
				});		
			}	
		}	
	}
	
	/**
	 * Remove a propriedade selected
	 * de todos os primeiros filhos (option)
	 * de select.
	 */
	function removePropriedadeSelected() {
		$('select :first-child').removeAttr('selected');
	}
	
	return Global;
	
}());

Advocacia.CpfCnpj = (function() {
	
	CpfCnpj = function() {
		this.input = $('js-cpfcnpj');
	}
	
	CpfCnpj.prototype.iniciar = function() {
		var maskBehavior = function(val) {
			return val.replace(/\D/g, '').length === 11 ? '000.000.000-00' : '00.000.000/0000-00';
		};
		
		var options = {
			onKeyPress: function(val, e, field, options) {
				field.mask(maskBehavior.apply({}, arguments), options);
			}	
		};
		
		this.input.mask(maskBehavior, options);
	}
	
	return CpfCnpj;
	
}());

$(function(){
	var global = new Advocacia.Global();
	global.iniciar();	
	
	//var cpfCnpj = new Advocacia.CpfCnpj();
	//cpfCnpj.iniciar();
});