import { Injectable } from '@angular/core';
//import 'rxjs/add/operator/map';
import { Logger } from 'angular2-logger/app/core/logger';
//import *  as Config from '../app/config';

@Injectable()
export class LogService {

	private log: Logger;
	private logLevel: number = 1;

	constructor(private _logger: Logger) {
		this.log = _logger;
		console.info('LogService.constructor() - LogService created.');
	}
	
	debug(msg: string): void {
		this.log.debug('DEBUG: ' + msg);
	}
	
	info(msg: string): void {
		this.log.info('INFO: ' + msg);
	}
	
	warn(msg: string): void {
		this.log.warn('WARN: ' + msg);
	}
	
	error(msg: string): void {
		this.log.error('ERR: ' + msg);
	}
	
	trace(msg: string): void {
		this.log.log('LOG: ' + msg);
	}
	
	setLogLevel(value: number): void {
		// Set the log level using the config value
		this.logLevel = value;
		this.log.level = value;
		this.log.info('LogService.setLogLevel() - Changing log level. New LoggerLevel: ' + this.log.level + '  ServiceLevel: ' + this.getLogLevelAsText());
	}
	
	getLogLevel(): number {
		return this.logLevel;
	}
	
	getLogLevelAsText(): string {
		switch(this.logLevel) { 
			case 0: { 
				return 'OFF'; 
			} 
			case 1: { 
				return 'ERROR'; 
			} 
			case 2: { 
				return 'WARN'; 
			} 
			case 3: { 
				return 'INFO'; 
			} 
			case 4: { 
				return 'DEBUG'; 
			} 
			case 5: { 
				return 'LOG'; 
			} 
			default: { 
				return 'NOT FOUND'; 
			} 
		} 	
	}

}
