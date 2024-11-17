import { Component, Input } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http'; // Para requisições HTTP
import { CommonModule } from '@angular/common'; // Para diretivas como *ngIf e *ngFor
import { FormsModule } from '@angular/forms'; // Para [(ngModel)]
import { Router } from '@angular/router'; // Para redirecionamento

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})

export class FormComponent {
  @Input() btnText: string = "Enviar";
  email: string = "";
  senha: string = "";

  private apiUrl = 'http://localhost:8080/usuarios/login';

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.post(this.apiUrl, { email: this.email, senha: this.senha }).subscribe({
      next: (response) => {
        alert('Login realizado com sucesso!');
        console.log(response); // SUCESSO!!!
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        alert('Login inválido');
        console.error(err);
      }
    });
  }
}
