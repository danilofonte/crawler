package olx.actions;

import java.util.Iterator;

import models.Anuncio;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import constans.CrawlerConstants;

public class OlxActions {

	private static final Logger LOG = (Logger) Logger.getLogger(OlxActions.class);

	public static Anuncio getAnuncio(String link, String html) {

		Anuncio anuncio = new Anuncio();

		anuncio.siteReferencia = CrawlerConstants.OlxConstant;

		anuncio.link = link;

		Document doc = Jsoup.parse(html);

		

		Elements elements = doc.getElementsByAttribute("onClick");

		Iterator<Element> iterator = elements.iterator();

		while (iterator.hasNext()) {

			Element element = iterator.next();

			if (element.attributes().get("onClick").contains("car_model")) {
				anuncio.nmVersao = ((TextNode) element.childNodes().get(0)).text().trim();
			}

			if (element.attributes().get("onClick").contains("car_year")) {
				anuncio.ano = ((TextNode) element.childNodes().get(0)).text().trim();
			}

			if (element.attributes().get("onClick").contains("car_fuel")) {
				anuncio.tipoCombustivel = ((TextNode) element.childNodes().get(0)).text().trim();
			}

		}

		elements = doc.getElementsByAttributeValue("class", "actual-price");

		iterator = elements.iterator();

		while (iterator.hasNext()) {

			Element element = iterator.next();

			for (Node node : element.childNodes()) {

				anuncio.preco = ((TextNode) node).text().trim();

			}

		}

		elements = doc.getElementsByAttributeValue("class", "text");

		iterator = elements.iterator();

		while (iterator.hasNext()) {

			Element element = iterator.next();

			int i = 0;

			for (Node node : element.childNodes()) {

				if (null != node.attributes().get("class") && !node.attributes().get("class").isEmpty() && node.attributes().get("class").contains("term")) {

					if (((TextNode) node.childNodes().get(0)).text().contains("Quilometragem")) {
						anuncio.km = ((TextNode) element.childNodes().get(3).childNodes().get(0)).text().trim();
					}

					if (((TextNode) node.childNodes().get(0)).text().contains("Portas")) {
						anuncio.portas = ((TextNode) element.childNodes().get(3).childNodes().get(0)).text().trim();
					}

					if (((TextNode) node.childNodes().get(0)).text().contains("Final de Placa")) {
						anuncio.placa = ((TextNode) element.childNodes().get(3).childNodes().get(0)).text().trim();
					}

					if (((TextNode) node.childNodes().get(0)).text().contains("Câmbio")) {
						anuncio.cambio = ((TextNode) element.childNodes().get(3).childNodes().get(0)).text().trim();
					}
				}
				
				if (null != node.attributes().get("class") && !node.attributes().get("class").isEmpty() && node.attributes().get("class").contains("OLXad-photo-main")) {
					if (null != node.childNode(0).attributes().get("class") && !node.childNode(0).attributes().get("class").isEmpty() && node.childNode(0).attributes().get("class").contains("box-image")) {
						anuncio.imagemUrl = node.childNode(0).childNode(0).attributes().get("src");
					}
				
				}
				i++;
			}

		}
		
		elements = doc.getElementsByAttributeValue("class", "OLXad-photo-main");

		iterator = elements.iterator();

		while (iterator.hasNext()) {

			Element element = iterator.next();

			int i = 0;

			for (Node node : element.childNodes()) {
				if (null != node.attributes().get("class") && !node.attributes().get("class").isEmpty() && node.attributes().get("class").contains("box-image")) {
					for (Node childNode : node.childNodes()) {
						if (null != childNode.attributes().get("src") && !childNode.attributes().get("src").isEmpty()) {
							anuncio.imagemUrl = childNode.attributes().get("src");
						}

					}
					
				}
				i++;
			}
		}

		if (StringUtils.isNotEmpty(anuncio.ano))
			return anuncio;

		return null;

	}

}
