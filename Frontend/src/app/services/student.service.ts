import { Injectable } from '@angular/core';
import { UrlService } from './url.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { User, Student } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(
    private urlService: UrlService,
    private http: HttpClient,
  ) { }

  updateStudent(student: Student): Observable<Student> {
    return this.http.put(`${this.urlService.url}/api/v1/students/${student.id}`, student);
  }

  addStudent(student) {
    return this.http.post(`${this.urlService.url}/api/v1/students/`, student)
  }
}
