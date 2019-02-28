import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
 * A JFrame for Conway's Game of Life.
 */
public class GameOfLifeFrame extends TextFrame {
	private File current = new File("current.txt");
	private File oldcurrent = new File("oldcurrent.txt");
	private CoordMaker coordmaker = new CoordMaker(current);
	private CoordMaker oldcoordmaker = new CoordMaker(oldcurrent);
	private int a = 0;
	private int b = 0;

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a frame for Conway's Game of Life.
	 */
	public GameOfLifeFrame() {

		super("Conway's Game of Life");
		addMenuBarGame();
	}

	/**
	 * Visualizes the game frame.
	 */

	public void start() {
		resetText();
		addText("This frame is just for load txt files of coordinate ");
		setTextDimension(a, b);
		setTextEditable(false);
		setResizable(true);
		setVisible(true);
		coordmaker.clearCoord();
		coordmaker.clearCurrent();
		
	}

	protected JMenuItem menuGame;

	/**
	 * Adds the game menu bar to the frame.
	 */
	protected void addMenuBarGame() {
		ActionListener menuGameListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play();
			}
		};

		menuGame = new JMenuItem("Play conway animation");
		menuGame.setMnemonic(KeyEvent.VK_P);
		menuGame.addActionListener(menuGameListener);
		menuBar.add(menuGame);

	}

	public void resetText() {
	}

	public void loadTextFile(File file) {
		//setText("The game is loaded from " + file.getName() + "\n");
		oldcoordmaker.setCurrent(file);
		oldcurrent = oldcoordmaker.current;
		coordmaker.setCurrent(file);
		current = coordmaker.current;
	}

	public void saveTextFile(File file) {

	}

	public void play() {
		AnimatedConway2 conway = new AnimatedConway2("Conway", coordmaker.coord);
		conway.start();
		conway.playAnimation();
		// this.close();

	}

	public static void main(String[] args) {
		GameOfLifeFrame frame = new GameOfLifeFrame();
		frame.start();
	}

}
