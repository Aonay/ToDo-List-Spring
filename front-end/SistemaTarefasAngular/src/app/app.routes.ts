import { RouterModule, Routes } from '@angular/router';
import { FormLoginComponent } from './components/forms/form-login/form-login.component';
import { FormCadastroComponent } from './components/forms/form-cadastro/form-cadastro.component';
import { NgModule } from '@angular/core';
import { HomeComponent } from './components/pages/home/home.component';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { FormTarefaComponent } from './components/forms/form-tarefa/form-tarefa.component';
import { AuthGuard } from './components/pages/dashboard/modal/guards/auth.guard.spec';

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' }, //redireciona sempre para /home

    { path: 'home', component: HomeComponent}, 
    { path: 'login', component: FormLoginComponent }, 
    { path: 'cadastro', component: FormCadastroComponent }, 
    { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] }, // Protege a rota com o guard

    { path: 'tarefas', component: FormTarefaComponent, canActivate: [AuthGuard]  } 

  ];

  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
  })

  export class AppRoutes { }