import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FormComponent } from './components/form/form.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: FormComponent },
  { path: 'dashboard', component: DashboardComponent },
];

// path: 'nomeDoPath', component: EscolherComponenteParaRenderizar
// Em cada Componente, sera injetado os outros components necessários em tags HTML
