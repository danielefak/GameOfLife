import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.imageio.*;
import java.io.*;

/**
 * A JFrame for 2D graphics.
 * 
 * @author Hendrik Speleers
 * @author NMCGJ, AY 2015-2016
 */
public class GraphicsFrame extends JFrame {

   private static final long serialVersionUID = 1L;

   /**
    * Constructs a graphics frame with a given title.
    * 
    * @param title
    *           the frame title
    */
   public GraphicsFrame(String title) {
      setTitle(title);
      setFrameOptions();
      addComponents();
      addMenuBar();
   }

   /**
    * Visualizes the graphics frame.
    */
   public void start() {
      setVisible(true);
   }

   /**
    * Closes the graphics frame.
    */
   public void close() {
      setVisible(false);
      dispose();
   }

   /**
    * Sets the general options for the frame.
    */
   protected void setFrameOptions() {
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      Dimension dimension = toolkit.getScreenSize();

      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(dimension.width / 6, dimension.height / 6,
            dimension.width * 2 / 3, dimension.height * 2 / 3);
   }

   protected JMenuBar menuBar;
   protected JMenu menuFile;
   protected JMenuItem menuFileNew, menuFileOpen, menuFileSave, menuFileExit;

   protected File file;
   protected JFileChooser fileChooser;
   protected FileNameExtensionFilter pngFilter, jpgFilter, imageFilter;

