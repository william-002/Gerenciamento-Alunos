import { TestBed } from '@angular/core/testing';
import { DefaultImageDirective } from './default-image.directive';

describe('DefaultImageDirective', () => {
  it('should create an instance', () => {
    TestBed.configureTestingModule({
      declarations: [ DefaultImageDirective ]
    });
    const fixture = TestBed.createComponent(DefaultImageDirective);
    expect(fixture).toBeTruthy();
  });
});
