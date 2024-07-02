import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Comune } from '../interface/comune.model';

@Injectable({
  providedIn: 'root'
})
export class ComuniService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getComuni(): Observable<Comune[]> {
    return this.http.get<Comune[]>(`${this.baseUrl}/comuni`);
  }

  createComune(comune: Comune): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/comune`, comune);
  }

  deleteComune(comuneId: string): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/comune/${comuneId}`);
  }

  getComuneById(comuneId: string): Observable<Comune> {
    return this.http.get<Comune>(`${this.baseUrl}/comune?id=${comuneId}`);
  }
}
