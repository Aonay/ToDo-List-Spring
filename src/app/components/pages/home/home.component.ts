import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Importa o Router para navegação

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private router: Router) {}

  navigateToLogin() {
    this.router.navigate(['/login']); // Navega para a página de login
  }

  navigateToCadastro() {
    this.router.navigate(['/cadastro']); // Navega para a página de cadastro
  }
}
