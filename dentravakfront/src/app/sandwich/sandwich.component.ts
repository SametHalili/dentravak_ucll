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

  constructor(private lunchService: SandwichService) { }

  ngOnInit() {
    this.getLunches();
  }

  getLunches(): void {
    timer(0, 2500)
      .subscribe(() => this.lunchService.getSandwiches()
        .subscribe(
          sandwiches => this.sandwiches = sandwiches,
          err => console.error(err),
          () => console.log('done loading json')));
  }

}
