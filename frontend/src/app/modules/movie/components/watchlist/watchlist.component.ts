import { Component, OnInit } from '@angular/core';


import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';

@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  movies: Array<Movie>;
  useWatchlistApi = true;

  constructor(private movieService: MovieService) {
    this.movies = [];
  }

  ngOnInit() {
    this.movieService.getWatchlistMovies().subscribe((movies) => {
      this.movies.push(...movies);
    });
  }

}
