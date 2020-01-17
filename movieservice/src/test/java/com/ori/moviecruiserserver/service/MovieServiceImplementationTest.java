package com.ori.moviecruiserserver.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ori.moviecruiserserver.exception.MovieAlreadyExistsException;
import com.ori.moviecruiserserver.exception.MovieNotFoundException;
import com.ori.moviecruiserserver.model.Movie;
import com.ori.moviecruiserserver.repository.MovieRepository;

public class MovieServiceImplementationTest {
	
	@Mock
	MovieRepository movieRepository;
	
	private transient Movie movie;
	
	@InjectMocks
	private transient MovieServiceImplementation movieServiceImpl;

	transient Optional<Movie> options;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		movie = new Movie(1, "POC", "good movie", "www.abc.com", "2015-03-23", "John123");
		options = Optional.of(movie);
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull("JpaRepository creation failed: use @InjectMocks on movieServiceImpl", movie);
	}

	@Test
	public void testSaveMovieSuccess() throws MovieAlreadyExistsException {
		when(movieRepository.save(movie)).thenReturn(movie);
		final boolean flag = movieServiceImpl.saveMovie(movie);
		assertTrue("saving movie failed", flag);
		verify(movieRepository, times(1)).save(movie);
	}

	@Test(expected = MovieAlreadyExistsException.class)
	public void testSaveMovieFailure() throws MovieAlreadyExistsException {
		when(movieRepository.findById(1)).thenReturn(options);
		when(movieRepository.save(movie)).thenReturn(movie);
		final boolean flag = movieServiceImpl.saveMovie(movie);
		assertFalse("saving movie failed", flag);
		verify(movieRepository, times(1)).findById(movie.getId());
	}


	@Test
	public void testUpdateMovie() throws MovieNotFoundException {
		when(movieRepository.findById(1)).thenReturn(options);
		when(movieRepository.save(movie)).thenReturn(movie);
		movie.setComments("not so good movie");
		final Movie movie1 = movieServiceImpl.updateMovie(movie);
		assertEquals("updaing movie failed", "not so good movie", movie1.getComments());
		verify(movieRepository, times(1)).save(movie);
		verify(movieRepository, times(1)).findById(movie.getId());
	}


	
	@Test
	public void testDeleteMovieById() throws MovieNotFoundException {
		when(movieRepository.findById(1)).thenReturn(options);
		doNothing().when(movieRepository).delete(movie);
		movie.setComments("Movie is Good");
		final boolean flag = movieServiceImpl.deleteMovieById(1);
		assertTrue("deleting movie failed", flag);
		verify(movieRepository, times(1)).delete(movie);
		verify(movieRepository, times(1)).findById(movie.getId());
	}

	@Test
	public void testGetMovieById() throws MovieNotFoundException {
		when(movieRepository.findById(1)).thenReturn(options);
		final Movie movie1 = movieServiceImpl.getMovieById(1);
		assertEquals("Fetching Movie by ID Failed", movie, movie1);
		verify(movieRepository, times(1)).findById(movie.getId());
	}

	@Test
	public void testGetAllMovies() throws MovieNotFoundException {
		final List<Movie> movies = new ArrayList<>();
		movies.add(movie);
		when(movieRepository.findByUserId(movie.getUserId())).thenReturn(movies);
		final List<Movie> movies1 = movieServiceImpl.getMyMovies("John123");
		assertEquals(movies, movies1);
		verify(movieRepository, times(1)).findByUserId("John123");
	}


}
