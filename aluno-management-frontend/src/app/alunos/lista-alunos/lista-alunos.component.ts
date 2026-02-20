import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AlunoService } from '../../core/services/aluno.service';
import { MatDialog, MatPaginator, MatSnackBar, MatTableDataSource } from '@angular/material';
import { ExcluirAlunoDialogComponent } from '../components/excluir-aluno-dialog/excluir-aluno-dialog.component';

@Component({
  selector: 'app-lista-alunos',
  templateUrl: './lista-alunos.component.html',
  styleUrls: ['./lista-alunos.component.css']
})
export class ListaAlunosComponent implements AfterViewInit {

  alunos: any[] = [];


  displayedColumns: string[] = ['matricula', 'nome', 'status', 'acoes'];
  dataSource = new MatTableDataSource<any>(this.alunos);

  filtro: string = '';

  alundoIdParaExcluir: number | null = null;

  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private alunoService: AlunoService, private router: Router, private dialog: MatDialog, private snack: MatSnackBar) { }

  ngAfterViewInit(): void {
    this.paginator._intl.itemsPerPageLabel = "Alunos por página";
    this.alunoService.listar().subscribe((resposta: any) => {
      this.alunos = resposta.content;
      this.dataSource = new MatTableDataSource<any>(this.alunos);
      this.dataSource.paginator = this.paginator;


    });
  }


  editarAluno(id: number) {
    this.router.navigate(['/alunos/editar', id])
  }


  buscar() {
    this.alunoService.buscar(this.filtro).subscribe((resposta: any) => {
      this.dataSource.data = resposta.content;
    })
  }


  visualizarAluno(id: number) {
    this.router.navigate(['/alunos/visualizar', id])
  }


  excluirAluno(id: number) {
    this.alunoService.excluir(id).subscribe(() => {
      this.alunos = this.alunos.filter(aluno => aluno.id !== id);

      this.dataSource.data = this.alunos;               
      
      this.snack.open('Aluno excluído com sucesso.', 'OK', {
        duration: 3000,          // 3s
        horizontalPosition: 'right',
        verticalPosition: 'top'
      });
    }, () => {
      
      this.snack.open('Não foi possível excluir o aluno.', 'Fechar', {
        duration: 4000,
        horizontalPosition: 'right',
        verticalPosition: 'top'
      });
    });
  }



  abrirModalExclusao(aluno: any) {
    const ref = this.dialog.open(ExcluirAlunoDialogComponent, {
      width: '350px',
      data: { nome: aluno.nome } // aparecerá no modal
    });

    ref.afterClosed().subscribe(confirmado => {
      if (confirmado) {
        this.excluirAluno(aluno.id);

        // Atualiza a fonte da tabela para mostrar a remoção
        this.dataSource.data = this.alunos.filter(a => a.id !== aluno.id);
        this.alunos = this.dataSource.data;

      }
    });
  }

  title = 'aluno-management-ui';
}


