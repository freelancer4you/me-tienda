import { Component, OnInit } from '@angular/core';
import 'rxjs/add/operator/toPromise';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: [
    require('./app.style.less').toString()
  ],
})
export class AppComponent implements OnInit {
  
  name = 'Me Tienda';

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.authService.checkLoginState();
  }
}
