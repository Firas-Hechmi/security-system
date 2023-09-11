import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FilterLogsModel } from 'src/app/dashboard/model/FilterLogsModel';
import { LogService } from 'src/app/dashboard/service/LogService';

@Component({
  selector: 'app-logs-filter',
  templateUrl: './logs-filter.component.html',
  styleUrls: ['./logs-filter.component.css']
})
export class LogsFilterComponent implements OnInit {

   filterLogsForm! : FormGroup
   logLevels = ["INFO","WARN","ERROR"]
  constructor(private logService : LogService) { }

  ngOnInit(): void {
    this.filterLogsForm = new FormGroup({
      message : new FormControl(""),
      logLevel : new FormControl(""),
    })
  }

  isNotValidForm(){
    return this.filterLogsForm.value.message === "" && this.filterLogsForm.value.logLevel === ""
  }

  filterLogs(): void{
     this.logService.filterLogs(new FilterLogsModel(this.filterLogsForm.value.message,this.filterLogsForm.value.logLevel))
  }

}
