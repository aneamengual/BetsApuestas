package pruebas;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import test.dataAccess.*;
import domain.Apuesta;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Seleccion;
import domain.Usuario;
import exceptions.EventAlreadyExists;
import exceptions.PronosticoAlreadyExists;
import exceptions.QuestionAlreadyExist;

public class usuarioMayorApuestaDAB {

	

	 //sut:system under test
	 static DataAccess sut=new DataAccess(true);
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	 @Before
	 public void iniciar() {
		  sut=new DataAccess();
		 
		 //additional operations needed to execute the test 
		 testDA.borrar();
		
	 }
	 
	 
	 Event ev;
	 Question q;
	 Pronostico p;
	 Seleccion s;
	 Apuesta a;
	 Apuesta b;
	 Usuario u ;
	 Usuario ub;
	 
	 @Test
	 public void test1() {
		 try {
			// System.out.println("1" + sut.usuarioMayorApuesta().getNombreUsuario());
			 assertNull(sut.usuarioMayorApuesta());
		 }catch(Exception e){
			 fail("no va");
		 }
	 }
	 
	 
	 
	 @Test
	 //En este todo debería ir bien, ya que habra alguna apuesta y el user no esta bloqueado
	 public void test2() {
		 testDA.open();
		 	 u = testDA.crearUser("alex");
		 	 ub= testDA.crearUser("maider");
		 	
		 s = testDA.anadirSeleccion("footboll", "masculino", "liga");
		 try {
			 	System.out.println("2");
				try {
					ev = testDA.anadirEvent("Madrid-Eibar", new Date(), s);
				} catch (EventAlreadyExists e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 q = testDA.createQuestion(ev, "¿quien ganará?", 2);
				} catch (QuestionAlreadyExist e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 p = testDA.anadirPronostico(q, "Eibar", (float) 0.2);
				} catch (PronosticoAlreadyExists e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a = testDA.anadirApuesta(u, p, 5, "efectivo", ev, 2020);
				b = testDA.anadirApuesta(ub, p, 20, "efectivo", ev, 2020);
				
				testDA.close();
				
				Usuario expected = sut.usuarioMayorApuesta();
				assertEquals(expected.getNombreUsuario(),ub.getNombreUsuario());
				
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 System.out.println("No deberia fallar");
		 }finally{
			 testDA.open();
			 boolean dos = testDA.removeUsuario(u);
			 boolean uno = testDA.removeApuesta(b);
			 
         boolean boo=testDA.removeTodo(ev, s, q, p, a, ub);
       //  testDA.close();
        System.out.println("Finally "+b);          
       }
			 
		 }
	 
	 
	 
	 @Test
	 public void test3() {
		 testDA.open();
		 	Usuario u = testDA.crearUser("alex");
		 	Usuario ub= testDA.crearUser("maider");
		 
		 s = testDA.anadirSeleccion("footboll", "masculino", "liga");
		 try {
			 	try {
				testDA.bloquearUsuario("maider", 202);
			 	}catch(Exception e) {
			 		
			 	}
				try {
					ev = testDA.anadirEvent("Madrid-Eibar", new Date(), s);
				} catch (EventAlreadyExists e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 q = testDA.createQuestion(ev, "¿quien ganará?", 2);
				} catch (QuestionAlreadyExist e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 p = testDA.anadirPronostico(q, "Eibar", (float) 0.2);
				} catch (PronosticoAlreadyExists e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a = testDA.anadirApuesta(u, p, 5, "efectivo", ev, 2020);
				b = testDA.anadirApuesta(ub, p, 20, "efectivo", ev, 2020);
				
				testDA.close();
				
				Usuario salido = sut.usuarioMayorApuesta();
				System.out.println(salido.getNombre());
				assertEquals(u.getNombre(),salido.getNombre());
				
				
		 }catch(Exception e) {
			 e.printStackTrace();
			 System.out.println("No deberia fallar");
		 }finally{
			 testDA.open();
			 boolean dos = testDA.removeUsuario(u);
			 boolean n= testDA.removeUsuario(ub);
			 boolean uno = testDA.removeApuesta(b);
			 
      //   boolean boo=testDA.removeTodo(ev, s, q, p, a, ub);
        // testDA.close();
        System.out.println("Finally "+b);          
       }
			 
		 }
	 
	 
	 
	 @Test
	 public void test4() {
		
		 testDA.open();
		 
		 	Usuario u = testDA.crearUserBloqueado("alex");
		 	Usuario ub= testDA.crearUserBloqueado("maider");
		 	
		 s = testDA.anadirSeleccion("footboll", "masculino", "liga");
		 try {
			 	
				try {
					sut.bloquearUsuario("maider", 202);
					
				 	}catch(Exception e) {
				 	}
				
				try {

			 		sut.bloquearUsuario("alex", 202);
			 		System.out.println(u.isBloqueado());
			 		System.out.println(ub.isBloqueado());
					
				 	}catch(Exception e) {
				 	}
				 		
				 		
				 	
				try {
					ev = testDA.anadirEvent("Madrid-Eibar", new Date(), s);
				} catch (EventAlreadyExists e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 q = testDA.createQuestion(ev, "¿quien ganará?", 2);
				} catch (QuestionAlreadyExist e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 p = testDA.anadirPronostico(q, "Eibar", (float) 0.2);
				} catch (PronosticoAlreadyExists e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a = testDA.anadirApuesta(u, p, 5, "efectivo", ev, 2020);
				b = testDA.anadirApuesta(ub, p, 20, "efectivo", ev, 2020);
				
				//testDA.close();
				
				System.out.println(u.isBloqueado());
				System.out.println(ub.isBloqueado());
				assertNull(sut.usuarioMayorApuesta());
				
			 
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 System.out.println("No deberia fallar");
		 }finally{
			 testDA.open();
			 boolean dos = testDA.removeUsuario(u);
			 boolean uno = testDA.removeApuesta(b);
			 
         boolean boo=testDA.removeTodo(ev, s, q, p, a, ub);
         testDA.close();
        System.out.println("Finally "+b);          
       }
			 
		 }
	
	 }
	 


