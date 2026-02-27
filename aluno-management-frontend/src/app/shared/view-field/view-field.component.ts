import { Component, Input, OnChanges  } from '@angular/core';

@Component({
  selector: 'app-view-field',
  templateUrl: './view-field.component.html',
  styleUrls: ['./view-field.component.css']
})

export class ViewFieldComponent implements OnChanges {
  @Input() label = '';
  @Input() icon = '';
  @Input() value: any;
  @Input() hint = '';
  @Input() type: 'text' | 'email' | 'tel' | 'number' = 'text';

  displayValue = '';

  ngOnChanges(): void {
    this.displayValue = (this.value === null || this.value === undefined) ? '' : String(this.value);
  }
}
