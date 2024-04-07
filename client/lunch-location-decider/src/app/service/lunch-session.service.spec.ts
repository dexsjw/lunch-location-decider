import { TestBed } from '@angular/core/testing';

import { LunchSessionService } from './lunch-session.service';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LunchSessionRequest, LunchSessionResponse } from '../models/lunch-session';

describe('LunchSessionService', () => {
  let service: LunchSessionService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  const BASE_URL: string = "/lunch/session";

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ]
    });
    service = TestBed.inject(LunchSessionService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('#newLunchSession should return LunchSessionResponse', () => {
    const testData: LunchSessionResponse = {
      roomId: '4df4b1b5-af80-475a-b6f7-f47e381678f3', 
      hasOwnerCode: true, 
      activeStatus: true, 
      restaurants: '', 
      message: '88cb0b20'
    }

    httpClient.get<LunchSessionResponse>(BASE_URL + "/new")
      .subscribe(data => {
        expect(data).toEqual(testData);
        // expect(lunchSessionResponse.hasOwnerCode).toEqual(testData.hasOwnerCode);
        // expect(lunchSessionResponse.activeStatus).toEqual(testData.activeStatus);
        // expect(lunchSessionResponse.restaurants).toEqual(testData.restaurants);
      });
    
    const req = httpTestingController.expectOne(BASE_URL + "/new");
    expect(req.request.method).toEqual('GET');
    req.flush(testData);

    httpTestingController.verify();
  });

  it('#findLunchSession should return LunchSessionResponse', () => {
    const testReq: LunchSessionRequest = {
      roomId: '4df4b1b5-af80-475a-b6f7-f47e381678f3', 
      ownerCode: '4a03d509', 
      activeStatus: true, 
      restaurant: ''
    }

    const testData: LunchSessionResponse = {
      roomId: '4df4b1b5-af80-475a-b6f7-f47e381678f3', 
      hasOwnerCode: false, 
      activeStatus: true, 
      restaurants: '', 
      message: ''
    }

    httpClient.post<LunchSessionResponse>(BASE_URL + "/find", testReq)
      .subscribe(data => {
        expect(data).toEqual(testData);
        // expect(lunchSessionResponse.hasOwnerCode).toEqual(testData.hasOwnerCode);
        // expect(lunchSessionResponse.activeStatus).toEqual(testData.activeStatus);
        // expect(lunchSessionResponse.restaurants).toEqual(testData.restaurants);
      });
    
    const req = httpTestingController.expectOne(BASE_URL + "/find");
    expect(req.request.method).toEqual('POST');
    req.flush(testData);

    httpTestingController.verify();
  });

  it('#updateLunchSessionRestaurants should return LunchSessionResponse', () => {
    const testReq: LunchSessionRequest = {
      roomId: '4df4b1b5-af80-475a-b6f7-f47e381678f3', 
      ownerCode: '4a03d509', 
      activeStatus: true, 
      restaurant: ''
    }

    const testData: LunchSessionResponse = {
      roomId: '4df4b1b5-af80-475a-b6f7-f47e381678f3', 
      hasOwnerCode: false, 
      activeStatus: true, 
      restaurants: '', 
      message: ''
    }

    httpClient.put<LunchSessionResponse>(BASE_URL + "/update", testReq)
      .subscribe(data => {
        expect(data).toEqual(testData);
      });
    
    const req = httpTestingController.expectOne(BASE_URL + "/update");
    expect(req.request.method).toEqual('PUT');
    req.flush(testData);

    httpTestingController.verify();
  });

  it('#endLunchSession should return LunchSessionResponse', () => {
    const testReq: LunchSessionRequest = {
      roomId: '4df4b1b5-af80-475a-b6f7-f47e381678f3', 
      ownerCode: '4a03d509', 
      activeStatus: true, 
      restaurant: ''
    }

    const testData: LunchSessionResponse = {
      roomId: '4df4b1b5-af80-475a-b6f7-f47e381678f3', 
      hasOwnerCode: false, 
      activeStatus: true, 
      restaurants: '', 
      message: ''
    }

    httpClient.put<LunchSessionResponse>(BASE_URL + "/end", testReq)
      .subscribe(data => {
        expect(data).toEqual(testData);
      });
    
    const req = httpTestingController.expectOne(BASE_URL + "/end");
    expect(req.request.method).toEqual('PUT');
    req.flush(testData);

    httpTestingController.verify();
  });

});
