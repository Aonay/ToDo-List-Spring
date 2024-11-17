import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [], // Adicione quaisquer imports necessários
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  nomeUsuario: string | null = '';

  ngOnInit(): void {
    // Recupera o nome do usuário armazenado no localStorage
    this.nomeUsuario = localStorage.getItem('userName');
  }
}
