import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginComponent } from './login.component';

class RouterMock {
  navigate(commands: any[]) {}
}

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let router: RouterMock;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [ ReactiveFormsModule ],
      providers: [
        FormBuilder,
        { provide: Router, useClass: RouterMock }
      ],
      // ignora app-input-field e tags do Material
      schemas: [ NO_ERRORS_SCHEMA ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    router = TestBed.get(Router);
    component.ngOnInit();
    fixture.detectChanges();
  });

  it('cria o componente', () => {
    expect(component).toBeTruthy();
  });

  it('inicia com form inválido', () => {
    expect(component.form.invalid).toBeTruthy();
  });

  it('valida e navega quando o formulário for válido', () => {
    spyOn(router, 'navigate');

    component.usuarioControl.setValue('usuario1'); // 8+ chars, letras e números
    component.senhaControl.setValue('Senha123');   // mesma coisa

    expect(component.form.valid).toBeTruthy();

    component.entrar();

    expect(router.navigate).toHaveBeenCalledWith(['/alunos']);
  });

  it('não navega com formulário inválido', () => {
    spyOn(router, 'navigate');

    component.usuarioControl.setValue('abc'); // inválido
    component.senhaControl.setValue('123');   // inválido

    component.entrar();

    expect(router.navigate).not.toHaveBeenCalled();
  });
});