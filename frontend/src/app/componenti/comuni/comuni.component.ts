import { Component, OnInit } from '@angular/core';
import { ComuniService } from '../../serivce/comuni.service';
import { Comune } from '../../interface/comune.model';

@Component({
  selector: 'app-comuni',
  templateUrl: './comuni.component.html',
  styleUrls: ['./comuni.component.css']
})
export class ComuniComponent implements OnInit {
  comuni: Comune[] = [];
  newComune: Comune = { id: '', name: '', regione: '' };
  deleteComuneId: string = '';
  getComuneId: string = '';
  comuneResult: Comune | null = null;

  constructor(private comuniService: ComuniService) { }

  ngOnInit(): void {
    this.getComuni();
  }

  getComuni(): void {
    this.comuniService.getComuni().subscribe((data: Comune[]) => {
      this.comuni = data;
    });
  }

  createComune(): void {
    this.comuniService.createComune(this.newComune).subscribe((data: string) => {
      console.log(data);
      this.getComuni();
    });
  }

  deleteComune(): void {
    this.comuniService.deleteComune(this.deleteComuneId).subscribe((data: string) => {
      console.log(data);
      this.getComuni();
    });
  }

  getComuneById(): void {
    this.comuniService.getComuneById(this.getComuneId).subscribe((data: Comune) => {
      this.comuneResult = data;
    });
  }
}
