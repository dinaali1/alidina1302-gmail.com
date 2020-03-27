import { Injectable } from '@angular/core';
import { UrlService } from './url.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { User, Student, Professor } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class ProfessorService {

  constructor(
    private urlService: UrlService,
    private http: HttpClient,
  ) { }

  updateProfessor(professor: Professor): Observable<Professor> {
    return this.http.put(`${this.urlService.url}/api/v1/professors/${professor.id}`, professor);
  }
}
