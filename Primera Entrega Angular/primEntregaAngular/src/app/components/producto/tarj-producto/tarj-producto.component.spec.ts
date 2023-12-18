import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TarjProductoComponent } from './tarj-producto.component';

describe('TarjProductoComponent', () => {
  let component: TarjProductoComponent;
  let fixture: ComponentFixture<TarjProductoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TarjProductoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TarjProductoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
