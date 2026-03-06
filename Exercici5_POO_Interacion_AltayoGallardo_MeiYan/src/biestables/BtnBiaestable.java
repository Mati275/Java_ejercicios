package biestables;

import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class BtnBiaestable extends JFrame implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private JButton btnOne, btnTwo;
	
	public BtnBiaestable (String nombre) {
		super(nombre);
		initComponents();
	}

	private void initComponents() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(1,2)); //1 FILA 2 COL
		
		btnOne = new JButton("Activo");
		btnTwo = new JButton ("Inactivo");
		
		this.getContentPane().add(btnOne);
		this.getContentPane().add(btnTwo);
		
	}
	
	
	public static void main (String args[]) {

        EventQueue.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {
                        JFrame frame = new BtnBiaestable("BOTON BIESTABLE");
                        frame.setSize(800,400);
                        frame.setVisible(true);

                    }
                }
        );

    }

	
	
}
