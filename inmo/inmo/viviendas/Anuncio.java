package inmo.viviendas;
import java.util.ArrayList;

import inmo.usuarios.Cliente;

public class Anuncio {
	private Tipo tipo;
	private Zona zona;
	private double sup;
	private int nHabit;
	private float precio;
	private Cliente cli;
	private String estado = "pendiente";
	private int id;
	private ArrayList<String> manana = new ArrayList<String>();
	private String orden;
	private ArrayList<String> tarde = new ArrayList<String>();

	
	public Anuncio(Tipo tipo, Zona zona, int nHabit, double sup, float precio, Cliente cli, int id){
		this.tipo = tipo;
		this.zona = zona;
		this.sup = sup;
		this.nHabit = nHabit;
		this.precio = precio;
		this.cli = cli;
		this.id = id;
		
		this.getManana().add(Dia.LUNES.toString());
		this.getManana().add(Dia.MARTES.toString());
		this.getManana().add(Dia.MIERCOLES.toString());
		this.getManana().add(Dia.JUEVES.toString());
		this.getManana().add(Dia.VIERNES.toString());
		
		this.getTarde().add(Dia.LUNES.toString());
		this.getTarde().add(Dia.MARTES.toString());
		this.getTarde().add(Dia.MIERCOLES.toString());
		this.getTarde().add(Dia.JUEVES.toString());
		this.getTarde().add(Dia.VIERNES.toString());
		
	}
	



	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	public Double getSup() {
		return sup;
	}
	public void setSup(Double sup) {
		this.sup = sup;
	}
	public int getNHabit() {
		return nHabit;
	}
	public void setNHabit(int nHabit) {
		this.nHabit = nHabit;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public Cliente getClient() {
		return cli;
	}
	public void setClient(Cliente cli) {
		this.cli = cli;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getOrden(){
		return orden;
	}
	
	public void setOrden(String orden){
		this.orden = orden;
	}
	

	public String toString() {
		return "Tipo : " + getTipo() 
		 + "\n Zona : " + getZona()
		 + "\n Superficie : " +  getSup()
		 + "\n Hbitaciones : " + getNHabit()
		 + "\n Precio : " + getPrecio()
		 + "\n Propietario : " + getClient()
		 + "\n Estado : " + getEstado();
	}
	

	
	public String citas() {
		return "Tipo : " + getTipo() 
		 + "\n Zona : " + getZona()
		 + "\n Superficie : " +  getSup()
		 + "\n Hbitaciones : " + getNHabit()
		 + "\n Precio : " + getPrecio()
		 + "\n Propietario : " + getClient()
		 + "\n Estado : " + getEstado()
		 +"\n Citas disponibles de manana: " + getManana().toString()
		+"\n Citas disponible de tarde: " + getTarde().toString();

	}




	public ArrayList<String> getManana() {
		return manana;
	}




	public void setManana(ArrayList<String> manana) {
		this.manana = manana;
	}




	public ArrayList<String> getTarde() {
		return tarde;
	}




	public void setTarde(ArrayList<String> tarde) {
		this.tarde = tarde;
	}




	
}
