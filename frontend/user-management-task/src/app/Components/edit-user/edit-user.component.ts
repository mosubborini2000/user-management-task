import { Component } from '@angular/core';
import { UserModel } from '../../Model/user';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../Services/user.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-user',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './edit-user.component.html',
  styleUrl: './edit-user.component.css'
})
export class EditUserComponent {

  constructor(private route: ActivatedRoute, private userService: UserService, private router: Router) { }

  user: UserModel = {
    id : -1,
    firstName: '',
    lastName: '',
    userName: '',
    password: ''
  };

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params['id'];
      this.getUser(id);
    });
  }

  getUser(id: number) {
    this.userService.getUserById(id).subscribe((res) => {
      this.user = res;
    });
  }

  onUpdate() {
    console.log(this.user)
    this.userService.updateUser(this.user).subscribe((res) => {
      this.router.navigate(['/user']);
    });
  }

}
