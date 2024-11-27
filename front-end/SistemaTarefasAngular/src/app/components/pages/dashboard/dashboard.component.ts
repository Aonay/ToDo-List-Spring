import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // Import necessário

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, HttpClientModule], // Inclua o CommonModule
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
    this.http.get(`${this.apiBaseUrl}/pendentes`).subscribe({
      next: (data: any) => (this.pendentes = data),
      error: (err) => console.error('Erro ao carregar pendentes', err),
    });

    this.http.get(`${this.apiBaseUrl}/em-andamento`).subscribe({
      next: (data: any) => (this.emAndamento = data),
      error: (err) => console.error('Erro ao carregar em andamento', err),
    });

    this.http.get(`${this.apiBaseUrl}/concluidas`).subscribe({
      next: (data: any) => (this.concluidas = data),
      error: (err) => console.error('Erro ao carregar concluídas', err),
    });
  }

  navigateToAddTarefa(): void {
    this.router.navigate(['/tarefas']);
  }
}
