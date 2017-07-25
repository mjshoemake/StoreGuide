import { Component } from '@angular/core';
import { Injectable } from '@angular/core';
import { Logger } from 'angular2-logger/app/core/logger';
import { LogService } from './log.service';

@Component({
	selector: 'main-comp',
	templateUrl: './main.component.html',
    styleUrls: []
})

@Injectable()
export class MainComponent  {

	description: string = 'This is the main component of the StoreGuide application.';
	log: LogService;

	constructor(private _logger: LogService) {
		this.log = _logger; 
		this.log.debug('MainComponent.constructor() BEGIN');
		this.log.info('MainComponent.constructor() BEGIN');
	}
	
}