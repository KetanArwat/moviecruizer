package com.ori.moviecruiserserver.service;

import java.util.List;

import com.ori.moviecruiserserver.exception.MovieAlreadyExistsException;
import com.ori.moviecruiserserver.exception.MovieNotFoundException;
import com.ori.moviecruiserserver.model.Movie;

public interface MovieService {
	
	boolean saveMovie(Movie movie)throws MovieAlreadyExistsException;
	
	Movie updateMovie(Movie movie)throws MovieNotFoundException;
	
	boolean deleteMovieById(int id)throws MovieNotFoundException;
	
	Movie getMovieById(int id)throws MovieNotFoundException;
	
	List<Movie> getMyMovies(String userId);

}
