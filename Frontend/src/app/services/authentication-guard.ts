import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { NotificationService } from './notification.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

  constructor(
    private router: Router,
    private notificationService: NotificationService
  ) { }

  async canActivate(): Promise<boolean> {
    const token = localStorage.getItem('myToken');
    if (token && token.length > 0) {
      return true;
    } else {
      this.notificationService.showWarningNotification('You must login first')
      this.router.navigate(['login']);
    }
  }

}
