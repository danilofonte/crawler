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
public class Marca extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3111636012781511750L;

	@Indexed
	public String codigo;

	@Indexed
	public String nomeWebMotors;

	@Indexed
	public String nomeOlx;

	@Indexed
	public String nome;

	public Marca() {
		super();
	}

	public Marca(String codigo, String nomeWebMotors, String nomeOlx) {
		super();
		this.codigo = codigo;
		this.nomeWebMotors = nomeWebMotors;
		this.nomeOlx = nomeOlx;
	}

	public static List<Marca> arrayToListWebMotors(List<StringMap<Object>> list) {
		List<Marca> marcasAtivas = new ArrayList<Marca>();

		for (StringMap<Object> map : list) {

			Marca marcaAtiva = new Marca();

			for (Entry<String, Object> set : map.entrySet()) {
				if (set.getKey().contains(CrawlerConstants.STRING_CODE_CONSTANT)) {
					marcaAtiva.codigo = set.getValue().toString().replace(".0", "");
				} else if (set.getKey().contains(CrawlerConstants.STRING_NAME_CONSTANT)) {
					marcaAtiva.nomeWebMotors = (String) set.getValue();
				}
			}

			marcaAtiva.nome = marcaAtiva.nomeWebMotors.replaceAll("\\W", "").toLowerCase();

			Key<Marca> key = Marca.find("nomeWebMotors", marcaAtiva.nomeWebMotors).getKey();

			if (key == null)
				marcasAtivas.add(marcaAtiva);

		}

		return marcasAtivas;
	}

	public static void updateMarcaAtivaByOlxName(String nameOlx, String realNameOlx) {

		MorphiaQuery q = Marca.q();

		q.criteria("nomeWebMotors").startsWithIgnoreCase(nameOlx);

		List<Marca> marcasAtivas = q.asList();

		if (null != marcasAtivas && !marcasAtivas.isEmpty()) {
			marcasAtivas.get(0).nomeOlx = realNameOlx;
			marcasAtivas.get(0).save();
		}

	}

}
