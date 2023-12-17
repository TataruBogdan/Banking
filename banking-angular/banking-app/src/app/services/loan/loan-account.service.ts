import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {LoanAccountAppResponse} from "../../interfaces/LoanAccountAppResponse";

@Injectable({
  providedIn: 'root'
})
export class LoanAccountService {

    constructor(
        private http: HttpClient
    ) { }

    public getAllLoanAccounts(): Observable<LoanAccountAppResponse> {

        const url: string = `http://localhost:8400/accounts-loan`;
        const authToken = localStorage.getItem('TOKEN');

        const headers: HttpHeaders = new HttpHeaders({
            'Access-Control-Allow-Origin': 'http://localhost:4200',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${authToken}`,
            'Access-Control-Allow-Methods':'GET, PUT, POST, DELETE, PATCH, OPTIONS'
        });

        const options:{headers: HttpHeaders} = {headers: headers};

        return this.http.get<LoanAccountAppResponse>(url, options);
    }

}
