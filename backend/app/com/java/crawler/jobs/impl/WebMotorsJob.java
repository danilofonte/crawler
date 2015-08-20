package com.java.crawler.jobs.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import play.Logger;
import play.Play;
import play.jobs.Every;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.libs.WS;
import play.libs.WS.HttpResponse;

import com.google.code.morphia.Key;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.StringMap;
import com.google.gson.reflect.TypeToken;
import com.java.crawler.contants.CrawlerConstants;
import com.java.crawler.jobs.DefaultCrawlerJob;
import com.java.crawler.jobs.process.WebMotorsCrawlerJobProcess;
import com.java.crawler.models.Cidade;
import com.java.crawler.models.Estado;
import com.java.crawler.models.MarcaAtiva;
import com.java.crawler.models.ModeloAtivo;

@OnApplicationStart
@Every("1h")
public class WebMotorsJob extends Job implements DefaultCrawlerJob {

	public void doJob() {

		getMarcas();
		getModelos();
		getEstadosCidadesPorAlfabeto();

		// WebMotorsCrawlerJobProcess.performCralwerProcess();

	}

	public void getMarcas() {
		HttpResponse response = WS.url(CrawlerConstants.MARCAS_URL_WEBMOTORS)
				.get();

		Map result = (Map) new GsonBuilder().create().fromJson(
				response.getJson(), Object.class);

		List principal = (ArrayList) result.get(Play.configuration
				.getProperty("url.webmotors.marcas.objeto.principal"));
		List common = (ArrayList) result.get(Play.configuration
				.getProperty("url.webmotors.marcas.objeto.common"));

		MarcaAtiva.insert(MarcaAtiva.arrayToList(principal));
		MarcaAtiva.insert(MarcaAtiva.arrayToList(common));
	}

	public void getModelos() {
		List<MarcaAtiva> marcasAtivas = MarcaAtiva.findAll();

		for (MarcaAtiva marcaAtiva : marcasAtivas) {
			String url = CrawlerConstants.MODELOS_URL_WEBMOTORS.replace(
					"{marca}", marcaAtiva.nome).replace("{tipoanuncio}",
					CrawlerConstants.TIPO_CONSULTA);

			HttpResponse response = WS.url(url).get();

			List<ModeloAtivo> result = new GsonBuilder().create().fromJson(
					response.getJson(), new TypeToken<List<ModeloAtivo>>() {
					}.getType());

			ModeloAtivo.insert(ModeloAtivo.arrayToList(result, marcaAtiva));
		}

	}

	public void getEstadosCidadesPorAlfabeto() {
		for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
			getEstadosCidades(String.valueOf(alphabet));
		}
	}

	public void getEstadosCidades(String letra) {
		HttpResponse response = WS.url(
				CrawlerConstants.CIDADES_ESTADOS_URL_WEBMOTORS.replace(
						"{letra}", letra)).get();

		Map result = (Map) new GsonBuilder().create().fromJson(
				response.getJson(), Object.class);

		StringMap<Object> results = (StringMap<Object>) result
				.get(Play.configuration
						.getProperty("url.webmotors.cidades.estados.RESULTS"));

		List estados = null;

		List cidades = null;

		if (null != results.get(Play.configuration
				.getProperty("url.webmotors.cidades.estados.CIDADES"))) {
			cidades = (List) results.get(Play.configuration
					.getProperty("url.webmotors.cidades.estados.CIDADES"));
		}
		if (null != results.get(Play.configuration
				.getProperty("url.webmotors.cidades.estados.ESTADOS"))) {
			estados = (List) results.get(Play.configuration
					.getProperty("url.webmotors.cidades.estados.ESTADOS"));
		}

		if (estados != null) {
			Estado.insert(Estado.arrayToList(estados));
		}

		if (cidades != null) {
			Cidade.insert(Cidade.arrayToList(cidades));
		}
	}

}
