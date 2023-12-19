import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {CurrentAccountAppResponse} from "../../interfaces/CurrentAccountAppResponse";
import {CURRENT_ACCOUNT_API_URL} from "../../../assets/const/app.const";

@Injectable({
  providedIn: 'root'
})
export class CurrentAccountService {

    constructor(
      private http: HttpClient
    ) { }

    public getAllCurrentAccounts(): Observable<CurrentAccountAppResponse> {

        const url: string = `http://localhost:8200/accounts-current`;
        const authToken = localStorage.getItem('TOKEN');

        const headers: HttpHeaders = new HttpHeaders({
            'Access-Control-Allow-Origin': 'http://localhost:4200',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${authToken}`,
            'Access-Control-Allow-Methods':'GET, PUT, POST, DELETE, PATCH, OPTIONS'
        });

        const options:{headers: HttpHeaders} = {headers: headers};

        return this.http.get<CurrentAccountAppResponse>(url, options);
    }

    public getAccountByIndividualId(id: number): Observable<CurrentAccountAppResponse> {

        const url: string = `${CURRENT_ACCOUNT_API_URL}/account-current/${id}`;
        const authHeader = new HttpHeaders( {
            'Authorization': `Bearer ${localStorage.getItem('TOEKN')}`,
            'Content-Type': 'application/json'
        });
        //must send the headers?
        return this.http.get<CurrentAccountAppResponse>(url);
    }
}
