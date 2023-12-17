import {IndividualAppResponse} from "./IndividualAppResponse";
import {LoanStatusResponse} from "./LoanStatusResponse";


export interface LoanAccountAppResponse {

    iban: string,
    loanAmount: number,
    individualId: number,
    period: number,
    interestRate: number,
    startDate: Date,
    loanStatus: string,
    principal: number,
    individual: IndividualAppResponse
}
