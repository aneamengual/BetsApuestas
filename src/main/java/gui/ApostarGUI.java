package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;

//import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Pronostico;
import domain.Question;
import domain.Seleccion;
import domain.Usuario;
import exceptions.ApuestaAlreadyExists;

import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class ApostarGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private Usuario user;
	private Seleccion seleccion;
	
	private JLabel JLabelError = new JLabel();
	private JLabel JLabelMsg = new JLabel();

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private final JLabel jLabelPronosticos = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Pronosticos")); 

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JButton jButtonBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
	
	private final JLabel jLabelHowToPay = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("HowToPay"));
	private final JRadioButton jRadioButtonCoupon = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Coupons")); 
	private final JRadioButton jRadioButtonCreditCard = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("CreditCard"));
	
	private final ButtonGroup buttonGroup = new ButtonGroup();

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JScrollPane scrollPanePronosticos = new JScrollPane();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();
	private JTable tablePronosticos = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelPronosticos;
	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private String[] columnNamesPronosticos = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Pronostico"), 
			ResourceBundle.getBundle("Etiquetas").getString("%Ganancia")

	};
	private final JButton jButtonBet = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bet")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelBet = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MoneyToBet")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JTextField textField = new JTextField();
	

	public ApostarGUI(Usuario user, Seleccion seleccion)
	{

		textField.setBounds(484, 319, 64, 19);
		textField.setColumns(10);
		try
		{
			Apostar(user, seleccion);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void Apostar(Usuario user, Seleccion seleccion) throws Exception{
		this.user=user;
		this.seleccion=seleccion;
		
		this.setSize(new Dimension(794, 595));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		getContentPane().setLayout(null);
		jLabelEventDate.setBounds(40, 34, 140, 16);

		this.getContentPane().add(jLabelEventDate);
		jLabelQueries.setBounds(40, 210, 636, 14);
		this.getContentPane().add(jLabelQueries);
		jLabelEvents.setBounds(315, 34, 259, 16);
		this.getContentPane().add(jLabelEvents);
		jButtonClose.setBounds(484, 505, 166, 46);
		jLabelPronosticos.setBounds(40, 348, 636, 16);
		this.getContentPane().add(jLabelPronosticos);
		
		JLabelError.setBounds(40, 489, 341, 13);
		JLabelError.setForeground(Color.red);		
		getContentPane().add(JLabelError);
		
		JLabelMsg.setBounds(391, 489, 358, 13);
		JLabelMsg.setForeground(Color.red);	
		getContentPane().add(JLabelMsg);
		

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});
		jButtonBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButtonBack_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose);

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonthEspecified(jCalendar1.getDate(), seleccion);
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
		jCalendar1.setBounds(40, 50, 225, 150);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					 
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonthEspecified(jCalendar1.getDate(), seleccion);
					}



					CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
													
					

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEventsEspecified(firstDay, seleccion);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		this.getContentPane().add(jCalendar1);
		
		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(3);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q);
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2)); 
			}
		});

		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableQueries.getSelectedRow();
				domain.Question quest=(domain.Question)tableModelQueries.getValueAt(i,2); // obtain ev object
				Vector<Pronostico> pronosticos=quest.getPronosticos();

				tableModelPronosticos.setDataVector(null, columnNamesPronosticos);
				tableModelPronosticos.setColumnCount(3);

				if (pronosticos.isEmpty())
					jLabelPronosticos.setText(ResourceBundle.getBundle("Etiquetas").getString("NoPronosticos")+": "+quest.getQuestion());
				else 
					jLabelPronosticos.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedPronos")+" "+quest.getQuestion());

				for (domain.Pronostico p:pronosticos){
					Vector<Object> row = new Vector<Object>();

					row.add(p.getSolucion());
					row.add(p.getPorcentageGanancia());
					row.add(p);
					tableModelPronosticos.addRow(row);	
				}
				tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
				tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
				tablePronosticos.getColumnModel().removeColumn(tablePronosticos.getColumnModel().getColumn(2));
			}
		});
		
		
		scrollPaneEvents.setBounds(315, 50, 388, 150);

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		scrollPaneQueries.setBounds(40, 225, 419, 113);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		scrollPanePronosticos.setBounds(40, 366, 419, 113);

		scrollPanePronosticos.setViewportView(tablePronosticos);
		tableModelPronosticos = new DefaultTableModel(null, columnNamesPronosticos);

		tablePronosticos.setModel(tableModelPronosticos);
		tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
		tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents);
		this.getContentPane().add(scrollPaneQueries);
		this.getContentPane().add(scrollPanePronosticos);
		
		jButtonBack.setBounds(40, 505, 166, 46);
		getContentPane().add(jButtonBack);
		jButtonBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBet_actionPerformed(e);
			}
		});
		jButtonBet.setBounds(262, 505, 166, 46);
		
		getContentPane().add(jButtonBet);
		jLabelBet.setBounds(484, 296, 130, 13);
		
		getContentPane().add(jLabelBet);
		
		getContentPane().add(textField);
		
		jLabelHowToPay.setBounds(484, 234, 219, 13);
		getContentPane().add(jLabelHowToPay);
		
		buttonGroup.add(jRadioButtonCoupon);
		jRadioButtonCoupon.setBounds(486, 252, 101, 21);
		getContentPane().add(jRadioButtonCoupon);
		
		buttonGroup.add(jRadioButtonCreditCard);
		jRadioButtonCreditCard.setBounds(589, 252, 160, 21);
		getContentPane().add(jRadioButtonCreditCard);

	}
	
	private void jButtonBet_actionPerformed(ActionEvent e) {
		
		int year=jCalendar1.getDate().getYear()+1900;
		int month=jCalendar1.getDate().getMonth();
		
		int j=tableEvents.getSelectedRow();
		domain.Event ev=(domain.Event)tableModelEvents.getValueAt(j,2);
		
		int i=tablePronosticos.getSelectedRow();
		domain.Pronostico pronostico=(domain.Pronostico)tableModelPronosticos.getValueAt(i,2);
		
		try {
			JLabelError.setText("");
			JLabelMsg.setText("");
			float apuesta= Float.parseFloat(textField.getText());
			if(apuesta< pronostico.getQuestion().getBetMinimum()) JLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorMenorQueBetMin"));
			else {
				if(jRadioButtonCoupon.isSelected()) {
					
					if(user.getCupon().getDineroAcumulado()<apuesta) JLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNotEnoughMoney"));
					else {
						BLFacade facade= MainGUI.getBusinessLogic();	
						facade.crearEstadistica(year);							
						facade.anadirApuesta(user, pronostico, apuesta, "cupon", ev, year);						
						facade.actualizarEstadistica(year);		
						JLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Done"));
					}
					
				}else if(jRadioButtonCreditCard.isSelected()) {
					
					if(user.getMiMonedero()<apuesta) JLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNotEnoughMoney"));
					else {
						BLFacade facade= MainGUI.getBusinessLogic();
						facade.crearEstadistica(year);
						if(apuesta>=100000) facade.anadirAListaNegra(user, year);
						facade.anadirApuesta(user, pronostico, apuesta, "tarjeta", ev, year);
					
						facade.actualizarEstadistica(year);
						JLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Done"));
					}
				}
				
			}
		}catch(NumberFormatException e2) {
			JLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorGeneral"));
			e2.printStackTrace();	
		}
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		System.exit(0);
	}
	private void jButtonBack_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
