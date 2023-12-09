import {Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "../auth/auth.service";
import {Observable} from "rxjs";
import {IndividualAppResponse} from "../../interfaces/IndividualAppResponse";
import {CLIENT_API_URL} from "../../../assets/const/app.const";

@Injectable({
  providedIn: 'root'
})
export class IndividualService {

    constructor(
        private http: HttpClient,
    ) {}


    public getIndividualById(id: number): Observable<IndividualAppResponse> {
        let httpHeaders: HttpHeaders = new HttpHeaders({
            'Access-Control-Allow-Origin': 'http://localhost:4200',
            'Authorization': `Bearer ${localStorage.getItem('TOKEN')}`
        })

        const url: string = `${CLIENT_API_URL}/individuals/get?id=${id}`;

        return this.http.get<IndividualAppResponse>(url, {headers: httpHeaders});
    }

    public getAllIndividuals(): Observable<IndividualAppResponse> {

        const url: string = `${CLIENT_API_URL}/individuals`;
        return this.http.get<IndividualAppResponse>(url);
    }



}
