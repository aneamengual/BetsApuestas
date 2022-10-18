package pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Usuario;
import exceptions.WrongUserOrPassword;

//@RunWith(MockitoJUnitRunner.class)

public class ComprobarMockIntTest {

	DataAccess dataAccess= Mockito.mock(DataAccess.class);
	
	Usuario mockedUser= Mockito.mock(Usuario.class);
	
	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(dataAccess);
	
	
	//Probamos metiendo correctamente todos los valores
	//Se debera ejecutar correctamente sin fallos
	@Test
	public void test1() {
		//Definimos los valores
		String nombreUsser="juanita";
		String pass="nosenada";
	
		//Configure Mockito
		Mockito.doReturn(pass).when(mockedUser).getPassword();
		Mockito.doReturn(nombreUsser).when(mockedUser).getNombreUsuario();
		try {
			
			//Mockito.doReturn(mockedUser).when(dataAccess).comprobar(nombreUsser, pass);
			Mockito.when(dataAccess.comprobar(nombreUsser, pass)).thenReturn(mockedUser);
			
			Usuario real= sut.comprobar(nombreUsser, pass);
			
			assertEquals(real, mockedUser);
			assertEquals(real.getNombreUsuario(),mockedUser.getNombreUsuario());
			assertEquals(real.getPassword(),mockedUser.getPassword());
			
		}catch(WrongUserOrPassword e) {
			fail("Si llega aqui mal");
		}
		
	}
	
	@Test
	//Comprobamos valores para asegurarnos de que va bien y no nos cambia los valores al 
	//introducirlos ni hace cosas raras
	public void test2() {
		//Definimos los valores
				String nombreUsser="juanita";
				String pass="nosenada";
				
				//Configure Mockito
				Mockito.doReturn(pass).when(mockedUser).getPassword();
				Mockito.doReturn(nombreUsser).when(mockedUser).getNombreUsuario();
				
				try {
					
					Mockito.doReturn(new Usuario(mockedUser.getPassword(),mockedUser.getNombreUsuario())).when(dataAccess).comprobar(Mockito.anyString(), Mockito.anyString());
					Usuario user = sut.comprobar(nombreUsser, pass);
					
					//verify the results
					ArgumentCaptor<String> nombreUsuario= ArgumentCaptor.forClass(String.class);
					ArgumentCaptor<String> passw= ArgumentCaptor.forClass(String.class);
					ArgumentCaptor<Usuario> persona= ArgumentCaptor.forClass(Usuario.class);
					
					Usuario u= Mockito.verify(dataAccess,Mockito.times(1)).comprobar(nombreUsuario.capture(), passw.capture());
					
					assertEquals(nombreUsuario.getValue(),nombreUsser);
					assertEquals(passw.getValue(),pass);
					
				} catch (WrongUserOrPassword e) {
					fail();
					System.out.println("No debería pasar por aquí");
				}
		
	}
	//Si nombre Usuario null
	@Test
	public void test3() {
		String nombreUsuario=null;
		String pass="nosenada";
		
		try {
			Mockito.doReturn(null).when(dataAccess).comprobar(Mockito.anyString(), Mockito.anyString());
			Usuario u = sut.comprobar(nombreUsuario, pass);
			
			Mockito.verify(dataAccess,Mockito.times(1)).comprobar(Mockito.anyString(), Mockito.anyString());
			
			assertNull(u);
		
		} catch (WrongUserOrPassword e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//Si password null
		@Test
		public void test4() {
			String nombreUsuario="juanita";
			String pass=null;
			
			try {
				Mockito.doReturn(null).when(dataAccess).comprobar(Mockito.anyString(), Mockito.anyString());
				Usuario u = sut.comprobar(nombreUsuario, pass);
				
				Mockito.verify(dataAccess,Mockito.times(1)).comprobar(Mockito.anyString(), Mockito.anyString());
				
				assertNull(u);
			
			} catch (WrongUserOrPassword e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
	

}