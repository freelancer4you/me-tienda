import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/Rx';
import { Profile }    from './profile/profile';
import { Address }    from './profile/address';

@Injectable()
export class AuthService {

  private authState: AuthState;
  private clientId: string =  'portfolio-service';
  private redirectUrl: string =  'http://localhost:8080/auth/realms/portfolio/protocol/openid-connect/auth';
 
  private id_token_payload: any;

  constructor(private http: Http) {}

  public login() {
    let url = this.redirectUrl +
    '?response_type=id_token+token&scope=openid%20profile' +
    '&client_id=' + encodeURIComponent(this.clientId) +
    '&redirect_uri=' + encodeURIComponent(window.location.origin) +
    '&nonce=' + encodeURIComponent(this.createNonce());
    location.href = url;
  }

  public checkLoginState() {
    
    let oidcResponse = window.location.hash.substr(1);
    if (!oidcResponse) {
      return;
    }
    let oidcParams = oidcResponse.split('&');
    let splitParams: string[];
    let data = <AuthState>{};
    for (let i = 0; i < oidcParams.length; i++) {      
        splitParams = oidcParams[i].split('=');
        splitParams[0] = decodeURIComponent(splitParams[0]);
        splitParams[1] = decodeURIComponent(splitParams[1]);
        data[splitParams[0]] = splitParams[1];      
    }
    this.authState = data;    
    let split_id_token = this.authState.id_token.split('.');
    this.id_token_payload = JSON.parse(atob(split_id_token[1]));        
  }

  public logout() {
    this.authState = null;
  }

  public isLoggedIn(): boolean {
    //console.log('isLoggedIn:' + !!this.authState);
    return !!this.authState;
  }

  public getAuthHeaders(): Headers {
    return new Headers({'Authorization': 'Bearer ' + this.authState.access_token});
  }

  public getUserName(): Headers {    
    return this.id_token_payload.preferred_username ?
    this.id_token_payload.preferred_username : this.id_token_payload.name;
  }

  public getProfile(address:Address): Profile {
    console.log(this.id_token_payload);
    return new Profile(this.id_token_payload.email,
                       this.id_token_payload.name,
                       this.id_token_payload.family_name,
                       this.id_token_payload.given_name,
                       this.id_token_payload.preferred_username,
                       address);        
  }

  private createNonce(): string {
    // Nonce Generierung: Statische Demo-Implementierung
    let nonce = Math.floor(Math.random() * 1000) + '';
    localStorage.setItem('nonce', nonce);
    return nonce;
  }
}

interface AuthState {
  access_token: string;
  id_token: string;
  expires_in: number;
  token_type: string;
  'not-before-policy': number;
  session_state: string;
}
