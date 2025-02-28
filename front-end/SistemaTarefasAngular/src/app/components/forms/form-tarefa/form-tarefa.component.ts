import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Tarefa } from '../../../models/Tarefa';

@Component({
  selector: 'app-form-tarefa',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './form-tarefa.component.html',
  styleUrls: ['./form-tarefa.component.css']
})
export class FormTarefaComponent {
  id: number = 0;
  descricao: string = '';
  responsavel: string = '';
  prioridade: number = 0;
  status: string = 'pendente';
  titulo: string = '';
  
  private apiUrl = 'http://localhost:8080/tarefas/';

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    const userId = sessionStorage.getItem('userId');
  
    console.log('Título da Tarefa:', this.titulo);
    console.log('Descrição:', this.descricao);
    console.log('Responsável:', this.responsavel);
    console.log('Prioridade:', this.prioridade);
    console.log('Status:', this.status);
    console.log('userId:', userId);

    const tarefa: Tarefa = {
      id: this.id,
      titulo: this.titulo,
      descricao: this.descricao,
      prioridade: this.prioridade,
      status: this.status,
      responsavel: this.responsavel,
    };

    this.http.post(this.apiUrl, tarefa, { withCredentials: true }).subscribe({
      next: (response) => {
        alert('Tarefa criada com sucesso!');
        console.log(response);
        this.router.navigate(['dashboard']);
      },
      error: (error) => {
        alert('Erro ao criar nova Tarefa.');
        console.error(error);
      },
    });
  }

  closeForm() {
    this.router.navigate(['dashboard']);
  }
}
