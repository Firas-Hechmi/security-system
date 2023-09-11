import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthService } from '../../service/auth.service';
import { RegisterRequest } from '../../model/RegisterRequest';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  hidePassword=false

  registerError=""

  registered=false

  registerForm! : FormGroup

  constructor(private authService : AuthService) { }

  ngOnInit(): void {
    this.registerForm = new FormGroup({
      firstName : new FormControl(""),
      lastName : new FormControl(""),
      email : new FormControl(""),
      phoneNumber : new FormControl(""),
      password : new FormControl(""),
      role : new FormControl(""),
    })
  }

  register(){
   let firstName = this.registerForm.value.firstName
   let lastName = this.registerForm.value.lastName
   let email = this.registerForm.value.email
   let phoneNumber = this.registerForm.value.phoneNumber
   let password = this.registerForm.value.password
   let role = this.registerForm.value.role
  
  this.registerError = ""
  this.registered = false
  console.log(this.registerForm)
  if( firstName == "" || lastName  == "" || email  == "" || phoneNumber  == "" || password  == "" || role  == ""){
        this.registerError = "Please fill in all the fields"
  }else{
    this.authService.register(new RegisterRequest(
      firstName,lastName,email,password,phoneNumber,role
    )).subscribe({
      next : (response)=>{
        this.registered = true
      },
      error : (error)=>{
        this.registerError = "Register Failed"
      }
    }
    )
  }

  }

}
