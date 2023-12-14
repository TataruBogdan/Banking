import {Component, OnInit} from '@angular/core';
import {IndividualAppResponse} from "../interfaces/IndividualAppResponse";
import {IndividualService} from "../services/individualService/individual.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-individuals',
  templateUrl: './individuals.component.html',
  styleUrls: ['./individuals.component.css']
})
export class IndividualsComponent implements OnInit{

    individuals: IndividualAppResponse[] =[];
    message: string = '';

    constructor(
        private individualService: IndividualService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit(): void {
        this.refreshIndividuals();
    }


    refreshIndividuals() {
        this.individualService.getAllIndividuals().subscribe({
            next: (response: any) => {
                this.individuals = response
                this.message = 'individuals found'
            },
            error:(err: ' Error on getting clients') => console.log(err)
        })
    }

}
