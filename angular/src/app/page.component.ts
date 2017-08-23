import { Component } from '@angular/core';
import { Injectable } from '@angular/core';
import { Logger } from 'angular2-logger/app/core/logger';
import { LogService } from './log.service';
import { BrandIcon } from './brand-icon';
import { NavItem } from './nav-item';
import { NavItemService } from './nav-item.service';
import { HeaderComponent } from './header.component';

@Component({
	selector: 'page',
	templateUrl: './page.component.html',
	providers: [HeaderComponent],
	styleUrls: []
})

@Injectable()
export class PageComponent  {
	pageName: string = '[PageName]';
	subTitle: string = '[SubTitle]';

	// Preferences
	showBreadcrumbs: boolean = true;
	showHint: boolean = true;
	headerDisplay: string = 'block';
	brandForename: string = 'Map My Shop';
	brandSurname: string = '';

	brandIcon = new BrandIcon({
			image: 'assets/images/crossewalk3.png',
			displayText: 'Map My Shop'
		});

	navItems: NavItem[] = []
	navItemService: NavItemService;
	headerComp: HeaderComponent;

	// Messages
	//alertMsg: string = 'Danger, Will Robinson!!  Danger!!';
	alertMsg: string = undefined;
	alertType: string = 'alert-success';

	log: LogService;

	constructor(private _logger: LogService,
	            private _navItemService: NavItemService,
	            private _headerComp: HeaderComponent) {
    // Arguments
		this.log = _logger;
		this.navItemService = _navItemService;

		this.log.debug('PageComponent.constructor() BEGIN');
		this.log.info('PageComponent.constructor() BEGIN');
    this.navItems = this.navItemService.getAll();
		this.log.info('PageComponent.constructor()    NavItem count=' + this.navItems.length);
		this.headerComp = _headerComp;
	}

	closeHamburgerMenu() {
	  this.headerComp.closeHamburgerMenu();
	}


}
