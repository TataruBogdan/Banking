import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import axios, {AxiosRequestConfig, AxiosResponse} from "axios";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

    constructor(private http:HttpClient) {

    }

    isUserLoggedIn(): boolean {
        let user = localStorage.getItem('AUTHENTICATED_USER');
        console.log(user);
        return !(user === null);
    }


    logout():void {
      localStorage.removeItem('AUTHENTICATED_USER');
      localStorage.removeItem("TOKEN");
    }

    getToken(): string | null {
        let token = localStorage.getItem('TOKEN');
        console.log('this is the token' + token);
        return token;
    }

    isAuthenticated(): string | null {
        return localStorage.getItem('TOKEN');
    }

    getUser(): string | null {
        return localStorage.getItem('AUTHENTICATED_USER')
    }

    public executeAuthenticationService(email: string, password: string): Promise<AxiosResponse> {

        const credentials = {
            email: email,
            password: password
        }

        let promise = axios.post("http://localhost:8080/login", credentials);
        console.log("PROMISE" + promise);
        //return promise;
        return promise
    }

}
