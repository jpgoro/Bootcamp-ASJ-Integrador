import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TarjProveedorComponent } from './tarj-proveedor.component';

describe('TarjProveedorComponent', () => {
  let component: TarjProveedorComponent;
  let fixture: ComponentFixture<TarjProveedorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TarjProveedorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TarjProveedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
