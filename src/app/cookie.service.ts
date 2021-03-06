
import { Injectable } from '@angular/core';

@Injectable()
export class  CookieConsent {
    
    constructor() {
    }

    public getCookie(name: string) {
        let ca: Array<string> = document.cookie.split(';');
        let caLen: number = ca.length;
        let cookieName = name + "=";
        let c: string;

        for (let i: number = 0; i < caLen; i += 1) {
            c = ca[i].replace(/^\s\+/g, "");
         
            if (c.indexOf(cookieName) === 0 || c.indexOf(cookieName) === 1) {
                let cookie = c.substring(cookieName.length, c.length);
                if(cookie.startsWith('=')){
                    return cookie.replace('=','');
                }
                return cookie;
            }
        }
        return undefined;
    }

    public deleteCookie(name) {
        this.setCookie(name, "", -1);
    }

    public setCookie(name: string, value: any, expireDays: number, path: string = "") {
        let d:Date = new Date();
        d.setTime(d.getTime() + expireDays * 24 * 60 * 60 * 1000);
        let expires:string = "expires=" + d.toUTCString();
        document.cookie = name + "=" + JSON.stringify(value) + "; " + expires + (path.length > 0 ? "; path=" + path : "");
    }
}