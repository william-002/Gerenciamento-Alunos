import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { By } from '@angular/platform-browser';
import { ViewFieldComponent } from './view-field.component';

describe('ViewFieldComponent', () => {
  let component: ViewFieldComponent;
  let fixture: ComponentFixture<ViewFieldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewFieldComponent ],
      schemas: [ NO_ERRORS_SCHEMA ] // ignora mat-*
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFieldComponent);
    component = fixture.componentInstance;
  });

  it('renderiza label e valor', () => {
    component.label = 'Matrícula';
    component.icon = 'confirmation_number';
    component.value = 'A123';

    // ✅ necessário porque o componente calcula o valor em ngOnChanges
    if (typeof (component as any).ngOnChanges === 'function') {
      (component as any).ngOnChanges({});
    }

    fixture.detectChanges();

    const label = fixture.debugElement.query(By.css('mat-label')).nativeElement;
    expect(label.textContent).toContain('Matrícula');

    const input = fixture.debugElement.query(By.css('input')).nativeElement as HTMLInputElement;
    expect(input.value).toBe('A123');
  });
});