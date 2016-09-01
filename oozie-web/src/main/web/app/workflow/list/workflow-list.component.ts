import { Component } from "@angular/core";
import { ROUTER_DIRECTIVES, RouteSegment } from "@angular/router";
import { WorkflowService } from "../workflow.service";
import 'rxjs/add/operator/map';

@Component({
    templateUrl: '/app/coordinator/list/coordinator-list.component.html',
    directives: [ROUTER_DIRECTIVES],
})
export class WorkflowListComponent{
    private workflow;
    constructor(private workflowService: WorkflowService, private route: RouteSegment) {

        workflowService.getWorkflow(route.getParam("id")).subscribe(data => this.workflow = data.json());
    }
}