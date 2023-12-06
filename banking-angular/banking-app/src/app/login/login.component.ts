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


}
