package br.com.jdrmservices.araujocafe.model.constants;

public enum Vara {

	UNICA("ÚNICA"),
	V1("1º"),
	V2("2º"),
	V3("3º"),
	V4("4º"),
	V5("5º"),
	V6("6º"),
	V7("7º"),
	V8("8º"),
	V9("9º"),
	V10("10º"),
	V11("11º"),
	V12("12º"),
	V13("13º"),
	V14("14º"),
	V15("15º"),
	V16("16º"),
	V17("17º"),
	V18("18º"),
	V19("19º"),
	V20("20º"),
	V21("21º"),
	V22("22º"),
	V23("23º"),
	V24("24º"),
	V25("25º"),
	V26("26º"),
	V27("27º"),
	V28("28º"),
	V29("29º"),
	V30("30º"),
	V31("31º"),
	V32("32º"),
	V33("33º"),
	V34("34º"),
	V35("35º"),
	V36("36º");
	
	private String descricao;
	
	Vara(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}	
}
