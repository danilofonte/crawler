package com.java.crawler.contants;

import play.Play;

public class CrawlerConstants {
	
	public static final String MARCAS_URL_WEBMOTORS = Play.configuration.getProperty("url.webmotors.marcas");
	
	public static final String MODELOS_URL_WEBMOTORS = Play.configuration.getProperty("url.webmotors.modelos");

	public static final String TIPO_CONSULTA = Play.configuration.getProperty("url.webmotors.modelos.tipo.anuncio");
	
	public static final String STRING_CODE_CONSTANT = "C";
	
	public static final String STRING_NAME_CONSTANT = "N";
	
	public static final String CRAWLER_PATTERN_WEBMOTORS = Play.configuration.getProperty("url.webmotors.veiculo");

	public static final String ANUNCIO_PAG_WEBMOTORS = Play.configuration.getProperty("url.webmotors.comprar");
	
	public static final String ANUNCIO_PES_PAG_WEBMOTORS = Play.configuration.getProperty("url.webmotors.proximo");
}
