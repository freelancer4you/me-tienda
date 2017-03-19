import { Address }    from './address';

export class Profile {
  constructor(
    public salutation: string,
    public title: string,
    public firstName: string,
    public lastName: string,
    public address: Address,
    public phoneNumber: string,
    public email: string
  ) {  }
}