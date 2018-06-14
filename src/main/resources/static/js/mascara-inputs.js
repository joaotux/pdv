$(function() {
	$('.js-mascara').maskMoney({
		prefix : 'R$ ',
		allowNegative : true,
		thousands : '.',
		decimal : ',',
		affixesStay : false
	});
	$('.js-mascara-not-prefix').maskMoney({
		prefix : ' ',		
		allowNegative : true,
		thousands : '.',
		decimal : ',',
		affixesStay : false
	});
	$('.js-currency-porcento').maskMoney({
		allowNegative : true,
		decimal : ',',
		affixesStay : false
	});
	$("input[id*='cpf']").inputmask({
		mask : [ '999.999.999-99' ],
		keepStatic : true
	});
	$("input[id*='cnpj']").inputmask({
		mask : [ '99.999.999/9999-99' ],
		keepStatic : true
	});
	$("input[id*='cpfcnpj']").inputmask({
		mask : [ '999.999.999-99', '99.999.999/9999-99' ],
		keepStatic : true
	});
	$('.js-mascara-porcento').maskMoney({
		prefix : '% ',
		allowNegative : true,
		thousands : '.',
		decimal : ',',
		affixesStay : false
	});
});