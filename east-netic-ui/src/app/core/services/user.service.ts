import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {OAUTH_TOKEN_URL, USER_URL} from "../constants/enpoints";
import {User} from "../models/user";
import {Observable} from "rxjs";

@Injectable()
export class UserService {
  constructor(private httpClient: HttpClient) {
  }

  login(user: User): Observable<any> {
    const grantType = 'password';

    let params = new URLSearchParams();
    params.append('username', user.username);
    params.append('password', user.password);
    params.append('grant_type',grantType);

    let headers = new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8','Authorization': 'Basic '+btoa('east-netic'+":secret")});
    return this.httpClient.post(OAUTH_TOKEN_URL, params.toString(), { headers: headers });
  }

  register(user: User): Observable<User> {
    return this.httpClient.post<User>(USER_URL, user);
  }

  getUserByUsername(username: string): Observable<User> {
    return this.httpClient.get<User>(`${USER_URL}/${username}`)
  }
}
