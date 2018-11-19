import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewSandwichComponent } from './new-sandwich.component';

describe('NewSandwichComponent', () => {
  let component: NewSandwichComponent;
  let fixture: ComponentFixture<NewSandwichComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewSandwichComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewSandwichComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
