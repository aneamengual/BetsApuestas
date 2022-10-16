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
	import test.dataAccess.TestDataAccess;
	import domain.Apuesta;
	import domain.Event;
	import domain.Pronostico;
	import domain.Question;
	import domain.Seleccion;
	import domain.Usuario;
	import exceptions.EventAlreadyExists;
	import exceptions.PronosticoAlreadyExists;
	import exceptions.QuestionAlreadyExist;

	public class usuarioMayorApuestaDAW {

		 //sut:system under test
		 static DataAccess sut=new DataAccess();
		 
		 //additional operations needed to execute the test 
		 static TestDataAccess testDA=new TestDataAccess();

		 Event ev;
		 Question q;
		 Pronostico p;
		 Seleccion s;
		 Apuesta a;
		 Apuesta b;
		 
		 @Test
		 //En este todo debera ir bien, ya que habra alguna apuesta y el user no esta bloqueado
		 public void test1() {
			 testDA.open();
			 	Usuario u = testDA.crearUser("alex");
			 	Usuario ub= testDA.crearUser("maider");
			 	
			 s = testDA.addSeleccion("footboll", "masculino", "liga");
			 try {
				 	
					ev = testDA.addEvent("Madrid-Eibar", new Date(), s);
					try {
						 q = testDA.createQuestion(ev, "quien ganar?", 2);
					} catch (QuestionAlreadyExist e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p = testDA.addPronostico(q, "Eibar", (float) 0.2);
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
	         testDA.close();
	        System.out.println("Finally "+b);          
	       }
				 
			 }
		 
		 
		 @Test
		 public void test2() {
			 try {
				 assertNull(sut.usuarioMayorApuesta());
			 }catch(Exception e){
				 fail("no va");
			 }
		 }
		 
		 @Test
		 public void test3() {
			 testDA.open();
			 	Usuario u = testDA.crearUser("alex");
			 	Usuario ub= testDA.crearUser("maider");
			 	testDA.bloquearUsuario("maider", 2022);
			 s = testDA.addSeleccion("footboll", "masculino", "liga");
			 try {
				 	
					ev = testDA.addEvent("Madrid-Eibar", new Date(), s);
					try {
						 q = testDA.createQuestion(ev, "quien ganar?", 2);
					} catch (QuestionAlreadyExist e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p = testDA.addPronostico(q, "Eibar", (float) 0.2);
					a = testDA.anadirApuesta(u, p, 5, "efectivo", ev, 2020);
					b = testDA.anadirApuesta(ub, p, 20, "efectivo", ev, 2020);
					
					testDA.close();
					
					Usuario expected = sut.usuarioMayorApuesta();
					assertEquals(expected.getNombreUsuario(),u.getNombreUsuario());
				 
				 
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
		 
		 
		 
		 @Test
		 public void test4() {
			 testDA.open();
			 	Usuario u = testDA.crearUser("alex");
			 	Usuario ub= testDA.crearUser("maider");
			 	testDA.bloquearUsuario("maider", 2022);
			 	testDA.bloquearUsuario("alex", 2022);
			 s = testDA.addSeleccion("footboll", "masculino", "liga");
			 try {
				 	
					ev = testDA.addEvent("Madrid-Eibar", new Date(), s);
					try {
						 q = testDA.createQuestion(ev, "quien ganar?", 2);
					} catch (QuestionAlreadyExist e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p = testDA.addPronostico(q, "Eibar", (float) 0.2);
					a = testDA.anadirApuesta(u, p, 5, "efectivo", ev, 2020);
					b = testDA.anadirApuesta(ub, p, 20, "efectivo", ev, 2020);
					
					testDA.close();
					
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



