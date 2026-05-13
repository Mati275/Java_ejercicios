package btnDiagonal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BotonesDiagonales extends JFrame implements ActionListener{	

	private JButton un, cinc, nou;
    private JLabel nord, sud, est, oest;
    private JPanel tresPerTres;
    
    /* no us haurien de caldre m�s components que els que ja hi ha
     * declarats. Per� si en necessiteu algun m�s, declareu-lo privat i en
     * aquesta part del codi */
    
    public BotonesDiagonales (String tl) {
    	super(tl);
    	
    	initComponents();
    }
    
    private void initComponents() {    	
    	Dimension windowDimension, dimBtnContainer;
		
        // creaci� dels botons
        un = new JButton("1");
        cinc = new JButton("5");
        nou = new JButton("9");
        
        /* COMPLETAR: creaci� de les etiquetes */
        
        tresPerTres = new JPanel();
        
        
        //Ventana
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		windowDimension = new Dimension (800,600);
		setSize(windowDimension);			// Por defecto
		setMinimumSize(windowDimension);	// Minimo
		
        /* COMPLETAR: donar-li al panel tresPerTres el gestor de posicionament
         * m�s escaient ... */
		
		tresPerTres.setLayout(new GridLayout(3,3));
		
		un = new JButton("1");
		cinc = new JButton ("5");
		nou = new JButton ("9");
		
		tresPerTres.add(un);
		tresPerTres.add(new JLabel());
		tresPerTres.add(new JLabel());
		tresPerTres.add(new JLabel());
		tresPerTres.add(cinc);
		tresPerTres.add(new JLabel());
		tresPerTres.add(new JLabel());
		tresPerTres.add(new JLabel());
		tresPerTres.add(nou);
		
        /* COMPLETAR: incorporar al panel tresPerTres els components que
         * hagi de contenir... */
        
		getContentPane().add(tresPerTres, BorderLayout.CENTER);
		
		
        
        /* COMPLETAR: incorporar al contentPane del frame (this) els components
         * que hagi de contenir */
        
        
    }
    
    /* punt d'entrada en execuci�. Podeu deixar-lo tal i com est� */ 
     public static void main (String [] args) {
        EventQueue.invokeLater(
                new Runnable () {
                    public void run () {
                        JFrame jf = new BotonesDiagonales("Botons en Diagonal");
                        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        jf.pack();
                        jf.setVisible(true);
                    }
                }
        
        );
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
    
}



