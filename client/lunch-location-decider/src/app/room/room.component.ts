import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LunchSessionService } from '../service/lunch-session.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrl: './room.component.css'
})
export class RoomComponent implements OnInit {

  hasOwnerCode: boolean = false;
  roomCode: string = "";

  dataSource: string[] = [];
  displayedColumns: string[] = ["restaurants"]

  lunchSessionForm: FormGroup = this.fb.group({
    id: this.fb.control(''),
    ownerCode: this.fb.control(''),
    roomCode: this.fb.control(''),
    activeStatus: this.fb.control(''),
    restaurants: this.fb.control(''),
    restaurantsList: this.fb.array([])
  });

  constructor(private lunchSessionSvc: LunchSessionService, private dataSvc: DataService,
    private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.hasOwnerCode = this.dataSvc.hasLunchSessionOwnerCodeLocally(this.dataSvc.lunchSession.ownerCode);
    this.roomCode = this.dataSvc.lunchSession.roomCode;
    this.dataSource = this.dataSvc.lunchSession.restaurantsList ?? [];
  }  

  navigateBackToMain() {
    this.router.navigate(['/main']);
  }

  refreshTable() {
    this.lunchSessionSvc.findLunchSession(this.dataSvc.lunchSession)
      .then(lunchSession => {
        this.dataSvc.lunchSession = lunchSession;
        if (!lunchSession.activeStatus) {
          this.router.navigate(['/summary']);
        }
        this.dataSource = lunchSession.restaurantsList ?? [];
      })
      .catch(err => console.error(err));
  }

  submitRestaurantName() {
    this.lunchSessionForm.get("roomCode")?.setValue(this.dataSvc.lunchSession.roomCode);
    this.lunchSessionSvc.updateLunchSessionRestaurants(this.lunchSessionForm.value)
      .then(lunchSession => {
        this.dataSvc.lunchSession = lunchSession
        if (!lunchSession.activeStatus) {
          this.router.navigate(['/summary']);
        }
        this.dataSource = lunchSession.restaurantsList ?? []
      })
      .catch(err => console.error(err));
    this.lunchSessionForm.get("restaurants")?.setValue('');
  }

  endLunchSession() {
    this.lunchSessionSvc.endLunchSession(this.dataSvc.lunchSession)
      .then(lunchSession => {
        this.dataSvc.lunchSession = lunchSession;
        this.dataSvc.clearLunchSessionOwnerCodeLocally();
        this.router.navigate(['/summary']);
      })
      .catch(err => console.error(err));
  }

}
