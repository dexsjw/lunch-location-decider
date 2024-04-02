import { HttpClient } from '@angular/common/http';
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
