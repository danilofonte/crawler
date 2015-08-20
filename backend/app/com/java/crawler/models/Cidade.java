package com.java.crawler.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.code.morphia.Key;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Reference;
import com.google.gson.internal.StringMap;
import com.java.crawler.contants.CrawlerConstants;

import play.modules.morphia.Model;

@Entity
public class Cidade extends Model {
	
	@Indexed
	public String nome;
	
	public String uf;

	public Cidade() {
		super();
	}

	public Cidade(String nome, String uf) {
		super();
		this.nome = nome;
		this.uf = uf;
	}
	
	public static List<Cidade> arrayToList(List<StringMap<Object>> list) {
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		for (StringMap<Object> map : list) {
			
			Cidade cidade = new Cidade();

			for (Entry<String, Object> set : map.entrySet()) {
				if (set.getKey().contains(CrawlerConstants.STRING_UF_CONSTANT)) {
					cidade.uf = set.getValue().toString();
				} else if (set.getKey().contains(CrawlerConstants.STRING_NAME_CONSTANT_FULL)) {
					cidade.nome = (String) set.getValue();
				}
			}			
			
			Key<Cidade> key = Cidade.find("ufAndNome", cidade.uf,cidade.nome).getKey();
			
			if (key == null) {	
				cidades.add(cidade);
			}

		}

		return cidades;
	}
	
	

}
