import { Component, OnInit, Inject } from '@angular/core';
import { MovieService } from '../../movie.service';
import { MatSnackBar, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Movie } from '../../movie';

@Component({
  selector: 'app-movie-dialog',
  templateUrl: './movie-dialog.component.html',
  styleUrls: ['./movie-dialog.component.css']
})
export class MovieDialogComponent implements OnInit {

  movie: Movie;
  comments: string;
  actionType: string;

  constructor(private movieService: MovieService,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<MovieDialogComponent>) {

    this.comments = data.obj.comments;
    this.movie = data.obj;
    this.actionType = data.actionType;
  }

  ngOnInit() {
    console.log('Data recieved by dialog component: ' + this.data);
  }

  closeDialog() {
    this.dialogRef.close();
  }

  updateComment() {
    this.movie.comments = this.comments;
    this.movieService.updateComments(this.movie).subscribe(movie => {

      this.snackBar.open('Movie comments updated successfully', '', {
        duration: 2000
      });
    })


  }
}
