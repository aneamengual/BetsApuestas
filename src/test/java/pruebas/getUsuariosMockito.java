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
			Mockito.doReturn(null).when(dataAccess).anadirPronostico(Mockito.any(Question.class), Mockito.any(String.class), Mockito.any(Float.class));
			
			//invoke System Under Test (sut)  
			Pronostico p=sut.anadirPronostico(null, sol, porGan);
			
			//verify the results
			Mockito.verify(dataAccess,Mockito.times(1)).anadirPronostico(Mockito.any(Question.class), Mockito.any(String.class), Mockito.any(Float.class));
			assertTrue(p==null);
			
    	}catch(PronosticoAlreadyExists e) {
    		assertTrue(true);
    	}
    }
    
    @Test
    public void test3() {
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
			Mockito.when(dataAccess.anadirPronostico(Mockito.any(Question.class), Mockito.any(String.class), Mockito.any(Float.class))).thenThrow(PronosticoAlreadyExists.class);
			
			//invoke System Under Test (sut)  
			sut.anadirPronostico(mockedQuestion, sol, porGan);
			
			//if the program continues fail
		    fail();
			
    	}catch(PronosticoAlreadyExists e) {
    		assertTrue(true);
    	}
    }
    
}
