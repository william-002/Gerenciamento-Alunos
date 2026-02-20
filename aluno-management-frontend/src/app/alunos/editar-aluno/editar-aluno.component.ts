import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlunoService } from '../../core/services/aluno.service';
import { MessageService } from 'src/app/core/services/message.service';

@Component({
  selector: 'app-editar-aluno',
  templateUrl: './editar-aluno.component.html',
  styleUrls: ['./editar-aluno.component.css']
})
export class EditarAlunoComponent implements OnInit {

  id!: number;
  aluno: any;

  original: any;        // snapshot para comparação
  btnDesabilitado = true;


  fotoPreview: string | null = null;
  arquivoSelecionado: File | null = null;


  constructor(
    private route: ActivatedRoute,
    private alunoService: AlunoService,
    private router: Router,
    private message: MessageService
  ) { }

  ngOnInit() {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.buscarAluno();
  }

  buscarAluno() {
    this.alunoService.buscarPorId(this.id).subscribe((res: any) => {

      this.aluno = res;

      // Apenas campos editáveis
      this.original = {
        email: res.email,
        telefone: res.telefone,
        status: res.status
      };

      // verifica estado inicial
      this.verificarMudancas();
    });
  }

  verificarMudancas() {
    if (!this.aluno || !this.original) {
      this.btnDesabilitado = true;
      return;
    }

    const mudou =
      this.aluno.email !== this.original.email ||
      this.aluno.telefone !== this.original.telefone ||
      this.aluno.status !== this.original.status ||
      this.arquivoSelecionado != null;

    const valido = this.validar();

    //só habilita se mudou e estiver válido
    this.btnDesabilitado = !(mudou && valido);
  }

  validar(): boolean {
    return (
      this.emailValido(this.aluno.email) &&
      this.telefoneValido(this.aluno.telefone) &&
      !!this.aluno.status
    );
  }

  emailValido(email: string): boolean {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email || '');
  }

  
telefoneValido(telefone: string): boolean {
  const regex = /^\(?\d{2}\)?\s?\d{4,5}-?\d{4}$/;
  return regex.test((telefone || '').trim());
}


  salvar() {
  if (this.btnDesabilitado) return;

  const payload = {
    email: this.aluno.email.trim(),
    telefone: this.aluno.telefone.trim(),
    status: this.aluno.status
  };

  // Primeiro salva os dados normais
  this.alunoService.atualizar(this.id, payload).subscribe(() => {

    // Se não escolher foto nova, apenas volta para a tela
    if (!this.arquivoSelecionado) {
      this.message.success('Aluno atualizado com sucesso!');
      this.router.navigate(['/alunos']);
      return;
    }

    // Se escolher foto, enviar para /alunos/{id}/foto
    const formData = new FormData();
    formData.append('foto', this.arquivoSelecionado);

    this.alunoService.uploadFoto(this.id, formData).subscribe(() => {
      this.buscarAluno();
      this.message.success('Aluno atualizado com sucesso!');
      this.router.navigate(['/alunos']);
    });

  });
}


  voltar() {
    this.router.navigate(['/alunos']);
  }



  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files || input.files.length === 0) return;

    this.arquivoSelecionado = input.files[0];

    const reader = new FileReader();
    reader.onload = () => this.fotoPreview = reader.result as string;
    reader.readAsDataURL(this.arquivoSelecionado);
    this.verificarMudancas()
  }

}