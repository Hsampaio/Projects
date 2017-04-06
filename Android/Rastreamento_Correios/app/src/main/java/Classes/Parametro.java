package Classes;



public class Parametro {
	
	public Parametro(String sNome, String sValor) {
		this.nome = sNome;
		this.valor = sValor;
	}
	private String nome;
	private String valor;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
}
