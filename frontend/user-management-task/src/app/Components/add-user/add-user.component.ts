import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { UserModel } from '../../Model/user';
import { UserService } from '../../Services/user.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-add-user',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent {

  constructor(private userService: UserService, private router: Router) { }


  user: UserModel = {
    firstName: "",
    lastName: "",
    userName: "",
    password: ""
  }

  onSubmit(form: NgForm): void {
    console.log(form.value);
    console.log(this.user);
    this.userService.createUser(this.user).subscribe((res) => {
      this.router.navigate(['/user']);
    });
    
  }

}
