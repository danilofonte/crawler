package com.java.crawler.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import play.modules.morphia.Model;

import com.google.code.morphia.Key;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
import com.google.gson.internal.StringMap;
import com.java.crawler.contants.CrawlerConstants;

@Entity
public class MarcaAtiva extends Model {

	@Indexed
	public String codigo;
	
	@Indexed
	public String nome;

	public MarcaAtiva() {
		super();
	}

	public MarcaAtiva(String codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}

	public static List<MarcaAtiva> arrayToList(List<StringMap<Object>> list) {
		List<MarcaAtiva> marcasAtivas = new ArrayList<MarcaAtiva>();

		for (StringMap<Object> map : list) {

			MarcaAtiva marcaAtiva = new MarcaAtiva();

			for (Entry<String, Object> set : map.entrySet()) {
				if (set.getKey().contains(CrawlerConstants.STRING_CODE_CONSTANT)) {
					marcaAtiva.codigo = set.getValue().toString().replace(".0", "");
				} else if (set.getKey().contains(CrawlerConstants.STRING_NAME_CONSTANT)) {
					marcaAtiva.nome = (String) set.getValue();
				}
			}
			
			Key<MarcaAtiva> key = MarcaAtiva.find("nome", marcaAtiva.nome).getKey();
			
			if (key == null)			
				marcasAtivas.add(marcaAtiva);

		}

		return marcasAtivas;
	}

}
