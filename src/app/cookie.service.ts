
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
            //console.log(c);
            //console.log(c.indexOf(cookieName));
            //console.log(c.indexOf(name));
            //console.log(c === cookieName);
            if (c.indexOf(cookieName) === 1) {
                return c.substring(cookieName.length, c.length);
            }
        }
        return undefined;
    }

    public deleteCookie(name) {
        this.setCookie(name, "", -1);
    }

    public setCookie(name: string, value: string, expireDays: number, path: string = "") {
        let d:Date = new Date();
        d.setTime(d.getTime() + expireDays * 24 * 60 * 60 * 1000);
        let expires:string = "expires=" + d.toUTCString();
        document.cookie = name + "=" + value + "; " + expires + (path.length > 0 ? "; path=" + path : "");
    }
}