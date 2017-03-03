package projeto.controle;

/**
 * Esta classe fornece o endere�o dos recuros do jogo que s�o as imagens e os
 * aruivos de �udio.
 * 
 * @author Luiz Alberto
 * 
 */
public class URL {
	
	//java.net.URL scn = recursos.Recursos.class.getResource("scn/");

	private static final String DIR = "src/recursos/";
	private static final String SEPARADOR = "/";

	
	/**
	 * Endere�o de arquivos de tile.
	 * 
	 * @param arquivo
	 * @return String com a url do arquivo
	 */
	public static String tile(String arquivo) {
		StringBuilder builder = new StringBuilder();
		builder.append(DIR);
		builder.append("tiles");
		builder.append(SEPARADOR);
		builder.append(arquivo);
		return builder.toString();
	}

	/**
	 * Endere�o de arquivos de sprite.
	 * 
	 * @param arquivo
	 * @return String com a url do arquivo
	 */
	public static String sprite(String arquivo) {
		StringBuilder builder = new StringBuilder();
		builder.append(DIR);
		builder.append("sprites");
		builder.append(SEPARADOR);
		builder.append(arquivo);
		return builder.toString();
	}

	/**
	 * Endere�o de arquivos de �udio.
	 * 
	 * @param arquivo
	 * @return String com a url do arquivo
	 */
	public static String audio(String arquivo) {
		StringBuilder builder = new StringBuilder();
		builder.append(DIR);
		builder.append("audio");
		builder.append(SEPARADOR);
		builder.append(arquivo);
		return builder.toString();
	}

	/**
	 * Endere�o de arquivos de cen�rio.
	 * 
	 * @param arquivo
	 * @return String com a url do arquivo
	 */
	public static String scenario(String arquivo) {
		StringBuilder builder = new StringBuilder();
		builder.append(DIR);
		builder.append("scn");
		builder.append(SEPARADOR);
		builder.append(arquivo);
		return builder.toString();
	}
	

}
