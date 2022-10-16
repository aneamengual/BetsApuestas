package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Usuario;

import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BloquearGUI extends JFrame {

	private BLFacade facade= MainGUI.getBusinessLogic();
	
	private JPanel contentPane;
	
	private JLabel JLabelMsg = new JLabel();
	
	private final JButton jButtonBloquear = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bloquear"));
	private final JButton jButtonEliminar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Eliminar")); //$NON-NLS-1$ //$NON-NLS-2$
	
	private final DefaultListModel<String> modeloNegro = new DefaultListModel<String>();
	private final JScrollPane scrollListaNegra = new JScrollPane();
	
	private JList list= new JList();
	private DefaultListModel enMira = new DefaultListModel();
	private JScrollPane scrollPaneUsers = new JScrollPane(list);
	
	private final JComboBox jComboBoxListaN = new JComboBox();
	private DefaultComboBoxModel<String> listaN = new DefaultComboBoxModel<String>();
	
	private final JButton jButtonBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
	

	public BloquearGUI()
	{
		try{
			Bloquear();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void Bloquear() {
		
		Date dt=new Date();
        int year=dt.getYear()+1900;
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		jButtonBloquear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		JLabelMsg.setBounds(20, 41, 358, 13);
		JLabelMsg.setForeground(Color.red);	
		getContentPane().add(JLabelMsg);
			
		
		jButtonBloquear.setBounds(86, 66, 105, 25);
		contentPane.add(jButtonBloquear);
		
		jComboBoxListaN.setModel(listaN);
		/**ArrayList<Usuario> ln= facade.getListaNegra(year);
		for(int i=0; i<ln.size();i++) {
			listaN.addElement(ln.get(i).getNombreUsuario()+", "+ln.get(i).getNombre()+", "+ln.get(i).getDNI());
		}
		jComboBoxListaN.setBounds(86, 113, 232, 21);
		contentPane.add(jComboBoxListaN);**/
		
		
		scrollPaneUsers.setBounds(86, 113, 232, 158);
		contentPane.add(scrollPaneUsers);
		list.setModel(enMira);
		ArrayList<Usuario> ln= facade.getListaNegra(year);
		for(int i=0; i<ln.size();i++) {
			enMira.addElement(ln.get(i).getNombreUsuario()+", "+ln.get(i).getNombre()+", "+ln.get(i).getDNI());
		}
		

		
		jButtonBloquear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabelMsg.setText("");

				String user=null;
				user=(String) list.getSelectedValue();;
				String[] parts = user.split(",");
				String usernombre = parts[0];
				facade.bloquearUsuario(usernombre,year);
				JLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Blocked"));
				
				enMira.removeAllElements();
				ArrayList<Usuario> ln= facade.getListaNegra(year);
				for(int i=0; i<ln.size();i++) {
					enMira.addElement(ln.get(i).getNombreUsuario()+", "+ln.get(i).getNombre()+", "+ln.get(i).getDNI());
				}
			}
		});
		
jButtonBack.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		jButtonBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButtonBack_actionPerformed(e);
			}
		});
		jButtonBack.setBounds(10, 10, 85, 21);
		getContentPane().add(jButtonBack);
		jButtonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String user=null;
				user=(String) list.getSelectedValue();
				String[] parts = user.split(",");
				String usernombre = parts[0];
				facade.eliminarDeListaNegra(usernombre, year);
				JLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Removed"));
				
				enMira.removeAllElements();
				ArrayList<Usuario> ln= facade.getListaNegra(year);
				for(int i=0; i<ln.size();i++) {
					enMira.addElement(ln.get(i).getNombreUsuario()+", "+ln.get(i).getNombre()+", "+ln.get(i).getDNI());
				}
			}
		});
		
		
		jButtonEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jButtonEliminar.setBounds(213, 66, 105, 25);
		contentPane.add(jButtonEliminar);
	}
	
	
	private void jButtonBack_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
