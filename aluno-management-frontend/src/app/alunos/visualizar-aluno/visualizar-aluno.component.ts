import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlunoService } from 'src/app/core/services/aluno.service';

@Component({
  selector: 'app-visualizar-aluno',
  templateUrl: './visualizar-aluno.component.html',
  styleUrls: ['./visualizar-aluno.component.css']
})
export class VisualizarAlunoComponent implements OnInit {

  aluno: any;
  form: FormGroup;

  constructor(private route: ActivatedRoute, private alunoService: AlunoService, private router: Router, private fb: FormBuilder) { }

  ngOnInit() {


    this.form = this.fb.group({
      nome: [{ value: '', disabled: true }],
      cpf: [{ value: '', disabled: true }],
      email: [{ value: '', disabled: true }],
      telefone: [{ value: '', disabled: true }],
      matricula: [{ value: '', disabled: true }],
      status: [{ value: '', disabled: true }]
    });




    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.alunoService.buscarPorId(+id).subscribe((resposta: any) => {
        this.aluno = resposta;
        this.form.patchValue(resposta);
       //patchValue() preenche apenas os campos existentes, sem gerar erro.
      })
    }
  }

  voltar() {
    this.router.navigate(['/alunos']);
  }


  statusValue(status?: string): 'ativo' | 'inativo' | null {
    const s = (status || '').toLowerCase().trim();
    if (s === 'ativo') return 'ativo';
    if (s === 'inativo') return 'inativo';
    return null;
  }

  imgError(event: any) {
    (event.target as HTMLImageElement).src = 'assets/images/default-user.png';

  }


}
