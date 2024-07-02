import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contest } from '../interface/contest.model';

@Injectable({
  providedIn: 'root'
})
export class ContestService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getComuni(): Observable<any> {
    return this.http.get(`${this.baseUrl}/comuni`);
  }

  createContest(contest: Contest): Observable<any> {
    return this.http.post(`${this.baseUrl}/contest`, contest, { responseType: 'text' });
  }

  deleteContest(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/contest/${id}`, { responseType: 'text' });
  }

  getContestById(id: string): Observable<Contest> {
    return this.http.get<Contest>(`${this.baseUrl}/contest?id=${id}`);
  }
}
