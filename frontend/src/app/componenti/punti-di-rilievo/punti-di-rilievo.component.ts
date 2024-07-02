import { Component, OnInit } from '@angular/core';
import { PuntiDiRilievoService } from '../../serivce/punti-di-rilievo.service';
import { PuntoDiRilievo } from '../../interface/punto-di-rilievo.model';
import { ComuniService } from '../../serivce/comuni.service';
import { Comune } from '../../interface/comune.model';


@Component({
  selector: 'app-punti-di-rilievo',
  templateUrl: './punti-di-rilievo.component.html',
  styleUrls: ['./punti-di-rilievo.component.css']
})
export class PuntiDiRilievoComponent implements OnInit {
  puntiDiRilievo: PuntoDiRilievo[] = [];
  comuni: Comune[] = [];
  puntoDiRilievoId: string = '';
  puntoDiRilievoName: string = '';
  comuneSelezionato: string = '';
  deletePuntoDiRilievoId: string = '';
  getPuntoDiRilievoId: string = '';
  puntoDiRilievoResult: PuntoDiRilievo | null = null;

  constructor(
    private puntiDiRilievoService: PuntiDiRilievoService,
    private comuniService: ComuniService
  ) { }

  ngOnInit(): void {
    this.loadComuni();
  }

  loadComuni(): void {
    this.comuniService.getComuni().subscribe((comuni: Comune[]) => {
      this.comuni = comuni;
    });
  }

  getPuntiDiRilievo(): void {
    this.puntiDiRilievoService.getPuntiDiRilievo().subscribe((puntiDiRilievo: PuntoDiRilievo[]) => {
      this.puntiDiRilievo = puntiDiRilievo;
    });
  }

  createPuntoDiRilievo(): void {
    const puntoDiRilievo: PuntoDiRilievo = {
      id: this.puntoDiRilievoId,
      name: this.puntoDiRilievoName,
      comuneDiRiferimento: this.comuneSelezionato
    };
    this.puntiDiRilievoService.createPuntoDiRilievo(puntoDiRilievo).subscribe(response => {
      console.log(response);
      this.resetForm();
    });
  }

  deletePuntoDiRilievo(): void {
    this.puntiDiRilievoService.deletePuntoDiRilievo(this.deletePuntoDiRilievoId).subscribe(response => {
      console.log(response);
    });
  }

  getPuntoDiRilievoById(): void {
    this.puntiDiRilievoService.getPuntoDiRilievoById(this.getPuntoDiRilievoId).subscribe((puntoDiRilievo: PuntoDiRilievo) => {
      this.puntoDiRilievoResult = puntoDiRilievo;
    });
  }

  private resetForm(): void {
    this.puntoDiRilievoId = '';
    this.puntoDiRilievoName = '';
    this.comuneSelezionato = '';
  }
}