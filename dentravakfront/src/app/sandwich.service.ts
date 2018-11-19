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

  getSandwich (id: string): Observable<Sandwich> {
    const url = `${this.url}/${id}`;
    return this.http.get<Sandwich>(url)
      .pipe(
        catchError(this.handleError<Sandwich>(`sandwich id= ${id}`))
      );
  }

  addSandwich(sandwich: Sandwich): Observable<Sandwich> {
    const url = `${this.url}`;
    return this.http.post<Sandwich>(url, sandwich, httpOptions)
      .pipe(
        catchError(this.handleError('addSandwiches', sandwich))
      );
  }

  updateSandwich (sandwich: Sandwich): Observable<any> {
    const url = `${this.url}/${sandwich.id}`
    return this.http.put(url, sandwich, httpOptions)
      .pipe(
        catchError(this.handleError<any>('updateSandwich'))
      );
  }

  deleteSandwich (id: string): Observable<any> {
    const url = `${this.url}/${id}`;
    return this.http.delete<any>(url)
      .pipe(
        catchError(this.handleError<any>(`sandwich id= ${id}`))
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
