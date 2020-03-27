import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from 'app/services/user.service';
import { Router } from '@angular/router';
import { NotificationService } from 'app/services/notification.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private router: Router,
    private notificationService: NotificationService
  ) {
    this.signupForm = this.formBuilder.group({
      email: ['', [
        Validators.required,
        Validators.pattern(
          // tslint:disable-next-line: max-line-length
          /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        ),
      ]],
      password: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      role: ['', Validators.required],
    });
  }

  ngOnInit() {
  }

  signup(): void {
    const params: {
      email: string,
      password: string,
      lastName: string,
      firstName: string,
      role: string,
    } = this.signupForm.value;
    if (!this.signupForm.valid) {
      this.notificationService.showWarningNotification('form error');
      return ;
    }
    this.userService.signup(params).subscribe(
      res => {
        console.log(res);
        this.notificationService.showSuccessNotification('Login successfully');
        localStorage.setItem('myToken', res.token);
        localStorage.setItem('user', JSON.stringify(res.user));
        this.router.navigate(['home']);
      },
      err => {
        this.notificationService.showWarningNotification(err.error);
        console.error(err);
      }
    )
  }

}
