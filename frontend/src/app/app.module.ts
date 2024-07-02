import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ContenutiComponent } from './componenti/contenuti/contenuti.component';
import { ItinerariComponent } from './componenti/itinerari/itinerari.component';
import { PuntiDiRilievoComponent } from './componenti/punti-di-rilievo/punti-di-rilievo.component';
import { ContestsComponent } from './componenti/contests/contests.component';
import { DashboardComponent } from './componenti/dashboard/dashboard.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { ComuniComponent } from './componenti/comuni/comuni.component';
import { MatFormFieldModule } from '@angular/material/form-field';



import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { MatListModule } from '@angular/material/list';
import { ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms'; 


@NgModule({
  declarations: [
    AppComponent,
    ContenutiComponent,
    ItinerariComponent,
    PuntiDiRilievoComponent,
    ContestsComponent,
    DashboardComponent,
    ComuniComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatToolbarModule,
    MatButtonModule,
    MatInputModule,
    MatCardModule,
    MatSelectModule,
    MatFormFieldModule,
    MatListModule,
    ReactiveFormsModule,
    MatIconModule,
    FormsModule
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
