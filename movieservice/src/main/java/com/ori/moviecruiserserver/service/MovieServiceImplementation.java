package com.ori.moviecruiserserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ori.moviecruiserserver.exception.MovieAlreadyExistsException;
import com.ori.moviecruiserserver.exception.MovieNotFoundException;
import com.ori.moviecruiserserver.model.Movie;
import com.ori.moviecruiserserver.repository.MovieRepository;

@Service
public class MovieServiceImplementation implements MovieService {

	@Autowired
	private MovieRepository repository;
	
	public MovieServiceImplementation() {
	}
	

	@Override
	public boolean saveMovie(Movie movie) throws MovieAlreadyExistsException {
		
		final Optional<Movie> object = repository.findById(movie.getId());
		if(object.isPresent()) {
			throw new MovieAlreadyExistsException("Could Not Save Movie, Movie Already.");
		}
		repository.save(movie);
		return true;
	}

	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		final Movie updateMovie=repository.findById(movie.getId()).orElse(null);
		if (updateMovie == null) {
			throw new MovieNotFoundException("Could not update. Movie not found");
		}
		updateMovie.setComments(movie.getComments());
		repository.save(updateMovie);
		return updateMovie;

	}


	@Override
	public boolean deleteMovieById(int id) throws MovieNotFoundException {
		final Movie movie = repository.findById(id).get();
		if(movie == null) {
			throw new MovieNotFoundException("Movie Not Found.");
		}
		
		repository.delete(movie);
		return true;
	}

	@Override
	public Movie getMovieById(int id) throws MovieNotFoundException {
		final Movie movie = repository.findById(id).get();
		if(movie == null) {
			throw new MovieNotFoundException("Movie Not Found.");
		}
		return movie;
	}

	@Override
	public List<Movie> getMyMovies(String userId) {
		return repository.findByUserId(userId);
	}

}
