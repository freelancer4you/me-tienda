import { Address }    from './address';

export class Profile {
  constructor(
    public email: string,
    public name: string,
    public familyName: string,
    public givenName: string,
    public preferredUsername:string,
    public address: Address    
  ) {  }
}