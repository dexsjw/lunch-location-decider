import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LunchSessionRequest, LunchSessionResponse } from '../models/lunch-session';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LunchSessionService {

  BASE_URL: string = "/lunch/session";

  constructor(private httpClient: HttpClient) { }

  newLunchSession(): Promise<LunchSessionResponse> {
    return lastValueFrom(
      this.httpClient.get<LunchSessionResponse>(this.BASE_URL + '/new')
    );
  }

  findLunchSession(lunchSessionRequest: LunchSessionRequest): Promise<LunchSessionResponse> {
    return lastValueFrom(
      this.httpClient.post<LunchSessionResponse>(this.BASE_URL + '/find', lunchSessionRequest)
    );
  }

  updateLunchSessionRestaurants(lunchSessionRequest: LunchSessionRequest): Promise<LunchSessionResponse> {
    return lastValueFrom(
      this.httpClient.put<LunchSessionResponse>(this.BASE_URL + '/update', lunchSessionRequest)
    );
  }

  endLunchSession(lunchSessionRequest: LunchSessionRequest): Promise<LunchSessionResponse> {
    return lastValueFrom(
      this.httpClient.put<LunchSessionResponse>(this.BASE_URL + '/end', lunchSessionRequest)
    );
  }

}
