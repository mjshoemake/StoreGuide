import { Component, Injectable, Input } from '@angular/core';
import { Logger } from 'angular2-logger/app/core/logger';
import { LogService } from '../log.service';

@Component({
	selector: 'collapsiblePanel',
	templateUrl: './collapsible-panel.component.html',
	styleUrls: []
})

@Injectable()
export class CollapsiblePanelComponent  {
	@Input() panelStyle: string = 'block';
	@Input() collapseClass: string = 'collapse';
	@Input() title: string = '[CollapsiblePanel Title]';
	@Input() id: string = '1';

	log: LogService;

	constructor(private _logger: LogService) {
		this.log = _logger;
		this.log.info('CollapsiblePanel.constructor() BEGIN');
	}

}
