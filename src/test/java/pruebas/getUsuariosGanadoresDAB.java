package pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Seleccion;
import domain.Usuario;
import exceptions.EventFinished;
import exceptions.PronosticoAlreadyExists;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class getUsuariosGanadoresDAB {
/**
	// sut:system under test
	static DataAccess sut = new DataAccess();

	// additional operations needed to execute the test
	static TestDataAccess testDA = new TestDataAccess();

	private Seleccion sel;
	private Event event;
	private Question q;
	private Pronostico p;
	private Usuario u;

	@Test
	// pronostico is null
	public void test2() {
		try {
			// define paramaters
			Date date = new Date("05/10/2022");
			float betmin = (float) 5.35;
			float porGan = (float) 0.3;
			String preg = "Quien ganará?";
			String sol = "Real";
			p = null;

			// configure the state of the system (create object in the dabatase)
			testDA.open();
			sel = testDA.addSeleccion("Futbol", "Masc", "Nacional");
			event = testDA.addEvent("Atletico-Real", date, sel);
			q = testDA.addQuestion(preg, betmin, event);
			p = null;
			testDA.close();

			// invoke System Under Test (sut)
			Vector<Usuario> v = sut.getUsuariosGanadores(p, 2022);

			// verify the results
			assertTrue(v.isEmpty());

		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			testDA.removeEvent(event);
			testDA.removeSeleccion(sel);
			testDA.close();
			// System.out.println("Finally "+b);
		}
	}

	@Test
	// year is null
	public void test3() {
		try {
			// define paramaters
			Date date = new Date("05/10/2022");
			float betmin = (float) 5.35;
			float porGan = (float) 0.3;
			String preg = "Quien ganará?";
			String sol = "Real";
			int year = 0;

			// configure the state of the system (create object in the dabatase)
			testDA.open();
			sel = testDA.addSeleccion("Futbol", "Masc", "Nacional");
			event = testDA.addEvent("Atletico-Real", date, sel);
			q = testDA.addQuestion(preg, betmin, event);
			p = testDA.addPronostico(q, sol, porGan);
			testDA.close();

			// invoke System Under Test (sut)
			Vector<Usuario> v = sut.getUsuariosGanadores(p, year);

			// verify the results
			assertTrue(v.isEmpty());
		}catch(IndexOutOfBoundsException e) {
			fail();

		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			testDA.removeEvent(event);
			testDA.removeSeleccion(sel);
			testDA.close();
			// System.out.println("Finally "+b);
		}
	}

	/**@Test
	// apuestas is empty
	public void test4() {
		try {
			// define paramaters
			Date date = new Date("05/10/2023");
			float betmin = (float) 5.35;
			float porGan = (float) 0.3;
			String preg = "Quien ganará?";
			String sol = "Real";
			int year = 2023;

			// configure the state of the system (create object in the dabatase)
			testDA.open();
			u = testDA.crearUser("manuelll");
			sel = testDA.addSeleccion("Futbol", "Masc", "Nacional");
			event = testDA.addEvent("Atletico-Real", date, sel);
			q = testDA.addQuestion(preg, betmin, event);			
			p = testDA.addPronostico(q, sol, porGan);
			u.addApuesta(p, event, year);
			testDA.close();

			// invoke System Under Test (sut)
			Vector<Usuario> v = sut.getUsuariosGanadores(p, year);

			// verify the results
			assertTrue(!v.isEmpty());

		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			testDA.removeEvent(event);
			testDA.removeSeleccion(sel);
			testDA.close();
			// System.out.println("Finally "+b);
		}
	}**/

}
