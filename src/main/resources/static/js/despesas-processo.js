var Advocacia = Advocacia || {};
	
Advocacia.Despesa = (function(){
	
	Despesa = function() {
		this.url = $('#modal1');
		this.containerDespesa = $('#containerDespesa');
		this.inputDescricao = $('#descricao');
		this.inputQtde = $('#qtde');
		this.inputValor = $('#valor');
		this.processo = $('#processo');
		this.btnAdicionar = $('#btnAdicionarDespesa');
	}
	
	Despesa.prototype.iniciar = function(){
		this.btnAdicionar.on('click', onSalvarDespesa.bind(this));
	}
	
	function onSalvarDespesa(evento) {
		
		evento.preventDefault();
		
		var descricao = this.inputDescricao.val();
		var qtde = this.inputQtde.val();
		var valor = this.inputValor.val();
		var processo = this.processo.val();
		var url = this.url;

		var despesa = {
			'descricao': descricao,
			'qtde': qtde,
			'valor': valor
		}
		
		var resposta = $.ajax({
			url: this.url.data('url'),
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(despesa)
		});
		
		resposta.done(onSalvarDespesaSucesso.bind(this));
		//resposta.fail(onSalvarDespesaErro.bind(this));
	}
	
	function onSalvarDespesaSucesso(despesa) {		
		ajustarTituloDespesa(despesa);
				
		this.containerDespesa.append(
			'<div class="chip">\
				<input type="hidden" name="despesas" value="' + despesa.codigo + '" />\
				<label>' + despesa.descricao + '</label>\
				<i class="close material-icons" data-codigo="' + despesa.codigo + '" >close</i>\
			</div>'
		);

		reset.call(this);
	}
	
	function onExcluirDespesa(e) {

		e.preventDefault();
		
		var result = $.ajax({
			url: '/despesas/' + $(e.currentTarget.attributes[1]).val(),
			method: 'DELETE'
		});
	}
	
	function onSalvarDespesaErro(resposta) {		
		resposta.responseJSON.forEach(function(erro){
			alert(erro.defaultMessage);
			//this.containerErro.text(erro.defaultMessage);
		}.bind(this));		
	}
	
	function reset() {
		this.inputDescricao.val('');
		this.inputQtde.val('');
		this.inputValor.val('');
		this.processo.val('');
	}
	
	return Despesa;
	
}());

$(function(){
	var despesa = new Advocacia.Despesa();
	despesa.iniciar();
});