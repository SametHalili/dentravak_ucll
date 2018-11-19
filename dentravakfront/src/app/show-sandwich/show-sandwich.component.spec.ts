import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowSandwichComponent } from './show-sandwich.component';

describe('ShowSandwichComponent', () => {
  let component: ShowSandwichComponent;
  let fixture: ComponentFixture<ShowSandwichComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowSandwichComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowSandwichComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
