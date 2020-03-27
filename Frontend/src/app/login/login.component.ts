import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'app/services/user.service';
import { NotificationService } from 'app/services/notification.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private notificationService: NotificationService
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [
        Validators.required,
        Validators.pattern(
          // tslint:disable-next-line: max-line-length
          /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        ),
      ]],
      password: ['', Validators.required],
    });
  }

  login() {
    const email: string = this.loginForm.value.email;
    const password: string = this.loginForm.value.password;
    if (!this.loginForm.valid) {
      this.notificationService.showWarningNotification('form error');
      return ;
    }
    this.userService.login(email, password).subscribe(
      res => {
        this.notificationService.showSuccessNotification('Login successfully');
        localStorage.setItem('myToken', res.token);
        localStorage.setItem('user', JSON.stringify(res.user));
        this.router.navigate(['home']);
      },
      err => {
        this.notificationService.showWarningNotification(err.error);
        console.error(err);
      });
  }

}
