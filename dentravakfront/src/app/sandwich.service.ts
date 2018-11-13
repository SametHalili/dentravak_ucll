import { Injectable } from '@angular/core';
import { Sandwich } from './sandwich'
import { MessageService } from './message.service'
import { Observable, of } from 'rxjs'
import { catchError, map, tap } from 'rxjs/operators'
import { HttpClient, HttpHeaders } from '@angular/common/http'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class SandwichService {
  private url = 'http://localhost:8080/sandwiches'

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {

  }

  /* GET lunches */
  getSandwiches (): Observable<Sandwich[]> {
    const url = `${this.url}`;
    return this.http.get<Sandwich[]>(url)
    .pipe(
      catchError(this.handleError('getSandwiches', []))
    );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
  // dit was alleen voor tests, negeren aub
  private log(message: string) {
    this.messageService.add('SandwichService: ' + message);
  }
}
