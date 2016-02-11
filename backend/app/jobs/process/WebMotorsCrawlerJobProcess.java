package jobs.process;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Anuncio;
import webmotors.rules.WebMotorsCrawlerOnline;
import constans.CrawlerConstants;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class WebMotorsCrawlerJobProcess {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Anuncio> getAnuncios(String estadoCidade, String estado, String marca, String modelo) throws Exception {
		String crawlStorageFolder = "/data/crawl/root";
		int numberOfCrawlers = 4;

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);
		config.setResumableCrawling(false);

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("marca", marca);
		map.put("modelo", modelo);

		controller.setCustomData(map);

		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */
		controller.addSeed(escapeHtml(CrawlerConstants.CRAWLER_PATTERN_WEBMOTORS.replace("{estado}", estadoCidade).replace("{estado2}", estado).replace("{marca}", marca)
				.replace("{modelo}", modelo)));

		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(WebMotorsCrawlerOnline.class, numberOfCrawlers);
		
		Object anuncios = controller.getCrawlersLocalData();

		List<Anuncio> anunciosReais = new ArrayList<Anuncio>();

		if (anuncios instanceof ArrayList) {
			for (Object object : (List) anuncios) {
				if (object instanceof ArrayList) {
					for (Object object2 : (List) object) {
						if (object2 instanceof Anuncio) {
							anunciosReais.add((Anuncio) object2);
						}
					}
				} else if (object instanceof Anuncio) {
					anunciosReais.add((Anuncio) object);
				}
			}
		} else if (anuncios instanceof Anuncio) {
			anunciosReais.add((Anuncio) anuncios);
		}

		return !anunciosReais.isEmpty() ? anunciosReais : (List<Anuncio>) anuncios;
	}

}
