package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll(){
		List<Game> games = gameRepository.findAll();
		return games.stream().map(x -> new GameMinDTO(x)).toList();
		// TODO: handle exception
	}
	
	@Transactional(readOnly = true) // make sure that no writing operation
	public GameDTO findById(Long id) {
		Game game = gameRepository.findById(id).get();
		return new GameDTO(game);
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long listId) {
		List<GameMinProjection> game = gameRepository.searchByList(listId);
		return game.stream().map(x -> new GameMinDTO(x)).toList();
	}
}
