import { Component } from '@angular/core';
import { Injectable } from '@angular/core';
import { MainComponent } from './main.component';
import { Logger } from 'angular2-logger/app/core/logger';
import { LogService } from './log.service';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	//directives: [MainComponent],
	//styleUrls: ['../css/bootstrap.css','../css/cssReset.css','../css/theme1.css','../css/layout.css']
	styleUrls: []
})

@Injectable()
export class AppComponent  {

	title: string = 'MJS App!!';
	log: LogService;

	//constructor() {
	//	console.info('AppComponent.constructor() BEGIN');
	//}

	constructor(private _logger:LogService) {
		this.log = _logger;
		this.log.debug('AppComponent.constructor() BEGIN');
		this.log.info('AppComponent.constructor() BEGIN');
	}

}
