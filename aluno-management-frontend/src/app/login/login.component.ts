import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  mostrarSenha = false;

  // Mínimo 8, pelo menos 1 letra e 1 número
  credRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

  form: FormGroup;

  constructor(
    private router: Router,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.form = this.fb.group({
      usuario: ['', [Validators.required, Validators.pattern(this.credRegex)]],
      senha:   ['', [Validators.required, Validators.pattern(this.credRegex)]]
    });
  }

  // Getters para usar no template
  get usuarioControl(): FormControl {
    return this.form.get('usuario') as FormControl;
  }

  get senhaControl(): FormControl {
    return this.form.get('senha') as FormControl;
  }

  entrar() {
    // Se clicar sem preencher, marca para exibir erros
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    // Fluxo atual: navega para alunos (mock)
    this.router.navigate(['/alunos']);
  }
}