package olx.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Anuncio;
import models.Url;
import olx.actions.OlxActions;

import org.apache.log4j.Logger;

import constans.CrawlerConstants;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class OlxCrawlerOnline extends WebCrawler {

	private static final Logger LOG = (Logger) Logger.getLogger(OlxCrawlerOnline.class);

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

		if (href.contains(new StringBuilder("http://").append(map.get("estado"))) && href.contains(CrawlerConstants.ANUNCIO_PAG_OLX) && href.contains(map.get("modelo"))) {
			new Url(href).save();
			return true;
		} else if (href.contains(new StringBuilder("http://").append(map.get("estado")))
				&& href.contains(CrawlerConstants.ANUNCIO_PES_PAG_OLX.replace("{marca}", map.get("marca").toLowerCase()).replace("{modelo}", map.get("modelo").toLowerCase()))) {
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

		if (url.contains(new StringBuilder("http://").append(map.get("estado"))) && url.contains(CrawlerConstants.ANUNCIO_PAG_OLX) && url.contains(map.get("modelo"))) {
			if (page.getParseData() instanceof HtmlParseData) {
				HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

				anuncios.add(OlxActions.getAnuncio(url, htmlParseData.getHtml().replaceAll("\\r|\\n", "")));

			}
		}

	}

	@Override
	public Object getMyLocalData() {
		return anuncios;
	}

}
