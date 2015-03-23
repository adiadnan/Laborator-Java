package MovieColection;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MovieColection implements ActionListener {

	public static Collection colection;

	private JPanel panelAddMovie = new JPanel(new GridBagLayout());
	private JTextField movieName = new JTextField(20);
	private JTextField movieDay = new JTextField(20);
	private JTextField movieMonth = new JTextField(20);
	private JTextField movieYear = new JTextField(20);
	private JComboBox<Movie.Genre> movieGenre = new JComboBox<Movie.Genre>(
			Movie.Genre.values());
	private JTextField movieRating = new JTextField(20);
	private JTextField movieIMBD = new JTextField(20);
	private JButton buttonSubmitAdd = new JButton("Submit");

	private JPanel panelRemoveMovie = new JPanel(new GridBagLayout());
	private JTextField movieNameToDelete = new JTextField(20);
	private JButton buttonSubmitDelete = new JButton("Submit");

	private JPanel panelGenerateReport = new JPanel(new GridBagLayout());
	private JTextField reportName = new JTextField(20);
	private JButton buttonSubmitReport = new JButton("Submit");

	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
			}
		}
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				colection = new Collection();
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Movie Database");
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		 
		frame.addWindowListener( new WindowAdapter()
		 {
		   public void windowClosing(WindowEvent e)
		    {
			   String fileName = "data.txt";
				try {
					ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
					os.writeObject(colection);
					os.close();
				} catch (FileNotFoundException e2) {
					return;
				} catch (IOException e2) {
					return;
				}
				frame.setVisible(false);
				frame.dispose();
		     }
		  });
		
		
		MovieColection pmdb = new MovieColection();
		pmdb.addComponentToPane(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}

	public void addComponentToPane(Container pane) {
		String fileName = "data.txt";
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
			colection = null;
			System.gc();
			colection = new Collection();
			try {
				colection = (Collection) is.readObject();
				is.close();
			} catch (ClassNotFoundException e) {
				showMessage(e.toString());
			}
		} catch (FileNotFoundException e) {
			//continue
		} catch (IOException e) {
			showMessage(e.toString());
		}

		JTabbedPane tabbedPane = new JTabbedPane();
		GridBagConstraints gbc = new GridBagConstraints();

		insertNewLine(panelAddMovie, gbc, 0, "Movie name:");
		panelAddMovie.add(movieName, gbc);

		insertNewLine(panelAddMovie, gbc, 1, "Movie genre:");
		panelAddMovie.add(movieGenre, gbc);

		insertNewLine(panelAddMovie, gbc, 2, "Release day:");
		panelAddMovie.add(movieDay, gbc);

		insertNewLine(panelAddMovie, gbc, 3, "Release month:");
		panelAddMovie.add(movieMonth, gbc);

		insertNewLine(panelAddMovie, gbc, 4, "Release year:");
		panelAddMovie.add(movieYear, gbc);

		insertNewLine(panelAddMovie, gbc, 5, "Rating:");
		panelAddMovie.add(movieRating, gbc);

		insertNewLine(panelAddMovie, gbc, 6, "IMDB id:");
		panelAddMovie.add(movieIMBD, gbc);

		buttonSubmitAdd.addActionListener(this);
		panelAddMovie.add(buttonSubmitAdd);

		tabbedPane.addTab("Add a movie", panelAddMovie);

		insertNewLine(panelRemoveMovie, gbc, 0, "Movie name:");
		panelRemoveMovie.add(movieNameToDelete, gbc);

		buttonSubmitDelete.addActionListener(this);
		panelRemoveMovie.add(buttonSubmitDelete);

		tabbedPane.addTab("Remove a movie", panelRemoveMovie);

		insertNewLine(panelGenerateReport, gbc, 0, "Report path:");
		panelGenerateReport.add(reportName, gbc);

		buttonSubmitReport.addActionListener(this);
		panelGenerateReport.add(buttonSubmitReport);

		tabbedPane.addTab("Generate movies report", panelGenerateReport);

		pane.add(tabbedPane, BorderLayout.CENTER);
	}

	private void insertNewLine(JPanel panel, GridBagConstraints gbc, int gridy,
			String lblName) {
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = gridy;

		gbc.gridx = 0;
		panel.add(new JLabel(lblName), gbc);

		gbc.gridx = 1;
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "",
				JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object sourceObject = e.getSource();

		if (sourceObject == buttonSubmitAdd) {
			String mgenre;
			if (movieGenre.getSelectedItem() == Movie.Genre.actiune) mgenre = "actiune";
			else if (movieGenre.getSelectedItem() == Movie.Genre.comedie) mgenre = "comedie";
			else if (movieGenre.getSelectedItem() == Movie.Genre.drama) mgenre = "drama";
			else mgenre = "thriller";
			String mName = movieName.getText();
			String mDay = movieDay.getText();
			String mMonth = movieMonth.getText();
			String mYear = movieYear.getText();
			String mIMBD = movieIMBD.getText();
			String mRating =  movieRating.getText();
			
			try {
				colection.addMovie(mName, mgenre, mDay, mMonth, mYear, mIMBD, mRating);
				showMessage("Movie " + mName + " added to collection.");
			} catch (NameException | NullException | CategoryException
					| DateException | RatingException | ImbdException exp) {
				showMessage(exp.toString());
			}
			
			movieName.setText("");
			movieDay.setText("");
			movieMonth.setText("");
			movieYear.setText("");
			movieIMBD.setText("");
			movieRating.setText("");
		}
		
		else if (sourceObject == buttonSubmitDelete){
			try {
				colection.deleteMovie(movieNameToDelete.getText());
				showMessage("Movie " + movieNameToDelete.getText() + " deleted from the collection.");
			} catch (NullException | NameException | NameDoesNotExistException exp) {
				showMessage(exp.toString());
			}
			movieNameToDelete.setText("");
		}
		else if (sourceObject == buttonSubmitReport){
			try {
				colection.makeReport(reportName.getText());
				showMessage("The report " + reportName.getText() + " on the collection was made.");
			} catch (NullException e1) {
				showMessage(e1.toString());
			}
			reportName.setText("");
		}
	}
}
