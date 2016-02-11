package models;

import play.modules.morphia.Model;

import com.google.code.morphia.Key;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Reference;

@Entity
public class Anuncio extends Model {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4637409103475480815L;

	@Indexed
	public String link;
	
	public String preco;
	
	public String ano;
	
	public String km;
	
	public String tipoCombustivel;
	
	public String cor;
	
	public String cambio;
	
	public String placa;
	
	public String carroceria;
	
	public String portas;
	
	public String dataAnuncio;
	
	public String opcionais;
	
	public String cidade;
	
	public String estado;
	
	public String idAnuncio;
	
	public String nmVersao;
	
	public String tipo;
	
	public String referr;
	
	public String siteReferencia;
	
	public String imagemUrl;
	
	@Reference
	public Modelo modeloAtivo;
	
	public static void salvar(Anuncio anuncio) {
		Key<Anuncio> key = Anuncio.find("link", anuncio.link).getKey();
		
		if (key == null)			
			anuncio.save();
	}
	
	

}
