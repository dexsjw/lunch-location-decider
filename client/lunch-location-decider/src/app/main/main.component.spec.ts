import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainComponent } from './main.component';
import { LunchSessionResponse } from '../models/lunch-session';
import { LunchSessionService } from '../service/lunch-session.service';
import { DataService } from '../service/data.service';

describe('MainComponent', () => {
  let component: MainComponent;
  let fixture: ComponentFixture<MainComponent>;
  class MockLunchSessionService {
    mockResponse: LunchSessionResponse = {
      roomId: '4df4b1b5-af80-475a-b6f7-f47e381678f3', 
      hasOwnerCode: true, 
      activeStatus: true, 
      restaurants: '', 
      message: '88cb0b20'
    };
  }

  class MockDataService {
    mockData: LunchSessionResponse = {
      roomId: '4df4b1b5-af80-475a-b6f7-f47e381678f3', 
      hasOwnerCode: true, 
      activeStatus: true, 
      restaurants: '', 
      message: '88cb0b20'
    };
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MainComponent],
      providers: [MainComponent, 
        { provide: LunchSessionService, useClass: MockLunchSessionService },
        { provide: DataService, useClass: MockDataService }
      ]
    })
    .compileComponents();

    component = TestBed.inject(MainComponent);
    // service = TestBed.inject(LunchSessionService);
    // dataSvc = TestBed.inject(LunchSessionService);
    
    fixture = TestBed.createComponent(MainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
