package inmo.usuarios;

import java.util.List;
import java.util.ArrayList;

import inmo.excepciones.UsuarioException;
import inmo.viviendas.Anuncio;
import inmo.viviendas.Cita;

public class Cliente extends Usuario {
	private List<Anuncio> anuncios = new ArrayList<>();
	private List<Cita> citas = new ArrayList<>();
	
	public Cliente(String l, String c, String n) {
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
		cadena += "\n Tipo: Cliente";
		return cadena;
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
	
	public int getNumAnuncios(){
		return anuncios.size();
	}
	
	public int getNumCitas(){
		return citas.size();
	}
	
	public void guardarAnuncios(Anuncio a) {
			anuncios.add(a);
	}
	
	public void guardaCitas(Cita c) {
		citas.add(c);
	}
	
	public List<String> recuperarMisAnuncios() throws UsuarioException {

		List<String> anuncio = new ArrayList<>();
		
		for(int i = 0; i < getNumAnuncios(); i++) {
			anuncio.add(anuncios.get(i).toString());
		}
		return anuncio;
		
	}
	
	public List<String> recuperarMisCitas() throws UsuarioException {

		List<String> cita = new ArrayList<>();
		
		for(int i = 0; i < getNumAnuncios(); i++) {
			cita.add(citas.get(i).toString());
		}
		return cita;
		
	}

	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}
}
