import {Component, OnInit} from '@angular/core';
import {SharedDataService} from "../services/sharedData/shared-data.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit{

    name: string = '';


    constructor(
       private activatedRoute: ActivatedRoute,
       private router: Router,
       private sharedDataService: SharedDataService
    ) {}


    ngOnInit() {
        this.name = this.activatedRoute.snapshot.params['name'];
        this.sharedDataService.setUserName(this.name);
    }

}
