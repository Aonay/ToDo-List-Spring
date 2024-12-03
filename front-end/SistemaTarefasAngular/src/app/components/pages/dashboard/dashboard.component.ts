import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CdkDragDrop, transferArrayItem, moveItemInArray } from '@angular/cdk/drag-drop'; // Drag and drop
import { DragDropModule } from '@angular/cdk/drag-drop';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, HttpClientModule, DragDropModule, FormsModule], // Importações necessárias
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  nomeUsuario: string | null = '';
  userId: string | null = '';

  pendentes: any[] = [];
  andamento: any[] = [];
  concluidas: any[] = [];

  prioridadeSelecionada: string = ''; // Armazena o filtro de prioridade
  tarefasFiltradas: {
    pendentes: any[];
    andamento: any[];
    concluidas: any[];
  } = { pendentes: [], andamento: [], concluidas: [] }; // Tarefas filtradas por prioridade

  private apiBaseUrl = 'http://localhost:8080/tarefas/status';

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit(): void {
    this.nomeUsuario = sessionStorage.getItem('userName');
    this.userId = sessionStorage.getItem('userId');
    this.carregarTarefas();
  }

  carregarTarefas(): void {
    this.http.get<any[]>(`${this.apiBaseUrl}/pendentes`, { withCredentials: true }).subscribe({
      next: (data) => {
        this.pendentes = data;
        this.tarefasFiltradas.pendentes = [...this.pendentes];
      },
      error: (err) => console.error('Erro ao carregar tarefas pendentes', err),
    });

    this.http.get<any[]>(`${this.apiBaseUrl}/em-andamento`, { withCredentials: true }).subscribe({
      next: (data) => {
        this.andamento = data;
        this.tarefasFiltradas.andamento = [...this.andamento];
      },
      error: (err) => console.error('Erro ao carregar tarefas em andamento', err),
    });

    this.http.get<any[]>(`${this.apiBaseUrl}/concluidas`, { withCredentials: true }).subscribe({
      next: (data) => {
        this.concluidas = data;
        this.tarefasFiltradas.concluidas = [...this.concluidas];
      },
      error: (err) => console.error('Erro ao carregar tarefas concluídas', err),
    });
  }

  aplicarFiltro(): void {
    if (this.prioridadeSelecionada) {
      const prioridade = parseInt(this.prioridadeSelecionada, 10);

      this.tarefasFiltradas.pendentes = this.pendentes.filter(
        (tarefa) => tarefa.prioridade === prioridade
      );
      this.tarefasFiltradas.andamento = this.andamento.filter(
        (tarefa) => tarefa.prioridade === prioridade
      );
      this.tarefasFiltradas.concluidas = this.concluidas.filter(
        (tarefa) => tarefa.prioridade === prioridade
      );
    } else {
      // Se nenhuma prioridade for selecionada, exibe todas as tarefas
      this.tarefasFiltradas.pendentes = [...this.pendentes];
      this.tarefasFiltradas.andamento = [...this.andamento];
      this.tarefasFiltradas.concluidas = [...this.concluidas];
    }
  }

  navigateToAddTarefa(): void {
    this.router.navigate(['/tarefas']);
  }

  onDrop(event: CdkDragDrop<any[]>): void {
    if (event.previousContainer === event.container) {
      // Reordena tarefas dentro da mesma coluna
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      // Move a tarefa para outra coluna
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );

      // Obtém a tarefa movida
      const tarefaMovida = event.container.data[event.currentIndex];
      console.log('Tarefa Movida:', tarefaMovida);

      // Define o novo status baseado na lista de destino
      let novoStatus: string = '';
      if (event.container.id === 'pendentes') {
        novoStatus = 'pendente';
      } else if (event.container.id === 'andamento') {
        novoStatus = 'andamento';
      } else if (event.container.id === 'concluidas') {
        novoStatus = 'concluida';
      }

      // Atualiza o status da tarefa no backend
      this.atualizarStatusTarefa(tarefaMovida, novoStatus);
    }
  }

  atualizarStatusTarefa(tarefa: any, novoStatus: string): void {
    const url = `http://localhost:8080/tarefas/${tarefa.id}`;

    // Atualiza apenas o campo de status no backend
    const payload = {
      ...tarefa,
      status: novoStatus,
    };
    console.log('Payload enviado ao backend:', payload);

    this.http.put(url, payload, { withCredentials: true }).subscribe({
      next: (data: any) => {
        console.log('Status da tarefa atualizado com sucesso:', data);
      },
      error: (err) => {
        console.error('Erro ao atualizar status da tarefa:', err);
      },
    });
  }
}
