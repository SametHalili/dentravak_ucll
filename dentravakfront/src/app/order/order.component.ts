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
  orders: Order[];

  constructor(private orderService: OrderService) { }

  ngOnInit() {
    this.getOrders();
  }

  getOrders(): void {
    timer(0, 2500)
      .subscribe(() => this.orderService.getOrders()
        .subscribe(
          orders => this.orders = orders,
          err => console.error(err),
          () => console.log('done loading json')));

  }

}
