import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;

/**
 * A JPanel for 2D graphics.
 * 
 * @author Hendrik Speleers
 * @author NMCGJ, AY 2015-2016
 */
public class GraphicsPanel extends JPanel {

   private static final long serialVersionUID = 1L;

   protected ArrayList<DrawComponent> drawList;
   protected Color drawColor;
   protected Font drawFont;

   /**
    * Constructs a graphics panel.
    */
   public GraphicsPanel() {
      this(Color.BLACK, new Font(Font.SANS_SERIF, Font.PLAIN, 12));
   }

   /**
    * Constructs a graphics panel with a given drawing color and font.
    * 
    * @param color
    *           the drawing color
    * @param font
    *           the drawing font
    */
   public GraphicsPanel(Color color, Font font) {
      drawList = new ArrayList<DrawComponent>();
      drawColor = color;
      drawFont = font;
   }

   /**
    * Gets the drawing color.
    * 
    * @return the current color
    */
   public Color getDrawColor() {
      return drawColor;
   }

   /**
    * Sets the drawing color.
    * 
    * @param color
    *           the new color
    */
   public void setDrawColor(Color color) {
      drawColor = color;
   }

   /**
    * Gets the drawing font.
    * 
    * @return the current font
    */
   public Font getDrawFont() {
      return drawFont;
   }

   /**
    * Sets the drawing font.
    * 
    * @param font
    *           the new font
    */
   public void setDrawFont(Font font) {
      drawFont = font;
   }

   /**
    * Clears the graphics panel.
    */
   public void clear() {
      drawList.clear();
   }

   /**
    * Draws a given text on the panel.
    * 
    * @param text
    *           the text to be drawn
    * @param point
    *           the starting point of the text
    */
   public void drawText(String text, Point point) {
      drawList.add(new DrawText(text, point, drawColor, drawFont));
   }

   /**
    * Draws a given text on the panel.
    * 
    * @param text
    *           the text to be drawn
    * @param x
    *           the x-coordinate of the starting point of the text
    * @param y
    *           the y-coordinate of the starting point of the text
    */
   public void drawText(String text, int x, int y) {
      drawList.add(new DrawText(text, x, y, drawColor, drawFont));
   }

   /**
    * Draws a line on the panel.
    * 
    * @param point1
    *           the first point of the line
    * @param point2
    *           the second point of the line
    */
   public void drawLine(Point point1, Point point2) {
      drawList.add(new DrawLine(point1, point2, drawColor));
   }

   /**
    * Draws a line on the panel.
    * 
    * @param x1
    *           the x-coordinate of the first point of the line
    * @param y1
    *           the y-coordinate of the first point of the line
    * @param x2
    *           the x-coordinate of the second point of the line
    * @param y2
    *           the y-coordinate of the second point of the line
    */
   public void drawLine(int x1, int y1, int x2, int y2) {
      drawList.add(new DrawLine(x1, y1, x2, y2, drawColor));
   }

   /**
    * Draws a sequence of connected lines on the panel.
    * 
    * @param points
    *           array of points
    */
   public void drawPolyline(Point[] points) {
      drawList.add(new DrawPolyline(points, drawColor));
   }

   /**
    * Draws a sequence of connected lines on the panel.
    * 
    * @param x
    *           array of x-coordinates of the points
    * @param y
    *           array of y-coordinates of the points
    */
   public void drawPolyline(int[] x, int[] y) {
      drawList.add(new DrawPolyline(x, y, drawColor));
   }

   /**
    * Draws a rectangle on the panel.
    * 
    * @param point1
    *           the upper left corner of the rectangle
    * @param point2
    *           the lower right corner of the rectangle
    */
   public void drawRectangle(Point point1, Point point2) {
      drawRectangle(point1, point2, false);
   }

   /**
    * Draws a rectangle on the panel.
    * 
    * @param x
    *           the x-coordinate of the rectangle
    * @param y
    *           the y-coordinate of the rectangle
    * @param width
    *           the width of the rectangle
    * @param height
    *           the height of the rectangle
    */
   public void drawRectangle(int x, int y, int width, int height) {
      drawRectangle(x, y, width, height, false);
   }

