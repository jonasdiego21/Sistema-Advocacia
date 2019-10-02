var Advocacia = Advocacia || {};

Advocacia.DocumentoApresentado = (function() {
	
	DocumentoApresentado = function() {
		console.log("Construtor");
	}
	
	DocumentoApresentado.prototype.iniciar = function() {
		console.log("Prototype");
	}
	
	return DocumentoApresentado;
	
}());

$(function() {
	var advocacia = new Advocacia.DocumentoApresentado();
	advocacia.iniciar();
});