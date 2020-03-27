import { Injectable } from '@angular/core';
import { UrlService } from './url.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { stringify } from 'querystring';

@Injectable({
  providedIn: 'root'
})
export class PromotionsService {

  constructor(
    private urlService: UrlService,
    private http: HttpClient,
  ) { }

  getPromotionByYearAndGrade(filters): Observable<any> {
    const params = new HttpParams()
      .set('year', filters.year)
      .set('grade', filters.grade);
    return this.http.get(`${this.urlService.url}/api/v1/promotions/`, { params });
  }

  addStudentToPromotion(promotion): Observable<any> {
    return this.http.post(`${this.urlService.url}/api/v1/promotions/`, promotion);
  }
}
