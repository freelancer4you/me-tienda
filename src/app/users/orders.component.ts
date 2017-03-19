import { Component, OnInit, ViewChild } from '@angular/core';
import { Http, URLSearchParams, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { AuthService }  from '../auth.service';
import { Order }        from './order';
import { ErrrorHandler }    from '../errorhandler.service'
import { Router } from '@angular/router';
import { CookieConsent }  from '../cookie.service'

import { ContextMenuService, ContextMenuComponent } from 'angular2-contextmenu';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
})
export class OrderComponent implements OnInit {

  cart = [];
  categories = [];
  orderConfirmationMsg = '';
  myMap: Map<string, string[]>;

  @ViewChild('basicMenu') public basicMenu: ContextMenuComponent;
 
  constructor(private http: Http, 
              private contextMenuService: ContextMenuService,
              private authService: AuthService,
              private errrorHandler: ErrrorHandler,
              private cookieService: CookieConsent,
              private router:Router) {}

  ngOnInit() {
      this.http.get('/api/resources/categories')
        .toPromise()
        .then(r => r.json())
        .then(r => this.categories = r);
      this.myMap = new Map();
  }

  public sendOrder() {    
    // Client-ID könnte auf Serverseite über Header ermittelt werden
    var order = new Order("TodId", this.cart, new Date().getTime());

    if(!this.authService.isLoggedIn()){
      this.cookieService.setCookie('order', JSON.stringify(order), 1);
      this.router.navigate(['/login']);
      return;
    }

    let requestOptions = new RequestOptions({ headers: this.authService.getAuthHeaders() });

    this.http.post('api/resources/orders', order, requestOptions)
      // .map(res => res.json())
      .map(result => {
        this.orderConfirmationMsg = 'Bestellung wurde abgeschickt.';
        // return console.log(result);
      })
      .catch(this.errrorHandler.handleError)
      .toPromise();
  }

  public getProducts(category) {
    return this.myMap.get(category);
  }

  public loadProducts(category) {
    if (!this.myMap.has(category)) {
      // console.log('Load products');

      let params: URLSearchParams = new URLSearchParams();
      params.set('category', category);

      this.http.get('/api/resources/products', {
         search: params
       }).subscribe(
         (response) => this.myMap.set(category, response.json()),
<<<<<<< eeb788b02ce73fec4c3d0e0aa80bfc0b5bdaa20a
         (error) => this.errrorHandler.handleError
=======
         (error) => this.handleError
>>>>>>> Initial commit
       );
    }
  }

  public getClass(idx, list) {
    return idx <= 2 ? 'fif' : '';
  }

  public increaseProductCount(product) {
    // console.log('increaseProductCount:' + product.id);
    for (let i = 0; i < this.cart.length; i++) {
      if (product.id === this.cart[i].id) {
        this.cart[i].count++;
        // console.log(this.cart[i].count);
        break;
      }
    }
  }

  public addProductToCart(product) {
    // console.log('add ' + product + ' to cart');
    let productInCart = false;
    for (let i = 0; i < this.cart.length; i++) {
      if (product.id === this.cart[i].id) {        
        productInCart = true;
        break;
      }
    }
    if (!productInCart) {
      product.count++;
      this.cart.push(product);
    }
  }

  public removeProductFromCart(product) {
    // console.log('removeProductFromCart:' + product);
    let index = 0;
    for (let i = 0; i < this.cart.length; i++) {
      if (product.id === this.cart[i].id) {
        break;
      }
      index++;
    }

    if (index > -1) {
      this.cart.splice(index, 1);
    }
  }

  public onContextMenu($event: MouseEvent, item: any): void {
    this.contextMenuService.show.next({ event: $event, item: item });
    $event.preventDefault();
  }
<<<<<<< eeb788b02ce73fec4c3d0e0aa80bfc0b5bdaa20a
=======

  private handleError(error: any) {
    // log error
    // could be something more sofisticated
    let errorMsg = error.message || `Yikes! There was was a problem with our hyperdrive device and we couldn't retrieve your data!`;
    console.error(errorMsg);
    // instead of Observable we return a rejected Promise
    return Promise.reject(errorMsg);
  }

>>>>>>> Initial commit
}
