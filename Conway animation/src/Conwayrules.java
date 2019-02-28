import java.util.ArrayList;

public class Conwayrules  implements RuleMove   {
	protected ArrayList<Coordinate> coord;
	protected int x;
	protected int y;

	
	public Conwayrules( ArrayList<Coordinate> coords) {
		coord = coords;
		if (coord.size() != 0) {
			x = coord.get(0).getX();
			y = coord.get(0).getY();
		}

			
	}
	
	public void setCoord(ArrayList<Coordinate> coords){
		coord=coords;
	}
	
	public boolean rules(boolean matrice[][], int i, int j) {
		int vivo = 0;
		boolean newMatriceval = true;
		if (i - 1 >= 0 && j - 1 >= 0) {
			if (matrice[i - 1][j - 1] == true)
				vivo++;
		}
		if (i - 1 >= 0) {
			if (matrice[i - 1][j] == true)
				vivo++;
		}
		if (i - 1 >= 0 && j + 1 < y) {
			if (matrice[i - 1][j + 1] == true)
				vivo++;
		}
		if (j - 1 >= 0) {
			if (matrice[i][j - 1] == true)
				vivo++;
		}
		if (j + 1 < y) {
			if (matrice[i][j + 1] == true)
				vivo++;
		}
		if (i + 1 < x && j - 1 >= 0) {
			if (matrice[i + 1][j - 1] == true)
				vivo++;
		}
		if (i + 1 < x) {
			if (matrice[i + 1][j] == true)
				vivo++;
		}
		if (i + 1 < x && j + 1 < y) {
			if (matrice[i + 1][j + 1] == true)
				vivo++;
		}

		if (matrice[i][j] == false && vivo == 3)
			newMatriceval = true;
		if (matrice[i][j] == false && vivo != 3)
			newMatriceval = false;
		if (matrice[i][j] == true && (vivo == 3 || vivo == 2))
			newMatriceval = true;
		if (matrice[i][j] == true && (vivo != 3 && vivo != 2))
			newMatriceval = false;
		return newMatriceval;
	}

	public boolean[][] rulesMat() {
		boolean newmatrix[][] = new boolean[x][y];
		boolean matrix[][] = toMatrix(coord);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				newmatrix[i][j] = rules(matrix, i, j);
			}
		}
		return newmatrix;
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
	
	
	
}
