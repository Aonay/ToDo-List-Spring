import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tarefa } from '../models/Tarefa';

@Injectable({
  providedIn: 'root',
})
export class TarefaService {
  private baseUrl = 'http://localhost:8080/tarefas/status';

  constructor(private http: HttpClient) {}

  getTarefasPorStatus(status: string): Observable<Tarefa[]> {
    return this.http.get<Tarefa[]>(`${this.baseUrl}/${status}`);
  }
}
