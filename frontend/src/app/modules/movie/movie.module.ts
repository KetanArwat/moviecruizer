import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';


import { SharedModule } from '../shared/shared.module';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { MovieService } from './movie.service';
import { ContainerComponent } from './components/container/container.component';
import { MovieRouterModule } from './movie.router.module';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import { SearchComponent } from './components/search/search.component';
import { TokenInterceptorService } from './token-interceptor.service';


@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MovieRouterModule,
    SharedModule
  ],
  declarations: [
    ThumbnailComponent,
    ContainerComponent,
    WatchlistComponent,
    TmdbContainerComponent,
    MovieDialogComponent,
    SearchComponent
  ],

  entryComponents: [
    MovieDialogComponent
  ]
  ,
  exports: [
    MovieRouterModule,
  ],
  providers: [
    MovieService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }

  ]
})
export class MovieModule { }
