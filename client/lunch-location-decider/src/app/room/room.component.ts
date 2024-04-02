import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LunchSessionService } from '../service/lunch-session.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrl: './room.component.css'
})
export class RoomComponent implements OnInit {

  ownerCode: string = this.activatedRoute.snapshot.params['ownerCode'];
  hasOwnerCode: boolean = false;

  dataSource: string[] = [];
  displayedColumns: string[] = ["restaurants"]

  lunchSessionForm: FormGroup = this.fb.group({
    id: this.fb.control(''),
    ownerCode: this.fb.control(this.ownerCode),
    roomCode: this.fb.control(this.ownerCode.substring(9)),
    activeStatus: this.fb.control(''),
    restaurants: this.fb.control(''),
    restaurantsList: this.fb.array([])
  });

  constructor(private activatedRoute: ActivatedRoute, private lunchSessionSvc: LunchSessionService, 
    private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.hasOwnerCode = this.lunchSessionSvc.hasLunchSessionOwnerCodeLocally(this.ownerCode);
    this.lunchSessionSvc.findLunchSession(this.lunchSessionForm.value)
      .then(lunchSession => this.dataSource = lunchSession.restaurantsList ?? [])
      .catch(err => console.error(err));
  }  

  navigateBackToMain() {
    this.router.navigate(['/main']);
  }

  refreshTable() {
    this.lunchSessionSvc.findLunchSession(this.lunchSessionForm.value)
      .then(lunchSession => {
        if (!lunchSession.activeStatus) {
          this.router.navigate(['/summary', lunchSession.roomCode]);
        }
        this.dataSource = lunchSession.restaurantsList ?? [];
      })
      .catch(err => console.error(err));
  }

  submitRestaurantName() {
    this.lunchSessionSvc.updateLunchSessionRestaurants(this.lunchSessionForm.value)
      .then(lunchSession => this.dataSource = lunchSession.restaurantsList ?? [])
      .catch(err => console.error(err));
    this.lunchSessionForm.get("restaurants")?.setValue('');
  }

  endLunchSession() {
    this.lunchSessionSvc.endLunchSession(this.lunchSessionForm.value)
      .then(lunchSession => this.router.navigate(['/summary', lunchSession.roomCode]))
      .catch(err => console.error(err));
    this.lunchSessionSvc.deleteLunchSessionOwnerCodeLocally(this.ownerCode);
  }

}
