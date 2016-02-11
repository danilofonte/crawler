package controllers;

import java.util.ArrayList;
import java.util.List;

import jobs.process.OlxCrawlerJobProcess;
import jobs.process.WebMotorsCrawlerJobProcess;
import models.Anuncio;
import models.Marca;
import models.Modelo;

import org.apache.commons.lang.StringUtils;

import serializer.AnuncioSerializer;

public class CrawlerController extends DefaultController {

	public static void listarPorParametros(String estadoCidade, String estado, String marca, String modelo) {

		try {

			List<Anuncio> anuncios = new ArrayList<Anuncio>();

			Marca marcaAtiva = (Marca) Marca.find("nomeWebMotors", marca.toUpperCase()).asList().get(0);

			Modelo modeloAtivo = (Modelo) Modelo.find("nomeWebMotors", modelo.toUpperCase()).asList().get(0);

			if (!StringUtils.isEmpty(marcaAtiva.nomeOlx) && !StringUtils.isEmpty(modeloAtivo.nomeOlx)) {

				anuncios.addAll(OlxCrawlerJobProcess.getAnuncios(estadoCidade.split("-")[0], marcaAtiva.nomeOlx, modeloAtivo.nomeOlx));

			}

			anuncios.addAll(WebMotorsCrawlerJobProcess.getAnuncios(estadoCidade, estado, marca, modelo));

			renderJSON(anuncios, AnuncioSerializer.anuncioSerializer);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
