import {Component} from "@angular/core";
import {ROUTER_PROVIDERS, ROUTER_DIRECTIVES} from "@angular/router";
import { Http, Headers, Response } from "@angular/http";
import { Observable } from "rxjs/Rx";
import 'rxjs/add/operator/map';

@Component({
    templateUrl: '/app/coordinator-list/coordinator-list.component.html',
    directives: [ROUTER_DIRECTIVES],
})
export class CoordinatorListComponent{
    private coords;
    constructor(private http: Http) {
        this.http.get("api/v1/login?username=hi&password=bye&stayLoggedIn=false&clusterUri=http://psmelser.com")
        .subscribe(() =>
                http.get("api/v1/coordinators")
                .subscribe(data => this.coords = data.json())
        );
    }
}