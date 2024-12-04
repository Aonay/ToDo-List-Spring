import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {

    // Verifique se o usuário está autenticado (substitua isso pela sua lógica real)
    const isAuthenticated = !!sessionStorage.getItem('userId'); 

    if (isAuthenticated) {
      return true; // Permite o acesso à rota
    } else {
      // Redireciona para a página de login se não estiver autenticado
      this.router.navigate(['/login']);
      return false;
    }
  }
}
