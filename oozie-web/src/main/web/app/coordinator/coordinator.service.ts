import { Injectable } from "@angular/core";
import { HTTP_PROVIDERS, Http } from "@angular/http";
import { Observable } from "rxjs/Rx";
import 'rxjs/add/operator/map';

@Injectable()
export class CoordinatorService{
    constructor(private http: Http){}

    getRunningCoordinators(){
        return this.http.get("api/v1/login?username=hi&password=bye&stayLoggedIn=false&clusterUri=http://psmelser.com");
    }
}