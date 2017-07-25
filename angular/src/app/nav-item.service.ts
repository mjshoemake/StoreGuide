import { Inject } from '@angular/core';
import { Injectable } from '@angular/core';
import { NavItem } from './nav-item';
import { Logger } from 'angular2-logger/app/core/logger';
import { LogService } from './log.service';

@Injectable()
// NOTE: For now, there is no DB so data is stored
// in memory only.
export class NavItemService {

	log: LogService;

	// Placeholder for items.
	navItems: NavItem[] = [];

	constructor(private _logger:LogService) {
		this.log = _logger;
		this.log.debug('NavItemService.constructor()');

		// Populate nav items list
	  this.navItems = [
	    new NavItem({
			  href: '/resume',
  			glyphicon: 'glyphicon-folder-open',
	  		displayText: 'Resume'
		  }),
  	  new NavItem({
	  		href: '/articles',
		  	glyphicon: 'glyphicon-pencil',
			  displayText: 'Articles'
  		}),
	    new NavItem({
		  	href: '/downloads',
			  glyphicon: 'glyphicon-download-alt',
		  	displayText: 'Downloads'
	  	}),
	    new NavItem({
		  	href: '/referrals',
			  glyphicon: 'glyphicon-thumbs-up',
		  	displayText: 'Referrals'
	  	})
  	];
	}

	// GET /navitems
	getAll(): NavItem[] {
		this.log.debug('NavItemService.getAll() count=' + this.navItems.length);
		return this.navItems;
	}

}
