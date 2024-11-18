import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http'; // Para requisições HTTP
import { FormsModule } from '@angular/forms'; // Para [(ngModel)]
import { Router } from '@angular/router'; // Importa o Router para navegação


@Component({
  selector: 'app-form-login',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './form-login.component.html',
  styleUrls: ['./form-login.component.css']
})
export class FormLoginComponent {
  email: string = "";
  senha: string = "";

  private apiUrl = 'http://localhost:8080/usuarios/login';

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.post<any>(this.apiUrl, { email: this.email, senha: this.senha }).subscribe({
      next: (response) => {
        //armazena o nome do usuário na sessão ou localStorage para usar na pagina dashboard
        localStorage.setItem('userName', response.nome);

        alert('Login realizado com sucesso!');
        console.log(response); //console log bem sucedido

        //Redireciona o usuario para o dashboard
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        alert('Login inválido');
        console.error(err); //console log pra mostrar o erro
      }
    });
  }
  
  closeForm() {
    // Redireciona para a página inicial
    this.router.navigate(['/']);
  }

}
