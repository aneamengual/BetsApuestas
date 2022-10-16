package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Mensaje implements Serializable {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer mensajeNumber;

	public Usuario dueño;
	public Usuario receptor; //Sera opcional ya que el mensaje puede ser tb para todos los usuarios 
	//Solo habra receptor cuando un usuario responda a otro concretamente
	public String texto;

	public int megustas;
	public boolean dado;
	public Date date;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	public Vector<Mensaje> respuestas = new Vector<Mensaje>();



	public Vector<Mensaje> getRespuestas() {
		return respuestas;
	}
	public void setRespuestas(Vector<Mensaje> respuestas) {
		this.respuestas = respuestas;
	}
	public boolean isDado() {
		return dado;
	}
	public void setDado(boolean dado) {
		this.dado = dado;
	}
	public int getMegustas() {
		return megustas;
	}
	public void setMegustas(int megustas) {
		this.megustas = megustas;
	}



	///OTRO ATRIBUTO PODRIA SER CORREALACIONARLO CON LAS RESPUENTAS AL MENSAJE PRINCIPAL

	public Integer getMensajeNumber() {
		return mensajeNumber;
	}
	public void setMensajeNumber(Integer mensajeNumber) {
		this.mensajeNumber = mensajeNumber;
	}
	public Mensaje(Usuario due, String texto, Date date) {
		this.dueño=due;
		this.texto=texto;
		this.date=date;
		this.megustas=0;
		this.dado=false;
	}
	public Usuario getDueño() {
		return dueño;
	}
	public void setDueño(Usuario dueño) {
		this.dueño = dueño;
	}
	public Usuario getReceptor() {
		return receptor;
	}
	public void setReceptor(Usuario receptor) {
		this.receptor = receptor;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return dueño.getNombreUsuario() + "                   " + date + "\n" + texto;
	}

	public String fechaBonita(Date fecha) {
		// En esta linea de codigo estamos indicando el nuevo formato que queremos para nuestra fecha.
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		// Aqui usamos la instancia formatter para darle el formato a la fecha. Es importante ver que el resultado es un string.
		String fechaTexto = formatter.format(fecha);
		return fechaTexto;
	}

	public Vector<Mensaje> addMensaje(Mensaje men)  {

		respuestas.add(men);
		return respuestas;
	}
}