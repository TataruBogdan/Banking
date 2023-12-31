import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {DatePipe} from "@angular/common";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { FooterComponent } from './footer/footer.component';
import { MenuComponent } from './menu/menu.component';
import { CurrentAccountComponent } from './current-account/current-account.component';
import { LoanAccountComponent } from './loan-account/loan-account.component';
import { DepositAccountComponent } from './deposit-account/deposit-account.component';
import { TransactionComponent } from './transaction/transaction.component';
import { ErrorComponent } from './error/error.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { UsersComponent } from './users/users.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { IndividualComponent } from './individual/individual.component';
import { IndividualsComponent } from './individuals/individuals.component';
import {HttpInterceptorUserAuthService} from "./services/http/interceptorUser/http-interceptor-user-auth.service";

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    FooterComponent,
    MenuComponent,
    CurrentAccountComponent,
    LoanAccountComponent,
    DepositAccountComponent,
    TransactionComponent,
    ErrorComponent,
    LoginComponent,
    LogoutComponent,
    UsersComponent,
    IndividualComponent,
    IndividualsComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule
    ],
  providers: [{
      provide:HTTP_INTERCEPTORS, useClass: HttpInterceptorUserAuthService, multi:true
  }, [DatePipe]],
  bootstrap: [AppComponent]
})
export class AppModule { }
