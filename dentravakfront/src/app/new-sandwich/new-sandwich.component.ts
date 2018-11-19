import { Component, OnInit, Input } from '@angular/core';
import { Location } from '@angular/common'
import { ActivatedRoute } from '@angular/router'
import { SandwichService } from '../sandwich.service'
import { Sandwich } from '../sandwich';

@Component({
  selector: 'app-new-sandwich',
  templateUrl: './new-sandwich.component.html',
  styleUrls: ['./new-sandwich.component.css']
})
export class NewSandwichComponent implements OnInit {
  sandwich: Sandwich;
  submitted = false;

  constructor(
    private route: ActivatedRoute,
    private sandwichService: SandwichService,
    private location: Location
  ) { }

  ngOnInit() {
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.sandwichService.addSandwich(this.sandwich)
      .subscribe(() => this.goBack());
  }

}
