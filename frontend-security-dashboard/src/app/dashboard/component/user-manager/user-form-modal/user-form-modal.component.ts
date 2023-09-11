import { Component, OnInit , Inject , OnDestroy } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { error } from 'console';
import { Subscription } from 'rxjs';
import { RoleModel } from 'src/app/dashboard/model/RoleModel';
import { UserModel } from 'src/app/dashboard/model/UserModel';
import { UserRequestModel } from 'src/app/dashboard/model/UserRequestModel';
import { UserService } from 'src/app/dashboard/service/UserService';

@Component({
  selector: 'app-user-form-modal',
  templateUrl: './user-form-modal.component.html',
  styleUrls: ['./user-form-modal.component.css']
})
export class UserFormModalComponent implements OnInit{

  userForm! : FormGroup

  errorMsg : string=""
  successMsg : string=""

  roles : RoleModel[] = []

  listAccountStatus = ["ACTIVE","LOCKED","CLOSED"]

  buttonLabel = ""

  constructor(@Inject(MAT_DIALOG_DATA) public data : any,private userService : UserService) { }

  ngOnInit(): void {
    this.userForm = new FormGroup({
      firstName : new FormControl(""),
      lastName : new FormControl(""),
      email : new FormControl(""),
      phoneNumber : new FormControl(""),
      accountStatus : new FormControl(""),
      role : new FormControl("")
    })

   this.getAllRoles()
   this.initUserForm()

  }

  initUserForm(){
   if(this.data.action == "EDIT"){
      this.initUserEditForm()
      this.buttonLabel = "Edit"
   }else if(this.data.action == "ADD"){
       this.initAddUserForm()
       this.buttonLabel = "Add"
   }else
       this.errorMsg = "Unable to initiate the user form"

  }

  initUserEditForm(){
    this.userService.getUserByUsername(this.data.username).subscribe({
      next : (response : UserModel) => {
        if(response)  {
          this.userForm = new FormGroup({
            firstName : new FormControl(response.firstName),
            lastName : new FormControl(response.lastName),
            email : new FormControl(response.email),
            phoneNumber : new FormControl(response.phoneNumber),
            accountStatus : new FormControl(response.accountStatus),
            role : new FormControl(response.role.name)
          })
        }else{
          this.errorMsg = "Unable to initiate the user form"
        }
      },
      error: (error: any) => {
        console.error("An error occurred while fetching user data:", error);
        this.errorMsg = "Unable to initiate the user form";
      }
    })
  }

  initAddUserForm(){
    this.userForm = new FormGroup({
      firstName : new FormControl(""),
      lastName : new FormControl(""),
      email : new FormControl(""),
      phoneNumber : new FormControl(""),
      accountStatus : new FormControl(""),
      role : new FormControl("")
    })
  }

  getAllRoles(){
    this.userService.getAllRoles().subscribe(
      {
        next :(response : RoleModel[] )=>{
            if(response ){
              this.roles = response
            }
        },
        error : (error : any)=>{
          this.errorMsg = "Unable to retrieve list of roles"
        }
      }
    )
  }

  submitUserForm(){
    this.successMsg = ""
    this.errorMsg = ""
    let firstName = this.userForm.value.firstName
    let lastName = this.userForm.value.lastName
    let email = this.userForm.value.email
    let phoneNumber = this.userForm.value.phoneNumber
    let accountStatus = this.userForm.value.accountStatus
    let role = this.userForm.value.role
    if( firstName == "" || lastName  == "" || email  == "" || phoneNumber  == "" || accountStatus  == "" || role  == ""){
        this.errorMsg="Please fill in all the fields"
    }else{
       let user = new UserRequestModel(firstName,lastName,email,phoneNumber,accountStatus,role)
       if(this.data.action == "ADD")
            this.addUser(user)
       else if(this.data.action == "EDIT")
            this.updateUser(user,this.data.username)
    }  
  }

  addUser(newUser : UserRequestModel){
    this.userService.addUser(newUser).subscribe({
       next : (response : any) =>{
           this.userService.getAllUsers()
           this.successMsg = "User added successfully"
           this.userForm.reset()
       },
       error : (error : any)=>{
        this.errorMsg = "Fail to add user"
       }
    })
  }

  updateUser(updatedUser : UserRequestModel,username : string){
    this.userService.updateUser(updatedUser,username).subscribe({
       next : (response : any) =>{
           this.userService.getAllUsers()
           this.successMsg = "User updated successfully"
       },
       error : (error : any)=>{
        this.errorMsg = "Fail to update user"
       }
    })
  }

}
