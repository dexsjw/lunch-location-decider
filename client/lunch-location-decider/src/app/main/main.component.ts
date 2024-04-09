import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { LunchSessionService } from '../service/lunch-session.service';
import { Router } from '@angular/router';
import { LunchSessionRequest, LunchSessionResponse } from '../models/lunch-session';
import { MatDialog } from '@angular/material/dialog';
import { DialogNoSessionComponent } from '../dialog-no-session/dialog-no-session.component';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent{

  lunchSessionRequestForm: FormGroup = this.fb.group({
    roomId: this.fb.control(''),
    ownerCode: this.fb.control(''),
    activeStatus: this.fb.control(true),
    restaurant: this.fb.control('')
  });

  constructor(private lunchSessionSvc: LunchSessionService, private dataSvc: DataService, 
    private fb: FormBuilder, private router: Router, public dialog: MatDialog) {}

  openDialog(lunchSessionResponse: LunchSessionResponse) {
    const dialogRef = this.dialog.open(DialogNoSessionComponent, {data: lunchSessionResponse})
  }

  createNewLunchSession() {
    this.lunchSessionSvc.newLunchSession()
      .then(lunchSessionResponse => {
        this.dataSvc.lunchSessionResponse = lunchSessionResponse;

        localStorage.setItem(lunchSessionResponse.roomId ?? "", lunchSessionResponse.message ?? "");
        this.router.navigate(['/room']);
      })
      .catch(err => console.error(err.error));
  }

  findLunchSessionByRoomCode() {
    const roomId: string = this.lunchSessionRequestForm.get("roomId")?.value ?? "";
    const ownerCode: string = localStorage.getItem(roomId) ?? "";
    this.lunchSessionRequestForm.get("ownerCode")?.setValue(ownerCode);
    const lunchSessionRequest: LunchSessionRequest = this.lunchSessionRequestForm.value;

    this.lunchSessionSvc.findLunchSession(lunchSessionRequest)
      .then(lunchSessionResponse => {
        this.dataSvc.lunchSessionResponse = lunchSessionResponse;

        if (!lunchSessionResponse.activeStatus) {
          this.router.navigate(['/summary']);
        } else {
          this.router.navigate(['/room']);
        }
      })
      .catch(err => {
        this.openDialog(err.error);
      });
  }

}
