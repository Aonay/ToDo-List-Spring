import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormTarefaComponent } from '../../forms/form-tarefa/form-tarefa.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [FormTarefaComponent], // Apenas o componente necessário
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  nomeUsuario: string | null = '';

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.nomeUsuario = localStorage.getItem('userName');
    const userId = localStorage.getItem('userId');
  }

  navigateToAddTarefa(): void {
    this.router.navigate(['/tarefas']);
     // Navega para a página de cadastro de tarefas
  }
}

