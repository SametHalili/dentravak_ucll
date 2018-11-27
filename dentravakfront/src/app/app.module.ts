import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { SandwichComponent } from './sandwich/sandwich.component';
import { ShowSandwichComponent } from './show-sandwich/show-sandwich.component';
import { AppRoutingModule } from './app-routing.module';
import { NewSandwichComponent } from './new-sandwich/new-sandwich.component';
import { OrderComponent } from './order/order.component';
import { NewOrderComponent } from './new-order/new-order.component';

@NgModule({
  declarations: [
    AppComponent,
    SandwichComponent,
    ShowSandwichComponent,
    NewSandwichComponent,
    OrderComponent,
    NewOrderComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
