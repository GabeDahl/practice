import {School} from './school'

export interface Student {
    name: string;
    id?: Number;
    school?: School;
    email: string;
    ssn: Number
    phone: string
    street: string;
    city: string;
    state: string;
    zipcode: Number;
    schoolid?: Number;
}