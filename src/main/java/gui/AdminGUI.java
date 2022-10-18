package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Event;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class AdminGUI extends JFrame {
	
	private static final int CIEN = 100;
	private static final int DVEINT = 220;
	private static final int VEINTOCHO = 28;
	private static final int TREINTNUEVE = 39;
	private static final int CUARENTCINCO = 45;
	private static final int CINCUENTAYUNO = 51;
	private static final int SESNUEVE = 69;
	private static final int CINCO = 5;
	private static final int CIENTSEIS = 106;
	private static final int CIENTTREINTAYNUEVE = 139;
	private static final int CIENTSESENTAYUNO = 161;
	private static final int DOSCDIECISEIS = 216;
	private static final int DOSCSETENTAYCUATRO = 274;
	private static final int DOSCSETENTAYCINCO = 275;
	private static final int QUINCUARENTOCHO = 548;
	private static final int TRESCDIECISEIS = 316;
	private static final int TRESCCUARENTA = 340;
	
	private JPanel contentPane;
	private final JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private final JButton jButtonLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LogOut"));
	private final JButton jButtonContratar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Contratar"));
	private final JButton jButtonCrearPregunta = new JButton(ResourceBundle.getBundle("Etiquetas")
			.getString("CreateQuestion"));
	private final JButton jButtonCrearEvento = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
	private final JButton jButtonCrearPronos = new JButton(ResourceBundle.getBundle("Etiquetas")
			.getString("CreateForecast"));
	private final JButton jButtonCerrarApuesta = new JButton(ResourceBundle.getBundle("Etiquetas")
			.getString("CloseBet"));
	private final JButton jButtonEstadisticas = new JButton(ResourceBundle.getBundle("Etiquetas")
			.getString("Estadisticas")); 
	private final JButton jButtonBlock = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bloquear")); 

	
	
	public AdminGUI() {
		try {
			Admin();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the frame.
	 */

	
	private void Admin() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(CIEN, CIEN, QUINCUARENTOCHO, TRESCCUARENTA);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(CINCO, CINCO, CINCO, CINCO));
		setContentPane(contentPane);
		jButtonContratar.setBounds(VEINTOCHO, CINCUENTAYUNO, DVEINT, CUARENTCINCO);
		
		jButtonContratar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_actionPerformed(e);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(jButtonContratar);
		jButtonClose.setBounds(TRESCDIECISEIS, DOSCSETENTAYCINCO, CIENTTREINTAYNUEVE, VEINTOCHO);
		
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_3_actionPerformed(e);
			}
		});
		contentPane.add(jButtonClose);
		jButtonCrearPregunta.setBounds(DOSCSETENTAYCUATRO, CINCUENTAYUNO, DVEINT, CUARENTCINCO);
		
		jButtonCrearPregunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_1_actionPerformed(e);
			}
		});
		contentPane.add(jButtonCrearPregunta);
		jButtonCrearEvento.setBounds(VEINTOCHO, CIENTSEIS, DVEINT, CUARENTCINCO);
		
		jButtonCrearEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_2_actionPerformed(e);
			}
		});
		contentPane.add(jButtonCrearEvento);
		jButtonCrearPronos.setBounds(DOSCSETENTAYCUATRO, CIENTSEIS, DVEINT, CUARENTCINCO);
		
		jButtonCrearPronos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_4_actionPerformed(e);
			}
		});
		contentPane.add(jButtonCrearPronos);
		jButtonLogout.setBounds(SESNUEVE, DOSCSETENTAYCINCO, CIENTTREINTAYNUEVE, VEINTOCHO);
		
		jButtonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonLogout_actionPerformed(e);
			}
		});
		contentPane.add(jButtonLogout);
		jButtonCerrarApuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCerrarApuesta_actionPerformed(e);
			}
		});
		
		jButtonCerrarApuesta.setBounds(VEINTOCHO, CIENTSESENTAYUNO, DVEINT, CUARENTCINCO);
		contentPane.add(jButtonCerrarApuesta);
		jButtonEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonEstadisticas_actionPerformed(e);
			}
		});
		
		jButtonEstadisticas.setBounds(DOSCSETENTAYCUATRO, CIENTSESENTAYUNO, DVEINT, CUARENTCINCO);
		contentPane.add(jButtonEstadisticas);
		jButtonBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBlock_actionPerformed(e);
			}
		});
		jButtonBlock.setBounds(CINCUENTAYUNO, DOSCDIECISEIS, DVEINT, TREINTNUEVE);
		
		contentPane.add(jButtonBlock);
	}
	
	private static void jButtonCerrarApuesta_actionPerformed(ActionEvent e) {
		JFrame a = new CerrarApuestaGUI();
		a.setVisible(true);
	}
	
	private static void btnNewButton_actionPerformed(ActionEvent e) {
		JFrame a= new AñadirTrabajadorGUI();
		a.setVisible(true);
	}
	
	private static void btnNewButton_1_actionPerformed(ActionEvent e) {
		JFrame a = new CreateQuestionGUI(new Vector<Event>());
		a.setVisible(true);
	}
	
	private static void btnNewButton_2_actionPerformed(ActionEvent e) {
		JFrame a = new CreateEventGUI();
		a.setVisible(true);
	}
	
	private void btnNewButton_3_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		System.exit(0);
	}
	
	private static void btnNewButton_4_actionPerformed(ActionEvent e) {
		JFrame a= new CreatePronosticoGUI();
		a.setVisible(true);
	}
	
	private static void jButtonEstadisticas_actionPerformed(ActionEvent e) {
		JFrame a= new EstadisticaGUI();
		a.setVisible(true);
	}
	
	private static void jButtonBlock_actionPerformed(ActionEvent e) {
		JFrame a= new BloquearGUI();
		a.setVisible(true);
	}
	
	private void jButtonLogout_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		JFrame a= new MainGUI();
		a.setVisible(true);
	}
}
