import { Injectable } from '@angular/core';

declare var $:any;

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor() { }

  showSuccessNotification(message) {
    $.notify({
      icon: 'pe-7s-like2',
      message: message
    }, {
      type: 'success',
      timer: 1000,
      placement: {
        from: 'top',
        align: 'center'
      }
    });
  }

  showWarningNotification(message) {
    $.notify({
      icon: 'pe-7s-info',
      message: message
    }, {
      type: 'danger',
      timer: 1000,
      placement: {
        from: 'top',
        align: 'center'
      }
    });
  }
}
