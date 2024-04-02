import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { LunchSessionService } from '../service/lunch-session.service';
import { Router } from '@angular/router';
import { LunchSession } from '../models/lunch-session';
import { MatDialog } from '@angular/material/dialog';
import { DialogNoSessionComponent } from '../dialog-no-session/dialog-no-session.component';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent{

  lunchSessionForm: FormGroup = this.fb.group({
    id: this.fb.control(''),
    ownerCode: this.fb.control(''),
    roomCode: this.fb.control(''),
    activeStatus: this.fb.control(''),
    restaurants: this.fb.control(''),
    restaurantsList: this.fb.array([])
  });

  constructor(private lunchSessionSvc: LunchSessionService, private fb: FormBuilder, private router: Router,
    public dialog: MatDialog) {}

  openDialog(lunchSession: LunchSession) {
    const dialogRef = this.dialog.open(DialogNoSessionComponent, {data: lunchSession})
  }

  createNewLunchSession() {
    const lunchSession: LunchSession = this.lunchSessionForm.value;
    this.lunchSessionSvc.newLunchSession(lunchSession)
      .then(lunchSession => {
        this.lunchSessionSvc.saveLunchSessionOwnerCodeLocally(lunchSession.ownerCode);
        this.router.navigate(['/room', lunchSession.ownerCode]);
      })
      .catch(err => console.error(err));
  }

  findLunchSessionByRoomCode() {
    const lunchSession: LunchSession = this.lunchSessionForm.value;
    this.lunchSessionSvc.findLunchSession(lunchSession)
      .then(lunchSession => {
        if (lunchSession.restaurants?.startsWith("<Error>:")) {
          this.openDialog(lunchSession);
        } else if (!lunchSession.activeStatus) {
          this.router.navigate(['/summary', lunchSession.roomCode]);
        } else {
          this.router.navigate(['/room', lunchSession.ownerCode]);
        }
      })
      .catch(err => console.error(err));
  }

}
