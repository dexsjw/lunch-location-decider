import { TestBed } from '@angular/core/testing';

import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController, TestRequest } from '@angular/common/http/testing';
import { LunchSessionRequest, LunchSessionResponse } from '../models/lunch-session';

describe('LunchSessionService', () => {
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  const BASE_URL: string = "/lunch/session";

  const testReq: LunchSessionRequest = {
    roomId: '4df4b1b5-af80-475a-b6f7-f47e381678f3', 
    ownerCode: '4a03d509', 
    activeStatus: true, 
    restaurant: ''
  }

  const testRespHasOwnerCode: LunchSessionResponse = {
    roomId: '4df4b1b5-af80-475a-b6f7-f47e381678f3', 
    hasOwnerCode: true, 
    activeStatus: true, 
    restaurants: '', 
    message: '88cb0b20'
  }

  const testRespNoOwnerCode: LunchSessionResponse = {
    roomId: '4df4b1b5-af80-475a-b6f7-f47e381678f3', 
    hasOwnerCode: false, 
    activeStatus: true, 
    restaurants: '', 
    message: ''
  }

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ]
    });
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  })

  it('#newLunchSession should return LunchSessionResponse', () => {
    httpClient.get<LunchSessionResponse>(BASE_URL + "/new")
      .subscribe(data => {
        expect(data).toEqual(testRespHasOwnerCode);
      });
    
    const req: TestRequest = httpTestingController.expectOne(BASE_URL + "/new");
    expect(req.request.method).toEqual('GET');
    req.flush(testRespHasOwnerCode);
  });

  it('#findLunchSession should return LunchSessionResponse', () => {
    httpClient.post<LunchSessionResponse>(BASE_URL + "/find", testReq)
      .subscribe(data => {
        expect(data).toEqual(testRespNoOwnerCode);
      });
    
    const req = httpTestingController.expectOne(BASE_URL + "/find");
    expect(req.request.method).toEqual('POST');
    req.flush(testRespNoOwnerCode);
  });

  it('#updateLunchSessionRestaurants should return LunchSessionResponse', () => {
    httpClient.put<LunchSessionResponse>(BASE_URL + "/update", testReq)
      .subscribe(data => {
        expect(data).toEqual(testRespNoOwnerCode);
      });
    
    const req = httpTestingController.expectOne(BASE_URL + "/update");
    expect(req.request.method).toEqual('PUT');
    req.flush(testRespNoOwnerCode);
  });

  it('#endLunchSession should return LunchSessionResponse', () => {
    httpClient.put<LunchSessionResponse>(BASE_URL + "/end", testReq)
      .subscribe(data => {
        expect(data).toEqual(testRespNoOwnerCode);
      });
    
    const req = httpTestingController.expectOne(BASE_URL + "/end");
    expect(req.request.method).toEqual('PUT');
    req.flush(testRespNoOwnerCode);
  });

});
