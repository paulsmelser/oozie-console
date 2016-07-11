import { Component } from "@angular/core";
import { MaterializeDirective } from "angular2-materialize";

@Component({
    selector: "login",
    templateUrl: "/app/login/login.component.html",
    styleUrls: ["/app/login/login.component.css"],
    directives: [ MaterializeDirective ]
})
export class LoginComponent{
    loggedIn: boolean;

}