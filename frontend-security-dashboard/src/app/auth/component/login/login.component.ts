import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { FormControl, FormGroup } from '@angular/forms';
import { LoginRequest } from '../../model/LoginRequest';
import { LoginResponse } from '../../model/LoginResponse';
import { Router } from '@angular/router';
import { ThemePalette } from '@angular/material/core';
import { ProgressSpinnerMode } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm! : FormGroup
  hidePassword = true
  errorLogin = ""
  isOtpRequired = false

  color: ThemePalette = 'primary';
  mode: ProgressSpinnerMode = 'determinate';
  value = null;

  constructor(private authService : AuthService, private router : Router) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
       username : new FormControl(""),
       password : new FormControl(""),
       otp : new FormControl("")
    })
  }

  login(){
    let username = this.loginForm.value.username 
    let password = this.loginForm.value.password
    let otp = this.loginForm.value.otp

    if(username == "" || password == ''){
      this.errorLogin = "please fill in all the fields"
    }else if(this.isOtpRequired && otp == ""){
      this.errorLogin = "Code is empty"
    }else{
      
      this.errorLogin = ""
      this.authService.login(new LoginRequest(username, password,otp)).subscribe({
        next: (response : LoginResponse ) => {
            localStorage.setItem('jwt', response.jwt)
            localStorage.setItem('id', response.id)
            localStorage.setItem('email', response.email)
            localStorage.setItem('username', response.username)
            localStorage.setItem('role', response.role)
            this.router.navigate(['/dashboard'])
        },
        error: (error) => {
          if(error.status == 401){
            if(error.error.message == "OTP_REQUIRED")
              this.isOtpRequired = true
            else if (error.error.message == "INCORRECT_OTP")
              this.errorLogin = "Code is incorrect" 
            else
              this.errorLogin = "Incorrect credentials" 
          }else{
             this.errorLogin = "Cannot login , please retry later"
          }
        }
      });
    } 
  }

}
