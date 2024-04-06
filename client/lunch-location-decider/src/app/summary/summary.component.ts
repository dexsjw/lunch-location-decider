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

  roomId: string = "";
  restaurant: string = "";

  constructor(private dataSvc: DataService, private lunchSessionSvc: LunchSessionService, private router: Router) {}

  ngOnInit(): void {
    if (this.dataSvc.lunchSessionResponse.roomId.length <= 0) {
      this.router.navigate(['/main']);
    }
    this.roomId = this.dataSvc.lunchSessionResponse.roomId ?? "";
    this.restaurant = this.dataSvc.lunchSessionResponse.restaurants ?? "N.A.";
  }

  navigateBackToMain() {
    this.router.navigate(['/main']);
  }

}
