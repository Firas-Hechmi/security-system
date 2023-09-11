import { Injectable } from "@angular/core";
import { Subject } from "rxjs";
import { LogModel } from "../model/LogModel";
import { HttpClient } from "@angular/common/http";
import { FilterLogsModel } from "../model/FilterLogsModel";

@Injectable()
export class LogService {
    private baseUrl = "http://localhost:8080/logs/"
    private logsSubject : Subject<LogModel[]> = new Subject<LogModel[]>()
    private canFilter : Subject<boolean> = new Subject<boolean>()

    constructor(private http : HttpClient){}

    getAllLogs(){
        this.http.get<LogModel[]>(this.baseUrl).subscribe({
            next : (response : LogModel[])=>{
                if(response != undefined)
                 this.logsSubject.next(response)
            }
        })
        this.canFilter.next(true)
    }

    filterLogs(filterLogs : FilterLogsModel){
        this.http.post<LogModel[]>(this.baseUrl+"filter",filterLogs).subscribe({
            next : (response : LogModel[])=>{
                if(response != undefined)
                 this.logsSubject.next(response)
            }
        })
        this.canFilter.next(false)
    }

    getLogsSubject(){
        return this.logsSubject.asObservable()
    }

    canFilterLogs(){
        return this.canFilter.asObservable()
    }
}