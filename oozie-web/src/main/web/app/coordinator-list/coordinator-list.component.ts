import {Component} from "@angular/core";
import {ROUTER_PROVIDERS, ROUTER_DIRECTIVES} from "@angular/router";
import { Http, Headers, Response } from "@angular/http";
import { Observable } from "rxjs/Rx";
import 'rxjs/add/operator/map';

@Component({
    templateUrl: '/app/coordinator-list/coordinator-list.component.html',
    directives: [ROUTER_DIRECTIVES]
})
export class CoordinatorListComponent{
    private coords;
    constructor(private http: Http) {
        http.get("rest/v1/login?username=hi&password=bye&stayLoggedIn=false&clusterUri=http://psmelser.com")
        .subscribe(() =>
                http.get("rest/v1/coordinators")
                .subscribe(data => this.coords = data.json())
        );

        //
        //Promise.resolve(http.get("rest/v1/login?username=hi&password=bye&stayLoggedIn=false&clusterUri=http://psmelser.com")
        //    .map(result => result.text()))
        //    .then(http.get("rest/v1/coordinators")
        //    .map(result => coords.json())
        //    .subscribe(
        //        data => this.coords = data
        //));
    }
}