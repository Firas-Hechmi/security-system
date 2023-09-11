import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestBodyInfoComponent } from './request-body-info.component';

describe('RequestBodyInfoComponent', () => {
  let component: RequestBodyInfoComponent;
  let fixture: ComponentFixture<RequestBodyInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestBodyInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestBodyInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
