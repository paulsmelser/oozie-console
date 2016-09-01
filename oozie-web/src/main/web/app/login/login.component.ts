import { Component } from "@angular/core";
import { MaterializeDirective } from "angular2-materialize";
import { Http } from "@angular/http";

@Component({
    selector: "login",
    templateUrl: "/app/login/login.component.html",
    styleUrls: ["/app/login/login.component.css"],
    directives: [ MaterializeDirective ]
})
export class LoginComponent{
    private loggedIn: boolean;
    private demo: boolean;

    constructor(private http: Http){
        this.loggedIn = false;
        this.demo = false;
    }

    toggleDemoData(){
        if(!this.demo){
            this.demo = true;
            this.http.get("api/v1/login/demo");
        }
        this.demo = false;
        this.http.get("api/v1/login/demo/cancel");
    }
}