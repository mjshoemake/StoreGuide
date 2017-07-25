import { Component, Injectable, Input } from '@angular/core';
import { Logger } from 'angular2-logger/app/core/logger';
import { LogService } from './log.service';
import { BrandIcon } from './brand-icon';
import { NavItem } from './nav-item';

@Component({
	selector: 'appHeader',
	templateUrl: './header.component.html',
	styleUrls: []
})

@Injectable()
export class HeaderComponent  {
	@Input() brandIcon = new BrandIcon({
			image: '',
			displayText: ''
		});
	@Input() headerDisplay: string = 'block';
	@Input() brandForename: string = 'Child';
	@Input() brandSurname: string = 'Brand';
	@Input() navItems: NavItem[] = [];

	log: LogService;

	constructor(private _logger: LogService) {
		this.log = _logger;
		this.log.debug('HeaderComponent.constructor() BEGIN');
		this.log.info('HeaderComponent.constructor() BEGIN');
		this.log.info('HeaderComponent.constructor()    NavItem count=' + this.navItems.length);
	}

	closeHamburgerMenu() {
	  let button = document.getElementById('hamburgerButton');
	  let collapsible = document.getElementById('div-navbar-collapsible');
	  this.log.info('Collapsing menu after page change...');
	  if (button) {
	    button.className='navbar-toggle collapsed';
	    this.log.info('   HamburgerButton.class==' + button.className);
	  }
	  if (collapsible) {
	    collapsible.className='navbar-collapse collapse';
	    this.log.info('   CollapsibleDiv.class==' + button.className);
	  }
	}

}
