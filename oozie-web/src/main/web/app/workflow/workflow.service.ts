import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import 'rxjs/add/operator/map';

@Injectable()
export class WorkflowService{
    constructor(private http: Http){}

    login(){
        return this.http.get("api/v1/login?username=hi&password=bye&stayLoggedIn=false&clusterUri=http://psmelser.com");
    }

    getWorkflow(id: String){
        return this.http.get("api/v1/workflows/" + id);
    }
}