import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { EditarAlunoComponent } from './alunos/editar-aluno/editar-aluno.component';
import { ListaAlunosComponent } from './alunos/lista-alunos/lista-alunos.component'
import { FormsModule } from '@angular/forms';
import { NovoAlunoComponent } from './alunos/novo-aluno/novo-aluno.component';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material';
import { MatTableModule } from '@angular/material';
import { MatChipsModule } from '@angular/material';
import { MatFormFieldModule } from '@angular/material';
import { MatInputModule } from '@angular/material';
import { MatIconModule } from '@angular/material';
import { VisualizarAlunoComponent } from './alunos/visualizar-aluno/visualizar-aluno.component';
import { MatDividerModule } from '@angular/material';
import { MatCardModule } from '@angular/material/card';
import { MatRadioModule } from '@angular/material';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatTooltipModule } from '@angular/material';
import { MatPaginatorModule } from '@angular/material';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material';
import { ExcluirAlunoDialogComponent } from './alunos/components/excluir-aluno-dialog/excluir-aluno-dialog.component';
import { MatSnackBarModule } from '@angular/material';
import { DefaultImageDirective } from './shared/default-image.directive';
import { ViewFieldComponent } from './shared/view-field/view-field.component';
import { StatusBadgeComponent } from './shared/status-badge/status-badge.component';
import { CpfPipe } from './shared/pipes/cpf.pipe';
import { InputFieldComponent } from './shared/input-field/input-field.component';
import { TableActionsComponent } from './shared/table-actions/table-actions.component';

@NgModule({
  declarations: [
    AppComponent,
    EditarAlunoComponent,
    ListaAlunosComponent,
    NovoAlunoComponent,
    LoginComponent,
    VisualizarAlunoComponent,
    ExcluirAlunoDialogComponent,
    DefaultImageDirective,
    ViewFieldComponent,
    StatusBadgeComponent,
    CpfPipe,
    InputFieldComponent,
    TableActionsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatTableModule,
    MatChipsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatDividerModule,
    MatCardModule,
    MatRadioModule,
    MatButtonToggleModule,
    MatTooltipModule,
    MatPaginatorModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatSnackBarModule
  ],

  exports: [
    ViewFieldComponent,
    StatusBadgeComponent,
    CpfPipe,
    InputFieldComponent
  ],

  entryComponents: [
    ExcluirAlunoDialogComponent
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
