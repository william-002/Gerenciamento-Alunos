import { Component, Input, OnChanges } from '@angular/core';

type StatusAluno = 'ATIVO' | 'INATIVO';

@Component({
  selector: 'app-status-badge',
  templateUrl: './status-badge.component.html',
  styleUrls: ['./status-badge.component.css']
})
export class StatusBadgeComponent implements OnChanges {
  /** Valor recebido: 'ATIVO' | 'INATIVO'  */
  @Input() status: string | null | undefined;

  /**customizar o rótulo exibido acima (default: 'Status') */
  @Input() label = 'Status';

  /** Valor seguro para exibição */
  statusAtual: StatusAluno = 'INATIVO';

  ngOnChanges(): void {
    const s = (this.status || '').toString().trim().toUpperCase();
    this.statusAtual = (s === 'ATIVO' || s === 'INATIVO') ? (s as StatusAluno) : 'INATIVO';
  }
}
