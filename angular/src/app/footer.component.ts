import { Component } from '@angular/core';
import { Injectable } from '@angular/core';
import { Logger } from 'angular2-logger/app/core/logger';
import { LogService } from './log.service';

@Component({
	selector: 'appFooter',
	templateUrl: './footer.component.html',
	styleUrls: []
})

@Injectable()
export class FooterComponent  {
	footerMsg: string = '%Footer Message';
	log: LogService;

	constructor(private _logger: LogService) {
		this.log = _logger; 
		this.log.debug('HeaderComponent.constructor() BEGIN');
		this.log.info('HeaderComponent.constructor() BEGIN');
	}
	
}