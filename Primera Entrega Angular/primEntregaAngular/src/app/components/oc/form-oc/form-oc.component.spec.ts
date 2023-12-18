import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormOcComponent } from './form-oc.component';

describe('FormOcComponent', () => {
  let component: FormOcComponent;
  let fixture: ComponentFixture<FormOcComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FormOcComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FormOcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
