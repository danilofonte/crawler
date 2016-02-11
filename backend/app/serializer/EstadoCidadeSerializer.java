package serializer;


import play.Play;
import play.Play.Mode;
import flexjson.JSONSerializer;

public class EstadoCidadeSerializer {
	
	public static JSONSerializer estadoSerializer;
	public static JSONSerializer cidadeSerializer;

	static {

		boolean prettyPrint = Play.mode == Mode.DEV;

		
		estadoSerializer = new JSONSerializer().include("nome","sigla","urlEncoding").exclude("*").prettyPrint(prettyPrint);
		cidadeSerializer = new JSONSerializer().include("nome","uf").exclude("*").prettyPrint(prettyPrint);

	}

}
