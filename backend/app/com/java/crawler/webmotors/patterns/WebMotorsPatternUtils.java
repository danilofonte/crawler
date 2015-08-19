package com.java.crawler.webmotors.patterns;

import java.util.regex.Pattern;

import play.Play;

public class WebMotorsPatternUtils {

	public static final Pattern patternPreco = Pattern
			.compile("<meta name=\"wm.dt_prc\" content=\"([^\"]+)\" />");

	public static final Pattern patternAno = Pattern
			.compile("<meta name=\"wm.dt_anomod\" content=\"([^\"]+)\" />");

	public static final Pattern patternCor = Pattern
			.compile("<meta name=\"wm.dt_cor\" content=\"([^\"]+)\" />");

	/*public static final Pattern patternKm = Pattern
			.compile("<meta name=\"wm.dt_anomod\" content=\"([^\"]+)\" />");*/

	public static final Pattern patternCombustivel = Pattern
			.compile("<meta name=\"wm.dt_combustivel\" content=\"([^\"]+)\" />");

	public static final Pattern patternCambio = Pattern
			.compile("<meta name=\"wm.dt_cambio\" content=\"([^\"]+)\" />");

	public static final Pattern patternCarroceria = Pattern
			.compile("<meta name=\"wm.dt_carroceria\" content=\"([^\"]+)\" />");

	public static final Pattern patternDataAnuncio = Pattern
			.compile("/([0-9]{8})/");

	public static final Pattern patternPortas = Pattern
			.compile("/([0-9]{1})-portas/");

/*	public static final Pattern patternOpcionais = Pattern
			.compile("<meta name=\"wm.dt_anomod\" content=\"([^\"]+)\" />");*/

/*	public static final Pattern patternFinalClaca = Pattern
			.compile("<meta name=\"wm.dt_anomod\" content=(\\S+)\" />");*/
	
	public static final Pattern patternTipo = Pattern
			.compile("<meta name=\"wm.dt_tipoc\" content=\"([^\"]+)\" />");
	
	public static final Pattern patternCidade = Pattern
			.compile("<meta name=\"wm.dt_cidade\" content=\"([^\"]+)\" />");
	
	public static final Pattern patternEstado = Pattern
			.compile("<meta name=\"wm.dt_estado\" content=\"([^\"]+)\" />");
	
	public static final Pattern patternCodAnuncio = Pattern
			.compile("<meta name=\"wm.dt_codanunc\" content=\"([^\"]+)\" />");
	
	public static final Pattern patternNomeVersao = Pattern
			.compile("<meta name=\"wm.dt_mmv\" content=\"([^\"]+)\" />");
}
