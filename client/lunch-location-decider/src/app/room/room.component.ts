import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LunchSessionService } from '../service/lunch-session.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DataService } from '../service/data.service';
import { LunchSessionResponse } from '../models/lunch-session';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrl: './room.component.css'
})
export class RoomComponent implements OnInit {

  hasOwnerCode: boolean = false;
  roomId: string = "";

  dataSource: string[] = [];
  displayedColumns: string[] = ["restaurants"]

  lunchSessionRequestForm: FormGroup = this.fb.group({
    roomId: this.fb.control(''),
    ownerCode: this.fb.control(''),
    activeStatus: this.fb.control(true),
    restaurant: this.fb.control('', [Validators.required, Validators.minLength(1)])
  });

  constructor(private lunchSessionSvc: LunchSessionService, private dataSvc: DataService,
    private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    if (this.dataSvc.lunchSessionResponse.roomId.length <= 0) {
      this.router.navigate(['/main']);
    }
    this.hasOwnerCode = this.dataSvc.lunchSessionResponse.hasOwnerCode ?? false;
    this.roomId = this.dataSvc.lunchSessionResponse.roomId ?? "";
    const restaurants = this.dataSvc.lunchSessionResponse.restaurants ?? "";
    this.dataSource = restaurants.split(",");
  }  

  navigateBackToMain() {
    this.router.navigate(['/main']);
  }

  refreshTable() {
    this.populateLunchSessionRequestForm(false);

    this.lunchSessionSvc.findLunchSession(this.lunchSessionRequestForm.value)
      .then(lunchSessionResponse => {
        console.info(lunchSessionResponse);
        this.dataSvc.lunchSessionResponse = lunchSessionResponse;
        this.lunchSessionResponseCheck(lunchSessionResponse);
        this.populateDataSource(lunchSessionResponse);
      })
      .catch(err => console.error(err.error));
  }

  submitRestaurantName() {
    this.populateLunchSessionRequestForm(false);

    this.lunchSessionSvc.updateLunchSessionRestaurants(this.lunchSessionRequestForm.value)
      .then(lunchSessionResponse => {
        console.info(lunchSessionResponse);
        this.dataSvc.lunchSessionResponse = lunchSessionResponse;
        this.lunchSessionResponseCheck(lunchSessionResponse);
        this.populateDataSource(lunchSessionResponse);
        this.lunchSessionRequestForm.get("restaurant")?.setValue('');
      })
      .catch(err => console.error(err.error));
  }

  endLunchSession() {
    this.populateLunchSessionRequestForm(true)

    this.lunchSessionSvc.endLunchSession(this.lunchSessionRequestForm.value)
      .then(lunchSessionResponse => {
        console.info(lunchSessionResponse);
        this.dataSvc.lunchSessionResponse = lunchSessionResponse;
        localStorage.removeItem(this.roomId);
        this.router.navigate(['/summary']);
      })
      .catch(err => console.error(err.error));
  }

  populateLunchSessionRequestForm(endSession: boolean) {
    const ownerCode: string = localStorage.getItem(this.roomId) ?? "";
    this.lunchSessionRequestForm.get("ownerCode")?.setValue(ownerCode);
    this.lunchSessionRequestForm.get("roomId")?.setValue(this.roomId);
    if (endSession) {
      this.lunchSessionRequestForm.get("activeStatus")?.setValue(false);
    }
  }

  lunchSessionResponseCheck(lunchSessionResponse: LunchSessionResponse) {
    if (!lunchSessionResponse.activeStatus) {
      this.router.navigate(['/summary']);
    }
    this.hasOwnerCode = lunchSessionResponse.hasOwnerCode ?? false;
  }

  populateDataSource(lunchSessionResponse: LunchSessionResponse) {
    const restaurants = lunchSessionResponse.restaurants ?? "";
    this.dataSource = restaurants.split(",");
  }

}
