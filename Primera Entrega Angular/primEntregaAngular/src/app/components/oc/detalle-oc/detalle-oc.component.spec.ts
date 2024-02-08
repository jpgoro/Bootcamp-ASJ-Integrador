import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleOcComponent } from './detalle-oc.component';

describe('DetalleOcComponent', () => {
  let component: DetalleOcComponent;
  let fixture: ComponentFixture<DetalleOcComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DetalleOcComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DetalleOcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