   /**
    * Draws a rectangle on the panel.
    * 
    * @param point1
    *           the upper left corner of the rectangle
    * @param point2
    *           the lower right corner of the rectangle
    * @param filled
    *           the rectangle is filled or not
    */
   public void drawRectangle(Point point1, Point point2, boolean filled) {
      drawList.add(new DrawRectangle(point1, point2, filled, drawColor));
   }

   /**
    * Draws a rectangle on the panel.
    * 
    * @param x
    *           the x-coordinate of the rectangle
    * @param y
    *           the y-coordinate of the rectangle
    * @param width
    *           the width of the rectangle
    * @param height
    *           the height of the rectangle
    * @param filled
    *           the rectangle is filled or not
    */
   public void drawRectangle(int x, int y, int width, int height, boolean filled) {
      drawList.add(new DrawRectangle(x, y, width, height, filled, drawColor));
   }

   /**
    * Draws an oval on the panel.
    * 
    * @param point1
    *           the upper left corner of the oval bounding box
    * @param point2
    *           the lower right corner of the oval bounding box
    */
   public void drawOval(Point point1, Point point2) {
      drawOval(point1, point2, false);
   }

   /**
    * Draws an oval on the panel.
    * 
    * @param x
    *           the x-coordinate of the oval bounding box
    * @param y
    *           the y-coordinate of the oval bounding box
    * @param width
    *           the width of the oval bounding box
    * @param height
    *           the height of the oval bounding box
    */
   public void drawOval(int x, int y, int width, int height) {
      drawOval(x, y, width, height, false);
   }

   /**
    * Draws an oval on the panel.
    * 
    * @param point1
    *           the upper left corner of the oval bounding box
    * @param point2
    *           the lower right corner of the oval bounding box
    * @param filled
    *           the oval is filled or not
    */
   public void drawOval(Point point1, Point point2, boolean filled) {
      drawList.add(new DrawOval(point1, point2, filled, drawColor));
   }

   /**
    * Draws an oval on the panel.
    * 
    * @param x
    *           the x-coordinate of the oval bounding box
    * @param y
    *           the y-coordinate of the oval bounding box
    * @param width
    *           the width of the oval bounding box
    * @param height
    *           the height of the oval bounding box
    * @param filled
    *           the oval is filled or not
    */
   public void drawOval(int x, int y, int width, int height, boolean filled) {
      drawList.add(new DrawOval(x, y, width, height, filled, drawColor));
   }

   /**
    * Draws a polygon on the panel.
    * 
    * @param points
    *           array of polygon points
    */
   public void drawPolygon(Point[] points) {
      drawPolygon(points, false);
   }

   /**
    * Draws a polygon on the panel.
    * 
    * @param x
    *           array of x-coordinates of the polygon points
    * @param y
    *           array of y-coordinates of the polygon points
    */
   public void drawPolygon(int[] x, int[] y) {
      drawPolygon(x, y, false);
   }

   /**
    * Draws a polygon on the panel.
    * 
    * @param points
    *           array of polygon points
    * @param filled
    *           the polygon is filled or not
    */
   public void drawPolygon(Point[] points, boolean filled) {
      drawList.add(new DrawPolygon(points, filled, drawColor));
   }

   /**
    * Draws a polygon on the panel.
    * 
    * @param x
    *           array of x-coordinates of the polygon points
    * @param y
    *           array of y-coordinates of the polygon points
    * @param filled
    *           the polygon is filled or not
    */
   public void drawPolygon(int[] x, int[] y, boolean filled) {
      drawList.add(new DrawPolygon(x, y, filled, drawColor));
   }

   /**
    * Draws an image on the panel.
    * 
    * @param image
    *           the image to be drawn
    * @param point1
    *           the upper left corner of the image
    */
   public void drawImage(Image image, Point point) {
      drawList.add(new DrawImage(image, point));
   }

