package pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Seleccion;
import exceptions.EventFinished;
import exceptions.PronosticoAlreadyExists;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class getUsuariosGanadoresDAB {
	
	//sut:system under test
	static DataAccess sut=new DataAccess();
		 
	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();
	
	private Seleccion sel;
	private Event event;
	private Question q;
	
	
	@Test
	//question null
	public void test2() {
		try {
			//define paramaters
			Date date = new Date("05/10/2022");
			float betmin=(float) 5.35;
			float porGan=(float) 0.3;
			String preg="Quien ganará?";
			String sol="Real";
			
			//invoke System Under Test (sut)  
			Pronostico p=sut.anadirPronostico(null, sol, porGan);
			
			//verify the results
			assertTrue(q==null);
			
		}catch(PronosticoAlreadyExists e) {
			fail();
		}
	}
	
	@Test
	//pronostico is null
	public void test3() {
		try {
			//define paramaters
			Date date = new Date("05/10/2022");
			float betmin=(float) 5.35;
			float porGan=(float) 0.3;
			String preg="Quien ganará?";
			String sol="Real";
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			sel = testDA.addSeleccion("Futbol", "Masc", "Nacional");
			event= testDA.addEvent("Atletico-Real", date, sel);
			q= testDA.addQuestion(null, betmin, event);
			testDA.close();	
			
			//invoke System Under Test (sut)  
			Pronostico p=sut.anadirPronostico(q, sol, porGan);
			
			//verify the results
			assertTrue(p==null);
			
			//p datubasean dago
			testDA.open();
			boolean exist = testDA.existPronos(p);
			assertTrue(!exist);
			testDA.close();
			
		}catch(PronosticoAlreadyExists e) {
			fail();
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean b=testDA.removeEvent(event);
	          testDA.close();
	      //     System.out.println("Finally "+b);          
	        }
	}
	

}
