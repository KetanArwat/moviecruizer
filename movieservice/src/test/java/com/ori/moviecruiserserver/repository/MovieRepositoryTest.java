package com.ori.moviecruiserserver.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ori.moviecruiserserver.model.Movie;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MovieRepositoryTest {

	@Autowired
	private transient MovieRepository movieRepository;

	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	@Test
	public void testSaveMovie() throws Exception {
		movieRepository.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23", "John123"));
		Movie sampleMovie = movieRepository.findByTitle("superman");
		final Movie movie = movieRepository.getOne(sampleMovie.getId());
		assertThat(movie.getId()).isEqualTo(sampleMovie.getId());
	}
	
	@Test
	public void testUpdateMovie() throws Exception {
		movieRepository.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23", "John123"));
		Movie sampleMovie = movieRepository.findByTitle("superman");
		final Movie movie = movieRepository.getOne(sampleMovie.getId());
		assertEquals("superman", sampleMovie.getTitle());
		movie.setComments("Hi");
		movieRepository.save(movie);
		final Movie tempMovie = movieRepository.getOne(sampleMovie.getId());
		assertEquals("Hi", tempMovie.getComments());
	}
	
	@Test
	public void testDeleteMovie() throws Exception {
		movieRepository.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23", "John123"));
		Movie sampleMovie = movieRepository.findByTitle("superman");
		final Movie movie = movieRepository.getOne(sampleMovie.getId());
		assertEquals("superman", sampleMovie.getTitle());
		movieRepository.delete(movie);
		
		assertEquals(Optional.empty(), movieRepository.findById(sampleMovie.getId()));
	}
	
	@Test
	public void testGetMovie() throws Exception {
		movieRepository.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23", "John123"));
		Movie sampleMovie = movieRepository.findByTitle("superman");
		final Movie movie = movieRepository.getOne(sampleMovie.getId());
		assertEquals(sampleMovie.getId(), movie.getId());
	}
	
	@Test
	public void testGetMyMovies() throws Exception {
		movieRepository.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23", "John123"));
		movieRepository.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23", "John123"));
		final List<Movie> movies = movieRepository.findAll();
		assertEquals("good movie", movies.get(0).getComments());
	}

}
