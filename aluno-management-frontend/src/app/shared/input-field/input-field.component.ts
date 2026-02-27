import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-input-field',
  templateUrl: './input-field.component.html',
  styleUrls: ['./input-field.component.css']
})
export class InputFieldComponent {
  @Input() label = '';
  @Input() icon = '';
  @Input() placeholder = '';
  @Input() type = 'text';
  @Input() maxlength?: number;

  @Input() control!: FormControl;
  @Input() errorText = 'Valor inválido';
}