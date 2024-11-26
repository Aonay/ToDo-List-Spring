import { RouterModule, Routes } from '@angular/router';
import { FormLoginComponent } from './components/forms/form-login/form-login.component';
import { FormCadastroComponent } from './components/forms/form-cadastro/form-cadastro.component';
import { NgModule } from '@angular/core';
import { HomeComponent } from './components/pages/home/home.component';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { FormTarefaComponent } from './components/forms/form-tarefa/form-tarefa.component';

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' }, //redireciona sempre para /home

    { path: 'home', component: HomeComponent}, 
    { path: 'login', component: FormLoginComponent }, 
    { path: 'cadastro', component: FormCadastroComponent }, 
    { path: 'dashboard', component: DashboardComponent }, 
    { path: 'tarefas', component: FormTarefaComponent } 
  ];

  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
  })

  export class AppRoutes { }