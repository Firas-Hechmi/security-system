import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestMonitorComponent } from './request-monitor.component';

describe('RequestMonitorComponent', () => {
  let component: RequestMonitorComponent;
  let fixture: ComponentFixture<RequestMonitorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestMonitorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestMonitorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
