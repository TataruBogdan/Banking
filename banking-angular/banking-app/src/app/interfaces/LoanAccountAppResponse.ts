import {IndividualAppResponse} from "./IndividualAppResponse";



export interface LoanAccountAppResponse {

    iban: string,
    loanAmount: number,
    individualId: number,
    period: number,
    interestRate: number,
    startDate: Date,
    loanStatus: string,
    principal: number,
    individualDTO: IndividualAppResponse
}
