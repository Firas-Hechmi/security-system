import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { DashboardComponent } from "./dashboard.component";
import { UserManagerComponent } from "./component/user-manager/user-manager.component";
import { RequestMonitorComponent } from "./component/request-monitor/request-monitor.component";
import { LogMonitorComponent } from "./component/log-monitor/log-monitor.component";

const routes : Routes=[
    {
      path : '',
      component : DashboardComponent,
      children : [
        {
            path : 'user-manager',
            component : UserManagerComponent
        },
        {
            path : 'request-monitor',
            component : RequestMonitorComponent
        },{
            path : 'log-monitor',
            component : LogMonitorComponent
        },
        {
            path : '**',
            redirectTo : 'user-manager',
            pathMatch : 'full'
        }
      ]
    }
]

@NgModule({
    imports : [RouterModule.forChild(routes)],
    exports : [RouterModule]
})
export class DashboardRoutingModule {}