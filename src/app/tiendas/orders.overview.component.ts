import { Component, OnInit } from '@angular/core';
// import { Http, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {$WebSocket} from 'angular2-websocket/angular2-websocket';

@Component({
  selector: 'app-orders-overview',
  templateUrl: './orders.overview.component.html',
})
export class OrdersOverviewComponent implements OnInit {

  orders = [];

  ws: $WebSocket;
  constructor() {
  }

  ngOnInit() {
    this.ws = new $WebSocket('ws://localhost:9000/orders');
    this.ws.onMessage(
      (msg: MessageEvent) => {
        //console.log('onMessage ', JSON.parse(msg.data));
        this.orders.push(JSON.parse(msg.data));
      },
      {autoApply: false}
    );
  }

  public formatDate(dateLongVal){
    let date = new Date(dateLongVal);
    return  date.getDate() + "." + ("0" + (date.getMonth())).slice(-2) + "." + date.getFullYear();
  }
}
