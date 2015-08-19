package com.java.crawler.jobs.process;

import java.util.List;

import com.java.crawler.contants.CrawlerConstants;
import com.java.crawler.contants.CrawlerSession;
import com.java.crawler.contants.EstadosEnum;
import com.java.crawler.models.MarcaAtiva;
import com.java.crawler.models.ModeloAtivo;
import com.java.crawler.webmotors.rules.WebMotorsCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class WebMotorsCrawlerJobProcess {

	public static void performCralwerProcess() {
		CrawlerSession.ESTADO_ENUM = EstadosEnum.SP;

		List<MarcaAtiva> marcasAtivas = MarcaAtiva.findAll();

		for (MarcaAtiva marcaAtiva : marcasAtivas) {

			List<ModeloAtivo> modelosAtivos = ModeloAtivo.find("marcaAtiva",
					marcaAtiva).asList();

			CrawlerSession.MARCA_WEBMOTOR = marcaAtiva;

			for (ModeloAtivo modeloAtivo : modelosAtivos) {

				CrawlerSession.MODELO_WEBMOTOR = modeloAtivo;

				try {
					getAnuncios();
				} catch (Exception e) {
				}

			}

		}
	}

	private static void getAnuncios() throws Exception {
		String crawlStorageFolder = "/data/crawl/root";
		int numberOfCrawlers = 4;

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);
		config.setResumableCrawling(true);

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher,
				robotstxtServer);

		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */
		controller.addSeed(CrawlerConstants.CRAWLER_PATTERN_WEBMOTORS
				.replace("{estado}",
						CrawlerSession.ESTADO_ENUM.getNomeParametro())
				.replace("{estado2}", CrawlerSession.ESTADO_ENUM.getNomeGet())
				.replace("{marca}", CrawlerSession.MARCA_WEBMOTOR.nome)
				.replace("{modelo}", CrawlerSession.MODELO_WEBMOTOR.nome));

		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(WebMotorsCrawler.class, numberOfCrawlers);
	}

}
