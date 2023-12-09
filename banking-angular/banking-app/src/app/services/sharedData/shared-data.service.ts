import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {

    private userNameSubject = new BehaviorSubject<string>('');
    userNam$ = this.userNameSubject.asObservable();

    constructor() { }

    setUserName(name: string) {
        this.userNameSubject.next(name);
    }
}
