package pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Seleccion;
import domain.Usuario;
import exceptions.PronosticoAlreadyExists;
import test.dataAccess.TestDataAccess;

public class getUsuariosGanadoresDAW {

	//sut:system under test
	static DataAccess sut=new DataAccess();
		 
	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();

	private Seleccion sel;
	private Event event;
	private Question q;
	private Usuario u;
	private Pronostico p;
	
	@Test
	//sut.createQuestion:  The event has one apuesta. 
	public void test1() {
		try {
			//define paramaters
			Date date = new Date("05/10/2022");
			float betmin=(float) 5.35;
			float dinero= (float) 30;
			float porGan=(float) 0.3;
			String preg="Quien ganará?";
			String sol="Real";
	
			
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			u = testDA.addUsuario("Pepe", "Gil", "Gil", 688888888, "hsbhs@gmail.com", "pepito", date, "665655D", "soypepe", 6565665);
			sel = testDA.addSeleccion("Futbol", "Masc", "Nacional");
			event= testDA.addEvent("Atletico-Real", date, sel);
			q= testDA.addQuestion(preg, betmin, event);
			p= testDA.addPronostico(q, sol, porGan);
			testDA.close();	
			
			//invoke System Under Test (sut)  
			Pronostico pronos=sut.anadirPronostico(q, sol, porGan);
			u.addApuesta(pronos, event, dinero);
			
			//verify the results
			assertTrue(event.getApuestas().isEmpty());
			
			//p is DB
			testDA.open();
			boolean exist = testDA.existPronos(pronos);
			assertTrue(exist);
			testDA.close();
			
		}catch(PronosticoAlreadyExists e) {
			fail();
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean b=testDA.removeEvent(event);
	          b=testDA.removeUsuario(u);
	          b=testDA.removeSeleccion(sel);
	          testDA.close();         
	        }
	}
	
	@Test
	//sut.createQuestion:  The event has no apuesta. 
	public void test2() {
		try {
			//define paramaters
			Date date = new Date("05/10/2022");
			float betmin=(float) 5.35;
			float dinero= (float) 30;
			float porGan=(float) 0.3;
			String preg="Quien ganará?";
			String sol="Real";
	
			
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			u = testDA.addUsuario("Pepe", "Gil", "Gil", 688888888, "hsbhs@gmail.com", "pepito", date, "665655D", "soypepe", 6565665);
			sel = testDA.addSeleccion("Futbol", "Masc", "Nacional");
			event= testDA.addEvent("Atletico-Real", date, sel);
			q= testDA.addQuestion(preg, betmin, event);
			p= testDA.addPronostico(q, sol, porGan);
			testDA.close();	
			
			//invoke System Under Test (sut)  
			Pronostico pronos=sut.anadirPronostico(q, sol, porGan);
			
			//verify the results
			assertTrue(event.getApuestas().isEmpty());
			
			//p is DB
			testDA.open();
			boolean exist = testDA.existPronos(pronos);
			assertTrue(exist);
			testDA.close();
			
		}catch(PronosticoAlreadyExists e) {
			fail();
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean b=testDA.removeEvent(event);
	          b=testDA.removeUsuario(u);
	          b=testDA.removeSeleccion(sel);
	          testDA.close();         
	        }
	}
	
	

}
