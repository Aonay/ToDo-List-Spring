import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common'; // Importe o CommonModule

@Component({
  selector: 'app-form-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './form-modal.component.html',
  styleUrls: ['./form-modal.component.css'],
})
export class FormModalComponent {
  @Input() mostrar: boolean = false;
  @Output() toggle = new EventEmitter<void>();

  fecharModal(): void {
    this.toggle.emit();
  }
}