   /**
    * Adds the menu bar to the frame.
    */
   protected void addMenuBar() {
      ActionListener menuFileListener = new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JMenuItem actionMenu = (JMenuItem) e.getSource();
            if (actionMenu == menuFileNew) {
               newFile();
            } else if (actionMenu == menuFileOpen) {
               openFile();
            } else if (actionMenu == menuFileSave) {
               saveFile();
            } else if (actionMenu == menuFileExit) {
               close();
            }
         }
      };

      fileChooser = new JFileChooser();
      imageFilter = new FileNameExtensionFilter("All Image Files",
            ImageIO.getReaderFileSuffixes());
      jpgFilter = new FileNameExtensionFilter("JPEG Files (*.jpg, *.jpeg)",
            "jpg", "jpeg");
      pngFilter = new FileNameExtensionFilter("PNG Files (*.png)", "png");
      fileChooser.addChoosableFileFilter(imageFilter);
      fileChooser.addChoosableFileFilter(jpgFilter);
      fileChooser.addChoosableFileFilter(pngFilter);
      fileChooser.setAcceptAllFileFilterUsed(false);
      file = new File("");

      menuBar = new JMenuBar();
      setJMenuBar(menuBar);

      menuFile = new JMenu("File");
      menuFile.setMnemonic(KeyEvent.VK_F);
      menuBar.add(menuFile);

      menuFileNew = new JMenuItem("New");
      menuFileNew.setMnemonic(KeyEvent.VK_N);
      menuFileNew.addActionListener(menuFileListener);
      menuFile.add(menuFileNew);

      menuFileOpen = new JMenuItem("Open");
      menuFileOpen.setMnemonic(KeyEvent.VK_O);
      menuFileOpen.addActionListener(menuFileListener);
      menuFile.add(menuFileOpen);

      menuFileSave = new JMenuItem("Save");
      menuFileSave.setMnemonic(KeyEvent.VK_S);
      menuFileSave.addActionListener(menuFileListener);
      menuFile.add(menuFileSave);

      menuFile.addSeparator();

      menuFileExit = new JMenuItem("Exit");
      menuFileExit.setMnemonic(KeyEvent.VK_X);
      menuFileExit.addActionListener(menuFileListener);
      menuFile.add(menuFileExit);
   }

   /**
    * Indicates whether the menu bar is visible or not.
    * 
    * @param visible
    *           the new status
    */
   public void setMenuVisible(boolean visible) {
      menuBar.setVisible(visible);
   }

   protected GraphicsPanel graphicsPanel;

   /**
    * Adds the components to the frame.
    */
   protected void addComponents() {
      graphicsPanel = new GraphicsPanel();
      getContentPane().add(graphicsPanel, BorderLayout.CENTER);
   }

   /**
    * Gets the graphics panel of the frame.
    * 
    * @return the panel
    */
   public GraphicsPanel getGraphicsPanel() {
      return graphicsPanel;
   }

   /**
    * Clears the graphics (and possibly the related file pointer).
    */
   protected void newFile() {
      file = new File("");
      clearGraphics();
   }

   /**
    * Opens a file and the image.
    */
   protected void openFile() {
      fileChooser.setDialogTitle("Open File");
      fileChooser.setSelectedFile(file);
      int rVal = fileChooser.showOpenDialog(this);
      if (rVal == JFileChooser.APPROVE_OPTION) {
         file = fileChooser.getSelectedFile();
         loadGraphicsFile(file);
      }
   }

   /**
    * Saves graphics to a file.
    */
   protected void saveFile() {
      fileChooser.setDialogTitle("Save File");
      fileChooser.setSelectedFile(file);
      int rVal = fileChooser.showSaveDialog(this);
      if (rVal == JFileChooser.APPROVE_OPTION) {
         file = fileChooser.getSelectedFile();
         saveGraphicsFile(file);
      }
   }

   /**
    * Clears the graphics panel.
    */
   public void clearGraphics() {
      graphicsPanel.clear();
      graphicsPanel.repaint();
   }

   /**
    * Updates the graphics panel.
    */
   public void updateGraphics() {
      graphicsPanel.repaint();
   }

   /**
    * Loads a given image to the graphics panel.
    * 
    * @param image
    *           the image
    */
   public void loadGraphics(BufferedImage image) {
      if (image != null) {
         graphicsPanel.clear();
         graphicsPanel.drawImage(image, new Point());
         graphicsPanel.repaint();
      }
   }

   /**
    * Loads image from a file to the graphics panel.
    * 
    * @param file
    *           the file
    */
   public void loadGraphicsFile(File file) {
      try {
         if (file.canRead()) {
            BufferedImage image = ImageIO.read(file);
            loadGraphics(image);
         }
      } catch (IOException exc) {
         showErrorDialog("Failed to load image from file.");
      }
   }

   /**
    * Saves image from the graphics panel to a file.
    * 
    * @param file
    *           the file
    */
   public void saveGraphicsFile(File file) {
      try {
         file.createNewFile();
         if (file.canWrite()) {
            String type = getWriterImageFormat(file);
            if (type != null) {
               BufferedImage image = graphicsPanel.createImage();
               ImageIO.write(image, type, file);
            } else {
               showErrorDialog("Unknown image file extension.");
            }
         }
      } catch (IOException exc) {
         showErrorDialog("Failed to save image to file.");
      }
   }

   /**
    * Returns the image format name of a given file, or empty if unknown.
    * 
    * @param file
    *           the file
    * @return the image format
    */
   protected String getWriterImageFormat(File file) {
      String writerFormats[] = ImageIO.getWriterFormatNames();
      String filename = file.getName();
      String format = null;
      for (String f : writerFormats) {
         if (filename.endsWith(f)) {
            format = f;
         }
      }
      return format;
   }

   /**
    * Sets the preferred dimension of the graphics panel.
    * 
    * @param width
    *           the width of the graphics panel
    * @param height
    *           the height of the graphics panel
    */
   protected void setGraphicsDimension(int width, int height) {
      graphicsPanel.setPreferredSize(new Dimension(width, height));
      pack();
   }

   /**
    * Shows a dialog with a given message.
    * 
    * @param message
    *           the message
    * @param title
    *           the dialog title
    */
   protected void showMessageDialog(String message, String title) {
      JOptionPane.showMessageDialog(this, message, title,
            JOptionPane.PLAIN_MESSAGE);
   }

   /**
    * Shows a dialog with a given error message.
    * 
    * @param message
    *           the error message
    */
   protected void showErrorDialog(String message) {
      JOptionPane.showMessageDialog(this, message, "Error",
            JOptionPane.ERROR_MESSAGE);
   }

   /**
    * Shows a dialog requesting a user input.
    * 
    * @param message
    *           the message
    * @param init
    *           initial value
    * @return the user input (the null string if canceled)
    */
   protected String showInputDialog(String message, String init) {
      return JOptionPane.showInputDialog(this, message, init);
   }

   /**
    * Shows a dialog requesting an integer number as user input.
    * 
    * @param message
    *           the message
    * @param init
    *           initial value
    * @return the user input (the value 0 if canceled or invalid number)
    */
   protected int showInputDialogInt(String message, int init) {
      try {
         String input = JOptionPane.showInputDialog(this, message, init);
         return Integer.parseInt(input);
      } catch (NumberFormatException e) {
         return 0;
      } catch (NullPointerException e) {
         return 0;
      }
   }

   /**
    * Shows a dialog requesting a floating point number as user input.
    * 
    * @param message
    *           the message
    * @param init
    *           initial value
    * @return the user input (the value 0.0 if canceled or invalid number)
    */
   protected double showInputDialogDouble(String message, double init) {
      try {
         String input = JOptionPane.showInputDialog(this, message, init);
         return Double.parseDouble(input);
      } catch (NumberFormatException e) {
         return 0.0;
      } catch (NullPointerException e) {
         return 0.0;
      }
   }

   /**
    * A small test of the GraphicsFrame class.
    */
   public static void main(String[] args) {
      GraphicsFrame frame = new GraphicsFrame("My Graphics Frame");
      frame.setMenuVisible(true);
      frame.start();

      GraphicsPanel graphics = frame.getGraphicsPanel();
      graphics.setBackground(Color.WHITE);
      graphics.setDrawColor(Color.BLUE);
      Point p1 = new Point(50, 50);
      Point p2 = new Point(220, 200);
      graphics.drawRectangle(p1, p2, true);

      graphics.setDrawColor(Color.YELLOW);
      Point p3 = new Point(70, 70);
      Point p4 = new Point(140, 140);
      graphics.drawOval(p3, p4, true);

      graphics.setDrawColor(Color.RED);
      Point[] pp = new Point[3]; 
      pp[0] = new Point(120, 180);
      pp[1] = new Point(200, 180);
      pp[2] = new Point(160, 110);
      graphics.drawPolygon(pp, true);

      graphics.setDrawColor(Color.BLACK);
   }

}
