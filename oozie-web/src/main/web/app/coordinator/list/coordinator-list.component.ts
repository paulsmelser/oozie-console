import { Component, OnInit } from "@angular/core";
import { ROUTER_DIRECTIVES, Router } from "@angular/router";
import { Http } from "@angular/http";
import { CoordinatorService } from "../coordinator.service";
import 'rxjs/add/operator/map';
import {LoginService, HadoopAccount, HadoopBuilder} from "../../login/login.service";

@Component({
    templateUrl: '/app/coordinator/list/coordinator-list.component.html',
    directives: [ROUTER_DIRECTIVES],
})
export class CoordinatorListComponent implements OnInit{
    private coords;
    constructor(private http: Http, private coordinatorService: CoordinatorService, private router: Router, private loginService: LoginService) {}

    ngOnInit(): void {
        var hadoopAccount = HadoopAccount.builder()
            .withUsername("joe")
            .withPassword("hello")
            .withClusterUri("http://psmelser.com")
            .build()
        this.loginService.login(hadoopAccount)
            .subscribe(() =>
                this.http.get("api/v1/coordinators")
                    .map(res => res.json())
                    .subscribe(data => this.coords = data)
            );
        // this.coordinatorService.getRunningCoordinators()
        //     .subscribe(() =>
        //         this.http.get("api/v1/coordinators")
        //             .subscribe(data => this.coords = data.json())
        //     );
    }

    details(coord){
        this.router.navigateByUrl("/coordinator/"+coord.coordJobId);
    }
}