import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import 'rxjs/add/operator/map';

@Injectable()
export class CoordinatorService{
    constructor(private http: Http){}

    getRunningCoordinators(){
        return this.http.get("api/v1/login?username=hi&password=bye&stayLoggedIn=false&clusterUri=http://psmelser.com");
    }

    getCoordinator(id: String){
        return this.http.get("api/v1/coordinator/"+id);
    }
}