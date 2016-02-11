package serializer;

import play.Play;
import play.Play.Mode;
import flexjson.JSONSerializer;

public class AnuncioSerializer {
	
	public static JSONSerializer anuncioSerializer;
	
	

	static {

		boolean prettyPrint = Play.mode == Mode.DEV;

		
		anuncioSerializer = new JSONSerializer().include("link","preco","ano","km","tipoCombustivel","cor","cambio","placa","carroceria","portas","dataAnuncio","opcionais","cidade","estado","idAnuncio","nmVersao","tipo","referr","siteReferencia","imagemUrl").exclude("*").prettyPrint(prettyPrint);

	}

}
