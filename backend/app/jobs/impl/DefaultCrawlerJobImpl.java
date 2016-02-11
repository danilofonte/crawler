package jobs.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jobs.DefaultCrawlerJob;
import models.Cidade;
import models.Estado;
import models.Marca;
import models.Modelo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import play.Play;
import play.jobs.Job;
import play.jobs.On;
import play.jobs.OnApplicationStart;
import play.libs.WS;
import play.libs.WS.HttpResponse;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.StringMap;
import com.google.gson.reflect.TypeToken;

import constans.CrawlerConstants;

@SuppressWarnings("rawtypes")
@On("0 0 20 1/1 * ? *")
//@OnApplicationStart
public class DefaultCrawlerJobImpl extends Job implements DefaultCrawlerJob {

	public void doJob() {

		getMarcasWebMotors();
		getMarcasOlx();
		getModelosWebMotors();
		getModelosOlx();
		getEstadosCidadesPorAlfabeto();

	}

	@SuppressWarnings({ "unchecked" })
	public void getMarcasWebMotors() {
		HttpResponse response = WS.url(CrawlerConstants.MARCAS_URL_WEBMOTORS).get();

		Map result = (Map) new GsonBuilder().create().fromJson(response.getJson(), Object.class);

		List principal = (ArrayList) result.get(Play.configuration.getProperty("url.webmotors.marcas.objeto.principal"));
		List common = (ArrayList) result.get(Play.configuration.getProperty("url.webmotors.marcas.objeto.common"));

		Marca.insert(Marca.arrayToListWebMotors(principal));
		Marca.insert(Marca.arrayToListWebMotors(common));
	}

	public void getModelosWebMotors() {
		List<Marca> marcasAtivas = Marca.findAll();

		for (Marca marcaAtiva : marcasAtivas) {
			String url = CrawlerConstants.MODELOS_URL_WEBMOTORS.replace("{marca}", marcaAtiva.nomeWebMotors).replace("{tipoanuncio}", CrawlerConstants.TIPO_CONSULTA);

			HttpResponse response = WS.url(url).get();

			List<Modelo> result = new GsonBuilder().create().fromJson(response.getJson(), new TypeToken<List<Modelo>>() {
			}.getType());

			Modelo.insert(Modelo.arrayToListWebMotors(result, marcaAtiva));
		}

	}

	public void getEstadosCidadesPorAlfabeto() {
		for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
			getEstadosCidades(String.valueOf(alphabet));
		}
	}

	@SuppressWarnings("unchecked")
	public void getEstadosCidades(String letra) {
		HttpResponse response = WS.url(CrawlerConstants.CIDADES_ESTADOS_URL_WEBMOTORS.replace("{letra}", letra)).get();

		Map result = (Map) new GsonBuilder().create().fromJson(response.getJson(), Object.class);

		StringMap<Object> results = (StringMap<Object>) result.get(Play.configuration.getProperty("url.webmotors.cidades.estados.RESULTS"));

		List estados = null;

		List cidades = null;

		if (null != results.get(Play.configuration.getProperty("url.webmotors.cidades.estados.CIDADES"))) {
			cidades = (List) results.get(Play.configuration.getProperty("url.webmotors.cidades.estados.CIDADES"));
		}
		if (null != results.get(Play.configuration.getProperty("url.webmotors.cidades.estados.ESTADOS"))) {
			estados = (List) results.get(Play.configuration.getProperty("url.webmotors.cidades.estados.ESTADOS"));
		}

		if (estados != null) {
			Estado.insert(Estado.arrayToList(estados));
		}

		if (cidades != null) {
			Cidade.insert(Cidade.arrayToList(cidades));
		}
	}

	@Override
	public void getMarcasOlx() {
		HttpResponse response = WS.url(CrawlerConstants.MARCAS_URL_OLX).get();

		Document doc = Jsoup.parse(response.getString());

		Elements elements = doc.getElementsByAttributeValue("id", "vehicle_brand_vb");

		Iterator<Element> iterator = elements.iterator();

		while (iterator.hasNext()) {

			Element element = iterator.next();

			for (Node node : element.childNodes()) {

				if (null != node.attributes().get("value") && !node.attributes().get("value").isEmpty()) {
					String nameOlx = !node.attributes().get("title").contains("-") ? node.attributes().get("title") : node.attributes().get("title").split("-")[0].length() <= 2 ? node.attributes().get("title").split("-")[1] : node.attributes().get("title").split("-")[0];

					Marca.updateMarcaAtivaByOlxName(nameOlx, node.attributes().get("title"));
				}
			}

		}

	}

	@Override
	public void getModelosOlx() {
		List<Marca> marcasAtivas = Marca.findAll();

		for (Marca marcaAtiva : marcasAtivas) {
			if (null != marcaAtiva.nomeOlx && !marcaAtiva.nomeOlx.isEmpty()) {
				String url = CrawlerConstants.MODELOS_URL_OLX.replace("{marca}", marcaAtiva.nomeOlx);

				HttpResponse response = WS.url(url).get();

				Document doc = Jsoup.parse(response.getString());

				Elements elements = doc.getElementsByAttributeValue("id", "vehicle_model_vm");

				Iterator<Element> iterator = elements.iterator();

				while (iterator.hasNext()) {

					Element element = iterator.next();

					for (Node node : element.childNodes()) {

						if (null != node.attributes().get("value") && !node.attributes().get("value").isEmpty()) {

							Modelo.updateModeloAtivoByOlxName(node.attributes().get("title"));
						}
					}

				}

			}
		}
	}

}