   /**
    * Draws an image on the panel.
    * 
    * @param image
    *           the image to be drawn
    * @param x
    *           the x-coordinate of the image box
    * @param y
    *           the y-coordinate of the image box
    */
   public void drawImage(Image image, int x, int y) {
      drawList.add(new DrawImage(image, x, y));
   }

   /**
    * Draws an image on the panel inside a box (scaled if necessary).
    * 
    * @param image
    *           the image to be drawn
    * @param point1
    *           the upper left corner of the image box
    * @param point2
    *           the lower right corner of the image box
    */
   public void drawImage(Image image, Point point1, Point point2) {
      drawList.add(new DrawImage(image, point1, point2));
   }

   /**
    * Draws an image on the panel inside a box (scaled if necessary).
    * 
    * @param image
    *           the image to be drawn
    * @param x
    *           the x-coordinate of the image box
    * @param y
    *           the y-coordinate of the image box
    * @param width
    *           the width of the image box
    * @param height
    *           the height of the image box
    */
   public void drawImage(Image image, int x, int y, int width, int height) {
      drawList.add(new DrawImage(image, x, y, width, height));
   }

   /**
    * Creates an image from the graphics on the panel.
    * 
    * @return the image
    */
   public BufferedImage createImage() {
      BufferedImage image = new BufferedImage(getWidth(), getHeight(),
            BufferedImage.TYPE_INT_RGB);
      Graphics2D graphics = image.createGraphics();
      print(graphics);
      return image;
   }

   /**
    * Draws all the components on the panel.
    * 
    * @param g
    *           the graphics context
    */
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      for (int i = 0; i < drawList.size(); i++) {
         drawList.get(i).draw(g);
      }
   }

}

/**
 * An interface for components to be drawn.
 */
interface DrawComponent {

   /**
    * Draws the component.
    * 
    * @param g
    *           the graphics context
    */
   public void draw(Graphics g);

}

/**
 * A DrawComponent to draw text.
 */
class DrawText implements DrawComponent {
   public String text;
   public int x, y;
   public Color color;
   public Font font;

   /**
    * Constructs a text drawer.
    * 
    * @param text
    *           the text to be drawn
    * @param point
    *           the starting point of the text
    * @param color
    *           the color of the text
    * @param font
    *           the font of the text
    */
   DrawText(String text, Point point, Color color, Font font) {
      this.text = text;
      this.x = point.x;
      this.y = point.y;
      this.color = color;
      this.font = font;
   }

   /**
    * Constructs a text drawer.
    * 
    * @param text
    *           the text to be drawn
    * @param x
    *           the x-coordinate of the starting point of the text
    * @param y
    *           the y-coordinate of the starting point of the text
    * @param color
    *           the color of the text
    * @param font
    *           the font of the text
    */
   DrawText(String text, int x, int y, Color color, Font font) {
      this.text = text;
      this.x = x;
      this.y = y;
      this.color = color;
      this.font = font;
   }

   /**
    * Draws the text.
    * 
    * @param g
    *           the graphics context
    */
   public void draw(Graphics g) {
      g.setColor(color);
      g.setFont(font);
      g.drawString(text, x, y);
   }
}

/**
 * A DrawComponent to draw lines.
 */
class DrawLine implements DrawComponent {
   public int x1, y1, x2, y2;
   public Color color;

   /**
    * Constructs a line drawer.
    * 
    * @param point1
    *           the first point of the line
    * @param point2
    *           the second point of the line
    * @param color
    *           the color of the line
    */
   DrawLine(Point point1, Point point2, Color color) {
      this.x1 = point1.x;
      this.y1 = point1.y;
      this.x2 = point2.x;
      this.y2 = point2.y;
      this.color = color;
   }

   /**
    * Constructs a line drawer.
    * 
    * @param x1
    *           the x-coordinate of the first point of the line
    * @param y1
    *           the y-coordinate of the first point of the line
    * @param x2
    *           the x-coordinate of the second point of the line
    * @param y2
    *           the y-coordinate of the second point of the line
    * @param color
    *           the color of the line
    */
   DrawLine(int x1, int y1, int x2, int y2, Color color) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
      this.color = color;
   }

   /**
    * Draws the line.
    * 
    * @param g
    *           the graphics context
    */
   public void draw(Graphics g) {
      g.setColor(color);
      g.drawLine(x1, y1, x2, y2);
   }
}

