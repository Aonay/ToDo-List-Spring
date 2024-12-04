import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import 'zone.js';
import { FormLoginComponent } from './app/components/forms/form-login/form-login.component';
import { provideHttpClient } from '@angular/common/http'; // Aqui adicionamos o HttpClient



bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));


