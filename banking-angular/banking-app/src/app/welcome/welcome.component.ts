import {Component, OnInit, ViewChild} from '@angular/core';
import {SharedDataService} from "../services/sharedData/shared-data.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CurrentAccountComponent} from "../current-account/current-account.component";
import {CurrentAccountService} from "../services/current/current-account.service";

@Component({
    selector: 'app-welcome',
    styleUrls: ['./welcome.component.css'],
    templateUrl: 'welcome.component.html'
})
export class WelcomeComponent implements OnInit{

    userName: string = '';

    constructor(
       private activatedRoute: ActivatedRoute,
       private router: Router,
       private sharedDataService: SharedDataService
    ) {}


    ngOnInit() {
        this.userName = this.activatedRoute.snapshot.params['userName'];
        //this.sharedDataService.setUserName(this.name);
    }

    triggerGetAccounts() {
        this.sharedDataService.triggerGetAccountsMethod();
    }

}
