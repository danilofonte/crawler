package controllers;

import java.util.List;

import models.Marca;
import models.Modelo;
import serializer.MarcaModeloSerializer;

import com.google.code.morphia.Key;

public class MarcaModeloController extends DefaultController {
	
	public static void getMarcas() {
		renderJSON(Marca.findAll(), MarcaModeloSerializer.marcaSerializer);
	}
	
	public static void getModelosPorMarca(String marca) {
		List<Key<Marca>> marcaAtiva = Marca.find("nomeWebMotors", marca).asKeyList();
		
		renderJSON(Modelo.q().filter("marcaAtiva in",marcaAtiva).asList(), MarcaModeloSerializer.modeloSerializer);
	}

}
