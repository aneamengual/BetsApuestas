package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.Apuesta;
import domain.Estadistica;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Seleccion;
import domain.Usuario;
import exceptions.QuestionAlreadyExist;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
	
	public boolean removePronostico(Pronostico p) {
		System.out.println(">> DataAccessTest: removeEvent");
		Pronostico pronos = db.find(Pronostico.class, p.getNumPronostico());
		if (pronos!=null) {
			db.getTransaction().begin();
			db.remove(pronos);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
	
	
	public boolean removeSeleccion(Seleccion sel) {
		System.out.println(">> DataAccessTest: removeEvent");
		Seleccion s = db.find(Seleccion.class, sel.getId());
		if (s!=null) {
			db.getTransaction().begin();
			db.remove(s);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
		
		public Pronostico addPronostico(Question q, String solucion, float porGanancia) {
			System.out.println(">> DataAccessTest: addPronostico");
			Pronostico pronos=null;
				db.getTransaction().begin();
				try {
				    pronos=new Pronostico(q, solucion, porGanancia);
					db.persist(pronos);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return pronos;
	    }
		
		public Event addEvent(String description, Date eventDate, Seleccion seleccion) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(description, eventDate, seleccion);
					db.persist(ev);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		
		public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
			System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
			
				Event ev = db.find(Event.class, event.getEventNumber());
				
				if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
				
				db.getTransaction().begin();
				Question q = ev.addQuestion(question, betMinimum);
				//db.persist(q);
				db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
								// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
				db.getTransaction().commit();
				return q;
			
		}
		
		public Seleccion addSeleccion(String deporte, String genero, String seleccion) {
			System.out.println(">> DataAccessTest: addSeleccion");
			Seleccion sel=null;
				db.getTransaction().begin();
				try {
				    sel=new Seleccion(deporte, genero, seleccion);
					db.persist(sel);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return sel;
	    }
		
		public Question addQuestion(String query, float betMinimum, Event event) {
			System.out.println(">> DataAccessTest: addQuestion");
			Question q=null;
				db.getTransaction().begin();
				try {
				    q=new Question(query, betMinimum, event);
					db.persist(q);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return q;
	    }
		
		public Usuario addUsuario(String nombre, String apellido1, String apellido2, int telefono, String correo, String nombreUsuario, Date fechaNacimiento, String DNI, String password, int numTarjeta) {
			System.out.println(">> DataAccessTest: addUsuario");
			Usuario u=null;
				db.getTransaction().begin();
				try {
				    u=new Usuario( nombre, apellido1, apellido2, telefono, correo, nombreUsuario, fechaNacimiento, DNI, password, numTarjeta);
					db.persist(u);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return u;
	    }
		
		public boolean existPronos(Pronostico pronos) {
			System.out.println(">> DataAccessTest: existPronos");
			Pronostico p = db.find(Pronostico.class, pronos.getNumPronostico());
			if (p!=null) {
				return true;
			} else 
			return false;
			
		}
		
		public Usuario crearUser(String nombreUsuario){
			System.out.println(">> DataAccess: addApuesta");
			String contrasena ="hola";
			int tel= 787788;
			int tarjeta=8898989;
			Date date = new Date();
			String apellido1="ghjh";
			String ape2="sdg";
			String correo="ghj@hkj";
			String dni="7868D";
			String nombre="alex";
			Usuario u = new Usuario(nombre,apellido1,ape2,tel,correo,nombreUsuario,date,dni, contrasena,tarjeta);
			db.getTransaction().begin();
			db.persist(u);
			db.getTransaction().commit();
			return u;
			
			}
		public Apuesta anadirApuesta(Usuario user, Pronostico respuesta, float apuesta, String modoPago, Event ev, int year){
			System.out.println(">> DataAccess: CreateApuesta=> Usuario= "+ user+ " pronostico= "+respuesta + " apuesta= "+apuesta);
			
			Usuario u=db.find(Usuario.class, user.getNombreUsuario());	
			Event e=db.find(Event.class, ev.getEventNumber());
			Seleccion sel=db.find(Seleccion.class, e.getSeleccion().getId());
			Estadistica esta;
			TypedQuery<Estadistica> query = db.createQuery("SELECT e FROM Estadistica e WHERE e.year=?1",Estadistica.class);
			query.setParameter(1, year);
			List<Estadistica> estadistica=query.getResultList();
			if(!estadistica.isEmpty()) {
			 esta= estadistica.get(0);
			}else {
			 esta = new Estadistica(year);
			}
			db.getTransaction().begin();
			Apuesta a= u.addApuesta(respuesta, ev, apuesta);
			e.addApuesta(user, respuesta, apuesta);
			sel.setCuentaDeApuestas(sel.getCuentaDeApuestas()+1);
			if(modoPago.equals("tarjeta")) {
				float dineroempresa=esta.getGanancias()+apuesta;
				esta.setGanancias(dineroempresa);
				
				float dinerouser= u.getMiMonedero()-apuesta;
				u.setMiMonedero(dinerouser);
			}
			db.persist(esta);
			db.persist(e);
			db.persist(u);
			db.getTransaction().commit();
			return a;
		}
		
		public void bloquearUsuario(String username, int year) {
			System.out.println(">> DataAccess: bloquearUsuario=> Usuario= "+ username);
			Usuario u=db.find(Usuario.class, username);
			
			TypedQuery<Estadistica> query = db.createQuery("SELECT e FROM Estadistica e WHERE e.year=?1",Estadistica.class);
			query.setParameter(1, year);
			List<Estadistica> estadistica=query.getResultList();
			Estadistica esta= estadistica.get(0);
			
			db.getTransaction().begin();
			u.setBloqueado(true);
			esta.eliminarDeListaNegra(u);
			db.persist(u);
			db.persist(esta);
			db.getTransaction().commit();
		}
		
		public boolean removeApuesta(Apuesta a) {
			System.out.println("Quitamos la apuesta uno");
			Apuesta ap = db.find(Apuesta.class, a.getId());
			if(ap!=null) {
			db.getTransaction().begin();
			db.remove(ap);
			db.getTransaction().commit();
			}else {
				return false;
			}
			return true;
		}
		
		public boolean removeUsuario(Usuario a) {
			System.out.println("Quitamos la apuesta uno");
			Usuario ap = db.find(Usuario.class, a.getNombreUsuario());
			if(ap!=null) {
			db.getTransaction().begin();
			db.remove(ap);
			db.getTransaction().commit();
			}else {
				return false;
			}
			return true;
		}
		
		public boolean removeTodo(Event ev, Seleccion s, Question q, Pronostico p, Apuesta a, Usuario u) {
			System.out.println(">> DataAccessTest: removeEvent");
			Event e = db.find(Event.class, ev.getEventNumber());
			Question qe = db.find(Question.class, q.getQuestionNumber());
			Pronostico po=db.find(Pronostico.class, p.getNumPronostico());
			Apuesta ap= db.find(Apuesta.class, a.getId());
			Usuario us = db.find(Usuario.class, u.getNombreUsuario());
			Seleccion se = db.find(Seleccion.class, s.getId());
			if (e!=null) {
				db.getTransaction().begin();
				db.remove(e);
				db.remove(qe);
				db.remove(ap);
				db.remove(us);
				db.remove(po);
				db.remove(se);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
		}
		
		public Vector<Usuario> getUsuariosGanadores(Pronostico pronos, int year) {	
			db.getTransaction().begin();	
			Vector<Usuario> ganadores = new Vector<Usuario>();	
			TypedQuery<Apuesta> query = db.createQuery("SELECT a FROM Apuesta a WHERE a.respuesta=?1",Apuesta.class);   
			query.setParameter(1, pronos);
			List<Apuesta> apuestas = query.getResultList();	
			TypedQuery<Estadistica> queryest = db.createQuery("SELECT e FROM Estadistica e WHERE e.year=?1",Estadistica.class);
			queryest.setParameter(1, year);
			List<Estadistica> estadistica=queryest.getResultList();
			Estadistica est= estadistica.get(0);		
			if(!apuestas.isEmpty()) {
				extractedSetGanancias(pronos, ganadores, apuestas, est);
				db.persist(est);	 
			}
			db.getTransaction().commit();
		 	return ganadores;
		}

		private void extractedSetGanancias(Pronostico pronos, Vector<Usuario> ganadores, List<Apuesta> apuestas, Estadistica est) {
			float totalpagado = extractedGetUsuariosGanadores(pronos, ganadores, apuestas, est);
			est.setGanancias(est.getGanancias()-totalpagado);
		}

		private float extractedGetUsuariosGanadores(Pronostico pronos, Vector<Usuario> ganadores, List<Apuesta> apuestas, Estadistica est) {
			float totalpagado=0;	
			for (Apuesta a:apuestas){
				Usuario ganador=a.getUser();
				Usuario u=db.find(Usuario.class, ganador.getNombreUsuario());
			 
				totalpagado = extractedGananciasUsuario(pronos, totalpagado, a, u);
			 
				if(u.getCuentaDeApuestas()>=100 && u.getPorcentajeGanancias()>=95) {
					est.anadirUsuarioAListaNegra(u);
				} 
				db.persist(u);
				ganadores.add(ganador);
			}
			return totalpagado;
		}

		private float extractedGananciasUsuario(Pronostico pronos, float totalpagado, Apuesta a, Usuario u) {
			float gana= a.getApuesta()+a.getApuesta()*pronos.getPorcentageGanancia();
			totalpagado=totalpagado+gana;
			float tenia=u.getMiMonedero();
			u.setMiMonedero(tenia+gana);	 
			u.setApuestasganadas(u.getApuestasganadas()+1);
			u.setPorcentajeGanancias((float)((u.getApuestasganadas()*100)/u.getCuentaDeApuestas()));
			return totalpagado;
		}
}

