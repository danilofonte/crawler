package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import play.modules.morphia.Model;

import com.google.code.morphia.Key;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
import com.google.gson.internal.StringMap;

import constans.CrawlerConstants;

@Entity
public class Estado extends Model {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3037464214476622458L;

	@Indexed
	public String sigla;

	@Indexed
	public String nome;	
	
	public String urlEnconding;

	public Estado() {
		super();
	}

	public Estado(String sigla, String nome) {
		super();
		this.sigla = sigla;
		this.nome = nome;
	}	
	
	public static List<Estado> arrayToList(List<StringMap<Object>> list) {
		List<Estado> estados = new ArrayList<Estado>();

		for (StringMap<Object> map : list) {

			Estado estado = new Estado();

			for (Entry<String, Object> set : map.entrySet()) {
				if (set.getKey().contains(CrawlerConstants.STRING_NAME_CONSTANT_FULL)) {
					estado.nome = set.getValue().toString();
				} else if (set.getKey().contains(CrawlerConstants.STRING_ACRONYM_CONSTANT)) {
					estado.sigla = (String) set.getValue();
				}
			}
			
			estado.urlEnconding= estado.nome.replace(" ", CrawlerConstants.ESTADO_URL_ENCODING);
			
			Key<Estado> key = Estado.find("sigla", estado.sigla).getKey();
			
			if (key == null)			
				estados.add(estado);

		}

		return estados;
	}
	
	
}
