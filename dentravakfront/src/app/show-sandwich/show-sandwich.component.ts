import { Component, OnInit, Input } from '@angular/core';
import { Location } from '@angular/common'
import { ActivatedRoute } from '@angular/router'
import { SandwichService } from '../sandwich.service'
import { Sandwich } from '../sandwich';


@Component({
  selector: 'app-show-sandwich',
  templateUrl: './show-sandwich.component.html',
  styleUrls: ['./show-sandwich.component.css']
})
export class ShowSandwichComponent implements OnInit {
  @Input() sandwich: Sandwich;

  constructor(
    private route: ActivatedRoute,
    private sandwichService: SandwichService,
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
    this.sandwichService.updateSandwich(this.sandwich)
      .subscribe(() => this.goBack());
  }

}
