// contenuti.component.ts
import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-contenuti',
  templateUrl: './contenuti.component.html',
  styleUrls: ['./contenuti.component.css']
})
export class ContenutiComponent {
  
  baseUrl = 'http://localhost:8080';
  
  contenutoId: string = '';
  contenutoDesc: string = '';
  selectedComune: string = '';
  selectedPunto: string = '';
  deleteContenutoId: string = '';
  getContenutoId: string = '';
  contenutoResult: any;

  comuni: any[] = [];
  puntiDiRilievo: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getComuni();
  }

  getComuni() {
    this.http.get<any[]>(`${this.baseUrl}/comuni`)
      .subscribe(data => {
        this.comuni = data;
      }, error => {
        console.error('Error:', error);
      });
  }

  aggiornaPunti() {
    if (!this.selectedComune) {
      alert('Seleziona prima un comune.');
      return;
    }
    this.http.get<any[]>(`${this.baseUrl}/puntiDiRilievo`)
      .subscribe(data => {
        this.puntiDiRilievo = data.filter(punto => punto.comuneDiRiferimento === this.selectedComune);
      }, error => {
        console.error('Error:', error);
      });
  }

  createContenuto() {
    const contenuto = {
      id: this.contenutoId,
      desc: this.contenutoDesc,
      punto: this.selectedPunto
    };
    this.http.post(`${this.baseUrl}/contenuto`, contenuto)
      .subscribe(data => {
        console.log('Contenuto aggiunto con successo:', data);
      }, error => {
        console.error('Errore durante l\'aggiunta del contenuto:', error);
      });
  }





  deleteContenuto() {
    this.http.delete(`${this.baseUrl}/contenuto/${this.deleteContenutoId}`)
      .subscribe(data => {
        console.log('Contenuto eliminato con successo:', data);
      }, error => {
        console.error('Errore durante l\'eliminazione del contenuto:', error);
      });
  }

  getContenutoById() {
    this.http.get<any>(`${this.baseUrl}/contenuto?id=${this.getContenutoId}`)
      .subscribe(data => {
        this.contenutoResult = data;
      }, error => {
        console.error('Errore durante la ricerca del contenuto:', error);
      });
  }
}
