import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AlunoService {

  constructor(private http: HttpClient) { }

  buscarPorId(id: number) {
    return this.http.get(`http://localhost:8080/alunos/${id}`);
  }

  listar() {
    return this.http.get('http://localhost:8080/alunos');
  }

  criar(aluno: any) {
    return this.http.post('http://localhost:8080/alunos', aluno);
  }

  atualizar(id: number, aluno: any) {
    return this.http.put(`http://localhost:8080/alunos/${id}`, aluno)
  }

  excluir(id: number) {
    return this.http.delete(`http://localhost:8080/alunos/${id}`);
  }

  buscar(filtro: string) {
    return this.http.get(`http://localhost:8080/alunos?filtro=${filtro}&page=0&size=10`);
  }


  uploadFoto(id: number, formData: FormData) {
    return this.http.post(`http://localhost:8080/alunos/${id}/foto`, formData);
  }

}
