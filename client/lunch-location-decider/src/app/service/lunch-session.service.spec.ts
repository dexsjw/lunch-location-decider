import { TestBed } from '@angular/core/testing';

import { LunchSessionService } from './lunch-session.service';

describe('LunchSessionService', () => {
  let service: LunchSessionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LunchSessionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
