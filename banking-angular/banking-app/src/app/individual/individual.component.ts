import {Component, OnInit} from '@angular/core';
import {IndividualAppResponse} from "../interfaces/IndividualAppResponse";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../services/auth/auth.service";
import {IndividualService} from "../services/individualService/individual.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-individual',
  templateUrl: './individual.component.html',
  styleUrls: ['./individual.component.css']
})
export class IndividualComponent implements OnInit{


    id: number = 0;

    constructor(
        private individualService: IndividualService,
        private route: ActivatedRoute,
        private authService: AuthService) { }

    individual: IndividualAppResponse = {
        address: "",
        birthDate: new Date(),
        emailAddress: "",
        employerName: "",
        firstName: "",
        id: 0,
        lastName: "",
        occupation: "",
        phoneNumber: "",
        rrn: ""
    }


    ngOnInit(): void {
        this.id=this.route.snapshot.params['id'];
        if (this.id != -1) {
            this.individualService.getIndividualById(this.id).subscribe(
                data => this.individual = data
            )
        }

    }





}
