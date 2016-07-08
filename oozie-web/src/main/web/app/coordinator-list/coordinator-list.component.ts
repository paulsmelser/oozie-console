import {Component} from "@angular/core";
import {ROUTER_PROVIDERS, ROUTER_DIRECTIVES} from "@angular/router";
import { Http, Headers, Response } from "@angular/http";
import { Observable } from "rxjs/Rx";
import { CoordinatorService } from "../coordinator/coordinator.service";
import 'rxjs/add/operator/map';

@Component({
    templateUrl: '/app/coordinator-list/coordinator-list.component.html',
    directives: [ROUTER_DIRECTIVES],
})
export class CoordinatorListComponent{
    private coords;
    constructor(private http: Http, private coordinatorService: CoordinatorService) {
        this.coordinatorService.getRunningCoordinators()
        .subscribe(() =>
                http.get("api/v1/coordinators")
                .subscribe(data => this.coords = data.json())
        );
    }
}