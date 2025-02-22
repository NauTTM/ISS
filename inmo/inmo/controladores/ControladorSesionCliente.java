package inmo.controladores;

import java.util.List;






import inmo.excepciones.UsuarioException;
import inmo.excepciones.ViviendaException;
import inmo.usuarios.Cliente;
import inmo.usuarios.GestorUsuarios;
import inmo.viviendas.GestorAnuncio;
import inmo.viviendas.Horario;
import inmo.viviendas.Orden;
import inmo.viviendas.Tipo;
import inmo.viviendas.Zona;

//import java.util.ArrayList;

import inmo.viviendas.Anuncio;
import inmo.viviendas.Cita;
import inmo.viviendas.Dia;
import inmo.excepciones.*;

public class ControladorSesionCliente {
	
	private GestorUsuarios gu; /** el gestor de usuarios */
	private  Cliente cli; /** el cliente de la sesión */
	private List<Anuncio> listaAnuncioCliente;
	
	
	
	/**
	 * Constructor que asigna el gestor de usuarios que recibe como parámetro	 * 
	 * @param gesu es el gestor de usuarios 
	 */
	public ControladorSesionCliente(GestorUsuarios gesu) {
		this.gu = gesu;
	}

	/**
	 * Metodo para registrarse como cliente en el sistema
	 * @param login del cliente a crear (ÚNICO)
	 * @param clave del cliente a crear
	 * @param nombre del cliente a crear
	 * @throws ExcepcionUsuario si ya existe un usuario con ese login
	 * 			o si hubo un error interno en la creación del cliente
	 */
	public void crearCliente(String login, String clave, String nombre) throws UsuarioException {
		// creo cliente
		gu.crearUsuario(login, clave, nombre, "Cliente");
	}	
	
	/**
	 * Metodo para identificar a un cliente en el sistema, guardando una referencia en el atributo cli
	 * @param login del cliente
	 * @param clave del cliente
	 * @throws ExcepcionUsuario si las credenciales de usuario no son válidas
	 * 			o si las credenciales no son de un administrativo
	 */
	public void identificarCliente(String login, String clave) throws UsuarioException {
		if (gu.validarUsuario(login, clave)) {
			// usuario válido
			try {
				this.setCli((Cliente) gu.getUsuario(login));
			} catch (ClassCastException e) {
				throw new UsuarioException("Usuario "+login+" no es Cliente");
			}
		}
		else // usuario no válido
			throw new UsuarioException("Credenciales de usuario no válidas");
	}
	public List<Anuncio> getListaAnuncioCliente() {
		return listaAnuncioCliente;
	}

	public void setListaAnuncioCliente(List<Anuncio> listaAnuncioCliente) {
		this.listaAnuncioCliente = listaAnuncioCliente;
	}	
	/**
	 * Metodo para cerrar sesión que elimina la referencia a cli
	 */
	public void cerrarSesion() {
		setCli(null);
	}

	public void crearAnuncioVivienda(Tipo tipo, Zona zona, int nHabit, double sup, float precio) throws UsuarioException, ViviendaException{
		GestorAnuncio.crearAnuncio(tipo, zona, nHabit, sup, precio, cli);
	}
		
	

	public List<String> recuperarMisAnuncios()throws UsuarioException {
		return cli.recuperarMisAnuncios();
	}

	public Cliente getCli() {
		return cli;
	}

	public void setCli(Cliente cli) {
		this.cli = cli;
	}

	public List<String> buscarViviendas(Tipo tipo, Zona zona, int minHab, int supMin, int supMax, int precioMin, int precioMax, Orden precioDecrec)throws UsuarioException{
		return GestorAnuncio.buscarViviendas(tipo, zona, minHab, supMin, supMax, precioMin, precioMax, precioDecrec);
		
	}

	public String informacionVivienda(int i)throws ViviendaException, UsuarioException {
		return GestorAnuncio.VerVivienda(i);
	}

	public void solicitarCita(int i, Horario manana, Dia lunes) throws ViviendaException, UsuarioException, CitaException  {
		Cita.solicitarVisita(i, manana, lunes, this.cli);
	}


	public void cancelarCita(int i)throws UsuarioException, CitaException {
		Cita.CancelarCita(i);
	}

	public List<String> recuperarMisCitas()throws UsuarioException {
		return Cita.ListarCitas(this.cli);
	}
	
}
