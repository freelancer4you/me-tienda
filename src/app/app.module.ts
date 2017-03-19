import { NgModule }      	from '@angular/core';
import { RouterModule }  	from '@angular/router';
import { FormsModule } 		from '@angular/forms';
import { BrowserModule } 	from '@angular/platform-browser';
import { HttpModule } 	 	from '@angular/http';

import { AppComponent }  	  from './app.component';
import { HomeComponent } 	  from './home/home.component';
import { LoginComponent }   from './login/login.component';
import { AccountComponent } from './account/account.component';
import { OrderComponent }   from './users/orders.component';
import { OrdersOverviewComponent } from './tiendas/orders.overview.component';
import { ProfileComponent }   from './profile/profile-form.component';
import { AuthService } 	 	  from './auth.service';
import { LoginGuard } 		  from './login-guard.service';

import { ContextMenuModule } from 'angular2-contextmenu';

@NgModule({
  declarations: [ AppComponent, 
                  LoginComponent, 
                  HomeComponent, 
                  AccountComponent, 
                  OrderComponent, 
                  OrdersOverviewComponent,
                  ProfileComponent ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ContextMenuModule,
    RouterModule.forRoot([
      { path: '', redirectTo: '/home', pathMatch: 'full' },
      { path: 'home', component: HomeComponent },
      { path: 'login', component: LoginComponent },
      { path: 'orders', 
        component: OrderComponent
      },
      { path: 'orders-overview', 
        component: OrdersOverviewComponent,
        canActivate: [LoginGuard]
      },
      {
        path: 'account',
        component: AccountComponent,
        canActivate: [LoginGuard]
      },
      { path: 'profile', 
        component: ProfileComponent,
        canActivate: [LoginGuard]
      }
    ])
  ],
  providers: 	[ LoginGuard, AuthService],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
