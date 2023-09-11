import { Component, OnInit ,OnDestroy} from '@angular/core';
import { UserService } from '../../service/UserService';
import { UserModel } from '../../model/UserModel';
import { MatDialog } from '@angular/material/dialog';
import { UserFormModalComponent } from './user-form-modal/user-form-modal.component';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-user-manager',
  templateUrl: './user-manager.component.html',
  styleUrls: ['./user-manager.component.css']
})
export class UserManagerComponent implements OnInit , OnDestroy {

  displayedColumns: string[] = ['username', 'firstName', 'lastName', 'email','role','accountStatus','actions'];

  users : UserModel[] = [];

  usersSubscription! : Subscription

  constructor(private userService : UserService,private dialog : MatDialog) { }

  ngOnInit(): void {
    
    this.usersSubscription = this.userService.getUsersSubject().subscribe(
      {
        next : (userList : UserModel[]) => this.users = userList
      }
    )

    this.userService.getAllUsers()

  }

  openAddUserForm(action : string){
    this.dialog.open(UserFormModalComponent,{
      width:"35%",
      data : {
        action : action
      }
      }
    )
  }

  openEditUserForm(action  : string , username : string){
    this.dialog.open(UserFormModalComponent,{
      width:"35%",
      data : {
        action : action,
        username : username
       }
      }
    )
  }

  ngOnDestroy(): void {
      this.usersSubscription.unsubscribe()
  }

}
