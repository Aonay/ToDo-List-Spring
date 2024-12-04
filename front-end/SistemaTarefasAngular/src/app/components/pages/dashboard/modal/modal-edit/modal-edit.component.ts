import { Component, Inject, inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule } from '@angular/material/dialog';  // Corrigido: importando o MatDialogModule
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';  // Se estiver usando select
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';  // Para usar ngModel
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-modal-edit',
  standalone: true,
  imports: [
    CommonModule, 
    MatButtonModule, 
    MatFormFieldModule, 
    MatInputModule, 
    MatSelectModule, 
    MatDialogModule,  // Importando MatDialogModule
    FormsModule // Para usar ngModel
  ],  
  templateUrl: './modal-edit.component.html',
  styleUrls: ['./modal-edit.component.css'],
})
export class ModalEditComponent {
  private dialog = inject(MatDialog);
  private http!: HttpClient;  // Injeção do HttpClient para chamadas HTTP

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}

  salvar(): void {
    // Exibindo os dados no console antes de enviar para a API
    console.log('ID da Tarefa:', this.data.id);
    console.log('Título:', this.data.titulo);
    console.log('Descrição:', this.data.descricao);
    console.log('Prioridade:', this.data.prioridade);
    console.log('Respon:', this.data.responsavel);
    console.log('Status:', this.data.status);
  
    // URL para a requisição PUT
    const url = `http://localhost:8080/tarefas/${this.data.id}`;
  
    // Montando o payload (adicionando os campos necessários)
    const payload = {
      id: this.data.id || 0,  // Garantindo que o id da tarefa seja enviado (caso esteja vazio, usa-se 0)
      titulo: this.data.titulo || '',  // Caso o título esteja vazio, envia uma string vazia
      descricao: this.data.descricao || '',  // Caso a descrição esteja vazia, envia uma string vazia
      prioridade: this.data.prioridade || 0,  // Caso a prioridade esteja vazia, envia 0
    };
  
    // Exibindo o payload no console
    console.log('Payload enviado ao backend:', payload);
  
    // Realizando a chamada PUT para atualizar a tarefa
    this.http.put(url, payload, { withCredentials: true }).subscribe({
      next: (response) => {
        console.log('Tarefa atualizada com sucesso!', response);
  
        // Se necessário, atualize o frontend localmente após a resposta da API
        // Aqui você pode fazer algo como emitir um evento para atualizar a lista de tarefas no dashboard.
      },
      error: (err) => {
        console.error('Erro ao atualizar tarefa:', err);
      }
    });
  }
  
  
}
