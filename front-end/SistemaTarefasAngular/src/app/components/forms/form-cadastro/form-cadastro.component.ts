import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-cadastro',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './form-cadastro.component.html',
  styleUrls: ['./form-cadastro.component.css']
})
export class FormCadastroComponent {
  nome: string = '';
  email: string = '';
  senha: string = '';
  private apiUrl = 'http://localhost:8080/usuarios/registrar';

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    const userData = {
      nome: this.nome,
      email: this.email,
      senha: this.senha,
    };

    this.http.post(this.apiUrl, userData).subscribe({
      next: (response) => {
        alert('Cadastro realizado com sucesso!');
        console.log(response);
      },
      error: (error) => {
        alert('Erro ao realizar cadastro.');
        console.error(error);
      },
    });
  }

  closeForm() {
    // Redireciona para a página inicial
    this.router.navigate(['/']);
  }
}
