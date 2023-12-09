import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserAppResponse} from "../../interfaces/UserAppResponse";
import {CLIENT_API_URL} from "../../../assets/const/app.const";

@Injectable({
  providedIn: 'root'
})
export class UserService {

    constructor(
        private http: HttpClient
    ) { }

    public getAllUsers(): Observable<UserAppResponse> {

        const url: string = `${CLIENT_API_URL}/individuals`;

        return this.http.get<UserAppResponse>(url);
    }

}
