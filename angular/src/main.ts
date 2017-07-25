import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { enableProdMode } from '@angular/core';
import { environment } from './environments/environment';
import { AppModule } from './app/app.module';
import { Logger } from 'angular2-logger/app/core/logger';
import { LogService } from './app/log.service';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule, {
	providers:  [
	              { provide: LogService, useClass: LogService }
              ]
});
//platformBrowserDynamic().bootstrapModule(AppModule, []);
