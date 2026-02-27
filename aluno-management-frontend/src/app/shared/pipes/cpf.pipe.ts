import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'cpf',
  pure: true //só recalcula quando a entrada muda
})
export class CpfPipe implements PipeTransform {
  transform(value: string | number | null | undefined): string {
    if (value === null || value === undefined) return '';

    //converte para string e mantem apenas dígitos
    const digits = String(value).replace(/\D/g, '');

    // Se não tiver 11 dígitos, retorna o valor original como string sem quebrar
    if (digits.length !== 11) return String(value);

    // aplica a máscara 
    return digits.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
  }
}