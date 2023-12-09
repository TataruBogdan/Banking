import {Component, OnInit} from '@angular/core';
import {UserAppResponse} from "../interfaces/UserAppResponse";
import {UserService} from "../services/userService/user.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit{

    users: UserAppResponse[] = [];
    message: string = '';


    constructor(
        private userService: UserService,
    ) {
    }

    ngOnInit(): void {
        this.refreshUser();
    }

    refreshUser() {
        this.userService.getAllUsers().subscribe({
            next: (response: any) => {
                this.users = response
                this.message = 'Users found'
            },
            error:(err: 'Error on getting users') => console.log(err)
        })
    }



}
