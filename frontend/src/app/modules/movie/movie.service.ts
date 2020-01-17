import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


import { Movie } from './movie';

@Injectable()
export class MovieService {

  tmdbKey: string;
  tmdbEndPoint: string;
  imagePrefix: string;
  watchlistEndPoint: string;
  springEndPoint: string;
  searchMoviesEndpoint: string;

  constructor(private http: HttpClient) {
    this.tmdbKey = 'api_key=6b6fc8ef1bdd50846b358cc9c92cf94b';
    this.tmdbEndPoint = 'https://api.themoviedb.org/3/movie/';
    this.imagePrefix = 'https://image.tmdb.org/t/p/w500';
    this.watchlistEndPoint = 'http://localhost:3000/watchlist';
    this.springEndPoint = 'http://localhost:8080/api/movie';
    this.searchMoviesEndpoint = 'https://api.themoviedb.org/3/search/movie?';
  }

  getMovies(type: string, page: number = 1): Observable<Array<Movie>> {
    const endpoint = this.tmdbEndPoint + type + '?' + this.tmdbKey + '&page=' + page;
    return this.http.get(endpoint).pipe(
      map(this.pickMovieResults),
      map(this.buildPosterPath.bind(this))
    );
  }

  pickMovieResults(response) {
    return response['results'];
  }

  buildPosterPath(movies): Array<Movie> {
    return movies.map(movie => {
      movie.poster_path = this.imagePrefix + movie.poster_path;
      return movie;
    });
  }

  addMovieToWatchlist(movie: Movie) {
    return this.http.post(this.springEndPoint, movie);
  }

  getWatchlistMovies(): Observable<Array<Movie>> {
    return this.http.get<Array<Movie>>(this.springEndPoint);
  }

  deleteFromWatchList(movie: Movie) {
    const url = this.springEndPoint + '/' + movie.id;
    return this.http.delete(url, { responseType: 'text' });
  }

  updateComments(movie) {
    const url = this.springEndPoint + '/' + movie.id;
    return this.http.put(url, movie);
  }

  searchMovies(searchKey): Observable<Array<Movie>> {
    if (searchKey.length > 0) {
      const url = this.searchMoviesEndpoint + this.tmdbKey
        + '&language=en-US&page=1&include_adult=false&query=' + searchKey;

      return this.http.get(url).pipe(
        map(this.pickMovieResults),
        map(this.buildPosterPath.bind(this))
      );
    }
  }
}
