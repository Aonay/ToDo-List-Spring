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
  private http!: HttpClient  // Injeção do HttpClient para chamadas HTTP

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) 
  {}

  salvar(): void {
    // URL para a requisição PUT
    const url = `http://localhost:8080/tarefas/${this.data.id}`;
    console.log(this.data)

    // Realizando a chamada PUT para o backend
    this.http.put(url, this.data, { withCredentials: true }).subscribe({
      next: (response) => {
        console.log('Tarefa atualizada com sucesso!', response);
      },
      error: (err) => {
        console.error('Erro ao atualizar tarefa:', err);
      }
    });
  }

  openDialog(): void {
    this.dialog.open(ModalContentComponent); // Abrir o modal definido abaixo
  }
}


// Este é o conteúdo do modal
@Component({
  selector: 'app-modal-content',
  standalone: true,
  template: '',
  imports: [MatDialogModule, MatButtonModule],
})
export class ModalContentComponent {}
