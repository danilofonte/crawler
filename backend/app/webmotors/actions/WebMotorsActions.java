package webmotors.actions;

import java.util.regex.Matcher;

import models.Anuncio;
import webmotors.patterns.WebMotorsPatternUtils;
import constans.CrawlerConstants;
import constans.CrawlerSession;

public class WebMotorsActions {

	public static void createAnuncio(String link, String html) {

		Anuncio anuncio = new Anuncio();

		Matcher ano = WebMotorsPatternUtils.patternAno.matcher(html);

		Matcher preco = WebMotorsPatternUtils.patternPreco.matcher(html);

		Matcher cor = WebMotorsPatternUtils.patternCor.matcher(html);

		Matcher portas = WebMotorsPatternUtils.patternPortas.matcher(html);

		Matcher carroceria = WebMotorsPatternUtils.patternCarroceria.matcher(html);

		Matcher dataAnuncio = WebMotorsPatternUtils.patternDataAnuncio.matcher(html);

		Matcher cambio = WebMotorsPatternUtils.patternCambio.matcher(html);

		Matcher combustivel = WebMotorsPatternUtils.patternCombustivel.matcher(html);

		Matcher tipo = WebMotorsPatternUtils.patternTipo.matcher(html);

		Matcher cidade = WebMotorsPatternUtils.patternCidade.matcher(html);

		Matcher estado = WebMotorsPatternUtils.patternEstado.matcher(html);

		Matcher codAnuncio = WebMotorsPatternUtils.patternCodAnuncio.matcher(html);

		Matcher nomeVersao = WebMotorsPatternUtils.patternNomeVersao.matcher(html);

		anuncio.ano = ano.find() ? ano.group(1) : null;

		anuncio.cambio = cambio.find() ? cambio.group(1) : null;

		anuncio.cor = cor.find() ? cor.group(1) : null;

		anuncio.carroceria = carroceria.find() ? carroceria.group(1) : null;

		anuncio.dataAnuncio = dataAnuncio.find() ? dataAnuncio.group(1) : null;

		anuncio.link = link;

		anuncio.modeloAtivo = CrawlerSession.MODELO_WEBMOTOR;

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

	public static Anuncio getAnuncio(String link, String html) {

		Anuncio anuncio = new Anuncio();

		Matcher ano = WebMotorsPatternUtils.patternAno.matcher(html);

		Matcher preco = WebMotorsPatternUtils.patternPreco.matcher(html);

		Matcher cor = WebMotorsPatternUtils.patternCor.matcher(html);

		Matcher portas = WebMotorsPatternUtils.patternPortas.matcher(html);

		Matcher carroceria = WebMotorsPatternUtils.patternCarroceria.matcher(html);

		Matcher dataAnuncio = WebMotorsPatternUtils.patternDataAnuncio.matcher(html);

		Matcher cambio = WebMotorsPatternUtils.patternCambio.matcher(html);

		Matcher combustivel = WebMotorsPatternUtils.patternCombustivel.matcher(html);

		Matcher tipo = WebMotorsPatternUtils.patternTipo.matcher(html);

		Matcher cidade = WebMotorsPatternUtils.patternCidade.matcher(html);

		Matcher estado = WebMotorsPatternUtils.patternEstado.matcher(html);

		Matcher codAnuncio = WebMotorsPatternUtils.patternCodAnuncio.matcher(html);

		Matcher nomeVersao = WebMotorsPatternUtils.patternNomeVersao.matcher(html);

		Matcher imagemUrl = WebMotorsPatternUtils.patternImagemUrl.matcher(html);

		anuncio.ano = ano.find() ? ano.group(1) : null;

		anuncio.cambio = cambio.find() ? cambio.group(1) : null;

		anuncio.cor = cor.find() ? cor.group(1) : null;

		anuncio.carroceria = carroceria.find() ? carroceria.group(1) : null;

		anuncio.dataAnuncio = dataAnuncio.find() ? dataAnuncio.group(1) : null;

		anuncio.link = link;

		anuncio.portas = portas.find() ? portas.group(1) : null;

		anuncio.preco = preco.find() ? preco.group(1) : null;

		anuncio.tipoCombustivel = combustivel.find() ? combustivel.group(1) : null;

		anuncio.cidade = cidade.find() ? cidade.group(1) : null;

		anuncio.estado = estado.find() ? estado.group(1) : null;

		anuncio.idAnuncio = codAnuncio.find() ? codAnuncio.group(1) : null;

		anuncio.nmVersao = nomeVersao.find() ? nomeVersao.group(1) : null;

		anuncio.tipo = tipo.find() ? tipo.group(1) : null;

		anuncio.imagemUrl = imagemUrl.find() ? imagemUrl.group().split("\\?")[0].replace("<meta property=\"og:image\" name=\"og:image\" content=\"", "") : null;

		anuncio.siteReferencia = CrawlerConstants.WebMotorsConstant;

		return anuncio;

	}

}
