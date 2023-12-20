import {Component, OnInit} from '@angular/core';
import {TransactionAppResponse} from "../interfaces/TransactionAppResponse";
import {ActivatedRoute} from "@angular/router";
import {TransactionService} from "../services/transaction/transaction.service";
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit{

    transactionForm!: FormGroup;

    transactionsResponse: TransactionAppResponse[] =[];
    ids: string[] = [];
    private message: string = '';
    statusList: string ='';
    transactionId: string ='';
    transactionWithId: string = '';

    constructor(
        private route: ActivatedRoute,
        private transactionsService: TransactionService,
        private formBuilder: FormBuilder,
    ) {}

    ngOnInit(): void {
        this.filterTransactions();
        this.initForm();
    }

    initForm(): void {
        this.transactionForm = this.formBuilder.group({
            fromIban: [''],
            toIban: [''],
            amount: ['', [Validators.required, Validators.min(0.01)]]
        });
    }



    filterTransactions() {

        const statusArray = this.statusList.split(',').map(status =>status.trim().toUpperCase());

        if (this.statusList.length <= 0) {
            return
        }
        const searchInputDTO = {statusList : statusArray}
        this.transactionsService.getAllTransactionsWithStatus(searchInputDTO).subscribe({
            next: (response: any) => {
                console.log(response)
                this.ids = (response.ids)
                this.getTransaction();
                this.message = 'Transactions found'
            },
            error: (err: 'Error getting transactions') => console.log(err)
        })
    }

    submitForm() {
        if (this.transactionForm.valid) {
            const formData = this.transactionForm.value;

            this.transactionsService.processTransaction(formData).subscribe( {
                next: (response: any) => {
                    this.transactionsResponse = Array.of(response)
                    this.message = 'Transaction created'
                },
                error: (err: 'Error on creating transaction') => console.log(err)
            })
        }
    }

    getTransaction() {
        for (const id of this.ids) {
            this.transactionsService.getTransactionWithId(id).subscribe( {
                next:(response) => {
                    this.transactionsResponse.push(response)
            }
            });
        }
    }

    getExecuteTransaction() {
        this.transactionsService.executeTransaction(this.transactionId).subscribe( {
            next:(response) => {
                this.transactionsResponse = Array.of(response)
                this.message = 'Transaction executed'
            },
            error: (err: 'Error on creating transaction') => console.log(err)
        })
    }

    getTransactionId() {
        this.transactionsService.getTransactionWithId(this.transactionWithId).subscribe( {
            next: (response) => {
                this.transactionsResponse = Array.of(response)
                this.message ='Transaction with id'
            },
            error: (err: 'Error on retrieving transaction') => console.log(err)
        })
    }

}
