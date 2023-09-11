import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Subject } from "rxjs";
import { RequestModel } from "../model/RequestModel";

@Injectable()
export class RequestService{

    private baseUrl = "http://localhost:8080/requests/"
    
    private listRequestSubject : Subject<RequestModel[]> = new Subject<RequestModel[]>()

    constructor (private http : HttpClient){}

    getAllRequests(){
        this.http.get<RequestModel[]>(this.baseUrl).subscribe({
            next : (reponse : RequestModel[])=>{
                if(reponse != undefined)
                  this.listRequestSubject.next(reponse)
            }
        })
    }

    deteleRequest(id : number){
        this.http.delete(this.baseUrl+id).subscribe({
            next : (response) => this.getAllRequests(),
            error :(error) => alert("Fail to delete element ") 
        })
    } 

    getListRequestSubject(){
        return this.listRequestSubject.asObservable()
    }


}