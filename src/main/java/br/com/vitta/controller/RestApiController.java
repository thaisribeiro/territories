package br.com.vitta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vitta.model.Square;
import br.com.vitta.model.Territory;
import br.com.vitta.service.TerritoriesService;

@RestController
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	TerritoriesService territoriesService;

	/***
	 * Cria um novo território
	 * 
	 * @param terroritory
	 * @param ucBuilder
	 * @return
	 */
	@RequestMapping(value = "/territories/", method = RequestMethod.POST)
	public ResponseEntity<?> createTerritory(@RequestBody Territory territory, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Territories : {}", territory);
		if (territoriesService.isTerritoriesExist(territory)) {

			return new ResponseEntity(new Error("This new territory overlays another territory."),
					HttpStatus.NO_CONTENT);

		}

		territoriesService.insertTerritorires(territory);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/territories/{id}").buildAndExpand(territory.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);

	}

	/***
	 * Lista todos os territórios
	 * 
	 * @return
	 */
	@RequestMapping(value = "/territories/", method = RequestMethod.GET)
	public ResponseEntity<List<Territory>> listAllTerritories() {
		List<Territory> territories = territoriesService.findAllTerritories();
		if (territories.isEmpty()) {

			return new ResponseEntity(new Error("This territory was not found."), HttpStatus.NOT_FOUND);
		} else {

		}
		return new ResponseEntity<List<Territory>>(territories, HttpStatus.OK);
	}

	/***
	 * Busca um território por id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/territories/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTerritory(@PathVariable("id") long id) {
		logger.info("Fetching Territory with id {}", id);
		Territory territory = territoriesService.findById(id);
		if (territory == null) {
			logger.error("Territory with id {} not found.", id);
			return new ResponseEntity(new Error("This territory was not found."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Territory>(territory, HttpStatus.OK);
	}

	/***
	 * Deleta um território por id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/territories/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		Territory territory = territoriesService.findById(id);
		if (territory == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new Error("This territory was not found."), HttpStatus.NOT_FOUND);
		}
		territoriesService.deleteTerritoriesById(id);
		return new ResponseEntity<Territory>(HttpStatus.NO_CONTENT);
	}

	/***
	 * Lista a area
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	@RequestMapping(value = "/squares/{x}/{y}", method = RequestMethod.GET)
	public ResponseEntity<?> getSquare(@PathVariable("x") long x, @PathVariable("y") long y) {

		Square square = territoriesService.findBySquares(x, y);
		if (square == null) {
			return new ResponseEntity(new Error("This square does not belong to any territory"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Square>(square, HttpStatus.OK);
	}

	/***
	 * Pinta a área
	 * 
	 * @param x
	 * @param y
	 * @param square
	 * @return
	 */
	@RequestMapping(value = "/squares/{x}/{y}/paint", method = RequestMethod.PATCH)
	public ResponseEntity<?> updateUser(@PathVariable("x") long x, @PathVariable("x") long y,
			@RequestBody Square square) {

		Square currentSquare = territoriesService.findByXY(x, y);

		if (currentSquare == null) {

			return new ResponseEntity(new Error("This square does not belong to any territory"), HttpStatus.NOT_FOUND);
		}
		currentSquare.setPainted(true);
		territoriesService.paintedSquare(currentSquare);

		return new ResponseEntity<Square>(currentSquare, HttpStatus.OK);
	}

	/***
	 * Busca um território por id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/territories/{id}?withpainted={bool}", method = RequestMethod.GET)
	public ResponseEntity<?> getTerritoryPainted(@PathVariable("id") long id, @PathVariable("bool") boolean bool) {

		Territory territory = territoriesService.findByIdPainted(id, bool);
		if (territory == null) {

			return new ResponseEntity(new Error("This territory was not found."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Territory>(territory, HttpStatus.OK);
	}

}
