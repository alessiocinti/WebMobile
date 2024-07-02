import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ContenutiService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getPuntiDiRilievoByComune(comune: string) {
    return this.http.get<any[]>(`${this.baseUrl}/puntiDiRilievo?comune=${comune}`);
  }

  createContenuto(contenuto: any) {
    return this.http.post(`${this.baseUrl}/contenuto`, contenuto);
  }

  deleteContenuto(contenutoId: string) {
    return this.http.delete(`${this.baseUrl}/contenuto/${contenutoId}`);
  }

  getContenutoById(contenutoId: string) {
    return this.http.get<any>(`${this.baseUrl}/contenuto?id=${contenutoId}`);
  }
}
