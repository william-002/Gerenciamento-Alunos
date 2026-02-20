import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  usuario = '';
  senha = '';
  mostrarSenha = false;

  usuarioValido = false;
  senhaValida = false;

  // min 8, pelo menos 1 letra e 1 número
  private readonly credRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

  constructor(private router: Router) {}

  validarUsuario() {
    this.usuarioValido = this.credRegex.test((this.usuario || '').trim());
  }

  validarSenha() {
    this.senhaValida = this.credRegex.test((this.senha || '').trim());
  }

  entrar() {
    if (this.usuarioValido && this.senhaValida) {
      this.router.navigate(['/alunos']);
    }
  }
}