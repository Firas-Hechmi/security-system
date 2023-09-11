import { NgModule } from "@angular/core";
import { LoginComponent } from "./component/login/login.component";
import { RegisterComponent } from "./component/register/register.component";
import { SharedModule } from "../shared/shared.module";
import { AuthComponent } from "./auth.component";
import { AuthRoutingModule } from "./auth-routing.module";
import { AuthService } from "./service/auth.service";

@NgModule({
    declarations: [
        AuthComponent,
        LoginComponent,
        RegisterComponent
    ],
    imports: [SharedModule , AuthRoutingModule],
    providers: [AuthService],
    exports: [AuthComponent]
})
export class AuthModule {}