import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TarjOcComponent } from './tarj-oc.component';

describe('TarjOcComponent', () => {
  let component: TarjOcComponent;
  let fixture: ComponentFixture<TarjOcComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TarjOcComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TarjOcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
