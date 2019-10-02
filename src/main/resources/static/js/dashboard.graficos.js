var Advocacia = Advocacia || {};

/* Global Functions*/
function gerarCores(bar) {
	var cor = [];
	
	for(i = 0; i < bar.length; i++) {
		cor.unshift('rgba(' + Math.floor(Math.random() * 255) + ',' + Math.floor(Math.random() * 255) + ',' + Math.floor(Math.random() * 255) + ', 0.5)');
	}
	
	return cor;
}
/* TOTAL DE DESPESAS POR ANO */
Advocacia.GraficoDespesasNoAno = (function(){
	
	function GraficoDespesasNoAno() {
		this.ctx = $('#graficoDespesasAno')[0].getContext('2d'); 
	}
	
	GraficoDespesasNoAno.prototype.iniciar = function() {
		this.r = $.ajax({
			url: '/contasPagar/totalDespesasNoAno',
			method: 'GET',
			success: gerarGrafico.bind(this)
		});
	}

	function gerarGrafico(despesas) {
		
		var ano = [];
		var total = [];
		
		despesas.forEach(function(r) {
			ano.unshift(r.ano);
			total.unshift(r.total);
		});
		
		var despesas = new Chart(this.ctx, {
			type: 'bar',
			data: {
				labels: ano,
				datasets: [{
					label: "TOTAL DE DESPESAS NO ANO",
					backgroundColor: gerarCores(ano),
					//backgroundColor: "rgba(26, 79, 148, 0.5)",
					//backgroundColor: "rgba(255, 0, 0, 0.5)",
					pointBorderColor: "rgba(255, 0, 0, 1)",
					pointBackgroundColor: "#fff",
					data: total
				}]
			}
		});
	}
	
	return GraficoDespesasNoAno;
	
}());

/* TOTAL DE DESPESAS POR MÊS */
Advocacia.GraficoDespesasNoMes = (function(){
	
	function GraficoDespesasNoMes() {
		this.ctx = $('#graficoDespesasMes')[0].getContext('2d'); 
	}
	
	GraficoDespesasNoMes.prototype.iniciar = function() {
		this.r = $.ajax({
			url: '/contasPagar/totalDespesasNoMes',
			method: 'GET',
			success: gerarGrafico.bind(this)
		});
	}

	function gerarGrafico(despesas) {
		
		var mes = [];
		var total = [];
		
		despesas.forEach(function(r) {
			mes.unshift(r.mes);
			total.unshift(r.total);
		});
		
		var despesas = new Chart(this.ctx, {
			type: 'line',
			data: {
				labels: mes,
				datasets: [{
					label: "TOTAL DE DESPESAS NO MÊS",
					backgroundColor: gerarCores(mes),
					//backgroundColor: "rgba(255, 0, 0, 0.5)",
					pointBorderColor: "rgba(255, 0, 0, 1)",
					pointBackgroundColor: "#fff",
					data: total
				}]
			}
		});
	}
	
	return GraficoDespesasNoMes;
	
}());

/* TOTAL DE RECEITAS POR ANO */
Advocacia.GraficoReceitasNoAno = (function(){
	
	function GraficoReceitasNoAno() {
		this.ctx = $('#graficoReceitasAno')[0].getContext('2d'); 
	}
	
	GraficoReceitasNoAno.prototype.iniciar = function() {
		this.r = $.ajax({
			url: '/contasReceber/totalReceitasNoAno',
			method: 'GET',
			success: gerarGrafico.bind(this)
		});
	}

	function gerarGrafico(receitas) {
		
		var ano = [];
		var total = [];
		
		receitas.forEach(function(r) {
			ano.unshift(r.ano);
			total.unshift(r.total);
		});
		
		var receitas = new Chart(this.ctx, {
			type: 'bar',
			data: {
				labels: ano,
				datasets: [{
					label: "TOTAL DE RECEITAS NO ANO",
					backgroundColor: gerarCores(ano),
					//backgroundColor: "rgba(0, 255, 0, 0.5)",
					pointBorderColor: "rgba(0, 255, 0, 1)",
					pointBackgroundColor: "#fff",
					data: total
				}]
			}
		});
	}
	
	return GraficoReceitasNoAno;
	
}());

/* TOTAL DE RECEITAS POR MÊS */
Advocacia.GraficoReceitasNoMes = (function(){
	
	function GraficoReceitasNoMes() {
		this.ctx = $('#graficoReceitasMes')[0].getContext('2d'); 
	}
	
	GraficoReceitasNoMes.prototype.iniciar = function() {
		this.r = $.ajax({
			url: '/contasReceber/totalReceitasNoMes',
			method: 'GET',
			success: gerarGrafico.bind(this)
		});
	}

	function gerarGrafico(receitas) {
		
		var mes = [];
		var total = [];
		
		receitas.forEach(function(r) {
			mes.unshift(r.mes);
			total.unshift(r.total);
		});
		
		var receitas = new Chart(this.ctx, {
			type: 'line',
			data: {
				labels: mes,
				datasets: [{
					label: "TOTAL DE RECEITAS NO MÊS",
					backgroundColor: gerarCores(mes),
					//backgroundColor: "rgba(0, 255, 0, 0.5)",
					pointBorderColor: "rgba(0, 255, 0, 1)",
					pointBackgroundColor: "#fff",
					data: total
				}]
			}
		});
	}
	
	return GraficoReceitasNoMes;
	
}());

$(function() {
	var graficoDespesasNoAno = new Advocacia.GraficoDespesasNoAno();
	graficoDespesasNoAno.iniciar();
	
	var graficoDespesasNoMes = new Advocacia.GraficoDespesasNoMes();
	graficoDespesasNoMes.iniciar();
	
	var graficoReceitasNoAno = new Advocacia.GraficoReceitasNoAno();
	graficoReceitasNoAno.iniciar();
	
	var graficoReceitasNoMes = new Advocacia.GraficoReceitasNoMes();
	graficoReceitasNoMes.iniciar();
});