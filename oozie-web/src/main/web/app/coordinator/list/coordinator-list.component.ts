import { Component, OnInit } from "@angular/core";
import { ROUTER_DIRECTIVES, Router } from "@angular/router";
import { Http } from "@angular/http";
import { CoordinatorService } from "../coordinator.service";
import 'rxjs/add/operator/map';

@Component({
    templateUrl: '/app/coordinator/list/coordinator-list.component.html',
    directives: [ROUTER_DIRECTIVES],
})
export class CoordinatorListComponent implements OnInit{
    private coords;
    constructor(private http: Http, private coordinatorService: CoordinatorService, private router: Router) {}

    ngOnInit(): void {
        this.coordinatorService.getRunningCoordinators()
            .subscribe(() =>
                this.http.get("api/v1/coordinators")
                    .subscribe(data => this.coords = data.json())
            );
    }

    details(coord){
        this.router.navigate(["/coordinator/"+coord.coordJobId]);
    }
}