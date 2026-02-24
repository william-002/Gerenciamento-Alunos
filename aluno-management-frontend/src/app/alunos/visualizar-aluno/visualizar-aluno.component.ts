import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlunoService } from 'src/app/core/services/aluno.service';

@Component({
  selector: 'app-visualizar-aluno',
  templateUrl: './visualizar-aluno.component.html',
  styleUrls: ['./visualizar-aluno.component.css']
})
export class VisualizarAlunoComponent implements OnInit {

  aluno: any;

  constructor(private route: ActivatedRoute, private alunoService: AlunoService, private router: Router) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if(id){
      this.alunoService.buscarPorId(+id).subscribe((resposta: any) => {
        this.aluno = resposta;
      })
    }
  }

  voltar(){
    this.router.navigate(['/alunos']);
  }

  formatarCPF(cpf: string): string {
  const s = (cpf || '').replace(/\D/g, ''); // mantém só dígitos
  if (s.length !== 11) return cpf || '';    // se não tiver 11 dígitos, retorna como veio
  return s.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
}


statusValue(status?: string): 'ativo' | 'inativo' | null {
  const s = (status || '').toLowerCase().trim();
  if (s === 'ativo') return 'ativo';
  if (s === 'inativo') return 'inativo';
  return null;
}

imgError(event: any) {
  (event.target as HTMLImageElement).src = 'assets/images/default-user.png';

}


}
