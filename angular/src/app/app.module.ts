// Imports
import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { DownloadsModule } from './downloads/downloads.module';
import { ReferralsModule } from './referrals/referrals.module';
import { ArticlesModule } from './articles/articles.module';
import { ResumeModule } from './resume/resume.module';
import { SharedModule } from './shared/shared.module';
import {Logger, Options as LoggerOptions} from 'angular2-logger/app/core/logger';
import { environment } from '../environments/environment';

// Declarations
import { AppComponent } from './app.component';
import { MainComponent } from './main.component';
import { HeaderComponent } from './header.component';
import { FooterComponent } from './footer.component';
import { PageComponent } from './page.component';
import { LogService } from './log.service';
import { NavItemService } from './nav-item.service';
import { routing } from './app.routes';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

// Decorator
@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    DownloadsModule,
    ReferralsModule,
    ArticlesModule,
    ResumeModule,
    SharedModule,
    routing,
    NgbModule.forRoot()
  ],
  declarations: [
  	// Root declarations only
  	AppComponent,
  	MainComponent,
  	HeaderComponent,
  	FooterComponent,
  	PageComponent
  ],
  providers: [Logger, LogService, NavItemService],
  bootstrap: [AppComponent]
})

export class AppModule {
  constructor(private logService: LogService) {
    if (isDevMode()) {
      console.info('AppModule.constructor() - To see debug logs enter: \'logger.level = logger.Level.DEBUG;\' in your browser console');
    }
    logService.setLogLevel(environment.logger.level);
    logService.info('AppModule.constructor() - Testing angular2_logger...');
  }
}
