import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatSelectModule} from '@angular/material/select';
import {MatTableModule} from '@angular/material/table';
import { ReactiveFormsModule } from "@angular/forms";
import { NgHttpLoaderModule } from 'ng-http-loader';
import {MatDialogModule} from '@angular/material/dialog';
import { MatTooltipModule} from '@angular/material/tooltip';

@NgModule(
    {
        imports :[ NgHttpLoaderModule.forRoot()],
        exports:[
            CommonModule,
            HttpClientModule,
            ReactiveFormsModule,
            MatSidenavModule,
            MatIconModule,
            MatToolbarModule,
            MatListModule,
            MatButtonModule,
            MatCardModule,
            MatInputModule,
            MatFormFieldModule,
            MatSelectModule,
            MatProgressSpinnerModule,
            MatDialogModule,
            MatTableModule,
            MatTooltipModule,
            NgHttpLoaderModule
        ]
    }
)
export class SharedModule{}