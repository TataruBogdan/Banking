import {Component, OnInit} from '@angular/core';

import {AuthService} from "../services/auth/auth.service";
import {SharedDataService} from "../services/sharedData/shared-data.service";


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit{

    userName: string = '';
    isUserLoggedIn: boolean = false;


    constructor(
        public authenticationsService: AuthService,
        //private sharedDataService: SharedDataService
    ) {}

    ngOnInit(): void {
        this.isUserLoggedIn = this.authenticationsService.isUserLoggedIn();
        // this.sharedDataService.userNam$.subscribe((name) => {
        //     this.userName = name;
        // });
    }

}
