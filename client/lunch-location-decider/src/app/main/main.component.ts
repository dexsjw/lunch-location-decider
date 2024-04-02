import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { LunchSessionService } from '../service/lunch-session.service';
import { Router } from '@angular/router';
import { LunchSession } from '../models/lunch-session';

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

  constructor(private lunchSessionSvc: LunchSessionService, private fb: FormBuilder, private router: Router) {}

  createNewLunchSession() {
    const lunchSession: LunchSession = this.lunchSessionForm.value;
    this.lunchSessionSvc.createNewLunchSession(lunchSession)
      .then(lunchSession => {
        this.lunchSessionSvc.saveLunchSessionOwnerCodeLocally(lunchSession.ownerCode);
        this.router.navigate(['/room']);
      })
      .catch(err => console.error(err));
  }


}
