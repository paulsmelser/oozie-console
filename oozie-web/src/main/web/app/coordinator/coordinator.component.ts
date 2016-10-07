import {Component, OnInit} from "@angular/core";
import {ROUTER_DIRECTIVES, RouteSegment} from "@angular/router";
import "rxjs/add/operator/map";
import {CoordinatorService} from "./coordinator.service";

@Component({
    templateUrl: '/app/coordinator/coordinator.component.html',
    directives: [ROUTER_DIRECTIVES],
})
export class CoordinatorComponent implements OnInit{
    private coord;
    private coordString : String;
    constructor(private coordinatorService: CoordinatorService, private route: RouteSegment) {}

    ngOnInit(): void {
        this.coordinatorService.getCoordinator(this.route.getParam("id"))
            .map(data => data.json()).subscribe(json => {
            this.coord = json;
            this.coordString = this.coord;
        });
    }
}