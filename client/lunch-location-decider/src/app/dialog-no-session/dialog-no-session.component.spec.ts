import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogNoSessionComponent } from './dialog-no-session.component';

describe('DialogNoSessionComponent', () => {
  let component: DialogNoSessionComponent;
  let fixture: ComponentFixture<DialogNoSessionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DialogNoSessionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DialogNoSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
