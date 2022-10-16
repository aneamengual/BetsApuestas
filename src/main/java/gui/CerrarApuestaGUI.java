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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;
import exceptions.ResultAlreadyExists;

public class CerrarApuestaGUI extends JFrame {
private static final long serialVersionUID = 1L;
	
	private JLabel JLabelError = new JLabel();
	private JLabel JLabelMsg = new JLabel();

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private final JLabel jLabelPronosticos = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Pronosticos")); 

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JButton jButtonBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
	
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
	private final JButton jButtonCloseBet = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CloseBet"));
	

	public CerrarApuestaGUI()
	{
		try
		{
			CerrarApuesta();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void CerrarApuesta() throws Exception{
		this.setSize(new Dimension(700, 595));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		getContentPane().setLayout(null);
		jLabelEventDate.setBounds(40, 34, 140, 16);

		this.getContentPane().add(jLabelEventDate);
		jLabelQueries.setBounds(40, 210, 636, 14);
		this.getContentPane().add(jLabelQueries);
		jLabelEvents.setBounds(288, 34, 259, 16);
		this.getContentPane().add(jLabelEvents);
		jButtonClose.setBounds(484, 505, 166, 46);
		jLabelPronosticos.setBounds(40, 348, 636, 16);
		this.getContentPane().add(jLabelPronosticos);
		
		JLabelError.setBounds(40, 489, 301, 13);
		JLabelError.setForeground(Color.red);		
		getContentPane().add(JLabelError);
		
		JLabelMsg.setBounds(345, 489, 331, 13);
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
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
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
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
					}



					CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
													
					

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEvents(firstDay);

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
		
		scrollPaneEvents.setBounds(288, 50, 388, 150);

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
		jButtonCloseBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBet_actionPerformed(e);
			}
		});
		jButtonCloseBet.setBounds(262, 505, 166, 46);
		
		getContentPane().add(jButtonCloseBet);

	}
	
	private void jButtonBet_actionPerformed(ActionEvent e) {
		int z=tableEvents.getSelectedRow();
		domain.Event evento=(domain.Event)tableModelEvents.getValueAt(z,2);
		
		int j=tableQueries.getSelectedRow();
		domain.Question quest=(domain.Question)tableModelQueries.getValueAt(j,2);
		
		int i=tablePronosticos.getSelectedRow();
		domain.Pronostico pronostico=(domain.Pronostico)tableModelPronosticos.getValueAt(i,2);
		
		try {
			JLabelError.setText("");
			JLabelMsg.setText("");
			
			int year= jCalendar1.getDate().getYear()+1900;
			
			BLFacade facade= MainGUI.getBusinessLogic();
			facade.anadirResultado(quest, pronostico);
			facade.getUsuariosGanadores(pronostico, year);
			facade.cerrarEvento(evento);
			JLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Done"));
			
		}catch(ResultAlreadyExists e1) {
			JLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ResultAlreadyExists"));
			e1.printStackTrace();
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
