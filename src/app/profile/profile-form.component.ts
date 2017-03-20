import { Component, OnInit } from '@angular/core';
import { Http, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Address }    from './address';
import { ErrrorHandler }    from '../errorhandler.service'
import { CookieConsent }    from '../cookie.service'
import { AuthService }    from '../auth.service'


@Component({
  selector: 'app-profile',
  templateUrl: './profile-form.component.html',
})
export class ProfileComponent implements OnInit {
    
    address = new Address('','','','',"")

    constructor(private http: Http,
                private errrorHandler: ErrrorHandler,
                private cookieService: CookieConsent,
                private authService: AuthService){}

    ngOnInit() {

      // Order laden
      let orderCookie = this.cookieService.getCookie('order');
      console.log(orderCookie);

      //wenn Order verschickt onSubmit() löschen
      this.cookieService.deleteCookie('order');
    }

    onSubmit() { 
      console.log("submit data ...");
      /*this.http.post('/api/resources/defaultregistration', this.address)
            // .map(res => res.json())
            .map(result => {
              console.log("Registration successfull");
            })
            .catch(this.errrorHandler.handleError)
            .toPromise(); 
      */
      console.log(this.authService.getProfile(this.address));
    }

}