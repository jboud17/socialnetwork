export class User {
    'id': number;
    'firstName': string;
    'lastName': string;
    
    constructor (id: number, firstName: string, lastName: string){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    getId(): number{
        return this.id;
    }

    getFirstName(): string{
        return this.firstName;
    }

    getLastName(): string{
        return this.lastName;
    }
}