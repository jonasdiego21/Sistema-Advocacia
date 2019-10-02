var Advocacia = Advocacia || {};
	
Advocacia.ComboEstadoTramitacao = (function(){
	
	function ComboEstadoTramitacao() {
		this.comboTramitacao = $('#estadoTramitacao');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboEstadoTramitacao.prototype.iniciar = function(){
		this.comboTramitacao.on('change', onEstadoTramitacaoAlterado.bind(this));	
	}
	
	function onEstadoTramitacaoAlterado() {
		this.emitter.trigger('modificado', this.comboTramitacao.val());
	}
	
	return ComboEstadoTramitacao;
}());

Advocacia.ComboMunicipioTramitacao = (function(){
	
	function ComboMunicipioTramitacao(estadoTramitacao){
		this.comboEstadoTramitacao = estadoTramitacao;
		this.comboCidadeTramitacao = $('#cidadeTramitacao');
		this.preloaderTramitacao = $('#lct-preloader-cidade-tramitacao');
		this.containerCidadeTramitacao = $('#ltc-container-cidade-tramitacao');
		this.codigoMunicipioTramitacaoSelecionado = $('#inputMunicipioSelecionadoTramitacao');
	}
	
	ComboMunicipioTramitacao.prototype.iniciar = function(){		
		reset.call(this);
		
		this.comboEstadoTramitacao.on('modificado', onEstadoTramitacaoAlterado.bind(this));
		
		var codigoEstadoTramitacao = this.comboCidadeTramitacao.val();
		carregarMunicipiosTramitacao.call(this, codigoEstadoTramitacao);
		
	}
	
	function onEstadoTramitacaoAlterado(evento, codigoEstadoTramitacao) {
		this.codigoMunicipioTramitacaoSelecionado.val('');
		carregarMunicipiosTramitacao.call(this, codigoEstadoTramitacao);
	}
	
	function carregarMunicipiosTramitacao(codigoEstadoTramitacao) {
		
		if (codigoEstadoTramitacao) {			
			var resposta = $.ajax({
				url: this.comboCidadeTramitacao.data('url'),
				method: 'GET',
				data: {'codigo': codigoEstadoTramitacao},
				beforeSend: iniciarRequisicaoTramitacao.bind(this),
				complete: finalizarRequisicaoTramitacao.bind(this)
			});
			
			resposta.done(onBuscarMunicipiosFinalizadoTramitacao.bind(this));
			
		}
		
	}
	
	function onBuscarMunicipiosFinalizadoTramitacao(cidadesTramitacao) {
		
		var options = [];
		
		if (cidadesTramitacao.length > 0) {
			
			cidadesTramitacao.forEach(function(cidade){
				options.push('<option value="' + cidade.codigo + '" >' + cidade.nome + '</option>');
			});
			
			this.comboCidadeTramitacao.removeAttr('disabled');
			
			this.comboCidadeTramitacao.html(options.join(''));
		} else {
			this.comboCidadeTramitacao.html('<option>Não há municípios</option>');
			this.comboCidadeTramitacao.attr('disabled', true);
		}
		
		var codigoMunicipioTramitacaoSelecionado = this.codigoMunicipioTramitacaoSelecionado.val();
		if (codigoMunicipioTramitacaoSelecionado) {
			this.comboCidadeTramitacao.val(codigoMunicipioTramitacaoSelecionado);
		}
		
		$('select').select();
	}
	
	function iniciarRequisicaoTramitacao() {
		this.preloaderTramitacao.removeAttr('style');
		this.containerCidadeTramitacao.attr('class', 'input-field col s12 m3 l3');
	}
	
	function finalizarRequisicaoTramitacao() {
		this.preloaderTramitacao.attr('style', 'display: none');
		this.containerCidadeTramitacao.attr('class', 'input-field col s12 m4 l4');
	}
	
	function reset() {
		this.comboCidadeTramitacao.html('<option value="">Selecione o município</option>');
		this.comboCidadeTramitacao.val('');
		this.comboCidadeTramitacao.attr('disabled', 'disabled');
		$('select').select();
	}
	
	return ComboMunicipioTramitacao;
	
}());

$(function(){
	
	var comboEstadoTramitacao = new Advocacia.ComboEstadoTramitacao();
	comboEstadoTramitacao.iniciar();
	
	var comboCidadeTramitacao = new Advocacia.ComboMunicipioTramitacao(comboEstadoTramitacao);
	comboCidadeTramitacao.iniciar();
	
});