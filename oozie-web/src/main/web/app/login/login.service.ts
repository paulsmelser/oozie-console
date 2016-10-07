import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import 'rxjs/add/operator/map';

@Injectable()
export class LoginService{
    constructor(private http: Http){}

    login(hadoopAccount: HadoopAccount){
        return this.http.get("api/v1/login?username="+hadoopAccount.Username+"&password="+hadoopAccount.Password+"&stayLoggedIn=false&clusterUri="+hadoopAccount.ClusterUri);
    }
}

export class HadoopAccount{
    constructor(private username: string, private password: string, private clusterUri: string) {
    };

    public static builder(): HadoopBuilder {
        return new HadoopBuilder();
    }

    get Username(): string {
        return this.username;
    }

    get Password(): string {
        return this.password;
    }

    get ClusterUri(): string {
        return this.clusterUri;
    }
}

export class HadoopBuilder{
    private username: string;
    private password: string;
    private clusterUri: string;

    withUsername(value: string): HadoopBuilder {
        this.username = value;
        return this;
    }

    withPassword(value: string): HadoopBuilder {
        this.password = value;
        return this;
    }

    withClusterUri(value: string): HadoopBuilder {
        this.clusterUri = value;
        return this;
    }

    build(): HadoopAccount {
        return new HadoopAccount(this.username, this.password, this.clusterUri);
    }
}