import { Component, OnInit } from '@angular/core';
import { UserModel } from '../../Model/user';
import { UserService } from '../../Services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit {

  userList: UserModel[] = [];

  constructor(private userService: UserService,  private router: Router) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe((res) => {
      this.userList = res;
    });
  }

  onEdit(userdata : UserModel) {
      this.router.navigate(['/edit-user', userdata.id]);
  }

  onDelete(id : number) {
    this.userService.deleteUser(id).subscribe((res) => {
      this.getAllUsers();
    });
  }

}
