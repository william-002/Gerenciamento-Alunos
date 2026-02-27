import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ReactiveFormsModule, FormControl, Validators } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { InputFieldComponent } from './input-field.component';

describe('InputFieldComponent', () => {
  let component: InputFieldComponent;
  let fixture: ComponentFixture<InputFieldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InputFieldComponent ],
      imports: [ ReactiveFormsModule ],
      schemas: [ NO_ERRORS_SCHEMA ] // ignora mat-form-field e mat-*
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InputFieldComponent);
    component = fixture.componentInstance;
    component.label = 'Email';
    component.icon = 'email';
    component.placeholder = 'digite seu email';
    component.maxlength = 10;
    component.errorText = 'Campo obrigatório';
    component.control = new FormControl('', [Validators.required]);
    fixture.detectChanges();
  });

  it('cria o componente', () => {
    expect(component).toBeTruthy();
  });

  it('mostra mat-error quando inválido e touched', () => {
    component.control.markAsTouched();
    fixture.detectChanges();

    const err = fixture.debugElement.query(By.css('mat-error'));
    expect(err).toBeTruthy();
    expect(err.nativeElement.textContent).toContain('Campo obrigatório');
  });

  it('não mostra mat-error quando válido', () => {
    component.control.setValue('ok');
    component.control.markAsTouched();
    fixture.detectChanges();

    const err = fixture.debugElement.query(By.css('mat-error'));
    expect(err).toBeFalsy();
  });
});