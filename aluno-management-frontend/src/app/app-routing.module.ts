import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EditarAlunoComponent } from './alunos/editar-aluno/editar-aluno.component';
import { ListaAlunosComponent } from './alunos/lista-alunos/lista-alunos.component';
import { NovoAlunoComponent } from './alunos/novo-aluno/novo-aluno.component';
import { LoginComponent } from './login/login.component';
import { VisualizarAlunoComponent } from './alunos/visualizar-aluno/visualizar-aluno.component';

//Rotas dos componentes da aplicação
const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'alunos', component: ListaAlunosComponent},
  {path: 'alunos/novo', component: NovoAlunoComponent},
  {path: 'alunos/visualizar/:id', component: VisualizarAlunoComponent},
  {path: 'alunos/editar/:id', component: EditarAlunoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
