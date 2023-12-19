import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {TransactionAppResponse} from "../../interfaces/TransactionAppResponse";
import {FormGroup} from "@angular/forms";
import {TransactionSearchInputDTO} from "../../interfaces/TransactionSearchInputDTO";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

    constructor(
        private http: HttpClient
    ) { }

    public getAllTransactionsWithStatus(status: TransactionSearchInputDTO) : Observable<string[]> {

        const url: string = `http://localhost:8500/transactions/search-by-status`;
        const authToken : string |null = localStorage.getItem('TOKEN');

        const headers: HttpHeaders = new HttpHeaders( {
            'Access-Control-Allow-Origin': 'http://localhost:4200',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${authToken}`,
            'Access-Control-Allow-Methods':'GET, PUT, POST, DELETE, PATCH, OPTIONS'
        });

        const options:{headers: HttpHeaders} = {headers: headers};

        return this.http.post<string[]>(url, status, options);
    }

    public processTransaction(transactionData: any): Observable<TransactionAppResponse> {

        const fromIban = transactionData.fromIban;
        const toIban = transactionData.toIban;
        const amount = transactionData.amount;

        const url: string = `http://localhost:8500/transactions/fromIban/${fromIban}/toIban/${toIban}`;
        const authToken: string | null = localStorage.getItem('TOKEN');

        const headers: HttpHeaders = new HttpHeaders({
            'Access-Control-Allow-Origin': 'http://localhost:4200',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${authToken}`,
            'Access-Control-Allow-Methods':'GET, PUT, POST, DELETE, PATCH, OPTIONS'
        });

        const options: {headers: HttpHeaders} = { headers: headers};

        return this.http.post<TransactionAppResponse>(url, {amount}, options)

    }

    getTransactionWithId(id: string): Observable<TransactionAppResponse> {
        const url: string = `http://localhost:8500/transactions/${id}`;
        const authToken : string |null = localStorage.getItem('TOKEN');

        const headers: HttpHeaders = new HttpHeaders( {
            'Access-Control-Allow-Origin': 'http://localhost:4200',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${authToken}`,
            'Access-Control-Allow-Methods':'GET, PUT, POST, DELETE, PATCH, OPTIONS'
        });

        const options:{headers: HttpHeaders} = {headers: headers};

        return this.http.get<TransactionAppResponse>(url, options);
    }

    executeTransaction(id: string): Observable<TransactionAppResponse> {
        const url: string = `http://localhost:8500/transactions/execute/${id}`;
        const authToken : string |null = localStorage.getItem('TOKEN');

        const headers: HttpHeaders = new HttpHeaders( {
            'Access-Control-Allow-Origin': 'http://localhost:4200',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${authToken}`,
            'Access-Control-Allow-Methods':'GET, PUT, POST, DELETE, PATCH, OPTIONS'
        });

        const options:{headers: HttpHeaders} = {headers: headers};

        return this.http.patch<TransactionAppResponse>(url, options);
    }
}
