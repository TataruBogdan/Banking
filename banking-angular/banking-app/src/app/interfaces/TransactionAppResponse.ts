import {IndividualAppResponse} from "./IndividualAppResponse";


export interface TransactionAppResponse {

    transactionId: string,
    fromIndividualId: number,
    fromIban: string,
    fromAccountType: string,
    fromIndividualDTO: IndividualAppResponse,
    toIndividualId: number,
    toIban: string,
    toAccountType: string,
    toIndividualDTO: IndividualAppResponse,
    transactionAmount: number,
    transactionTime: Date,
    status: string
}
