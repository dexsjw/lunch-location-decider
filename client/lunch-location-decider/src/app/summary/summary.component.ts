import { Component, OnInit } from '@angular/core';
import { LunchSessionService } from '../service/lunch-session.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LunchSession } from '../models/lunch-session';

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrl: './summary.component.css'
})
export class SummaryComponent implements OnInit {

  roomCode: string = this.activatedRoute.snapshot.params['roomCode'];
  restaurant: string = "";

  lunchSession: LunchSession = {
    id: 0,
    ownerCode: "",
    roomCode: this.roomCode,
    activeStatus: false,
    restaurants: "",
    restaurantsList: []
  }

  constructor(private activatedRoute: ActivatedRoute, private lunchSessionSvc: LunchSessionService, private router: Router) {}

  ngOnInit(): void {
    this.lunchSessionSvc.findLunchSession(this.lunchSession)
      .then(lunchSession => {
        console.info(lunchSession);
        this.restaurant = lunchSession.restaurants ?? "N.A."
      })
      .catch(err => console.error(err));
  }

  navigateBackToMain() {
    this.router.navigate(['/main']);
  }

}
