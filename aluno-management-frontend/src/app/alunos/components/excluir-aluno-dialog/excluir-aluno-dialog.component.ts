import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-excluir-aluno-dialog',
  templateUrl: './excluir-aluno-dialog.component.html'
})
export class ExcluirAlunoDialogComponent {

  constructor(
    private dialogRef: MatDialogRef<ExcluirAlunoDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  fechar(confirmar: boolean) {
    this.dialogRef.close(confirmar);
  }
}