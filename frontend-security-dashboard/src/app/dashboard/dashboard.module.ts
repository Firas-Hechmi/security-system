import { NgModule } from '@angular/core';
import { DashboardHeaderComponent } from './component/dashboard-header/dashboard-header.component';
import { DashboardComponent } from './dashboard.component';
import { SharedModule } from '../shared/shared.module';
import { UserManagerComponent } from './component/user-manager/user-manager.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { UserService } from './service/UserService';
import { UserFormModalComponent } from './component/user-manager/user-form-modal/user-form-modal.component';
import { RequestMonitorComponent } from './component/request-monitor/request-monitor.component';
import { RequestService } from './service/RequestService';
import { DeleteRequestComponent } from './component/request-monitor/delete-request/delete-request.component';
import { RequestBodyInfoComponent } from './component/request-monitor/request-body-info/request-body-info.component';
import { LogMonitorComponent } from './component/log-monitor/log-monitor.component';
import { LogService } from './service/LogService';
import { LogsFilterComponent } from './component/log-monitor/logs-filter/logs-filter.component';
import { TokenInterceptor } from './interceptor/token.interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

@NgModule({
    declarations:[
        DashboardComponent,
        DashboardHeaderComponent,
        UserManagerComponent,
        UserFormModalComponent,
        RequestMonitorComponent,
        DeleteRequestComponent,
        RequestBodyInfoComponent,
        LogMonitorComponent,
        LogsFilterComponent
    ],
    imports:[
       SharedModule,
       DashboardRoutingModule
    ],
    exports:[DashboardComponent],
    providers:[UserService,RequestService,LogService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: TokenInterceptor,
            multi: true
          }
        ]
})
export class DashboardModule{}