package controllers;

import models.Cidade;
import models.Estado;
import serializer.EstadoCidadeSerializer;

public class EstadoCidadeController extends DefaultController {

	
	public static void getEstados() {
		renderJSON(Estado.findAll(), EstadoCidadeSerializer.estadoSerializer);
	}
	
	public static void getCidadesPorEstado(String uf) {
		renderJSON(Cidade.find("uf", uf).asList(), EstadoCidadeSerializer.cidadeSerializer);
	}
	
}
