import { Injectable } from '@angular/core';
import {BehaviorSubject, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {

    private triggerGetAccounts = new Subject<void>();
    triggerGetAccounts$ = this.triggerGetAccounts.asObservable();

    constructor() { }

    triggerGetAccountsMethod() {
        this.triggerGetAccounts.next();
    }
}
