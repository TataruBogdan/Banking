import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../services/auth/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

    username: string = '';
    password: string = '';
    errorMessage: string = 'Invalid credentials';
    invalidLogin: boolean = false;

    constructor(private router: Router,
                private authenticationService: AuthService) {
    }

    ngOnInit() {
    }

    handleAuthLogin() {
        this.authenticationService
            .executeAuthenticationService(this.username, this.password)
            .then((response) => {
                const token = response.data.token;
                localStorage.setItem('AUTHENTICATED_USER', this.username);
                localStorage.setItem('TOKEN', token);
                console.log("GET TOKEN RESPONSE" + token);
                this.router.navigate(['welcome', this.username]);
                this.invalidLogin = false;
            }).catch((error) => {
                console.log(error);
                this.invalidLogin = true;
        })
    }


}
