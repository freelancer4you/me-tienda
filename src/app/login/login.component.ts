import { Component, OnInit, OnDestroy } from '@angular/core';
import { Http, URLSearchParams } from '@angular/http';
import { AuthService }    from '../auth.service'
import { ErrrorHandler }    from '../errorhandler.service'
import {Observable} from 'rxjs/Observable';
declare var gapi: any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  constructor(private authService: AuthService,              
              private http: Http,
              private errrorHandler: ErrrorHandler) {}

  ngOnInit() {  
  }

  ngOnDestroy() {
  }

  public login() {
    this.authService.login();
  }

  googleRegistration(){
      var so = this;
      
      var params = {
						'clientid': '554536775328-40gntdhkh3ep0irr0t4is5g46m9t5if0.apps.googleusercontent.com',
						'cookiepolicy': 'single_host_origin',
						'callback': function(result){
							
							gapi.client.load('plus','v1', function(){
              var request = gapi.client.plus.people.get({
                'userId': 'me'
              });
              request.execute(function(resp) {
                console.log('Retrieved profile for:' + resp.displayName);
                var googleAccount = {
												'familyName' : resp.name.familyName,
												'gender' : resp.gender,
												'givenName' : resp.name.givenName,
												'language' : resp.language,
												'displayName' : resp.displayName,
												'email' : resp.emails[0].value,
												'imageUrl' : resp.image.url,												
										};

                 so.http.post('/api/resources/googleregistration', googleAccount)
                .map(result => {
                  console.log("Registration successfull");
                })
                .catch(so.errrorHandler.handleError)
                .toPromise(); 

              });
              });
						},
						'approvalprompt': 'force',//'auto' Auto nicht verwenden, da ansonsten request.execute 2mal aufgerufen wird
						'scope': 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.profile.emails.read'
				};
				gapi.auth.signIn(params);
    }
 
  signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut();
  }
}
