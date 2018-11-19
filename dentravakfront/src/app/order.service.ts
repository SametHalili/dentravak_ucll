import { Injectable } from '@angular/core';
import { Order } from './order'
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
export class OrderService {
  private url = 'http://localhost:8080/orders'

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {

  }

  /* GET orders */
  getOrders (): Observable<Order[]> {
    const url = `${this.url}`;
    return this.http.get<Order[]>(url)
      .pipe(
        catchError(this.handleError('getOrders', []))
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
