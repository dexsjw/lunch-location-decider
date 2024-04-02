import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LunchSession } from '../models/lunch-session';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LunchSessionService {

  LUNCH_SESSION_OWNER_CODE: string = "LUNCH_SESSION_OWNER_CODE";
  BASE_URL: string = "/lunch/session";

  constructor(private httpClient: HttpClient) { }

  saveLunchSessionOwnerCodeLocally(ownerCode: string) {
    let ownerCodeObj: {[key: string]: string} = {};

    const ownerCodeMapString: string = localStorage.getItem(this.LUNCH_SESSION_OWNER_CODE) ?? "";
    if (ownerCodeMapString !== null && ownerCodeMapString.length > 0) {
      ownerCodeObj = JSON.parse(ownerCodeMapString);
    } 

    ownerCodeObj[ownerCode] = ownerCode;
    localStorage.setItem(this.LUNCH_SESSION_OWNER_CODE, JSON.stringify(ownerCodeObj));
  }

  hasLunchSessionOwnerCodeLocally(ownerCode: string): boolean {
    const ownerCodeObjString: string = localStorage.getItem(this.LUNCH_SESSION_OWNER_CODE) ?? "";
    if (ownerCodeObjString === null || ownerCodeObjString.length <= 0) {
      return false;
    } else {
      let ownerCodeObj: {[key: string]: string} = JSON.parse(ownerCodeObjString);
      return (ownerCodeObj[ownerCode] !== null && ownerCodeObj[ownerCode] !== "") ? true : false;
    }
  }

  deleteLunchSessionOwnerCodeLocally(ownerCode: string) {
    let ownerCodeObj: {[key: string]: string} = {};

    const ownerCodeMapString: string = localStorage.getItem(this.LUNCH_SESSION_OWNER_CODE) ?? "";
    if (ownerCodeMapString !== null && ownerCodeMapString.length > 0) {
      ownerCodeObj = JSON.parse(ownerCodeMapString);
    }

    delete ownerCodeObj[ownerCode];
    localStorage.setItem(this.LUNCH_SESSION_OWNER_CODE, JSON.stringify(ownerCodeObj));
  }

  newLunchSession(lunchSession: LunchSession): Promise<LunchSession> {
    return lastValueFrom(
      this.httpClient.post<LunchSession>(this.BASE_URL + '/new', lunchSession)
    );
  }

  findLunchSession(lunchSession: LunchSession): Promise<LunchSession> {
    return lastValueFrom(
      this.httpClient.post<LunchSession>(this.BASE_URL + '/find', lunchSession)
    );
  }

  updateLunchSessionRestaurants(lunchSession: LunchSession): Promise<LunchSession> {
    return lastValueFrom(
      this.httpClient.put<LunchSession>(this.BASE_URL + '/update', lunchSession)
    );
  }

  endLunchSession(lunchSession: LunchSession): Promise<LunchSession> {
    return lastValueFrom(
      this.httpClient.put<LunchSession>(this.BASE_URL + '/end', lunchSession)
    );
  }

}
