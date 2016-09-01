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
    constructor(private coordinatorService: CoordinatorService, private route: RouteSegment) {}

    ngOnInit(): void {
        this.coordinatorService.getCoordinator(this.route.getParam("id"))
            .subscribe(data => {
                this.coord = data.json();
            });

    }
}