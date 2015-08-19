package com.java.crawler.models;

import java.util.ArrayList;
import java.util.List;

import play.modules.morphia.Model;

import com.google.code.morphia.Key;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Reference;
import com.google.gson.annotations.SerializedName;

@Entity
public class ModeloAtivo extends Model {

	@Indexed
	@SerializedName("C")
	public String codigo;

	@Indexed
	@SerializedName("N")
	public String nome;

	@Reference
	public MarcaAtiva marcaAtiva;
	
	public static List<ModeloAtivo> arrayToList(List<ModeloAtivo> list, MarcaAtiva marcaAtiva) {
		List<ModeloAtivo> modelosAtivos = new ArrayList<ModeloAtivo>();

		for (ModeloAtivo modeloAtivo : list) {					

			Key<MarcaAtiva> key = ModeloAtivo.find("nome", modeloAtivo.nome).getKey();
			
			if (key == null) {	
				modeloAtivo.marcaAtiva = marcaAtiva;
				modelosAtivos.add(modeloAtivo);
			}

		}

		return modelosAtivos;
	}

}
