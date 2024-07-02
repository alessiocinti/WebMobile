// itinerari.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Itinerario } from '../interface/itinerari.model';

@Injectable({
  providedIn: 'root'
})
export class ItinerariService {

  private baseUrl = 'http://localhost:8080'; // Sostituisci con il tuo URL del backend

  constructor(private http: HttpClient) { }

  // Metodo per ottenere tutti gli itinerari
  getItinerari(): Observable<Itinerario[]> {
    return this.http.get<Itinerario[]>(`${this.baseUrl}/itinerari`);
  }

  // Metodo per creare un nuovo itinerario
  createItinerario(itinerario: Itinerario): Observable<any> {
    return this.http.post(`${this.baseUrl}/itinerari`, itinerario);
  }

  // Metodo per eliminare un itinerario dato l'id
  deleteItinerario(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/itinerari/${id}`);
  }

  // Metodo per ottenere un itinerario dato l'id
  getItinerarioById(id: string): Observable<Itinerario> {
    return this.http.get<Itinerario>(`${this.baseUrl}/itinerari/${id}`);
  }
}
