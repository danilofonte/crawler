package com.java.crawler.webmotors.actions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.java.crawler.contants.CrawlerConstants;
import com.java.crawler.contants.CrawlerSession;
import com.java.crawler.models.Anuncio;
import com.java.crawler.webmotors.patterns.WebMotorsPatternUtils;

public class WebMotorsActions {

	public static void createAnuncio(String link,String html) {

		Anuncio anuncio = new Anuncio();

		Matcher ano = WebMotorsPatternUtils.patternAno.matcher(html);

		Matcher preco = WebMotorsPatternUtils.patternPreco.matcher(html);

		Matcher cor = WebMotorsPatternUtils.patternCor.matcher(html);

		Matcher portas = WebMotorsPatternUtils.patternPortas.matcher(html);

		/*Matcher finalPlaca = WebMotorsPatternUtils.patternFinalClaca
				.matcher(html);*/

		Matcher carroceria = WebMotorsPatternUtils.patternCarroceria
				.matcher(html);

		Matcher dataAnuncio = WebMotorsPatternUtils.patternDataAnuncio
				.matcher(html);
		
		Matcher cambio = WebMotorsPatternUtils.patternCambio
				.matcher(html);

		/*Matcher km = WebMotorsPatternUtils.patternKm.matcher(html);*/

		Matcher combustivel = WebMotorsPatternUtils.patternCombustivel
				.matcher(html);
		
		Matcher tipo = WebMotorsPatternUtils.patternTipo.matcher(html);
		
		Matcher cidade = WebMotorsPatternUtils.patternCidade.matcher(html);
		
		Matcher estado = WebMotorsPatternUtils.patternEstado.matcher(html);
		
		Matcher codAnuncio = WebMotorsPatternUtils.patternCodAnuncio.matcher(html);
		
		Matcher nomeVersao = WebMotorsPatternUtils.patternNomeVersao.matcher(html);

		/*Matcher opcionais = WebMotorsPatternUtils.patternOpcionais
				.matcher(html);*/

		anuncio.ano = ano.find() ? ano.group(1) : null;
		
		anuncio.cambio = cambio.find() ? cambio.group(1) : null;
		
		anuncio.cor = cor.find() ? cor.group(1) : null;
		
		anuncio.carroceria = carroceria.find() ? carroceria.group(1) : null;
		
		anuncio.dataAnuncio = dataAnuncio.find() ? dataAnuncio.group(1) : null;
		
		/*anuncio.km = km.find() ? km.group(1) : null;*/
		
		anuncio.link = link;
		
		anuncio.modeloAtivo = CrawlerSession.MODELO_WEBMOTOR;
		
		/*anuncio.opcionais = opcionais.find() ? opcionais.group() : null;*/
		
		/*anuncio.placa = finalPlaca.find() ? finalPlaca.group() : null;*/
		
		anuncio.portas = portas.find() ? portas.group(1) : null;
		
		anuncio.preco = preco.find() ? preco.group(1) : null;
		
		anuncio.tipoCombustivel = combustivel.find() ? combustivel.group(1) : null;
		
		anuncio.cidade = cidade.find() ? cidade.group(1) : null;
		
		anuncio.estado = estado.find() ? estado.group(1) : null;
		
		anuncio.idAnuncio = codAnuncio.find() ? codAnuncio.group(1) : null;
		
		anuncio.nmVersao = nomeVersao.find() ? nomeVersao.group(1) : null;
		
		anuncio.tipo = tipo.find() ? tipo.group(1) : null;
		
		Anuncio.salvar(anuncio);

	}

}
