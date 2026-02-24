import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[appDefaultImage]'
})
export class DefaultImageDirective {

  @Input() appDefaultImage?: string;
  private defaultPath =  'assets/images/default-user.png';

  constructor(private element: ElementRef<HTMLImageElement>) { }

  @HostListener('error')
  onError() {
    const fallback = this.appDefaultImage || this.defaultPath;
    this.element.nativeElement.src = fallback;
  }

}
