import { TestBed } from '@angular/core/testing';

import { DataService } from './data.service';

describe('DataService', () => {
  let service: DataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataService);
  });

  it('lunchSessionResponse should be created', () => {
    expect(service.lunchSessionResponse).toBeTruthy();
    expect(service.lunchSessionResponse).toEqual({
      roomId: "",
      hasOwnerCode: false,
      activeStatus: false,
      restaurants: "",
      message: ""
    });
  });
});
