import {IndividualAppResponse} from "./IndividualAppResponse";


export interface DepositAccountAppResponse {

    iban: string,
    depositAmount: number,
    balance: number,
    individualId: number,
    interestRate: number,
    maturityRate: number,
    maturityDate: Date,
    maturityMonths: number,
    selfCapitalization: boolean,
    maturityIban: string,
    startDate: Date,
    status: string // this is enum -> ok to string ? Object !!!
    individualDTO: IndividualAppResponse
}


