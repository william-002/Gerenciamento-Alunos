import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, Validators } from '@angular/forms';
import { AlunoService } from '../../core/services/aluno.service';
import { MessageService } from 'src/app/core/services/message.service';

@Component({
  selector: 'app-novo-aluno',
  templateUrl: './novo-aluno.component.html',
  styleUrls: ['./novo-aluno.component.css']
})
export class NovoAlunoComponent {

  mensagemBackend = '';

  aluno: any = {
    nome: '',
    email: '',
    cpf: '',
    telefone: ''
  };

  fotoPreview: string | null = null;
  arquivoSelecionado: File | null = null;

  // Controles e validações mínimas
  nome = new FormControl('', [Validators.minLength(2)]);
  email = new FormControl('', [Validators.email, Validators.minLength(5)]);
  cpf = new FormControl('', [Validators.minLength(11)]);
  telefone = new FormControl('', [Validators.minLength(10)]);

  salvando = false;

  constructor(
    private router: Router,
    private alunoService: AlunoService,
    private message: MessageService
  ) { }

  voltar(): void {
    this.router.navigate(['/alunos']);
  }

  // Helpers de mensagens (uma mensagem simples por campo)
  errorValidName() { return this.nome.invalid ? 'Nome inválido.' : false; }
  errorValidEmail() { return this.email.invalid ? 'E-mail inválido.' : false; }
  errorValidCPF() { return this.cpf.invalid ? 'CPF inválido.' : false; }
  errorValidPhone() { return this.telefone.invalid ? 'Telefone inválido.' : false; }

  // Botão só habilita quando tudo estiver válido
  buttonDisabled() {
    const a = this.aluno || {};

    const nomeOk = String(a.nome || '').trim().length >= 3;
    const emailOk = !!(a.email && /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(a.email));
    const cpfOk = String(a.cpf || '').replace(/\D+/g, '').length === 11;
    const telOk = /^\(?\d{2}\)?\s?\d{4,5}-?\d{4}$/.test(String(a.telefone || '').trim());

    return !(nomeOk && emailOk && cpfOk && telOk) || this.salvando;
  }

  salvar(): void {
  if (this.buttonDisabled()) return;

  this.salvando = true;

  const payload = {
    ...this.aluno,
    cpf: (this.aluno.cpf || '').replace(/\D+/g, '')
  };

  this.alunoService.criar(payload).subscribe(
    (res: any) => {
      const id = res && res.id ? res.id : null;

      // Se não tem foto, finaliza direto
      if (!this.arquivoSelecionado || !id) {
        this.salvando = false;
        this.message.success('Aluno cadastrado com sucesso!');
        this.router.navigate(['/alunos']);
        return;
      }

      // Se tem foto, enviar
      const formData = new FormData();
      formData.append('foto', this.arquivoSelecionado);

      this.alunoService.uploadFoto(id, formData).subscribe(
        () => {
          this.salvando = false;
          this.message.success('Aluno cadastrado com sucesso!');
          this.router.navigate(['/alunos']);
        },
        () => {
          this.salvando = false;
          this.router.navigate(['/alunos']);
        }
      );
    },

    (err) => {
      this.salvando = false;

      if (err && (err.status === 400 || err.status === 500)) {
        this.mensagemBackend = 'CPF inválido ou já cadastrado!';
        return;
      }

      this.mensagemBackend = 'Não foi possível cadastrar.';
    }
  );
}



  onFileSelected(event: Event) {
  const input = event.target as HTMLInputElement;
  if (!input.files || input.files.length === 0) return;

  this.arquivoSelecionado = input.files[0];

  const reader = new FileReader();
  reader.onload = () => (this.fotoPreview = reader.result as string);
  reader.readAsDataURL(this.arquivoSelecionado);
}
}