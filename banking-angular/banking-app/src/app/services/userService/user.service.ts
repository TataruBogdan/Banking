import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserAppResponse} from "../../interfaces/UserAppResponse";
import {CLIENT_API_URL} from "../../../assets/const/app.const";
import {AxiosResponse} from "axios";
import {IndividualAppResponse} from "../../interfaces/IndividualAppResponse";

@Injectable({
  providedIn: 'root'
})
export class UserService {

    constructor(
        private http: HttpClient
    ) { }

    public getAllUsers(): Observable<UserAppResponse> {

        const url: string = `http://localhost:8080/users`;
        const authToken = localStorage.getItem('TOKEN');

        // Define the headers you want to send
        const headers = new HttpHeaders({
            'Access-Control-Allow-Origin': 'http://localhost:4200',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${authToken}`,
            'Access-Control-Allow-Methods':'GET, PUT, POST, DELETE, PATCH, OPTIONS'
        });

        const options = { headers: headers };

        return this.http.get<UserAppResponse>(url, options);
    }
}
