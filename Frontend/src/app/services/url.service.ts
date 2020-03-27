import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UrlService {
  environement: string;
  url: string;

  constructor() {
    // this.environement = 'dev';
    this.environement = 'local';
    switch (this.environement) {
      case 'local':
        this.url = 'http://localhost:8080';
        break;
      case 'dev':
        this.url = 'https://infinite-fjord-33948.herokuapp.com';
        break;
      default:
        break;
    }
  }
}
