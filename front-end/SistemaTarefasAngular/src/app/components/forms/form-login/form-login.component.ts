import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; 
import { Router } from '@angular/router'; 


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
        // Salva o nome e o ID do usuário no localStorage para usar dps
        localStorage.setItem('userName', response.nome); 
        localStorage.setItem('userId', response.id);
  
        alert('Login realizado com sucesso!');
        console.log(response); 
  
        // Redireciona para o dashboard
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        alert('Login inválido');
        console.error(err); 
      }
    });
  }
  
  
  closeForm() {
    // Redireciona para a página inicial
    this.router.navigate(['/']);
  }

}
