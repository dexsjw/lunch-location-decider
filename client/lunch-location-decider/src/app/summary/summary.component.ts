import { Component, OnInit } from '@angular/core';
import { LunchSessionService } from '../service/lunch-session.service';
import { Router } from '@angular/router';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrl: './summary.component.css'
})
export class SummaryComponent implements OnInit {

  roomCode: string = "";
  restaurant: string = "";

  constructor(private dataSvc: DataService, private lunchSessionSvc: LunchSessionService, private router: Router) {}

  ngOnInit(): void {
    this.roomCode = this.dataSvc.lunchSession.roomCode;
    this.restaurant = this.dataSvc.lunchSession.restaurants ?? "N.A.";
  }

  navigateBackToMain() {
    this.router.navigate(['/main']);
  }

}
