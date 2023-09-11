import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Location } from '@angular/common';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-dashboard-header',
  templateUrl: './dashboard-header.component.html',
  styleUrls: ['./dashboard-header.component.css']
})
export class DashboardHeaderComponent implements OnInit,OnDestroy {
  
  routeSubscription! : Subscription
  currentComponentIcon = ""
  currentComponentTitle = ""

  constructor(private router : Router,private location : Location) { }

  ngOnInit(): void {
      this.setSubBarTitle(this.location.path())
      this.routeSubscription = this.router.events.subscribe(event=>{
      if(event instanceof NavigationEnd){
        this.setSubBarTitle(event.url)
      }
    })
  }

  ngOnDestroy(): void {
      this.routeSubscription.unsubscribe()
  }

  logout(): void{
    localStorage.removeItem('jwt')
    localStorage.removeItem('id')
    localStorage.removeItem('email')
    localStorage.removeItem('username')
    localStorage.removeItem('role')
    this.router.navigate(['/auth/login'])
  }

  setSubBarTitle(path : string): void {
     switch (path) {
      case '/dashboard/user-manager':
        this.currentComponentTitle = "User manager"
        this.currentComponentIcon = "persong"
        break
      case '/dashboard/request-monitor':
        this.currentComponentTitle = "Request monitor"
        this.currentComponentIcon = "compare_arrows"
        break
      case '/dashboard/log-monitor':
        this.currentComponentTitle = "Log monitor"
        this.currentComponentIcon = "format_list_bulleted"
        break
      default :
      break
     }
  }
  

}
