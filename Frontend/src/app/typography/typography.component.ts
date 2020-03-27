import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NotificationService } from 'app/services/notification.service';
import { PromotionsService } from 'app/services/promotions.service';
import { StudentService } from 'app/services/student.service';
import { Student } from 'app/models/user';

@Component({
  selector: 'app-typography',
  templateUrl: './typography.component.html',
  styleUrls: ['./typography.component.css']
})
export class TypographyComponent implements OnInit {

  addStudentForm: FormGroup;
  addStudentToPromotionForm: FormGroup;


  constructor(
    private formBuilder: FormBuilder,
    private notificationService: NotificationService,
    private studentService: StudentService,
    private promotionService: PromotionsService,
  ) {
    this.addStudentForm = this.formBuilder.group({
      email: ['', [
        Validators.required,
        Validators.pattern(
          // tslint:disable-next-line: max-line-length
          /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        ),
      ]],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      grade: ['', Validators.required],
      numCarteEtudiant: ['', Validators.required],
    });
    this.addStudentToPromotionForm = this.formBuilder.group({
      studentId: ['', Validators.required],
      grade: ['', Validators.required],
      year: ['', Validators.required],
    })
  }

  ngOnInit() {
  }

  addStudent() {
    if (!this.addStudentForm.valid) {
      this.notificationService.showWarningNotification('Form Invalid');
      return;
    }
    const student = this.addStudentForm.value;
    this.studentService.addStudent(student).subscribe(
      res => this.notificationService.showSuccessNotification('Student added successfully'),
      err => this.notificationService.showWarningNotification(err.error),
    )
  }

  addStudentToPromotion() {
    const promotion = this.addStudentToPromotionForm.value;
    if (!this.addStudentToPromotionForm.valid) {
      this.notificationService.showWarningNotification('Form Invalid');
      return;
    }
    promotion.student = new Student();
    promotion.student.id = promotion.studentId;
    console.log(promotion)
    this.promotionService.addStudentToPromotion(promotion).subscribe(
      res => this.notificationService.showSuccessNotification('Student added successfully'),
      err => this.notificationService.showWarningNotification(err.error),
    )
  }

}
