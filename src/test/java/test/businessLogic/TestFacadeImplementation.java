package test.businessLogic;

import java.util.Date;
import java.util.Vector;

import configuration.ConfigXML;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Seleccion;
import domain.Usuario;
import test.dataAccess.TestDataAccess;

public class TestFacadeImplementation {
	TestDataAccess dbManagerTest;
 	
    
	   public TestFacadeImplementation()  {
			
			System.out.println("Creating TestFacadeImplementation instance");
			ConfigXML c=ConfigXML.getInstance();
			dbManagerTest=new TestDataAccess(); 
			dbManagerTest.close();
		}
	   
	   public Usuario crearUser(String s) {
			dbManagerTest.open();
			Usuario a =dbManagerTest.crearUser(s);
			dbManagerTest.close();
				
				return a;
		}
		
		 
		public boolean removePronostico(Pronostico p) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removePronostico(p);
			dbManagerTest.close();
			return b;
		}
		
		public boolean removeUsuario(Usuario u) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeUsuario(u);
			dbManagerTest.close();
			return b;
		}
		
		public boolean removeEvent(Event event) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeEvent(event);
			dbManagerTest.close();
			return b;
		}
		public boolean removeSeleccion(Seleccion s) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeSeleccion(s);
			dbManagerTest.close();
			return b;
		}
		
		public Pronostico addPronostico(Question q, String solucion, float porGanancia) {
			dbManagerTest.open();
			Pronostico p=dbManagerTest.addPronostico(q, solucion, porGanancia);
			dbManagerTest.close();
			return p;
		}
		
		public Vector<Usuario> getUsuariosGanadores(Pronostico pronos, int year) {
			dbManagerTest.open();
			Vector<Usuario> v=dbManagerTest.getUsuariosGanadores(pronos, year);
			dbManagerTest.close();
			return v;
		}

}
