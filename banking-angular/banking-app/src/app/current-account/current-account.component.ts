import {Component, OnInit} from '@angular/core';
import {CurrentAccountAppResponse} from "../interfaces/CurrentAccountAppResponse";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-current-account',
  templateUrl: './current-account.component.html',
  styleUrls: ['./current-account.component.css']
})
export class CurrentAccountComponent implements OnInit{

    message: boolean = false;
    iban: string = '';

    currentAccount: CurrentAccountAppResponse = {
        iban: "",
        balance: 0,
        currentStatus: "",
        individualId: 0,
        primaryAccount: false,
        startDate: new Date(),
    };


    constructor(
        private route: ActivatedRoute
    ) {}

    ngOnInit(): void {
        this.iban = this.route.snapshot.params['iban'];
    }

}
