import { Component, OnInit, OnDestroy } from '@angular/core';

import { AuthService }    from '../auth.service'
import { ActivatedRoute } from '@angular/router';
import { Order }          from '../users/order';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  order: Order;
  private sub: any;

  constructor(private authService: AuthService,
          private route: ActivatedRoute) {}

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
       //this.id = +params['id']; // (+) converts string 'id' to a number
       if (params instanceof Order) {
        this.order = params;
       }
       console.log(params);
       // In a real app: dispatch action to load the details here.
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  } 


  public login() {
    this.authService.login();
  }
}
