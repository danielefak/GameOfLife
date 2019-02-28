import java.io.File;
import java.util.ArrayList;

public class CoordMaker {
	protected ArrayList<Coordinate> coord;
	protected File current;
	protected int x;
	protected int y;
	
	public CoordMaker(File file) {
		current = file;
		coord = CoordinateIO.read("current.txt");
		if (coord.size() != 0) {
			x = coord.get(0).getX();
			y = coord.get(0).getY();
		}
	}

	public void clearCoord() {
		coord.clear();
		Coordinate empty = new Coordinate();
		empty.setX(0);
		empty.setY(0);
		coord.add(0, empty);
		x = coord.get(0).getX();
		y = coord.get(0).getY();
	}

	public void setCoord(ArrayList<Coordinate> old) {
		coord.clear();
		coord.addAll(old);
		x = coord.get(0).getX();
		y = coord.get(0).getY();
	}

	public void clearCurrent() {
		clearCoord();
		CoordinateIO.write(current, coord);
	}

	public void setCurrent(File file) {
		ArrayList<Coordinate> old = CoordinateIO.read("" + file.getName());
		clearCurrent();
		CoordinateIO.write(current, old);
		setCoord(old);
	}

	public void save(File file) {
		ArrayList<Coordinate> saveCurr = CoordinateIO.read("current.txt");
		CoordinateIO.write(file, saveCurr);
		setCurrent(file);
	}
	
}
