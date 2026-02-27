import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlunoService } from '../../core/services/aluno.service';
import { MessageService } from 'src/app/core/services/message.service';

@Component({
  selector: 'app-novo-aluno',
  templateUrl: './novo-aluno.component.html',
  styleUrls: ['./novo-aluno.component.css']
})
export class NovoAlunoComponent implements OnInit {

  mensagemBackend = '';

  fotoPreview: string | null = null;
  arquivoSelecionado: File | null = null;

  form!: FormGroup;
  salvando = false;

  constructor(
    private router: Router,
    private alunoService: AlunoService,
    private message: MessageService,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.form = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email, Validators.minLength(5)]],
      cpf: ['', [
        Validators.required,
        Validators.pattern(/^(\d{11}|\d{3}\.\d{3}\.\d{3}-\d{2})$/)
      ]],
      telefone: ['', [Validators.required, Validators.minLength(10)]]
    });

    // limpa mensagem de erro backend ao digitar CPF
    this.form.get('cpf')!.valueChanges.subscribe(() => {
      this.mensagemBackend = '';
    });
  }

  get nomeControl() { return this.form.get('nome')! }
  get emailControl() { return this.form.get('email')! }
  get cpfControl() { return this.form.get('cpf')! }
  get telefoneControl() { return this.form.get('telefone')! }

  voltar(): void {
    this.router.navigate(['/alunos']);
  }

  buttonDisabled() {
    return this.salvando || this.form.invalid;
  }

  salvar(): void {
    if (this.buttonDisabled()) return;

    this.salvando = true;

    const v = this.form.value;

    const payload = {
      nome: v.nome,
      email: v.email,
      cpf: (v.cpf || '').toString().replace(/\D/g, ''),
      telefone: (v.telefone || '').toString().replace(/\D/g, '')
    };

    this.alunoService.criar(payload).subscribe(
      (res: any) => {
        const id = res && res.id ? res.id : null;

        if (!this.arquivoSelecionado || !id) {
          this.salvando = false;
          this.message.success('Aluno cadastrado com sucesso!');
          this.router.navigate(['/alunos']);
          return;
        }

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
    if (!input.files || !input.files.length) {
      return;
    }

    this.arquivoSelecionado = input.files[0];

    const reader = new FileReader();
    reader.onload = () => this.fotoPreview = reader.result as string;
    reader.readAsDataURL(this.arquivoSelecionado);
  }
}