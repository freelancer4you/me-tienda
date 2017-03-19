import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/Rx';

@Injectable()
export class ErrrorHandler {

    public handleError(error: any) {
        // log error
        // could be something more sofisticated
        let errorMsg = error.message || `Yikes! There was was a problem with our hyperdrive device and we couldn't retrieve your data!`;
        console.error(errorMsg);
        // instead of Observable we return a rejected Promise
        return Promise.reject(errorMsg);
    }
}