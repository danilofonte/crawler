package serializer;

import play.Play;
import play.Play.Mode;
import flexjson.JSONSerializer;

public class MarcaModeloSerializer {
	
	public static JSONSerializer marcaSerializer;
	public static JSONSerializer modeloSerializer;

	static {

		boolean prettyPrint = Play.mode == Mode.DEV;

		
		marcaSerializer = new JSONSerializer().include("nome","nomeWebMotors","nomeOlx").exclude("*").prettyPrint(prettyPrint);
		modeloSerializer = new JSONSerializer().include("nome","nomeWebMotors","nomeOlx").exclude("*").prettyPrint(prettyPrint);

	}

}
