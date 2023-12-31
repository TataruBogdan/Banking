import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WelcomeComponent} from "./welcome/welcome.component";
import {LoginComponent} from "./login/login.component";
import {LogoutComponent} from "./logout/logout.component";
import {CurrentAccountComponent} from "./current-account/current-account.component";
import {DepositAccountComponent} from "./deposit-account/deposit-account.component";
import {LoanAccountComponent} from "./loan-account/loan-account.component";
import {TransactionComponent} from "./transaction/transaction.component";
import {ErrorComponent} from "./error/error.component";
import {IndividualComponent} from "./individual/individual.component";
import {IndividualsComponent} from "./individuals/individuals.component";
import {UsersComponent} from "./users/users.component";

const routes: Routes = [

    {path: '', component: LogoutComponent},
    {path: 'login', component: LoginComponent},
    {path: 'logout', component: LogoutComponent},
    {path: 'welcome/:userName', component: WelcomeComponent},
    {path: 'individual/update/:id', component: IndividualComponent},
    {path: 'individuals', component: IndividualsComponent},
    {path: 'current-account', component: CurrentAccountComponent},
    {path: 'deposit-account', component: DepositAccountComponent},
    {path: 'loan-account', component: LoanAccountComponent},
    {path: 'transaction', component: TransactionComponent},
    {path: 'users', component: UsersComponent},
    {path: '**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
