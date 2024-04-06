import { Injectable } from '@angular/core';
import { LunchSessionResponse } from '../models/lunch-session';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  lunchSessionResponse: LunchSessionResponse = {
    roomId: "",
    hasOwnerCode: false,
    activeStatus: false,
    restaurants: "",
    message: ""
  }

  constructor() { }

}
