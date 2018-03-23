export class User {
    'id': number;
    'firstName': string;
    'lastName': string;
    'email': string;
    
    constructor (id: number, firstName: string, lastName: string, email: string){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    getEmail(): string{
        return this.email;
    }
}