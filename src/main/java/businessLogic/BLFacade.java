package businessLogic;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Date;





//import domain.Booking;
import domain.Question;
import domain.Seleccion;
import domain.Subdito;
import domain.Usuario;
import domain.Apuesta;
import domain.Estadistica;
import domain.Event;
import domain.Mensaje;
import domain.Pronostico;
import exceptions.ApuestaAlreadyExists;
import exceptions.EventAlreadyExists;
import exceptions.EventFinished;
import exceptions.PronosticoAlreadyExists;
import exceptions.QuestionAlreadyExist;
import exceptions.ResultAlreadyExists;
import exceptions.UserAlreadyExists;
import exceptions.WrongUserOrPassword;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	@WebMethod public Vector<Event> getEventsEspecified(Date date, Seleccion sel);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	@WebMethod public Vector<Date> getEventsMonthEspecified(Date date, Seleccion sel);
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	@WebMethod Usuario anadirUsuario(String nombre, String apellido1, String apellido2, int telefono, String correo, String nombreUsuario, Date fechaNacimiento, String DNI, String password, int numTarjeta) throws UserAlreadyExists;

	@WebMethod public Usuario comprobar(String nombreUsuario, String password) throws WrongUserOrPassword;
	
	@WebMethod public boolean esSubdito(String nombreUsuario, String password) throws WrongUserOrPassword;
	
	@WebMethod public boolean esAdmin(String nombreUsuario, String password) throws WrongUserOrPassword;
	
	@WebMethod public Subdito contratarSubdito(String nombreUsuario, String password) throws UserAlreadyExists;
	
	@WebMethod public Event anadirEvent(String description, Date eDate, Seleccion s) throws EventAlreadyExists;
	
	@WebMethod public Pronostico anadirPronostico(Question question, String sol, float porGanancia) throws PronosticoAlreadyExists;
	
	@WebMethod public int getNumberOfEvents();
	
	@WebMethod public Apuesta anadirApuesta(Usuario user, Pronostico respuesta, float apuesta, String modoPago, Event ev, int year);
	
	@WebMethod public String anadirResultado(Question question, Pronostico pronostico) throws ResultAlreadyExists;
	
	@WebMethod Vector<Usuario> getUsuariosGanadores(Pronostico pronos, int year);
	
	@WebMethod public boolean cerrarEvento(Event event);

	@WebMethod int getNumCuponesConseguido(Usuario u);
	
	@WebMethod public float getDineroAcumulado(Usuario u);
	
	@WebMethod public Seleccion anadirSeleccion(String dep, String gen, String sel);
	
	@WebMethod public float getMiMonedero(Usuario u);
	
	@WebMethod public Usuario usuarioMasApostado();
	
	@WebMethod Usuario usuarioMayorApuesta();
	
	@WebMethod public Vector<Event> eventosMasApostados();
	
	@WebMethod public Vector<Seleccion> seleccionesMasApostadas();
	
	@WebMethod Usuario anadirAListaNegra(Usuario user, int year);
	
	@WebMethod public Usuario eliminarDeListaNegra(String user, int year);
	
	@WebMethod Estadistica crearEstadistica(int year);
	
	@WebMethod public Estadistica actualizarEstadistica(int year);
	
	@WebMethod public float conseguirGanancias(int year);
	
	@WebMethod ArrayList<Usuario> getListaNegra(int year);
	
	@WebMethod public void bloquearUsuario(String user, int year);
	
	@WebMethod public Vector<Mensaje> getMensajesAll();

	@WebMethod Mensaje guardarMensaje(Usuario user, String texto, Date date);
	
	@WebMethod int dadoMegusta(Mensaje m);
	
	@WebMethod Vector<Mensaje> filtrarPorUsuario(String nombreUsuario);
	
	@WebMethod Usuario crearUser(String nombreUsuario);
	
}
