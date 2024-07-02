import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-contests',
  templateUrl: './contests.component.html',
  styleUrls: ['./contests.component.css']
})
export class ContestsComponent implements OnInit {
  contestId: string = '';
  contestTitolo: string = '';
  contestDescrizione: string = '';
  dataInizioContest: string = '';
  dataFineContest: string = '';
  selectedComune: string = '';
  deleteContestId: string = '';
  getContestId: string = '';
  contestResult: any;
  comuni: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.fetchComuni();
  }

  fetchComuni() {
    this.http.get<any[]>('http://localhost:8080/comuni')
      .subscribe(data => {
        this.comuni = data;
      }, error => {
        console.error('Error:', error);
      });
  }

  createContest() {
    const contest = {
      id: this.contestId,
      titoloContest: this.contestTitolo,
      descrizioneContest: this.contestDescrizione,
      dataInizioContest: this.dataInizioContest,
      dataFineContest: this.dataFineContest,
      comuneDiRiferimento: this.selectedComune
    };

    this.http.post('http://localhost:8080/contest', contest, { responseType: 'text' })
      .subscribe(data => {
        console.log(data);
      }, error => {
        console.error('Error:', error);
      });
  }

  deleteContest() {
    this.http.delete(`http://localhost:8080/contest/${this.deleteContestId}`, { responseType: 'text' })
      .subscribe(data => {
        console.log(data);
      }, error => {
        console.error('Error:', error);
      });
  }

  getContestById() {
    this.http.get<any>(`http://localhost:8080/contest?id=${this.getContestId}`)
      .subscribe(data => {
        this.contestResult = data;
      }, error => {
        console.error('Error:', error);
      });
  }

  isFormValid(): boolean {
    return this.contestId.trim() !== '' &&
           this.contestTitolo.trim() !== '' &&
           this.contestDescrizione.trim() !== '' &&
           this.dataInizioContest.trim() !== '' &&
           this.dataFineContest.trim() !== '' &&
           this.selectedComune.trim() !== '';
  }
}
