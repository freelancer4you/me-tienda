export class Order {
  constructor(
    public client: string,
    public cart: string[],
    public date: number    
  ) {  }
}