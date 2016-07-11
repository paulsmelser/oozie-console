'use strict';

import { Component } from "@angular/core";
import { HTTP_PROVIDERS } from "@angular/http";
import { ROUTER_DIRECTIVES, ROUTER_PROVIDERS, Router, Routes } from '@angular/router';
import { CoordinatorListComponent } from "./coordinator-list/coordinator-list.component";
import { CoordinatorService } from "./coordinator/coordinator.service";
import { LoginComponent } from "./login/login.component";

@Component({
    selector: 'oozie-console',
    templateUrl: '/app/app.html',
    directives: [ROUTER_DIRECTIVES, LoginComponent],
    providers: [ROUTER_PROVIDERS, HTTP_PROVIDERS, CoordinatorService]
})
@Routes([
    {path: '/', component: CoordinatorListComponent }
])
export class AppComponent {
    constructor(private router:Router) {}

}
