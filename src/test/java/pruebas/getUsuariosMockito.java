package pruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Apuesta;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Seleccion;
import domain.Usuario;
import exceptions.EventFinished;
import exceptions.PronosticoAlreadyExists;
import exceptions.QuestionAlreadyExist;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class getUsuariosMockito {
	DataAccess dataAccess=Mockito.mock(DataAccess.class);
    Event mockedEvent=Mockito.mock(Event.class);
    Question mockedQuestion=Mockito.mock(Question.class);
    Pronostico mockedPronostico= Mockito.mock(Pronostico.class);
    
    @InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);
    
    @Test
    public void test1() {
    	try {
    		//define paramaters
    		Date date = new Date("05/10/2022");
			float betmin=(float) 5.35;
			float dinero= (float) 30;
			Float porGan=new Float(0.3);
			String preg="Quien ganara?";
			String sol="Real";
			
			//configure Mock
			Mockito.doReturn(preg).when(mockedQuestion).getQuestion();
			Mockito.doReturn(new Pronostico(mockedQuestion, sol, porGan)).when(dataAccess).anadirPronostico(Mockito.any(Question.class), Mockito.any(String.class), Mockito.any(Float.class));
			
			//invoke System Under Test (sut)  
			Pronostico p=sut.anadirPronostico(mockedQuestion, sol, porGan);
			
			//verify the results
			ArgumentCaptor<Question> questionCaptor = ArgumentCaptor.forClass(Question.class);
			ArgumentCaptor<String> solCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Float> gananciasCaptor = ArgumentCaptor.forClass(Float.class);
			
			Mockito.verify(dataAccess,Mockito.times(1)).anadirPronostico(questionCaptor.capture(),solCaptor.capture(), gananciasCaptor.capture());
			Float f=gananciasCaptor.getValue();
			
			assertEquals(questionCaptor.getValue(),mockedQuestion);
			assertEquals(solCaptor.getValue(), sol);
			assertEquals(gananciasCaptor.getValue(), porGan);
			
    	}catch(PronosticoAlreadyExists e) {
    		assertTrue(true);
    	}
    }
    
    
    @Test
    public void test2() {
    	//Pronostico null
    		//define paramaters
    		Date date = new Date("05/10/2022");
			float betmin=(float) 5.35;
			float dinero= (float) 30;
			Float porGan=new Float(0.3);
			String preg="Quien ganara?";
			int year=2022;
			String sol=null;
			
			//configure Mock
			//Mockito.doReturn(sol).when(mockedPronostico).getSolucion();
			Mockito.doReturn(null).when(dataAccess).getUsuariosGanadores(Mockito.any(Pronostico.class), Mockito.any(Integer.class));
			
			//invoke System Under Test (sut)  
			Vector<Usuario> v=sut.getUsuariosGanadores(null, year);
			
			//verify the results
			Mockito.verify(dataAccess,Mockito.times(1)).getUsuariosGanadores(Mockito.any(Pronostico.class), Mockito.any(Integer.class));
			assertTrue(v.isEmpty());
			
    }
    
   @Test   
    public void test3() {
	 //without error  
	   try
		{
    		//define paramaters
    		Date date = new Date("05/10/2022");
			float betmin=(float) 5.35;
			float dinero= (float) 30;
			Float porGan=new Float(0.3);
			String preg="Quien ganara?";
			String des= "Real-Alemania";
			String sol="Real";
			Usuario u = new Usuario("Pepe", "Gil", "Gil", 688888888, "hsbhs@gmail.com", "pepito", date, "665655D", "soypepe", 6565665);
			Vector<Usuario> v= new Vector<>();
			v.add(u);
			Seleccion sel= new Seleccion("Futbol", "Masc", "Nacional");
			Event e= new Event(des,date,sel);
			Question q= new Question(preg, betmin,e);
			Pronostico p= new Pronostico(q,sol,porGan);
			
			//configure Mock
			Mockito.when(dataAccess.getUsuariosGanadores(p, 2022)).thenReturn(v);
			
			//invoke System Under Test (sut)  
			sut.getUsuariosGanadores(p, 2022);
			
			//if the program continues fail
		   // fail();
		    
		}
		catch
		(Exception e) {		
		e.printStackTrace();
		}
		
			
    }
    
}
