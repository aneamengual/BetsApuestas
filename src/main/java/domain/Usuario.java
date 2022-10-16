package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Usuario implements Serializable{
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int telefono;
	private String correo;
	@Id
	private String nombreUsuario;
	private Date fechaNacimiento;
	private String DNI;
	private String password;
	private int numTarjeta;
	private float miMonedero;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Apuesta> apuestas=new Vector<Apuesta>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Cupon cupon;
	private int cuentaDeApuestas;
	private int apuestasganadas;
	private float porcentajeGanancias;
	private boolean bloqueado;
	
	public Usuario(String nombre, String apellido1, String apellido2, int telefono, String correo, String nombreUsuario, Date fechaNacimiento, String DNI, String password, int numTarjeta) {
		super();
		this.nombre= nombre;
		this.apellido1= apellido1;
		this.apellido2= apellido2;
		this.telefono= telefono;
		this.correo= correo;
		this.nombreUsuario= nombreUsuario;
		this.fechaNacimiento= fechaNacimiento;
		this.DNI= DNI;
		this.password= password;
		this.numTarjeta=numTarjeta;
		this.miMonedero=(float)ThreadLocalRandom.current().nextDouble(10, 2000000);
		this.cuentaDeApuestas=0;
		this.porcentajeGanancias=0;
		this.apuestasganadas=0;
		this.bloqueado=false;
	}
	
	public Usuario(String nombre, String password) {
		this.nombre= nombre;
		this.password= password;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(int numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public float getMiMonedero() {
		return miMonedero;
	}

	public void setMiMonedero(float miMonedero) {
		this.miMonedero = miMonedero;
	}

	public Vector<Apuesta> getApuestas() {
		return apuestas;
	}

	public void setApuestas(Vector<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}
	
	
	public Cupon getCupon() {
		return cupon;
	}

	public void setCupon(Cupon cupon) {
		this.cupon = cupon;
	}
	

	public int getCuentaDeApuestas() {
		return cuentaDeApuestas;
	}

	public void setCuentaDeApuestas(int cuentaDeApuestas) {
		this.cuentaDeApuestas = cuentaDeApuestas;
	}

	public float getPorcentajeGanancias() {
		return porcentajeGanancias;
	}

	public void setPorcentajeGanancias(float ganancias) {
		this.porcentajeGanancias = ganancias;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public int getApuestasganadas() {
		return apuestasganadas;
	}

	public void setApuestasganadas(int apuestasganadas) {
		this.apuestasganadas = apuestasganadas;
	}

	public Apuesta addApuesta(Pronostico respuesta, Event ev, float apuesta)  {
        Apuesta a=new Apuesta(this, respuesta, ev, apuesta);
        apuestas.add(a);
        cuentaDeApuestas++;
        if(cuentaDeApuestas%10==0) {
        	float dinero= (float) (this.getCupon().getDineroAcumulado()+5.0);
        	this.getCupon().setDineroAcumulado(dinero);
        	int cuponesobtenidos= this.getCupon().getNumCuponconseguido()+1;
        	this.getCupon().setNumCuponconseguido(cuponesobtenidos);
        }       	
        return a;
	}
	
	public Cupon addCupon()  {
        Cupon c=new Cupon(this);
        this.setCupon(c);
        return c;
	}

}
