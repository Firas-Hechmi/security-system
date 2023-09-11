import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { LoginRequest } from "../model/LoginRequest";
import { LoginResponse } from "../model/LoginResponse";
import { RegisterRequest } from "../model/RegisterRequest";

@Injectable()
export class AuthService {

    private baseUrl = "http://localhost:8080/users"

    constructor(private http : HttpClient){}

    login( loginRequest : LoginRequest){
         return this.http.post<LoginResponse>( this.baseUrl+"/login" , loginRequest)
    }

    register( registerRequest : RegisterRequest){
        return this.http.post<any>( this.baseUrl+"/register", registerRequest)
    }

}