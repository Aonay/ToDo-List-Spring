import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, HttpClientModule], // Importações necessárias
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  nomeUsuario: string | null = '';
  userId: string | null = '';

  pendentes: any[] = [];
  emAndamento: any[] = [];
  concluidas: any[] = [];

  private apiBaseUrl = 'http://localhost:8080/tarefas/status';

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit(): void {
    this.nomeUsuario = sessionStorage.getItem('userName');
    this.userId = sessionStorage.getItem('userId');

    this.carregarTarefas();
  }

  carregarTarefas(): void {
    this.http.get<any[]>(`${this.apiBaseUrl}/pendentes`, {withCredentials: true}).subscribe({
      next: (data) => (this.pendentes = data),
      error: (err) => console.error('Erro ao carregar tarefas pendentes', err),
    });

    this.http.get<any[]>(`${this.apiBaseUrl}/em_andamento`, {withCredentials: true}).subscribe({
      next: (data) => (this.emAndamento = data),
      error: (err) => console.error('Erro ao carregar tarefas em andamento', err),
    });

    this.http.get<any[]>(`${this.apiBaseUrl}/concluidas`, {withCredentials: true}).subscribe({
      next: (data) => (this.concluidas = data),
      error: (err) => console.error('Erro ao carregar tarefas concluídas', err),
    });
  }

  navigateToAddTarefa(): void {
    this.router.navigate(['/tarefas']);
  }
}
