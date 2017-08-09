package br.com.vitta.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.vitta.model.Square;
import br.com.vitta.model.Territory;

@Service("territoriesService")
public class TerritoriesServiceImpl implements TerritoriesService {

	private static final AtomicLong count = new AtomicLong();

	private static List<Territory> territories;
	private static List<Square> squares;
	
	static {
		territories = populaTerritories();
	}

	private static List<Territory> populaTerritories() {
		List<Territory> territories = new ArrayList<Territory>();
		Square square = new Square();
		List<Square> squares = new ArrayList<Square>();

		square.setX(1);
		square.setY(2);

		squares.add(square);
		territories.add(
				new Territory(count.incrementAndGet(), "A", "{ x: 0, y: 0 }", "{ x: 50, y: 50 }", 2500, 0, squares));

		return territories;
	}

	@Override
	public Territory findById(long id) {
		for (Territory territory : territories) {
			if (territory.getId() == id) {
				return territory;
			}
		}
		return null;
	}

	@Override
	public Territory findByIdPainted(long id, boolean paint) {
		for (Territory territory : territories) {
			squares.add(new Square(1, 2, false));
			Square square = new Square();
			if (territory.getId() == id && territory.getPainted_square().contains(square.isPainted())) {
				return territory;
			}
		}
		return null;
	}

	@Override
	public Territory findByName(String name) {
		for (Territory territory : territories) {
			if (territory.getName() == name) {
				return territory;
			}
		}
		return null;
	}

	@Override
	public Territory findByArea(long area) {
		for (Territory territory : territories) {
			if (territory.getArea() == area) {
				return territory;
			}
		}
		return null;
	}

	@Override
	public void insertTerritorires(Territory territory) {
		territory.setId(count.incrementAndGet());
		String end = territory.getEnd();
		String[] areaarray = end.replace("{", "").replace("}", "").replaceAll(" ", "").split(",");
		long calcula = Integer.parseInt(areaarray[0].substring(2, areaarray[0].length()))
				* Integer.parseInt(areaarray[1].substring(2, areaarray[1].length()));
		territory.setArea(calcula);
		List<Square> squares = new ArrayList<Square>();
		squares.add(new Square(1, 2, false));
		squares.add(new Square(2, 3, false));
		territories.add(territory);

	}

	@Override
	public void updateTerritories(Territory territory) {
		int index = territories.indexOf(territory);
		territories.set(index, territory);

	}

	@Override
	public void deleteTerritoriesById(long id) {
		for (Iterator<Territory> iterator = territories.iterator(); iterator.hasNext();) {
			Territory territory = iterator.next();
			if (territory.getId() == id) {
				iterator.remove();
			}
		}

	}

	@Override
	public List<Territory> findAllTerritories() {
		return territories;
	}

	@Override
	public void deleteAllTerritories() {
		territories.clear();

	}

	@Override
	public boolean isTerritoriesExist(Territory territory) {
		if (findByName(territory.getName()) != null) {

			return true;
		}
		if (findByArea(territory.getArea()) != null) {

			return true;
		}
		return false;
	}

	@Override
	public Square findBySquares(long x, long y) {
		for (Square square : squares) {
			if (square.getX() == x && square.getY() == y) {
				return square;
			}
		}
		return null;
	}

	@Override
	public void paintedSquare(Square square) {
		int index = squares.indexOf(square);
		squares.set(index, square);

	}

	@Override
	public Square findByXY(long x, long y) {
		for (Square square : squares) {
			if (square.getX() == x && square.getY() == y) {
				return square;
			}
		}
		return null;
	}

	@Override
	public boolean isSquaresExist(Square square) {
		if (findByXY(square.getX(), square.getY()) != null) {

			return true;
		}
		return false;
	}
}
