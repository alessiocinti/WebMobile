// itinerari.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

const baseUrl = 'http://localhost:8080';

@Component({
  selector: 'app-itinerari',
  templateUrl: './itinerari.component.html',
  styleUrls: ['./itinerari.component.css']
})
export class ItinerariComponent implements OnInit {
  itinerario: any = {};
  selectedComune: string = '';
  selectedPunti: string[] = [];
  comuni: any[] = [];
  puntiDiRilievo: any[] = [];
  deleteId: string = '';
  searchId: string = '';
  itinerarioResult: any = {};

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchComuni();
  }

  fetchComuni(): void {
    this.http.get<any[]>(`${baseUrl}/comuni`)
      .subscribe(data => {
        this.comuni = data;
      });
  }

  aggiornaPunti(): void {
    const comuneSelezionato = this.selectedComune;
    this.http.get<any[]>(`${baseUrl}/puntiDiRilievo`)
      .subscribe(data => {
        this.puntiDiRilievo = data.filter(punto => punto.comuneDiRiferimento === comuneSelezionato);
      });
  }

  createItinerario(): void {
    this.itinerario.comuneDiRiferimento = this.selectedComune;
    this.itinerario.puntiSelezionati = this.selectedPunti.join(', ');
    this.http.post(`${baseUrl}/itinerario`, this.itinerario)
      .subscribe(() => {
        console.log('Itinerario aggiunto con successo');
      }, error => {
        console.error('Errore durante l\'aggiunta dell\'itinerario:', error);
      });
  }

  deleteItinerario(): void {
    this.http.delete(`${baseUrl}/itinerario/${this.deleteId}`)
      .subscribe(() => {
        console.log('Itinerario eliminato con successo');
      });
  }

  getItinerarioById(): void {
    this.http.get<any>(`${baseUrl}/itinerario?id=${this.searchId}`)
      .subscribe(data => {
        this.itinerarioResult = data;
      });
  }
}
