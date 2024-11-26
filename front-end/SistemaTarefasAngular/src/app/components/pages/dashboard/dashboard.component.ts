import { Component, OnInit } from '@angular/core';
import { FormModalComponent } from '../../forms/form-modal/form-modal.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [FormModalComponent], // Adicione quaisquer imports necessários
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  nomeUsuario: string | null = '';
  mostrarModal: boolean = false; // Estado do modal

  ngOnInit(): void {
    // Recupera o nome do usuário armazenado no localStorage
    this.nomeUsuario = localStorage.getItem('userName');
  }

  // Alterna a visibilidade do modal
  toggleModal(): void {
    this.mostrarModal = !this.mostrarModal;
  }
}
