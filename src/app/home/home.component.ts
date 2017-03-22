import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService }    from '../auth.service'
import { CookieConsent }  from '../cookie.service'
import { ErrrorHandler }    from '../errorhandler.service'
import { Http, URLSearchParams, RequestOptions } from '@angular/http';
import { Order }        from '../users/order';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  private sub: any;

  constructor(private authService: AuthService,
              private route: ActivatedRoute,
              private router: Router,
              private cookieService: CookieConsent,
              private http: Http,
              private errrorHandler: ErrrorHandler) {}

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      
      let orderCookie = this.cookieService.getCookie('order');
      console.log(orderCookie);
      if(this.authService.isLoggedIn() && orderCookie !== undefined){          
                
        let params: URLSearchParams = new URLSearchParams();
        params.set('email', this.authService.getEmail());
        
        let order:Order = JSON.parse(orderCookie);
        order.client = this.authService.getEmail();
        
        this.cookieService.setCookie("order", order, 1);
        let requestOptions = new RequestOptions({ headers: this.authService.getAuthHeaders(),
           search: params });

        this.http.get('/api/resources/userregistered', requestOptions).subscribe(
          (response) => 
              this.navigate(response)
          ,
          (error) => this.errrorHandler.handleError
        );        
      }     
    });
  }

  private navigate(response) {
      console.log(response.json());
      console.log(response.json() === false);
      if(response.json() === false){
        this.router.navigate(['/profile']);
      }else{
        this.router.navigate(['/orders']);
      }
  }

}
