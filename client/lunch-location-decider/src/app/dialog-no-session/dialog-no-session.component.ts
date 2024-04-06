import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LunchSession } from '../models/lunch-session';

@Component({
  selector: 'app-dialog-no-session',
  templateUrl: './dialog-no-session.component.html',
  styleUrl: './dialog-no-session.component.css'
})
export class DialogNoSessionComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public lunchSessionData: LunchSession) {}

}
