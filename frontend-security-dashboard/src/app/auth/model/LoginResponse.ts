export class LoginResponse {
  
    constructor(public otpRequired : boolean, public jwt: string, public id: string,public username: string,public email: string, public role: string) {
 
    }
  
   
}
  