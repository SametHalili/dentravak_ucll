import { Component, OnInit } from '@angular/core';
import { SandwichService } from '../sandwich.service'
import { Sandwich } from '../sandwich'
import { timer } from 'rxjs'

@Component({
  selector: 'app-order',
  templateUrl: './sandwich.component.html',
  styleUrls: ['./sandwich.component.css']
})
export class SandwichComponent implements OnInit {
  sandwiches: Sandwich[];

  constructor(private sandwichService: SandwichService) { }

  ngOnInit() {
    this.getSandwiches();
  }

  getSandwiches(): void {
    timer(0, 2500)
      .subscribe(() => this.sandwichService.getSandwiches()
        .subscribe(
          sandwiches => this.sandwiches = sandwiches,
          err => console.error(err),
          () => console.log('done loading json')));
  }

  deleteSandwich(id: string): void {
    this.sandwichService.deleteSandwich(id)
      .subscribe(() => console.log("deleted"));
  }
}
