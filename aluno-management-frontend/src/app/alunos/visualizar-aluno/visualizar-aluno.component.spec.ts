import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizarAlunoComponent } from './visualizar-aluno.component';

describe('VisualizarAlunoComponent', () => {
  let component: VisualizarAlunoComponent;
  let fixture: ComponentFixture<VisualizarAlunoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisualizarAlunoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisualizarAlunoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
