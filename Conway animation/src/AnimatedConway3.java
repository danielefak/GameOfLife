import java.awt.Color;
import java.util.ArrayList;

public class AnimatedConway3 extends AnimationGraphicsFrame {

	private static final long serialVersionUID = 1L;
	private int radius = 15;
	private int raggio = radius;
	private boolean[][] matrice;
	private boolean[][] oldmatrice;
	private boolean[][] matriceiniziale;
	private ArrayList<Coordinate> list;
	private ArrayList<Coordinate> listainiziale;
	private int x = 0;
	private int y = 0;
	private int dist = (int) (radius / 2);
	private Conwayrules conwayRules;

	public AnimatedConway3(String title, ArrayList<Coordinate> lista) {
		super(title);
		list = lista;
		matrice = toMatrix(list);
		oldmatrice = matrice;
		matriceiniziale = toMatrix(list);
		x = list.get(0).getX();
		y = list.get(0).getY();
		conwayRules = new Conwayrules(list);
		listainiziale = list;
	}

	protected void animateInit() {
		raggio = radius;
		clearGraphics();
		updateGraphics();
		setAnimationDelay(50);
		setGraphicsDimension(y * 2 * radius + 2 * dist, x * 2 * radius + 2 * dist);
		this.setResizable(false);
		GraphicsPanel graphics = getGraphicsPanel();
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++) {
				if (matrice[i][j]) {
					graphics.setDrawColor(Color.black);
					// graphics.drawOval(j * 2 * radius + 10, i * 2 * radius +
					// 10, 2 * radius, 2 * radius, true);
					drawCircle(graphics, radius * (2 * j + 1) + dist, radius * (2 * i + 1) + dist, radius);
				}
			}
		updateGraphics();
		matrice = conwayRules.rulesMat();
		list = toList(matrice);
		conwayRules.setCoord(list);
	}

	protected void animateNext() {
		GraphicsPanel graphics = getGraphicsPanel();
		if (raggio >= 0) {
			clearGraphics();
			updateGraphics();
			 int red = (int) (Math.random() * 255);
		      int green = (int) (Math.random() * 255);
		      int blue = (int) (Math.random() * 255);
		      int red2 = (int) (Math.random() * 255);
		      int green2 = (int) (Math.random() * 255);
		      int blue2 = (int) (Math.random() * 255);
			for (int i = 0; i < x; i++)
				for (int j = 0; j < y; j++) {
					if (matrice[i][j] && oldmatrice[i][j]) {
						graphics.setDrawColor(Color.black);
						drawCircle(graphics, radius * (2 * j + 1) + dist, radius * (2 * i + 1) + dist, radius);
					}
					if (matrice[i][j] && !oldmatrice[i][j]) {
						
					      graphics.setDrawColor(new Color(red, green, blue));
						drawCircle(graphics, radius * (2 * j + 1) + dist, radius * (2 * i + 1) + dist, radius - raggio);
					}
					if (!matrice[i][j] && oldmatrice[i][j]) {
						
					      graphics.setDrawColor(new Color(red2, green2, blue2));
						drawCircle(graphics, radius * (2 * j + 1) + dist, radius * (2 * i + 1) + dist, raggio);
					}
				}
			raggio--;
			updateGraphics();
		}
		if (raggio == 0) {
			oldmatrice = matrice;
			matrice = conwayRules.rulesMat();
			list = toList(matrice);
			conwayRules.setCoord(list);
			raggio = radius;
		}

	}

	protected void animateFinal() {
		clearGraphics();
		updateGraphics();
		raggio = radius;
		oldmatrice = matriceiniziale;
		list = listainiziale;
		conwayRules.setCoord(list);
		matrice = conwayRules.rulesMat();
	}

	public boolean[][] toMatrix(ArrayList<Coordinate> list) {
		int a = list.get(0).getX();
		int b = list.get(0).getY();
		boolean matrix[][] = new boolean[a][b];
		for (int i = 0; i < a; i++)
			for (int j = 0; j < b; j++)
				matrix[i][j] = false;
		for (int i = 1; i < list.size(); i++) {
			matrix[list.get(i).getX()][list.get(i).getY()] = true;
		}
		return matrix;
	}

	public ArrayList<Coordinate> toList(boolean matrice[][]) {
		ArrayList<Coordinate> list = new ArrayList<Coordinate>(10);
		Coordinate A = new Coordinate(x, y);
		int w = 0;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (matrice[i][j] == true) {
					Coordinate E = new Coordinate(i, j);
					list.add(w, E);
					w++;
				}
			}
		}
		list.add(0, A);
		return list;
	}

	public void drawCircle(GraphicsPanel graphics, int Xc, int Yc, int raggio) {
		graphics.drawOval(Xc - raggio, Yc - raggio, 2 * raggio, 2 * raggio, true);
	}

}
