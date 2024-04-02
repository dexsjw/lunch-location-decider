import { Injectable } from '@angular/core';
import { LunchSession } from '../models/lunch-session';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  LUNCH_SESSION_OWNER_CODE: string = "LUNCH_SESSION_OWNER_CODE";

  lunchSession: LunchSession = {
    id: 0,
    ownerCode: "",
    roomCode: "",
    activeStatus: false,
    restaurants: "",
    restaurantsList: []
  }

  constructor() { }

  saveLunchSessionOwnerCodeLocally(ownerCode: string) {
    localStorage.setItem(this.LUNCH_SESSION_OWNER_CODE, ownerCode);
  }

  hasLunchSessionOwnerCodeLocally(ownerCode: string): boolean {
    const oc: string = localStorage.getItem(this.LUNCH_SESSION_OWNER_CODE) ?? "";
    return ownerCode === oc;
  }

  clearLunchSessionOwnerCodeLocally() {
    localStorage.clear();
  }

}
