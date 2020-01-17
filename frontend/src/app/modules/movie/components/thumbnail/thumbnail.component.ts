import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar'

import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { MatDialog } from '@angular/material';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';


@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input()
  movie: Movie;

  @Input()
  useWatchlistApi: boolean;

  @Output()
  addMovie = new EventEmitter();

  @Output()
  deleteMovie = new EventEmitter();


  constructor(private movieService: MovieService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog) {
  }

  ngOnInit() {

  }

  addToWatchList() {

    this.addMovie.emit(this.movie);

  }

  deleteFromWatchList() {
    this.deleteMovie.emit(this.movie);
  }

  updateFromWatchList(actionType) {
    let dialogRef = this.dialog.open(MovieDialogComponent, {
      width: '400px',
      data: {
        obj: this.movie,
        actionType: actionType
      }
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
    });

  }

}
