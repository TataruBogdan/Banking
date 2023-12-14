import {Component, OnDestroy, OnInit} from '@angular/core';
import {CurrentAccountAppResponse} from "../interfaces/CurrentAccountAppResponse";
import {ActivatedRoute} from "@angular/router";
import {CurrentAccountService} from "../services/current/current-account.service";
import {Subscription} from "rxjs";
import {SharedDataService} from "../services/sharedData/shared-data.service";

@Component({
    selector: 'app-current-account',
    templateUrl: './current-account.component.html',
    styleUrls: ['./current-account.component.css']
})
export class CurrentAccountComponent implements OnInit, OnDestroy{

    private triggerSubscription: Subscription;
    currentAccountsResponse: CurrentAccountAppResponse[] = [];
    message: string = '';
    iban: string = '';


    constructor(
        private route: ActivatedRoute,
        private currentAccountService: CurrentAccountService,
        private sharedService: SharedDataService
    ) {
        this.triggerSubscription = this.sharedService.triggerGetAccounts$.subscribe(
            () => {
                this.getAllCurrentAccounts();
            }
        );
    }

    ngOnInit(): void {
        this.iban = this.route.snapshot.params['iban'];
        this.refreshCurrent();
    }

    ngOnDestroy() {
        this.triggerSubscription.unsubscribe();
    }

    getAllCurrentAccounts() {
        this.currentAccountService.getAllCurrentAccounts().subscribe({
            next:(response: any) => {
                this.currentAccountsResponse = response
                this.message = 'Accounts found'
            },
            error:(err: 'Error on getting current accounts') => console.log(err)
        })
    }

    refreshCurrent() {
        this.getAllCurrentAccounts();
    }


}
