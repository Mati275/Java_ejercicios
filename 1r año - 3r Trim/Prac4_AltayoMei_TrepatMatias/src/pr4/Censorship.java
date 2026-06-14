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

	// 1
	/**
	 *
	 * @param path
	 * @return A list of banned words of the extension ".ban" of the both types that this binary file has.
	 */
	protected static List<String> getBannedWords(Path path) {

		List<String> wordsBanned = new ArrayList<>();

		try( DataInputStream in = new DataInputStream( new BufferedInputStream( Files.newInputStream(path)) ) )  {

			String word;
			int numWords;

			boolean type = in.readBoolean();

			// First type
			if( type ){

				while( !(word = in.readUTF()).equalsIgnoreCase("FILE_ENDS_HERE") ){

					wordsBanned.add(word); // Add the banned word into the list

				} // End of reading the file

			}
			// Second type
			else{

				numWords = in.readInt();

				for(int i = 0; i < numWords; i++) {

					wordsBanned.add( in.readUTF() );

				} // End of reading the file

			}


		} catch ( IOException err ){
			System.out.println(err);
		}


		return wordsBanned;
	}

	// 2
	/**
	 *
	 * @param path
	 * @return A list of string that contains line by line the text that has to be censored
	 */
	protected static List<String> loadTextFile (Path path) {

		List<String> lines = new ArrayList<>();
		String line;

		try(BufferedReader br = new BufferedReader( Files.newBufferedReader(path) ) ) {

			while( (line = br.readLine()) != null){
				lines.add(line);
			}

		} catch (IOException err) {
            System.out.println(err);
        }


        return lines;
	}

	// 3
	/**
	 *
	 * @param original
	 * @param bannedWordList
	 * @param statistics
	 * @return A list of string that contains line by line all the words of the text censored
	 */
	protected static List<String> censorText (List<String> original, 
			                                  List<String> bannedWordList, 
			                                  Map<String, Integer> statistics) {


		List<String> linesCensored = new ArrayList<>();

		// Fill the map, initializing all the values in 0
		for(String bannedWord : bannedWordList){

			statistics.put( bannedWord, 0 );

		}


		// Iterate the original text line by line
		for( String line : original ){
			linesCensored.add( censorLine(line, bannedWordList, statistics) ); // get line by line censored in a new list
		}

		return linesCensored;
	}

	// PRIVATE METHOD

	// 3.1
	/**
	 *
	 * @param line
	 * @param bannedWordList
	 * @param statistics
	 * @return The censored string of the first parameter, banning the words according to the bannedWordList
	 */
	private static String censorLine( String line,
									  List<String> bannedWordList,
									  Map<String, Integer> statistics){
		String censoredLine = "";
		String [] words = line.split(" ");

		boolean foundBannedWord;

		// Iterate the words in the line
		for( String word : words ){
			foundBannedWord = false;

			// For each word, iterate the banned words
			for(int i = 0; i < bannedWordList.size() && !foundBannedWord; i ++){

				// The word has to be censored (check both words in lower case, ignoring the difference of case)
				if( word.toLowerCase().contains(bannedWordList.get(i).toLowerCase()) ){

					word = "**** "; // Add "**** " to the final result
					statistics.put( bannedWordList.get(i), statistics.get(bannedWordList.get(i)) + 1 ); // Add to the value of the censored word one more

					foundBannedWord = true; // Stop the iteration
				}

			}

			censoredLine += word + " "; // Add the word (banned or not) and a space

		}

		// Assuming that the map has all the keys containing the banned words

		return censoredLine;

	}

	// 4
	/**
	 * Save in one file the results, the lines of the text, the text line by line, the number of banned words, and the banned words with it's number of times that were used
	 * @param path
	 * @param censored
	 * @param statistics
	 */
	protected void saveResults(Path path, List<String> censored, Map<String, Integer> statistics) {

		try( DataOutputStream out = new DataOutputStream( new BufferedOutputStream( Files.newOutputStream(path) ) ) ) {

			out.writeInt( censored.size() ); // Write the number of lines of the censored text

			// Write all the lines of the censored text
			for(String line : censored){
				out.writeUTF( line );
			}

			out.writeInt(statistics.size()); // Write the number of the censured words

			for( Map.Entry<String, Integer> entry : statistics.entrySet()) {
				out.writeUTF(entry.getKey());      // Write the key
				out.writeInt(entry.getValue());    // Write the value
			}

		} catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
	
	
}
