import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-request-body-info',
  templateUrl: './request-body-info.component.html',
  styleUrls: ['./request-body-info.component.css']
})
export class RequestBodyInfoComponent implements OnInit {

  requestBody = ""

  constructor(@Inject(MAT_DIALOG_DATA) public data : any) { }

  ngOnInit(): void {
    if(this.data.requestBody != undefined)
      this.requestBody = this.data.requestBody
  }

}
