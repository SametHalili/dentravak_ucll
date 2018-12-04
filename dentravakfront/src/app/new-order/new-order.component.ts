import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { SandwichService } from '../sandwich.service';
import { OrderService } from '../order.service';
import { Sandwich } from '../sandwich';
import { Order } from '../order';

@Component({
  selector: 'app-new-order',
  templateUrl: './new-order.component.html',
  styleUrls: ['./new-order.component.css']
})
export class NewOrderComponent implements OnInit {
  order = new Order();
  sandwich = new Sandwich();

  breadTypes = ['BOTERHAMMEKES',
            'TURKS_BROOD',
            'WRAP'];

  constructor(
    private route: ActivatedRoute,
    private sandwichService: SandwichService,
    private orderService: OrderService,
    private location: Location
  ) { }

  ngOnInit() {
    this.getSandwich();
  }

  getSandwich(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.sandwichService.getSandwich(id)
      .subscribe(sandwich => this.sandwich = sandwich);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.order.sandwichId = this.sandwich.id;
    this.order.price = this.sandwich.price;
    this.orderService.addOrder(this.order)
      .subscribe(() => this.goBack());
  }

}
