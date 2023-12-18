import {Component, OnDestroy, OnInit} from '@angular/core';
import {DepositAccountAppResponse} from "../interfaces/DepositAccountAppResponse";
import {ActivatedRoute} from "@angular/router";
import {DepositAccountService} from "../services/deposit/deposit-account.service";
import {SharedDataService} from "../services/sharedData/shared-data.service";


@Component({
  selector: 'app-deposit-account',
  templateUrl: './deposit-account.component.html',
  styleUrls: ['./deposit-account.component.css']
})
export class DepositAccountComponent implements OnInit, OnDestroy{

    depositAccountResponse: DepositAccountAppResponse[] = [];
    message: string = '';
    iban: string = '';

    constructor(
        private route: ActivatedRoute,
        private depositAccountService: DepositAccountService,
        private sharedService: SharedDataService
    ) {}

    ngOnDestroy(): void {
    }

    ngOnInit(): void {
        this.iban = this.route.snapshot.params['iban'];
        this.refreshDeposit();
    }

    getAllAccountsDeposit() {
        this.depositAccountService.getAllDepositAccounts().subscribe({
            next:(response:any) => {
                console.log(response)
                this.depositAccountResponse = response
                this.message = 'Accounts deposit found'
            },
            error:(err: 'Error on getting deposit accounts') => console.log(err)
        })
    }

    refreshDeposit() {
        this.getAllAccountsDeposit();
    }

    getDate() {
        this.depositAccountResponse.entries();

    }

}
