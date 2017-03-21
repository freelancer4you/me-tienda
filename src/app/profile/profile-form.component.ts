import { Component, OnInit } from '@angular/core';
import { Http, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Address }    from './address';
import { ErrrorHandler }    from '../errorhandler.service'
import { CookieConsent }    from '../cookie.service'
import { AuthService }    from '../auth.service'
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-profile',
  templateUrl: './profile-form.component.html',
})
export class ProfileComponent implements OnInit {
    
    address = new Address('','','','',"")

    constructor(private http: Http,
                private errrorHandler: ErrrorHandler,
                private cookieService: CookieConsent,
                private authService: AuthService,
                private router: Router,){}

    ngOnInit() {
    }

    onSubmit() { 
      console.log("submit data ...");
      console.log(this.authService.getProfile(this.address));

      this.http.post('/api/resources/defaultregistration', this.authService.getProfile(this.address))
            // .map(res => res.json())
            .map(result => {
              //console.log("Registration successfull");
              //console.log(result);

               // Order laden
                let orderCookie = this.cookieService.getCookie('order');
                //console.log(orderCookie);
                if(orderCookie !== undefined){          
                  this.router.navigate(['/orders']);
                }  
            })
            .catch(this.errrorHandler.handleError)
            .toPromise();       

    }

}