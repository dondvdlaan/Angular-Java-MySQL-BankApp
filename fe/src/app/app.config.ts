import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

export const appConfig: ApplicationConfig = {
  providers: [
    // from app.routes
    provideRouter(routes),
    provideClientHydration(),
    // Providing HttpClient through dependency injection
    provideHttpClient(), provideAnimationsAsync(),]
};
