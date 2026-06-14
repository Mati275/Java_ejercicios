package pr4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResultReader implements ActionListener {

	private JFrame frmResultsViewer;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JButton btnLoadResultsFile;
	
	private JFileChooser fileChooser = new JFileChooser(".");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultReader window = new ResultReader();
					window.frmResultsViewer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ResultReader() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frmResultsViewer = new JFrame();
		this.frmResultsViewer.setTitle("RESULTS VIEWER");
		this.frmResultsViewer.setBounds(100, 100, 616, 478);
		this.frmResultsViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmResultsViewer.getContentPane().setLayout(null);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBorder(new TitledBorder(null, "RESULTS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.scrollPane.setBounds(10, 11, 580, 371);
		this.frmResultsViewer.getContentPane().add(this.scrollPane);
		
		this.textArea = new JTextArea();
		this.scrollPane.setViewportView(this.textArea);
		
		this.btnLoadResultsFile = new JButton("LOAD RESULTS FILE");
		this.btnLoadResultsFile.addActionListener(this);
		this.btnLoadResultsFile.setBounds(233, 393, 157, 23);
		this.frmResultsViewer.getContentPane().add(this.btnLoadResultsFile);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.btnLoadResultsFile) {
			do_btnLoadResultsFile_actionPerformed(arg0);
		}
	}
	protected  void do_btnLoadResultsFile_actionPerformed(ActionEvent arg0) {
		fileChooser.setDialogTitle("Select File Containing RESULTS");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Results File","res"));
		fileChooser.showOpenDialog(this.frmResultsViewer);
		Path pathFileResults = fileChooser.getSelectedFile().toPath();
		
		this.textArea.setText("");
		
		DataInputStream dis = null;
		
		try {
			dis = new DataInputStream(new BufferedInputStream(Files.newInputStream(pathFileResults)));
			
			int nLines = dis.readInt();
			this.textArea.append("--- Censored file: "+nLines+" lines ---\n\n");
			for (int i=1; i<=nLines; i++)
				this.textArea.append(dis.readUTF()+"\n");
			this.textArea.append("\n");
			
			int nStats = dis.readInt();
			this.textArea.append("--- Statistics: "+nStats+" items ---\n\n");
			for (int i=1; i<=nStats; i++) {
				this.textArea.append(dis.readUTF()+": ");
				this.textArea.append(dis.readInt()+"\n");
			}
			
			dis.close();
		}
		catch (IOException ioex) {}
		catch (NullPointerException npe){
			//Exception thrown if user does not select a file, we do nothing
		}
	}
}
