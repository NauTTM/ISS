package inmo.viviendas;



import java.util.ArrayList;
import java.util.List;

import inmo.excepciones.CitaException;
import inmo.excepciones.UsuarioException;
import inmo.excepciones.ViviendaException;
import inmo.usuarios.Cliente;




public class Cita {

	
	private static List<Anuncio> citas = GestorAnuncio.anuncios;
	private static List<Cita> listacitas = new ArrayList<>();
	private Dia dia;
	private Horario horario;
	private String estado = "PROGRAMADO";
	private Cliente cli;
	private int id;

	
	public Cita(Dia dia, Horario horario, Cliente cli, int id) {
		this.dia = dia;
		this.horario = horario;
		this.cli = cli;
		this.id = id;
	}

	
	public static void solicitarVisita(int i, Horario horario, Dia dia, Cliente cli)throws UsuarioException, ViviendaException, CitaException {			
		
		
		if (i >= citas.size()) {
	        throw new ViviendaException("Anuncio no existente");
	    }
		
		if(cli.equals(GestorAnuncio.anuncios.get(i).getClient())) {
			throw new UsuarioException("El cliente ya es el propietario");
		}
		if(GestorAnuncio.anuncios.get(i).getEstado().equals("pendiente")) {
			throw new ViviendaException("Anuncio pendiente de validar");
		}
		if(GestorAnuncio.anuncios.get(i).getEstado().equals("No Valido")) {
			throw new ViviendaException("Anuncio no validado");
		}
		if(cli.getNumCitas() >= 3) {
			throw new CitaException("Limite de citas alcanzado");
		}
		for(int g = 0; g < listacitas.size(); g++) {
			if(listacitas.get(g).getId() == i) {
				throw new CitaException("Cita ya solicitada");
			}
		}
		
		
		
			if(citas.get(i) == null){
					throw new ViviendaException("Cita no disponible");
			}
		else {
			
			if(horario.toString().equals("manana")) {
					
					for(int y = 0; y < citas.get(i).getManana().size(); y++) {
						if(citas.get(i).getManana().get(y).equals(dia.toString())) {
							ArrayList<String> dias = citas.get(i).getManana();
							dias.remove(y);
							citas.get(i).setManana(dias);
							
							Cita c = new Cita(dia, horario, cli, citas.get(i).getId());
							listacitas.add(c);
							cli.guardaCitas(c);
						}
					}
			}
			
			if(horario.toString().equals("tarde")) {
				for(int y = 0; y < citas.get(i).getTarde().size(); y++) {
					if(citas.get(i).getTarde().get(y).equals(dia.toString())) {
						ArrayList<String> dias = citas.get(i).getTarde();
						dias.remove(y);
						citas.get(i).setTarde(dias);
						Cita c = new Cita(dia, horario, cli, citas.get(i).getId());
						listacitas.add(c);
						cli.guardaCitas(c);
						
					}
				}
			}
		}

		
	}
     
	public static List<String> ListarCitas(Cliente cli) {
		List<Cita> anuncios = Cita.listacitas;
		
	    List<String> citaString = new ArrayList<>();
	    for (int i = 0; i < anuncios.size(); i++) {
	        if (cli.getNombre().equals(anuncios.get(i).getClient().getNombre()) || GestorAnuncio.anuncios.get(i).getEstado().equals("Valido")) {
	            citaString.add(anuncios.get(i).cita());
	        }
	    }
	    return citaString;	
	}
	
	public static void CancelarCita(int i) throws CitaException {
		if (i >=0 && i < listacitas.size()) {
			 if (listacitas.get(i) != null) {
				 listacitas.remove(i);
			 }
		}else {
			throw new CitaException("No se puede cancelar esta cita");
		}
	}

	public Cliente getClient() {
		return cli;
	}
	public void setClient(Cliente cli) {
		this.cli = cli;
	}
	
	
	public Horario getHorario(){
		return horario;
	}
	
	public void setHorario(Horario horario){
		this.horario = horario;
	}
	
	public Dia getDia(){
		return dia;
	}
	public void setDia(Dia dia){
		this.dia = dia;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String cita() {
		String Nombre = cli.getNombre();
		return "\n Cliente: " + Nombre
		 + "\n Estado : " + getEstado()
		 +"\n dia: " + getDia()
		+"\n horario: " + getHorario();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	

}
