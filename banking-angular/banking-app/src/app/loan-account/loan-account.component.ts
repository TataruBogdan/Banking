import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {LoanAccountAppResponse} from "../interfaces/LoanAccountAppResponse";
import {LoanAccountService} from "../services/loan/loan-account.service";

@Component({
  selector: 'app-loan-account',
  templateUrl: './loan-account.component.html',
  styleUrls: ['./loan-account.component.css']
})
export class LoanAccountComponent implements OnInit{

    loanAccountResponse: LoanAccountAppResponse[] = [];
    message: string = '';


    constructor(
        private route: ActivatedRoute,
        private loanAccountService: LoanAccountService
    ) {
    }

    ngOnInit(): void {
        this.getAllDepositAccounts();
    }

    getAllDepositAccounts() {
        this.loanAccountService.getAllLoanAccounts().subscribe({
            next:(response: any) => {
                console.log(response)
                this.loanAccountResponse = response
                this.message ='Deposit accounts found'
            },
            error:(err: 'Error on getting deposit accounts') => console.log(err)
        })
    }
}
