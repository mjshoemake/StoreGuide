import { Component, Injectable, Input, Output, EventEmitter } from '@angular/core';
import { Logger } from 'angular2-logger/app/core/logger';
import { LogService } from '../log.service';

@Component({
	selector: 'collapsibleSubPanel',
	templateUrl: './collapsible-sub-panel.component.html',
	styleUrls: []
})

@Injectable()
export class CollapsibleSubPanelComponent {
	@Input() pretitle: string = '';
	@Input() boldtitle: string = '';
	@Input() posttitle: string = '';
	@Input() title: string = '';
	@Input() panelClass: string = '';
	@Input() collapseClass: string = 'panel-collapse collapse';
	@Input() id: string = '1';
	@Input() dataParent: string = '';
  @Output() click: EventEmitter<String> = new EventEmitter<String>();

	log: LogService;

	constructor(private _logger: LogService) {
		this.log = _logger;
		this.log.info('CollapsibleSubPanel.constructor() BEGIN');
	}

  onClick() {
    //then when the button is clicked, emit events to the parent.
		this.log.info('CollapsibleSubPanel.onClick() boldtitle=' + this.boldtitle);
    this.click.emit(this.boldtitle);
  }
}
