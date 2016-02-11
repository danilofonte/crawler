package models;

import java.util.ArrayList;
import java.util.List;

import play.modules.morphia.Model;

import com.google.code.morphia.Key;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Reference;
import com.google.gson.annotations.SerializedName;

@Entity
public class Modelo extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8342134640068557607L;

	@Indexed
	@SerializedName("C")
	public String codigo;

	@Indexed
	@SerializedName("N")
	public String nomeWebMotors;

	@Indexed
	@SerializedName("NOLX")
	public String nomeOlx;
	
	@Indexed
	public String nome;

	
	@Reference
	public Marca marcaAtiva;

	public static List<Modelo> arrayToListWebMotors(List<Modelo> list, Marca marcaAtiva) {
		List<Modelo> modelosAtivos = new ArrayList<Modelo>();

		for (Modelo modeloAtivo : list) {

			Key<Marca> key = Marca.find("nomeWebMotors", marcaAtiva.nomeWebMotors).getKey();

			modeloAtivo.nome = modeloAtivo.nomeWebMotors.replaceAll("\\W", "").toLowerCase();
			
			if (key != null) {
				modeloAtivo.marcaAtiva = marcaAtiva;
				modelosAtivos.add(modeloAtivo);
			}

		}

		return modelosAtivos;
	}

	public static void updateModeloAtivoByOlxName(String nameOlx) {

		MorphiaQuery q = Modelo.q();

		q.criteria("nome").startsWithIgnoreCase(nameOlx.replaceAll("\\W", "").toLowerCase());

		List<Modelo> modelosAtivos = q.asList();

		if (null != modelosAtivos && !modelosAtivos.isEmpty()) {
			modelosAtivos.get(0).nomeOlx = nameOlx;
			modelosAtivos.get(0).save();
		}

	}

}
