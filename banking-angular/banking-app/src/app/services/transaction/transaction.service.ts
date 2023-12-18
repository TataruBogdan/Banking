import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {TransactionAppResponse} from "../../interfaces/TransactionAppResponse";
import {CurrentAccountAppResponse} from "../../interfaces/CurrentAccountAppResponse";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

    constructor(
        private http: HttpClient
    ) { }

    public getAllTransactionsWithStatus(status: string[]) : Observable<TransactionAppResponse> {

        const url: string = `http://localhost:8500/transactions/search-by-status`;
        const authToken : string |null = localStorage.getItem('TOKEN');

        const headers: HttpHeaders = new HttpHeaders( {
            'Access-Control-Allow-Origin': 'http://localhost:4200',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${authToken}`,
            'Access-Control-Allow-Methods':'GET, PUT, POST, DELETE, PATCH, OPTIONS'
        });

        const options:{headers: HttpHeaders} = {headers: headers};

        return this.http.post<TransactionAppResponse>(url, status, options);
    }
}
