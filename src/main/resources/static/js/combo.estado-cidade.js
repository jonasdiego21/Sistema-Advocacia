var Advocacia = Advocacia || {};
	
Advocacia.ComboEstado = (function(){
	
	function ComboEstado() {
		this.combo = $('#estado');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboEstado.prototype.iniciar = function(){
		this.combo.on('change', onEstadoAlterado.bind(this));	
	}
	
	function onEstadoAlterado() {
		this.emitter.trigger('alterado', this.combo.val());
	}
	
	return ComboEstado;
}());

Advocacia.ComboMunicipio = (function(){
	
	function ComboMunicipio(comboEstado){
		this.comboEstado = comboEstado;
		this.combo = $('#cidade');
		this.preloader = $('#lct-preloader-cidade');
		this.containerCidade = $('#ltc-container-cidade');
		this.codigoMunicipioSelecionado = $('#inputMunicipioSelecionado');
	}
	
	ComboMunicipio.prototype.iniciar = function(){
		
		reset.call(this);
		
		this.comboEstado.on('alterado', onEstadoAlterado.bind(this));
		
		var codigoEstado = this.comboEstado.combo.val();
		carregarMunicipios.call(this, codigoEstado);
		
	}
	
	function onEstadoAlterado(evento, codigoEstado) {
		this.codigoMunicipioSelecionado.val('');
		carregarMunicipios.call(this, codigoEstado);
	}
	
	function carregarMunicipios(codigoEstado) {
		
		if (codigoEstado) {
			
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				data: {'codigo': codigoEstado},
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			
			resposta.done(onBuscarMunicipiosFinalizado.bind(this));
			
		}
		
	}
	
	function onBuscarMunicipiosFinalizado(cidades) {
		
		var options = [];
		
		if (cidades.length > 0) {
			
			cidades.forEach(function(cidade){
				options.push('<option value="' + cidade.codigo + '" >' + cidade.nome + '</option>');
			});
			
			this.combo.removeAttr('disabled');
			
			this.combo.html(options.join(''));
		} else {
			this.combo.html('<option>Não há municípios</option>');
			this.combo.attr('disabled', true);
		}
		
		var codigoMunicipioSelecionado = this.codigoMunicipioSelecionado.val();
		if (codigoMunicipioSelecionado) {
			this.combo.val(codigoMunicipioSelecionado);
		}
		
		$('select').select();
	}
	
	function iniciarRequisicao() {
		this.preloader.removeAttr('style');
		this.containerCidade.attr('class', 'input-field col s12 m3 l3');
	}
	
	function finalizarRequisicao() {
		this.preloader.attr('style', 'display: none');
		this.containerCidade.attr('class', 'input-field col s12 m4 l4');
	}
	
	function reset() {
		this.combo.html('<option value="">Selecione o município</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
		$('select').select();
	}
	
	return ComboMunicipio;
	
}());

$(function(){
	
	var comboEstado = new Advocacia.ComboEstado();
	comboEstado.iniciar();
	
	var comboCidade = new Advocacia.ComboMunicipio(comboEstado);
	comboCidade.iniciar();
	
});