package br.com.vitta.service;

import java.util.List;

import br.com.vitta.model.Square;
import br.com.vitta.model.Territory;

public interface TerritoriesService {

	Territory findById(long id);

	Territory findByName(String name);

	void insertTerritorires(Territory territory);

	void updateTerritories(Territory territory);

	void deleteTerritoriesById(long id);

	List<Territory> findAllTerritories();

	void deleteAllTerritories();

	boolean isTerritoriesExist(Territory territory);

	Territory findByArea(long area);

	Square findBySquares(long x, long y);

	void paintedSquare(Square square);

	Square findByXY(long x, long y);

	boolean isSquaresExist(Square square);

	Territory findByIdPainted(long id, boolean paint);

	


}
