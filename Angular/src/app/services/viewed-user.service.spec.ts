import { TestBed, inject } from '@angular/core/testing';

import { ViewedUserService } from './viewed-user.service';

describe('ViewedUserService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ViewedUserService]
    });
  });

  it('should be created', inject([ViewedUserService], (service: ViewedUserService) => {
    expect(service).toBeTruthy();
  }));
});
