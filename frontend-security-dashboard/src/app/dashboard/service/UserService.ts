import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserModel } from "../model/UserModel";
import { RoleModel } from "../model/RoleModel";
import { Subject } from "rxjs";
import { UserRequestModel } from "../model/UserRequestModel";

@Injectable()
export class UserService{

    private baseUrl = "http://localhost:8080"

    private usersSubject : Subject<UserModel[]> = new Subject<UserModel[]>()

    constructor (private http : HttpClient){}

    getAllUsers(){
        this.http.get<UserModel[]>(this.baseUrl+"/users").subscribe({
            next : (response : UserModel[]) => this.setUsers(response)
        })
    }
    
    addUser(newUser : UserRequestModel){
        return this.http.post(this.baseUrl+"/users/add-user",newUser)
    }

    updateUser(updatedUser : UserRequestModel,username : string ){
        return this.http.post(this.baseUrl+"/users/update-user/"+username,updatedUser)
    }
    getAllRoles(){
        return this.http.get<RoleModel[]>(this.baseUrl+"/roles")
    }

    getUsersSubject(){
        return this.usersSubject.asObservable();
    }

    getUserByUsername(username : string){
        return this.http.get<UserModel>(this.baseUrl+"/users/"+username)
    }

    setUsers(users : UserModel[]){
        if(users != undefined)
           this.usersSubject.next(users)
    }
}