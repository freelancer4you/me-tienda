import { Component } from '@angular/core';
<<<<<<< eeb788b02ce73fec4c3d0e0aa80bfc0b5bdaa20a
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService }    from '../auth.service'
import { CookieConsent }    from '../cookie.service'
=======
>>>>>>> Initial commit

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
<<<<<<< eeb788b02ce73fec4c3d0e0aa80bfc0b5bdaa20a

  private sub: any;

  constructor(private authService: AuthService,
              private route: ActivatedRoute,
              private router: Router,
              private cookieService: CookieConsent) {}

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      
      let orderCookie = this.cookieService.getCookie('order');
      console.log(orderCookie);
      if(this.authService.isLoggedIn() && orderCookie !== undefined){          
        this.router.navigate(['/orders']);
      }      
      this.cookieService.deleteCookie('order');
       //console.log(params);
       // In a real app: dispatch action to load the details here.
    });
  }
=======
>>>>>>> Initial commit
}
