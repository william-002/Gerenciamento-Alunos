import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExcluirAlunoDialogComponent } from './excluir-aluno-dialog.component';

describe('ExcluirAlunoDialogComponent', () => {
  let component: ExcluirAlunoDialogComponent;
  let fixture: ComponentFixture<ExcluirAlunoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExcluirAlunoDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExcluirAlunoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
