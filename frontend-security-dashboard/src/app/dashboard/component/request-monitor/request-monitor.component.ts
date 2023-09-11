import { Component, OnDestroy, OnInit } from '@angular/core';
import { RequestModel } from '../../model/RequestModel';
import { Subscription } from 'rxjs';
import { RequestService } from '../../service/RequestService';
import { MatDialog } from '@angular/material/dialog';
import { DeleteRequestComponent } from './delete-request/delete-request.component';
import { RequestBodyInfoComponent } from './request-body-info/request-body-info.component';

@Component({
  selector: 'app-request-monitor',
  templateUrl: './request-monitor.component.html',
  styleUrls: ['./request-monitor.component.css']
})
export class RequestMonitorComponent implements OnInit ,OnDestroy{

  displayedColumns: string[] = ['date', 'method', 'url', 'remoteAddress','body','status','actions'];

  listRequest : RequestModel[] = []
   
  listRequestSubscription! : Subscription

  constructor(private requestService : RequestService,private dialog : MatDialog) { }

  ngOnInit(): void {

    this.listRequestSubscription = this.requestService.getListRequestSubject().subscribe(
      newListRequest => this.listRequest = newListRequest
    )

    this.requestService.getAllRequests()
    
  }

  ngOnDestroy(): void {
      this.listRequestSubscription.unsubscribe()
  }

  deleteRequest(id : number){
      this.dialog.open(DeleteRequestComponent,{
        width : "25%",
        data :{
          id : id
        }
      })
  }

  showRequestBody(requestBody : string){
    console.log(requestBody)
    this.dialog.open(RequestBodyInfoComponent,{
      width : "25%",
      data : {
          requestBody : requestBody
      }
    })
  }

  formatDateTime(dateTime : string) : string{
     if(dateTime != undefined)
        return "Le "+dateTime.substring(0,10)+" à "+dateTime.substring(11,19)
     else
      return ""
  }

  isOK(status : number){
    let okStatus = [200,201,204]
    return okStatus.includes(status)
  }

  isKO(status : number){
    let koStatus = [400,401,403,404,500,502,503]
    return koStatus.includes(status)
  }
  
   getStatusDescription(status: number): string {
    switch (status) {
      case 200:
        return "OK";
      case 400:
        return "Bad Request";
      case 401:
        return "Unauthorized";
      case 403:
        return "Forbidden";
      case 404:
        return "Not Found";
      case 500:
        return "Internal Server Error";
      case 502:
        return "Bad Gateway: The server acting as a gateway or proxy received an invalid response from an upstream server";
      case 503:
        return "Service Unavailable: The server is currently unable to handle the request due to temporary overloading or maintenance"
      default:
        return "Unknown Status";
    }
  }
  
}
