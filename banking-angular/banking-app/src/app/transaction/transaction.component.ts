import {Component, OnInit} from '@angular/core';
import {TransactionAppResponse} from "../interfaces/TransactionAppResponse";
import {ActivatedRoute} from "@angular/router";
import {TransactionService} from "../services/transaction/transaction.service";

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit{

    transactionsResponse: TransactionAppResponse[] =[];
    private message: string = '';
    statusList: string ='';

    constructor(
        private route: ActivatedRoute,
        private transactionsService: TransactionService
    ) {
    }

    ngOnInit(): void {
        this.filterTransactions();
    }

    filterTransactions() {

        const statusArray = this.statusList.split(',').map(status =>status.trim().toUpperCase());


        if (this.statusList.trim() === '') {
            this.statusList
        }
        this.transactionsService.getAllTransactionsWithStatus(statusArray).subscribe({
            next: (response: any) => {
                this.transactionsResponse = response
                this.message = 'Transactions found'
            },
            error: (err: 'Error getting transactions') => console.log(err)
        })
    }

}
