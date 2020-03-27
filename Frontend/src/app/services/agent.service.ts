import { Injectable } from '@angular/core';
import { UrlService } from './url.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { User, Student, Professor, Agent } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AgentService {

  constructor(
    private urlService: UrlService,
    private http: HttpClient,
  ) { }

  updateAgent(agent: Agent): Observable<Agent> {
    return this.http.put(`${this.urlService.url}/api/v1/agents/${agent.id}`, agent);
  }
}
