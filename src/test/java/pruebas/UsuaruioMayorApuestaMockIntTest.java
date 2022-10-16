package pruebas;

import static org.junit.Assert.*;

import java.util.Date;

import org.mockito.*;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Apuesta;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Seleccion;
import domain.Usuario;
import exceptions.EventAlreadyExists;
import exceptions.PronosticoAlreadyExists;
import exceptions.QuestionAlreadyExist;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UsuaruioMayorApuestaMockIntTest {
	
	DataAccess dataAccess=Mockito.mock(DataAccess.class);
    Event mockedEvent=Mockito.mock(Event.class);
    BLFacade sut=new BLFacadeImplementation(dataAccess);
	
    Event ev;
	 Question q;
	 Pronostico p;
	 Seleccion s;
	 Apuesta a;
	 Apuesta b;
	 
	 @Test
		public void test1() {
		 try {
			 assertNull(sut.usuarioMayorApuesta());
		 }catch(Exception e){
			 fail("no va");
		 }
	 
	 }
	 

	@Test
	public void test2() {
		
		 	Usuario u = sut.crearUser("alex");
		 	Usuario ub= sut.crearUser("maider");
		 	
		 s = sut.anadirSeleccion("footboll", "masculino", "liga");
		 try {
			 	
				try {
					ev = sut.anadirEvent("Madrid-Eibar", new Date(), s);
				} catch (EventAlreadyExists e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 q = sut.createQuestion(ev, "quien ganar?", 2);
				} catch (QuestionAlreadyExist e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 p = sut.anadirPronostico(q, "Eibar", (float) 0.2);
				} catch (PronosticoAlreadyExists e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a = sut.anadirApuesta(u, p, 5, "efectivo", ev, 2020);
				b = sut.anadirApuesta(ub, p, 20, "efectivo", ev, 2020);
				
				
				
				Usuario expected = sut.usuarioMayorApuesta();
				assertEquals(expected.getNombreUsuario(),ub.getNombreUsuario());
			 
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 System.out.println("No deberia fallar");
		 }
	}
	
	
	@Test
	public void test3() {
		
		 	Usuario u = sut.crearUser("alex");
		 	Usuario ub= sut.crearUser("maider");
		 	sut.bloquearUsuario("maider", 2022);
		 	
		 s = sut.anadirSeleccion("footboll", "masculino", "liga");
		 try {
			 	
				try {
					ev = sut.anadirEvent("Madrid-Eibar", new Date(), s);
				} catch (EventAlreadyExists e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 q = sut.createQuestion(ev, "quien ganar?", 2);
				} catch (QuestionAlreadyExist e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 p = sut.anadirPronostico(q, "Eibar", (float) 0.2);
				} catch (PronosticoAlreadyExists e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a = sut.anadirApuesta(u, p, 5, "efectivo", ev, 2020);
				b = sut.anadirApuesta(ub, p, 20, "efectivo", ev, 2020);
				
				
				
				Usuario expected = sut.usuarioMayorApuesta();
				assertEquals(expected.getNombreUsuario(),u.getNombreUsuario());
			 
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 System.out.println("No deberia fallar");
		 }
	}
	
	@Test
	public void test4() {
		
		 	Usuario u = sut.crearUser("alex");
		 	sut.bloquearUsuario("alex", 2022);
		 	Usuario ub= sut.crearUser("maider");
		 	sut.bloquearUsuario("maider", 2022);
		 	
		 s = sut.anadirSeleccion("footboll", "masculino", "liga");
		 try {
			 	
				try {
					ev = sut.anadirEvent("Madrid-Eibar", new Date(), s);
				} catch (EventAlreadyExists e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 q = sut.createQuestion(ev, "quien ganar?", 2);
				} catch (QuestionAlreadyExist e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 p = sut.anadirPronostico(q, "Eibar", (float) 0.2);
				} catch (PronosticoAlreadyExists e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a = sut.anadirApuesta(u, p, 5, "efectivo", ev, 2020);
				b = sut.anadirApuesta(ub, p, 20, "efectivo", ev, 2020);
				
				
				
				 assertNull(sut.usuarioMayorApuesta());
				
			 
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 System.out.println("No deberia fallar");
		 }
	}
	

}
