import { Component, OnInit } from '@angular/core';

declare const $: any;
declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [
  // { path: 'home/dashboard', title: 'Dashboard',  icon: 'pe-7s-graph', class: '' },
  { path: 'user', title: 'User Profile', icon: 'pe-7s-user', class: '' },
  { path: 'table', title: 'Promotions', icon: 'pe-7s-note2', class: '' },
  { path: 'addStudent', title: 'Add Student', icon: 'pe-7s-news-paper', class: '' },
  // { path: 'home/icons', title: 'Icons',  icon:'pe-7s-science', class: '' },
  // { path: 'home/maps', title: 'Maps',  icon:'pe-7s-map-marker', class: '' },
  // { path: 'home/notifications', title: 'Notifications',  icon:'pe-7s-bell', class: '' },
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor() { }

  ngOnInit() {
    const user = JSON.parse(localStorage.getItem('user'));
    this.menuItems = ROUTES.filter(menuItem => {
      if (user.role === 'student') {
        return menuItem.path === 'user';
      } else {
        return menuItem;
      }
    });
  }
  isMobileMenu() {
    if ($(window).width() > 991) {
      return false;
    }
    return true;
  };
}
