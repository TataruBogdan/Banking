import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserLoginResponse} from "../../../interfaces/UserLoginResponse";

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorUserAuthService implements HttpInterceptor{
    constructor() { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<UserLoginResponse>> {

        const authToken = localStorage.getItem('TOKEN');
        console.log(authToken);
        if (authToken){
            const authRequest = req.clone( {
                setHeaders: {
                    'Access-Control-Allow-Origin': 'http://localhost:4200/',
                    'Authorization': `Bearer ${authToken}`,
                    'Access-Control-Allow-Methods':'GET, PUT, POST, DELETE, PATCH, OPTIONS',
                    'Content-Type': 'application/json'
                }
            });
            return next.handle(authRequest);
        }
        return next.handle(req);
    }


}
