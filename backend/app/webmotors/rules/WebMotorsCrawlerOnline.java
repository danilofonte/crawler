package webmotors.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Anuncio;
import models.Url;
import webmotors.actions.WebMotorsActions;
import constans.CrawlerConstants;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class WebMotorsCrawlerOnline extends WebCrawler {

	private List<Anuncio> anuncios = new ArrayList<Anuncio>();

	private HashMap<String, String> map;

	@SuppressWarnings("unchecked")
	@Override
	public void onStart() {
		map = (HashMap<String, String>) myController.getCustomData();
	}

	/**
	 * This method receives two parameters. The first parameter is the page in
	 * which we have discovered this new url and the second parameter is the new
	 * url. You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic). In this example,
	 * we are instructing the crawler to ignore urls that have css, js, git, ...
	 * extensions and to only accept urls that start with
	 * "http://www.ics.uci.edu/". In this case, we didn't need the referringPage
	 * parameter to make the decision.
	 */
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();

		if (href.contains("/comprar"))
			new Url(href).save();

		if (href.contains(CrawlerConstants.ANUNCIO_PAG_WEBMOTORS.replace("{marca}", map.get("marca").toLowerCase()).replace("{modelo}", map.get("modelo").toLowerCase()))) {
			return true;
		} else if (href
				.contains(CrawlerConstants.ANUNCIO_PES_PAG_WEBMOTORS.replace("{marca}", map.get("marca").toLowerCase()).replace("{modelo}", map.get("modelo").toLowerCase()))) {
			return true;
		}

		return false;
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	public void visit(Page page) {
		String url = page.getWebURL().getURL();

		if (url.contains(CrawlerConstants.ANUNCIO_PAG_WEBMOTORS.replace("{marca}",  map.get("marca").toLowerCase().toLowerCase()).replace("{modelo}",
				map.get("modelo").toLowerCase()))) {
			if (page.getParseData() instanceof HtmlParseData) {
				HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

				anuncios.add(WebMotorsActions.getAnuncio(url, htmlParseData.getHtml().replaceAll("\\r|\\n", "")));

			}
		}

	}

	@Override
	public Object getMyLocalData() {
		return anuncios;
	}
}