/**
 * A DrawComponent to draw polylines.
 */
class DrawPolyline implements DrawComponent {
   public int[] x, y;
   public Color color;

   /**
    * Constructs a polyline drawer.
    * 
    * @param points
    *           array of points
    * @param color
    *           the color of the polyline
    */
   DrawPolyline(Point[] points, Color color) {
      this.x = new int[points.length];
      this.y = new int[points.length];
      for (int i = 0; i < points.length; i++) {
         this.x[i] = points[i].x;
         this.y[i] = points[i].y;
      }
      this.color = color;
   }

   /**
    * Constructs a polyline drawer.
    * 
    * @param x
    *           array of x-coordinates of the points
    * @param y
    *           array of y-coordinates of the points
    * @param color
    *           the color of the polyline
    */
   DrawPolyline(int[] x, int[] y, Color color) {
      this.x = x;
      this.y = y;
      this.color = color;
   }

   /**
    * Draws the polygon.
    * 
    * @param g
    *           the graphics context
    */
   public void draw(Graphics g) {
      g.setColor(color);
      g.drawPolyline(x, y, x.length);
   }
}

/**
 * A DrawComponent to draw rectangles.
 */
class DrawRectangle implements DrawComponent {
   public int x, y, width, height;
   public boolean filled;
   public Color color;

   /**
    * Constructs a rectangle drawer.
    * 
    * @param point1
    *           the upper left corner of the rectangle
    * @param point2
    *           the lower right corner of the rectangle
    * @param filled
    *           the rectangle is filled or not
    * @param color
    *           the color of the rectangle
    */
   DrawRectangle(Point point1, Point point2, boolean filled, Color color) {
      this.x = Math.min(point1.x, point2.x);
      this.y = Math.min(point1.y, point2.y);
      this.width = Math.abs(point2.x - point1.x);
      this.height = Math.abs(point2.y - point1.y);
      this.filled = filled;
      this.color = color;
   }

   /**
    * Constructs a rectangle drawer.
    * 
    * @param x
    *           the x-coordinate of the rectangle
    * @param y
    *           the y-coordinate of the rectangle
    * @param width
    *           the width of the rectangle
    * @param height
    *           the height of the rectangle
    * @param filled
    *           the rectangle is filled or not
    * @param color
    *           the color of the rectangle
    */
   DrawRectangle(int x, int y, int width, int height, boolean filled, Color color) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.filled = filled;
      this.color = color;
   }

   /**
    * Draws the rectangle.
    * 
    * @param g
    *           the graphics context
    */
   public void draw(Graphics g) {
      g.setColor(color);
      if (filled) {
         g.fillRect(x, y, width + 1, height + 1);
      } else {
         g.drawRect(x, y, width, height);
      }
   }
}

/**
 * A DrawComponent to draw ovals.
 */
class DrawOval implements DrawComponent {
   public int x, y, width, height;
   public boolean filled;
   public Color color;

   /**
    * Constructs a oval drawer.
    * 
    * @param point1
    *           the upper left corner of the oval bounding box
    * @param point2
    *           the lower right corner of the oval bounding box
    * @param filled
    *           the oval is filled or not
    * @param color
    *           the color of the oval
    */
   DrawOval(Point point1, Point point2, boolean filled, Color color) {
      this.x = Math.min(point1.x, point2.x);
      this.y = Math.min(point1.y, point2.y);
      this.width = Math.abs(point2.x - point1.x);
      this.height = Math.abs(point2.y - point1.y);
      this.filled = filled;
      this.color = color;
   }

