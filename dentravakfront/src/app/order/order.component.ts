import { Component, OnInit } from '@angular/core';
import { OrderService } from '../order.service'
import { Order } from '../order'
import { timer } from 'rxjs'

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  lunches: Order[];

  constructor(private lunchService: OrderService) { }

  ngOnInit() {
    this.getLunches();
  }

  getLunches(): void {
    timer(0, 2500)
      .subscribe(() => this.lunchService.getLunches()
        .subscribe(
          lunches => this.lunches = lunches,
          err => console.error(err),
          () => console.log('done loading json')));
  }

}
