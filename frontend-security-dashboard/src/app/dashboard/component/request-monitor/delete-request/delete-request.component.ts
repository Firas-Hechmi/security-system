import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RequestService } from 'src/app/dashboard/service/RequestService';

@Component({
  selector: 'app-delete-request',
  templateUrl: './delete-request.component.html',
  styleUrls: ['./delete-request.component.css']
})
export class DeleteRequestComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data : any,private requestService : RequestService) { }

  ngOnInit(): void {
  }

  delete(){
     this.requestService.deteleRequest(this.data.id)
    
  }

}
