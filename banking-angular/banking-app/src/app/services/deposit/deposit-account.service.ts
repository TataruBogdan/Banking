import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {DepositAccountAppResponse} from "../../interfaces/DepositAccountAppResponse";

@Injectable({
  providedIn: 'root'
})
export class DepositAccountService {

    constructor(
      private http: HttpClient
    ) { }

    public getAllDepositAccounts(): Observable<DepositAccountAppResponse> {

        const url: string = `http://localhost:8300/accounts-deposit`;
        const authToken: string | null = localStorage.getItem('TOKEN');

        const headers: HttpHeaders = new HttpHeaders({
            'Access-Control-Allow-Origin': 'http://localhost:4200',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${authToken}`,
            'Access-Control-Allow-Methods':'GET, PUT, POST, DELETE, PATCH, OPTIONS'
        });

        const options:{headers: HttpHeaders} = {headers: headers};
        return this.http.get<DepositAccountAppResponse>(url, options);

    }

}
