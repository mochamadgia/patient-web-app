export class Address {
  id?: number;
  address?: string;
  suburb?: string;
  state?: string;
  postCode?: string;

  getObject(){
    return {
      "id":this.id,
      "address":this.address,
      "suburb":this.suburb,
      "state":this.state,
      "postCode":this.postCode
    }
  }
}
