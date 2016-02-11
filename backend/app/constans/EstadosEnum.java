package constans;

public enum EstadosEnum {
	
	SP("sp-sao-paulo","são%20paulo");
	
	private final String nomeParametro;
	
	private String nomeGet;

	private EstadosEnum(String nomeParametro, String nomeGet) {
		this.nomeParametro = nomeParametro;
		this.nomeGet = nomeGet;
	}

	public String getNomeParametro() {
		return nomeParametro;
	}

	public String getNomeGet() {
		return nomeGet;
	}
	
	

}
