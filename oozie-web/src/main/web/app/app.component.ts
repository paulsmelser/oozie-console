'use strict';

import {Component, provide} from "@angular/core";
import { HTTP_PROVIDERS } from "@angular/http";
import { ROUTER_DIRECTIVES, ROUTER_PROVIDERS, Router, Routes } from '@angular/router';
import { CoordinatorListComponent } from "./coordinator/list/coordinator-list.component";
import { CoordinatorComponent } from "./coordinator/coordinator.component";
import {CoordinatorService} from "./coordinator/coordinator.service";
import { LoginComponent } from "./login/login.component";
import {HashLocationStrategy, LocationStrategy} from "@angular/common";
import {LoginService} from "./login/login.service";

@Component({
    selector: 'oozie-console',
    templateUrl: '/app/app.html',
    directives: [ROUTER_DIRECTIVES, LoginComponent],
    providers: [
        ROUTER_PROVIDERS,
        HTTP_PROVIDERS,
        CoordinatorService,
        LoginService,
        provide(LocationStrategy, {useClass: HashLocationStrategy})
    ]
})
@Routes([
    { path: '/', component: CoordinatorListComponent },
    { path: '/coordinator/:id', component: CoordinatorComponent }
])
export class AppComponent {
    constructor(private router:Router) {}

}
