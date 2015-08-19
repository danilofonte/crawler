package com.java.crawler.webmotors.rules;

import java.util.regex.Pattern;

import org.w3c.dom.Document;

import play.Play;

import com.java.crawler.contants.CrawlerConstants;
import com.java.crawler.contants.CrawlerSession;
import com.java.crawler.document.DocumentUtils;
import com.java.crawler.models.Url;
import com.java.crawler.webmotors.actions.WebMotorsActions;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class WebMotorsCrawler extends WebCrawler {
	
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

		if (href.contains(CrawlerConstants.ANUNCIO_PAG_WEBMOTORS.replace("{marca}", CrawlerSession.MARCA_WEBMOTOR.nome.toLowerCase()).replace("{modelo}", CrawlerSession.MODELO_WEBMOTOR.nome.toLowerCase()))) {
			return true;
		} else if (href.contains(CrawlerConstants.ANUNCIO_PES_PAG_WEBMOTORS.replace("{marca}", CrawlerSession.MARCA_WEBMOTOR.nome.toLowerCase()).replace("{modelo}", CrawlerSession.MODELO_WEBMOTOR.nome.toLowerCase()))) {
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

		if (url.contains(CrawlerConstants.ANUNCIO_PAG_WEBMOTORS.replace("{marca}", CrawlerSession.MARCA_WEBMOTOR.nome.toLowerCase()).replace("{modelo}", CrawlerSession.MODELO_WEBMOTOR.nome.toLowerCase()))) {
			if (page.getParseData() instanceof HtmlParseData) {
				HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
				
				WebMotorsActions.createAnuncio(url, htmlParseData.getHtml().replaceAll("\\r|\\n", ""));
				
			}
		}

		
	}
}