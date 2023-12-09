import { TestBed } from '@angular/core/testing';

import { HttpInterceptorUserAuthService } from './http-interceptor-user-auth.service';

describe('HttpInterceptorUserAuthService', () => {
  let service: HttpInterceptorUserAuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpInterceptorUserAuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
