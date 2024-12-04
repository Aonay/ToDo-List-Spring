import { Component, Inject, inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';  // Corrigido: importando o MatDialogModule
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';  // Se estiver usando select
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';  // Para usar ngModel
import { HttpClient, HttpClientModule } from '@angular/common/http';


@Component({
  selector: 'app-modal-edit',
  standalone: true,
  imports: [
    CommonModule, 
    MatButtonModule, 
    MatFormFieldModule, 
    MatInputModule, 
    MatSelectModule, 
    MatDialogModule,  
    FormsModule,
    HttpClientModule, 
  ],  
  templateUrl: './modal-edit.component.html',
  styleUrls: ['./modal-edit.component.css'],
})
export class ModalEditComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<ModalEditComponent>, // Controle do modal
    private http: HttpClient // Injeção do HttpClient para chamadas HTTP
  ) {}

  salvar(): void {
    // URL para a requisição PUT
    const url = `http://localhost:8080/tarefas/${this.data.id}`;

    // Montando o payload com os campos editáveis
    const payload = {
      titulo: this.data.titulo || '', // Garante que o campo não seja nulo
      descricao: this.data.descricao || '',
      prioridade: this.data.prioridade || 0, // Padrão para prioridade
      responsavel: this.data.responsavel || '',
      status: this.data.status 
    };

    // Realizando a chamada PUT para atualizar a tarefa
    this.http.put(url, payload, { withCredentials: true }).subscribe({
      next: (response) => {
        console.log('Tarefa atualizada com sucesso!', response);

        // Fecha o modal após o sucesso
        this.dialogRef.close(true); // Envia um valor indicando sucesso
      },
      error: (err) => {
        console.error('Erro ao atualizar tarefa:', err);
      },
    });
  }

  cancelar(): void {
    // Fecha o modal sem realizar alterações
    this.dialogRef.close(false);
  }
}
