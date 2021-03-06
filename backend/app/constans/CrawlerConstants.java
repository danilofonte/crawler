package constans;

import play.Play;

public class CrawlerConstants {
	
	public static final String MARCAS_URL_WEBMOTORS = Play.configuration.getProperty("url.webmotors.marcas");
	
	public static final String MARCAS_URL_OLX = Play.configuration.getProperty("url.olx.marcas");

	public static final String MODELOS_URL_OLX = Play.configuration.getProperty("url.olx.modelos");
	
	public static final String MODELOS_URL_WEBMOTORS = Play.configuration.getProperty("url.webmotors.modelos");

	public static final String CIDADES_ESTADOS_URL_WEBMOTORS = Play.configuration.getProperty("url.webmotors.cidades.estados");
	
	public static final String TIPO_CONSULTA = Play.configuration.getProperty("url.webmotors.modelos.tipo.anuncio");
	
	public static final String STRING_CODE_CONSTANT = "C";
	
	public static final String STRING_NAME_CONSTANT = "N";
	
	public static final String STRING_NAME_CONSTANT_FULL = "Nome";
	
	public static final String STRING_ACRONYM_CONSTANT = "Sigla";
	
	public static final String STRING_UF_CONSTANT = "UF";
	
	public static final String CRAWLER_PATTERN_WEBMOTORS = Play.configuration.getProperty("url.webmotors.veiculo");

	public static final String ANUNCIO_PAG_WEBMOTORS = Play.configuration.getProperty("url.webmotors.comprar");
	
	public static final String ANUNCIO_PES_PAG_WEBMOTORS = Play.configuration.getProperty("url.webmotors.proximo");

	public static final String ESTADO_URL_ENCODING = "%20";
	
	public static final String CRAWLER_PATTERN_OLX = Play.configuration.getProperty("url.olx.veiculo");

	public static final String ANUNCIO_PAG_OLX = Play.configuration.getProperty("url.olx.comprar");
	
	public static final String ANUNCIO_PES_PAG_OLX = Play.configuration.getProperty("url.olx.proximo");
	
	public static final String WebMotorsConstant = "WEBMOTORS";
	
	public static final String OlxConstant = "OLX";
}
