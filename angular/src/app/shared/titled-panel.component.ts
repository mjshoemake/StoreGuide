import { Component, Injectable, Input } from '@angular/core';
import { Logger } from 'angular2-logger/app/core/logger';
import { LogService } from '../log.service';

@Component({
	selector: 'titledPanel',
	templateUrl: './titled-panel.component.html',
	styleUrls: []
})

@Injectable()
export class TitledPanelComponent  {
	@Input() panelStyle: string = '';
	@Input() title: string = '[TitledPanel Title]';

	log: LogService;

	constructor(private _logger: LogService) {
		this.log = _logger;
		this.log.info('CollapsiblePanel.constructor() BEGIN');
	}

}
