package domain;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Estadistica implements Serializable{
	
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private int id;
	private int year;
	//private int month;
	private float ganancias;
	@XmlIDREF
	private Vector<Event> eventosMasapostado;
	@XmlIDREF
	private Vector<Seleccion> seleccionesMasApostadas;
	@XmlIDREF
	private Usuario usuarioMasApostado;
	@XmlIDREF
	private Usuario usuarioMayorApuesta;
	@XmlIDREF
	private ArrayList<Usuario> listaNegra;
	
	/**public Estadistica(int year, Usuario u1, Usuario u2) {
		this.ganancias= 0;
		this.year=year;
		this.eventosMasapostado= new Vector<Event>();
		this.seleccionesMasApostadas= new Vector<Seleccion>();
		this.usuarioMasApostado=u1;
		this.usuarioMayorApuesta=u2;
		//this.apuestasMasApostadas= new Vector<Apuesta>();
		this.listaNegra= new ArrayList<Usuario>();
	}*/
	
	public Estadistica(int year) {
		this.ganancias= 0;
		this.year=year;
		//this.month=month;
		this.eventosMasapostado= new Vector<Event>();
		this.seleccionesMasApostadas=new Vector<Seleccion>();
		//this.apuestasMasApostadas= new Vector<Apuesta>();
		this.listaNegra= new ArrayList<Usuario>();
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getYear() {
		return year;
	}




	public void setYear(int year) {
		this.year = year;
	}
	
	


	/**public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}*/

	public float getGanancias() {
		return ganancias;
	}

	public void setGanancias(float ganancias) {
		this.ganancias = ganancias;
	}

	public Vector<Event> getEventosMasapostado() {
		return eventosMasapostado;
	}

	public void setEventosMasapostado(Vector<Event> eventosMasapostado) {
		this.eventosMasapostado = eventosMasapostado;
	}

	public Vector<Seleccion> getSeleccionesMasApostadas() {
		return seleccionesMasApostadas;
	}

	public void setSeleccionesMasApostadas(Vector<Seleccion> seleccionesMasApostadas) {
		this.seleccionesMasApostadas = seleccionesMasApostadas;
	}

	public Usuario getUsuarioMasApostado() {
		return usuarioMasApostado;
	}

	public void setUsuarioMasApostado(Usuario usuarioMasApostado) {
		this.usuarioMasApostado = usuarioMasApostado;
	}

	public Usuario getUsuarioMayorApuesta() {
		return usuarioMayorApuesta;
	}

	public void setUsuarioMayorApuesta(Usuario usuarioMayorApuesta) {
		this.usuarioMayorApuesta = usuarioMayorApuesta;
	}

	public ArrayList<Usuario> getListaNegra() {
		return listaNegra;
	}

	public void setListaNegra(ArrayList<Usuario> listaNegra) {
		this.listaNegra = listaNegra;
	}
	
	public void anadirUsuarioAListaNegra(Usuario u) {
		if(!listaNegra.contains(u)) listaNegra.add(u);
	}
	
	public void eliminarDeListaNegra(Usuario u) {
		listaNegra.remove(u);
		
	}
	
	

}
