import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './componenti/dashboard/dashboard.component';
import { ComuniComponent } from './componenti/comuni/comuni.component';
import { ContenutiComponent } from './componenti/contenuti/contenuti.component';
import { ContestsComponent } from './componenti/contests/contests.component';
import { ItinerariComponent } from './componenti/itinerari/itinerari.component';
import { PuntiDiRilievoComponent } from './componenti/punti-di-rilievo/punti-di-rilievo.component';

const routes: Routes = [
  {path: '', component: DashboardComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'contenuti', component: ContenutiComponent},
  {path: 'contests', component: ContestsComponent},
  {path: 'itinerari', component: ItinerariComponent},
  {path: 'puntiDiRilievo', component: PuntiDiRilievoComponent},
  {path: 'comuni', component: ComuniComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
