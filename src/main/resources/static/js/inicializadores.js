/* inicializadores.js */
$('.js-moeda').maskMoney({thousands:'.', decimal:',', allowZero:true});
$(".editor").jqte();
$('.timepicker').timepicker({defaultTime: 'now'});		
$('select').select();
$('ul.tabs').tabs();
$('.tooltipped').tooltip();		    	
$('.chips').chips();
$('.modal').modal();

/* mascaras.js */
$('.js-processo').mask('000000-00.0.00.0000');
$('.js-tombo').mask('000000000000');
$('.js-nomeSimples').mask('AAAAAAAAAA', {'translation': {0: {pattern: /[ a-zA-Z0-9*]/}}});
$('.js-numero').mask('00000000');
$('.js-inep').mask('00000000');
$('.js-cep').mask('00000-000');
$('.js-telefone').mask('(00) 0000-0000');
$('.js-celular').mask('(00) 0 0000-0000');
$('.js-matricula').mask('0000000000');
$('.js-cpf').mask('000.000.000-00');
$('.js-rg').mask('0000000000000');
$('.js-titulo').mask('000000000000');
$('.js-pisPasep').mask('000.00000.00-0');
$('.js-data').mask('00/00/0000');
$('.js-hora').mask('00:00:00');
$('.js-cargahoraria').mask('000');
$('.js-cnpj').mask('00.000.000/0000-00');

/* datepicker
data-uk-datepicker="{months:['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'], weekdays:['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb']}";
*/