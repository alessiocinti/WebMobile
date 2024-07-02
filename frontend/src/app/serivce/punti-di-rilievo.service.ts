import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PuntoDiRilievo } from '../interface/punto-di-rilievo.model';

@Injectable({
  providedIn: 'root'
})
export class PuntiDiRilievoService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getPuntiDiRilievo(): Observable<PuntoDiRilievo[]> {
    return this.http.get<PuntoDiRilievo[]>(`${this.baseUrl}/puntiDiRilievo`);
  }

  createPuntoDiRilievo(puntoDiRilievo: PuntoDiRilievo): Observable<any> {
    return this.http.post(`${this.baseUrl}/puntoDiRilievo`, puntoDiRilievo);
  }

  deletePuntoDiRilievo(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/puntoDiRilievo/${id}`);
  }

  getPuntoDiRilievoById(id: string): Observable<PuntoDiRilievo> {
    return this.http.get<PuntoDiRilievo>(`${this.baseUrl}/puntoDiRilievo?id=${id}`);
  }

  getPuntiDiRilievoByComune(comune: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/puntiDiRilievo?comune=${comune}`);
  }
}
