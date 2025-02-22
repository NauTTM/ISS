package inmo.usuarios;

public class Empleado extends Usuario {	

	public Empleado(String l, String c, String n) {
		super(l, c, n);
	}
	
	/**
	 * Método que devuelve una descripción del usuario
	 * 
	 * @return descripción
	 */

	public String toString() {
		// Compone una cadena con todos los campos y la retorna
		String cadena = super.toString();
		cadena += "\n Tipo: Empleado";
		return cadena;
	}
	
	
}
