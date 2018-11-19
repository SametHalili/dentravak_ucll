import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'
import { SandwichComponent } from './sandwich/sandwich.component'
import { ShowSandwichComponent } from './show-sandwich/show-sandwich.component';
import { NewSandwichComponent } from './new-sandwich/new-sandwich.component';
import { OrderComponent } from './order/order.component'

const routes: Routes = [
  { path: '', redirectTo: '/sandwiches', pathMatch: 'full' },
  { path: 'sandwiches', component: SandwichComponent },
  { path: 'sandwich/:id', component: ShowSandwichComponent },
  { path: 'newsandwich', component: NewSandwichComponent },
  { path: 'orders', component: OrderComponent }
]

@NgModule({
  declarations: [],
  imports: [ RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
