package pr4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class Censorship implements ActionListener {

	private JFrame frmCensorshipTool;
	private JTextArea bannedWordsArea;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTextArea censoredTextArea;
	private JScrollPane scrollPane_2;
	private JTextArea statisticsArea;
	private JScrollPane scrollPane_3;
	private JTextArea originalTextArea;
	private JButton loadBannedButton;
	private JButton saveResultsButton;
	private JButton loadTextButton;
	
	private JFileChooser fileChooser = new JFileChooser(".");
	private List<String> bannedWordsList;
	private List<String> censoredText;
	private Map<String, Integer> statistics;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Censorship window = new Censorship();
					window.frmCensorshipTool.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Censorship() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frmCensorshipTool = new JFrame();
		this.frmCensorshipTool.setTitle("CENSORSHIP TOOL");
		this.frmCensorshipTool.setBounds(100, 100, 922, 445);
		this.frmCensorshipTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmCensorshipTool.getContentPane().setLayout(null);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(10, 22, 186, 311);
		this.frmCensorshipTool.getContentPane().add(this.scrollPane);
		
		this.bannedWordsArea = new JTextArea();
		this.scrollPane.setViewportView(this.bannedWordsArea);
		this.bannedWordsArea.setBorder(new TitledBorder(null, "BANNED WORDS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		this.scrollPane_1 = new JScrollPane();
		this.scrollPane_1.setBounds(206, 177, 439, 156);
		this.frmCensorshipTool.getContentPane().add(this.scrollPane_1);
		
		this.censoredTextArea = new JTextArea();
		this.censoredTextArea.setBorder(new TitledBorder(null, "CENSORED TEXT", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.scrollPane_1.setViewportView(this.censoredTextArea);
		
		this.scrollPane_2 = new JScrollPane();
		this.scrollPane_2.setBounds(655, 22, 237, 311);
		this.frmCensorshipTool.getContentPane().add(this.scrollPane_2);
		
		this.statisticsArea = new JTextArea();
		this.statisticsArea.setBorder(new TitledBorder(null, "STATISTICS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.scrollPane_2.setViewportView(this.statisticsArea);
		
		this.loadBannedButton = new JButton("LOAD BANNED WORDS");
		this.loadBannedButton.addActionListener(this);
		this.loadBannedButton.setBounds(10, 355, 186, 23);
		this.frmCensorshipTool.getContentPane().add(this.loadBannedButton);
		
		this.saveResultsButton = new JButton("SAVE RESULTS");
		this.saveResultsButton.setEnabled(false);
		this.saveResultsButton.addActionListener(this);
		this.saveResultsButton.setBounds(655, 355, 237, 23);
		this.frmCensorshipTool.getContentPane().add(this.saveResultsButton);
		
		this.loadTextButton = new JButton("LOAD & CENSOR");
		this.loadTextButton.setEnabled(false);
		this.loadTextButton.addActionListener(this);
		this.loadTextButton.setBounds(206, 355, 439, 23);
		this.frmCensorshipTool.getContentPane().add(this.loadTextButton);
		
		this.scrollPane_3 = new JScrollPane();
		this.scrollPane_3.setBounds(206, 22, 439, 144);
		this.frmCensorshipTool.getContentPane().add(this.scrollPane_3);
		
		this.originalTextArea = new JTextArea();
		this.originalTextArea.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "ORIGINAL TEXT", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.scrollPane_3.setViewportView(this.originalTextArea);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.loadTextButton) {
			do_loadTextButton_actionPerformed(arg0);
		}
		if (arg0.getSource() == this.loadBannedButton) {
			do_loadBannedButton_actionPerformed(arg0);
		}
		if (arg0.getSource() == this.saveResultsButton) {
			do_saveResultsButton_actionPerformed(arg0);
		}

	}

	protected  void do_saveResultsButton_actionPerformed(ActionEvent arg0) {
		fileChooser.setDialogTitle("Select File to save results");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Results File","res"));
		fileChooser.showSaveDialog(this.frmCensorshipTool);
		Path saveResultsPath = fileChooser.getSelectedFile().toPath();
		
		saveResults(saveResultsPath, this.censoredText, this.statistics);
	}
	
	protected  void do_loadBannedButton_actionPerformed(ActionEvent arg0) {
		fileChooser.setDialogTitle("Select File Containing BANNED words");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Banned words file","ban"));
		fileChooser.showOpenDialog(this.frmCensorshipTool);
		Path pathBannedWords = fileChooser.getSelectedFile().toPath();
		
		this.bannedWordsList = this.getBannedWords(pathBannedWords);
		
		this.bannedWordsArea.setText("");
		
		for (String word: this.bannedWordsList)
			this.bannedWordsArea.append(word+"\n");
		
		this.bannedWordsArea.append("\nTotal words: "+bannedWordsList.size());
		
		this.loadTextButton.setEnabled(true);
	}
	
	
	protected  void do_loadTextButton_actionPerformed(ActionEvent arg0) {
		fileChooser.setDialogTitle("Select File to CENSOR ");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Text files","txt"));
		fileChooser.showOpenDialog(this.frmCensorshipTool);
		Path filePathToLoad = fileChooser.getSelectedFile().toPath();
		
		this.statistics = new TreeMap<String, Integer>();
		List<String> originalText = loadTextFile(filePathToLoad);
		this.censoredText = censorText(originalText, this.bannedWordsList, statistics);
		
		this.originalTextArea.setText("");
		for (String linia: originalText) {
			this.originalTextArea.append(linia+"\n");
		}
		this.originalTextArea.append("\n--- end of original text---");
		
		this.censoredTextArea.setText("");
		for (String linia: censoredText) {
			this.censoredTextArea.append(linia+"\n");
		}
		this.censoredTextArea.append("\n--- end of censored text---");
		
		Collections.sort(this.bannedWordsList);
		this.statisticsArea.setText("");
		for (String banned : this.bannedWordsList) {
			statisticsArea.append(banned+": "+statistics.get(banned)+"\n");
		}
		this.statisticsArea.append("\n--- end of statistics---");
		
		this.saveResultsButton.setEnabled(true);

	}


	/**
	 * 	Determinar que tipo es y hacer lectura
	 * 	Generar un String de paraulas
	 *
	 * @param path
	 * @return
	 */
	protected static List<String> getBannedWords(Path path) {
		/* This procedure produces a list containing all the banned words
		 * in file f. The file can be in any of the two documented formats*/

		/* COMPLETE 1 */

		List<String> words = new LinkedList<>();
		String word;

		int numWords;

	try (DataInputStream in =
				 new DataInputStream(
						 new BufferedInputStream(
								 Files.newInputStream(path) ) ) ) {

		if ( in.readBoolean() == true){

			while (! (word = in.readUTF()).equalsIgnoreCase("FILE_ENDS_HERE") ){
				words.add(word);
			}

		} else {
			numWords = in.readInt();

			for (int i = 0; i < numWords; i++){
				words.add(in.readUTF());
			}

		}

	} catch (IOException e){ System.out.println(e); }

		return words; //TODO: Update as necessary
	}

	/**
	 * 	Tenemos que usar el BuffedReader
	 *
	 * @param path
	 * @return
	 */
	protected static List<String> loadTextFile (Path path) {
		/* This procedure produces a list containing all the lines of text
		   in file in the given path */

		/* COMPLETE 2 */

		List<String> lines = new LinkedList<>();
		String line;

		try (BufferedReader br = Files.newBufferedReader(path)){
			while (! ( ( line = br.readLine() ) == null) ){
				lines.add(line);
			}

		} catch (IOException e){ System.out.println(e); }

		return lines; //TODO: Update as necessary
	}


	/*
	Buscar en el texto si aparece la palabra prohibida
	independientemente poner **** (4 asteriscos)
	No distinguir entre Mayuscula y Minuscula
	Si sale en el string si es delante, detras o entre medio

	textSplit(" "); separa las palabras por espacio
	string() pov = textSplit(" ");
	 */
	protected static List<String> censorText (List<String> original, 
			                                  List<String> bannedWordList, 
			                                  Map<String, Integer> statistics) {
		
		/* This procedure returns the censored text. It also updates the statistics
		 * argument (for each banned word, the number of times that word has been
		 * removed from the original text  
		 */
		
		/* COMPLETE 3 */



		return null; //TODO: Change as necessary
	}

	private static String censorLine (String line,
									  List<String> bannedWordList,
									  Map<String, Integer> statistics ){

		String[] words = line.split(" ");
		String finalLine = "";

		for( int i = 0; i < words.length ; i++ ){

			for( String bannedWord : bannedWordList ){

				if( words[i].contains(bannedWord) ){
					words[i] = "****";
					statistics.put(bannedWord, statistics.get(bannedWord) + 1);

				}

			}

			finalLine += words[i] + " ";

		}


		return finalLine;
	}






	/*
	Mapa --> cuantas veces se aliminaron del texto (relacionadas)/ Palabar que no se elimina nunca se pone 0
	Se genera el fichero, y se tine que usar un research...
	Se tiene que guardar resultados de arriba
	Guarda el resultado de la censura
	 */
	protected void saveResults(Path path, List<String> censored, Map<String, Integer> statistics) {
		/* Writes in f the censored text and the counting of banned words, according to the
		 * documented format
		 */
		
		/* COMPLETE 4 */

	}


}
