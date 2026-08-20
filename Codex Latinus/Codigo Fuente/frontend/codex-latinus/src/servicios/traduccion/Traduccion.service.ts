import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class TraduccionService{

    private sePuedeTraducirSubject = new BehaviorSubject<boolean>(false);
    sePuedeTraducir$ = this.sePuedeTraducirSubject.asObservable();

    isTraducible(): boolean{
        return this.sePuedeTraducirSubject.value;
    }

    setIsTraducible(sePuedeTraducir:boolean){
        this.sePuedeTraducirSubject.next(sePuedeTraducir);
    }
}
