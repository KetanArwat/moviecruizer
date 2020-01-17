import { Component, OnInit, Input } from '@angular/core';

import { Movie } from '../../movie';
import { MatSnackBar } from '@angular/material';
import { MovieService } from '../../movie.service';

@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input()
  movies: Array<Movie>;

  @Input()
  useWatchlistApi: boolean;

  constructor(private movieService: MovieService,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
  }

  addMovieToWatchlist(movie) {
    this.movieService.addMovieToWatchlist(movie).subscribe((movie) => {
      this.snackBar.open("Movie Added to your Watchlist.", '',
        { duration: 2000 });
    });
  }

  deleteMovieFromWatchlist(movie: Movie) {

    let title = movie.title;

    this.movieService.deleteFromWatchList(movie).subscribe((movie) => {

      let message = title + " deleted from your Watchlist.";
      for (var i = 0; i < this.movies.length; i++) {
        if (this.movies[i].title === title) {
          this.movies.splice(i, 1);
        }
      }

      this.snackBar.open(message, '',
        { duration: 2000 });
    });
  }
}
