export class RegisterRequest {
    
    constructor(public firstName : string , public lastName : string , public email : string ,
           public password : string ,public phoneNumber : string, public role : string){}
}