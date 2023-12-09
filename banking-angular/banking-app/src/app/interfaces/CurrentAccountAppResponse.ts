

export interface CurrentAccountAppResponse {

    iban: string,
    balance: number,
    individualId: number,
    startDate: Date,
    //currentStatus is Enum -> string ?
    currentStatus: string,
    primaryAccount: boolean
}
