import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

// Declarations
import { CollapsiblePanelComponent } from './collapsible-panel.component';
import { CollapsibleSubPanelComponent } from './collapsible-sub-panel.component';
import { TitledPanelComponent } from './titled-panel.component';

@NgModule({
  imports: [ CommonModule ],
  declarations: [
  	CollapsiblePanelComponent,
  	CollapsibleSubPanelComponent,
  	TitledPanelComponent
  ],
  exports: [
  	CollapsiblePanelComponent,
  	CollapsibleSubPanelComponent,
  	TitledPanelComponent,
    CommonModule,
    FormsModule
  ]
})
export class SharedModule { }
