import { CpfPipe } from './cpf.pipe';

describe('CpfPipe', () => {
  let pipe: CpfPipe;

  beforeEach(() => pipe = new CpfPipe());

  it('deve formatar quando tiver 11 dígitos', () => {
    expect(pipe.transform('12345678901')).toBe('123.456.789-01');
  });

  it('deve retornar vazio para null/undefined', () => {
    expect(pipe.transform(null)).toBe('');
    expect(pipe.transform(undefined)).toBe('');
  });

  it('deve retornar original quando não tiver 11 dígitos', () => {
    expect(pipe.transform('123')).toBe('123');
  });
});