   /**
    * Constructs a oval drawer.
    * 
    * @param x
    *           the x-coordinate of the oval bounding box
    * @param y
    *           the y-coordinate of the oval bounding box
    * @param width
    *           the width of the oval bounding box
    * @param height
    *           the height of the oval bounding box
    * @param filled
    *           the oval is filled or not
    * @param color
    *           the color of the oval
    */
   DrawOval(int x, int y, int width, int height, boolean filled, Color color) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.filled = filled;
      this.color = color;
   }

   /**
    * Draws the oval.
    * 
    * @param g
    *           the graphics context
    */
   public void draw(Graphics g) {
      g.setColor(color);
      if (filled) {
         g.fillOval(x, y, width, height);
      } else {
         g.drawOval(x, y, width, height);
      }
   }
}

/**
 * A DrawComponent to draw polygons.
 */
class DrawPolygon implements DrawComponent {
   public int[] x, y;
   public boolean filled;
   public Color color;

   /**
    * Constructs a polygon drawer.
    * 
    * @param points
    *           array of polygon points
    * @param filled
    *           the polygon is filled or not
    * @param color
    *           the color of the polygon
    */
   DrawPolygon(Point[] points, boolean filled, Color color) {
      this.x = new int[points.length];
      this.y = new int[points.length];
      for (int i = 0; i < points.length; i++) {
         this.x[i] = points[i].x;
         this.y[i] = points[i].y;
      }
      this.filled = filled;
      this.color = color;
   }

   /**
    * Constructs a polygon drawer.
    * 
    * @param x
    *           array of x-coordinates of the polygon points
    * @param y
    *           array of y-coordinates of the polygon points
    * @param filled
    *           the polygon is filled or not
    * @param color
    *           the color of the polygon
    */
   DrawPolygon(int[] x, int[] y, boolean filled, Color color) {
      this.x = x;
      this.y = y;
      this.filled = filled;
      this.color = color;
   }

   /**
    * Draws the polygon.
    * 
    * @param g
    *           the graphics context
    */
   public void draw(Graphics g) {
      g.setColor(color);
      if (filled) {
         g.fillPolygon(x, y, x.length);
      } else {
         g.drawPolygon(x, y, x.length);
      }
   }
}

/**
 * A DrawComponent to draw images.
 */
class DrawImage implements DrawComponent {
   public Image image;
   public int x, y, width, height;

   /**
    * Constructs an image drawer.
    * 
    * @param image
    *           the image to be drawn
    * @param point
    *           the upper left corner of the image
    */
   DrawImage(Image image, Point point) {
      this.image = image;
      this.x = point.x;
      this.y = point.y;
      this.width = image.getWidth(null);
      this.height = image.getHeight(null);
   }

   /**
    * Constructs an image drawer.
    * 
    * @param image
    *           the image to be drawn
    * @param x
    *           the x-coordinate of the image box
    * @param y
    *           the y-coordinate of the image box
    */
   DrawImage(Image image, int x, int y) {
      this.image = image;
      this.x = x;
      this.y = y;
      this.width = image.getWidth(null);
      this.height = image.getHeight(null);
   }

   /**
    * Constructs an image drawer.
    * 
    * @param image
    *           the image to be drawn
    * @param point1
    *           the upper left corner of the image box
    * @param point2
    *           the lower right corner of the image box
    */
   DrawImage(Image image, Point point1, Point point2) {
      this.image = image;
      this.x = Math.min(point1.x, point2.x);
      this.y = Math.min(point1.y, point2.y);
      this.width = Math.abs(point2.x - point1.x);
      this.height = Math.abs(point2.y - point1.y);
   }

   /**
    * Constructs an image drawer.
    * 
    * @param image
    *           the image to be drawn
    * @param x
    *           the x-coordinate of the image box
    * @param y
    *           the y-coordinate of the image box
    * @param width
    *           the width of the image box
    * @param height
    *           the height of the image box
    */
   DrawImage(Image image, int x, int y, int width, int height) {
      this.image = image;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }

   /**
    * Draws the image.
    * 
    * @param g
    *           the graphics context
    */
   public void draw(Graphics g) {
      g.drawImage(image, x, y, width, height, null);
   }
}
