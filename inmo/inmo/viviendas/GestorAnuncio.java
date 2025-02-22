package inmo.viviendas;


import java.util.List;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import inmo.excepciones.UsuarioException;
import inmo.excepciones.ViviendaException;
import inmo.usuarios.Cliente;


public class GestorAnuncio {
	
	public static List<Anuncio> anuncios = new ArrayList<>();
	
	private static final Comparator<Anuncio> PRECIO_CREC = new Comparator<Anuncio>(){
		public int compare(Anuncio a1, Anuncio a2) {
			return a1.getPrecio().compareTo(a2.getPrecio());
		}
	};
	
	private static final Comparator<Anuncio> PRECIO_DECREC = new Comparator<Anuncio>(){
		public int compare(Anuncio a1, Anuncio a2) {
			return a2.getPrecio().compareTo(a1.getPrecio());
		}
	};

	private static final Comparator<Anuncio> SUP_CREC = new Comparator<Anuncio>(){
		public int compare(Anuncio a1, Anuncio a2) {
			return a1.getSup().compareTo(a2.getSup());
		}
	};
			
	private static final Comparator<Anuncio> SUP_DECREC = new Comparator<Anuncio>(){
		public int compare(Anuncio a1, Anuncio a2) {
			return a2.getSup().compareTo(a1.getSup());
		}
	};
			
	public static void crearAnuncio(Tipo tipo, Zona zona, int nHabit, double sup, float precio, Cliente cli) throws UsuarioException, ViviendaException {
		if (cli == null){
			throw new UsuarioException("No hay ciente");
		}
		if(cli.getNumAnuncios() >= 5){
			throw new ViviendaException("Superado el limite de anuncios");
		}
		int id = anuncios.size();

		Anuncio a = new Anuncio(tipo, zona, nHabit, sup, precio, cli, id);
		anuncios.add(a);
		cli.guardarAnuncios(a);
	}


	public static List<Anuncio> listarAnunciosPendientes(){
		List<Anuncio> b = new ArrayList<>();
		for (Anuncio a : anuncios) {
			if(a.getEstado().equals("pendiente")) {
				b.add(a);
			}			
		}
		return b;
	}


	
	public static List<String> buscarViviendas(Tipo tipo, Zona zona, int minHab, int supMin, int supMax, int precioMin, int precioMax, Orden orden) {
		List<Anuncio> anuncios = new ArrayList<Anuncio>();
		List<String> anunciosString = new  ArrayList<String>();
		for(int i= 0; i < GestorAnuncio.anuncios.size();i++) {
			if((zona == null || GestorAnuncio.anuncios.get(i).getZona() == null || GestorAnuncio.anuncios.get(i).getZona().equals(zona)) &&
					(tipo == null || GestorAnuncio.anuncios.get(i).getTipo() == null || GestorAnuncio.anuncios.get(i).getTipo().equals(tipo)) &&
					(minHab == 0 || GestorAnuncio.anuncios.get(i).getNHabit() >= minHab) &&
					(supMin == 0 || GestorAnuncio.anuncios.get(i).getSup() >= supMin) && (supMax == 0 || GestorAnuncio.anuncios.get(i).getSup() <= supMax) &&
					(precioMin == 0 || GestorAnuncio.anuncios.get(i).getPrecio() >= precioMin) &&  (precioMax == 0 || GestorAnuncio.anuncios.get(i).getPrecio() <= precioMax)) {
				GestorAnuncio.anuncios.get(i).getManana();
				GestorAnuncio.anuncios.get(i).getTarde();
				anuncios.add(GestorAnuncio.anuncios.get(i));
			}
		}
		
		if(orden == Orden.PRECIO_CREC) {
			Collections.sort(anuncios, PRECIO_CREC);
		}
		
		if(orden == Orden.PRECIO_DECREC) {
			Collections.sort(anuncios, PRECIO_DECREC);
		}
		
		if(orden == Orden.SUP_CREC) {
			Collections.sort(anuncios, SUP_CREC);
		}
		
		if(orden == Orden.SUP_DECREC) {
			Collections.sort(anuncios, SUP_DECREC);
		}

		for(int i= 0; i < anuncios.size();i++) {
			anunciosString.add(anuncios.get(i).citas());

		}
		return anunciosString;
	}

	public static String VerVivienda(int i) {
		String anuncioString = anuncios.get(i).citas();
		return anuncioString;
	}
	
	public List<Anuncio> getAnuncios() {
		return anuncios;
	}


	public void setAnuncios(List<Anuncio> anuncios) {
		GestorAnuncio.anuncios = anuncios;
	}


	public static void validarAnuncio(int i, boolean b) {
		Anuncio a = anuncios.get(i);
		if(b){
			a.setEstado("Valido");
		}else{
			a.setEstado("No Valido");
		}
		
	}
}
