import { Component, OnDestroy, OnInit } from '@angular/core';
import { LogModel } from '../../model/LogModel';
import { LogService } from '../../service/LogService';
import { Subscription } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { LogsFilterComponent } from './logs-filter/logs-filter.component';

@Component({
  selector: 'app-log-monitor',
  templateUrl: './log-monitor.component.html',
  styleUrls: ['./log-monitor.component.css']
})
export class LogMonitorComponent implements OnInit,OnDestroy {

  displayedColumns: string[] = ['date', 'loggerName', 'message', 'logLevel'];

  logs : LogModel[] = []

  logsSubscription! : Subscription

  canFilter = true
  canFilterLogsSubscription! : Subscription
  
  
  constructor(private logService : LogService,private dialog : MatDialog) { }

  ngOnInit(): void {
    this.logsSubscription = this.logService.getLogsSubject().subscribe(
      newLogs => this.logs = newLogs
    )
    this.canFilterLogsSubscription = this.logService.canFilterLogs().subscribe(
      canFilterState => this.canFilter = canFilterState 
    )
    this.logService.getAllLogs()
  }

  ngOnDestroy(): void {
      this.logsSubscription.unsubscribe()
      this.canFilterLogsSubscription.unsubscribe()
  }

  formatDateTime(dateTime : string) : string{
    if(dateTime != undefined)
       return "Le "+dateTime.substring(0,10)+" à "+dateTime.substring(11,19)
    else
     return ""
 }

 openFilterDialog(){
   this.dialog.open(LogsFilterComponent,{
    width:"25%"
   })
 }

 reinitialize(){
  this.logService.getAllLogs()
 }


}
