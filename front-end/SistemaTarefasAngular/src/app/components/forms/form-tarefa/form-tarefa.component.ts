import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-form-tarefa',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './form-tarefa.component.html',
  styleUrls: ['./form-tarefa.component.css']
})
export class FormTarefaComponent {
  id: number = 0;
  titulo_tarefa: string = '';
  descricao: string = '';
  responsavel: string = '';
  prioridade: number = 0;
  status: string = 'pendente';
  
  
  private apiUrl = 'http://localhost:8080/tarefas/';

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    const userId = sessionStorage.getItem('userId');
    
    console.log('Título da Tarefa:', this.titulo_tarefa);
    console.log('Descrição:', this.descricao);
    console.log('Responsável:', this.responsavel);
    console.log('Prioridade:', this.prioridade); // Verificando o valor da prioridade
    console.log('Status:', this.status);
    console.log('userId:', userId); // Verificando o valor de userId

    const tarefa = {
      id: this.id,
      titulo_tarefa: this.titulo_tarefa,
      responsavel: this.responsavel,
      descricao: this.descricao,
      prioridade: this.prioridade,
      status: this.status,
      usuario_id: userId,     
    };

    this.http.post(this.apiUrl, tarefa).subscribe({
      next: (response) => {
        alert('Tarefa criada com sucesso!');
        console.log(response);
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
