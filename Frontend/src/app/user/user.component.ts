import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { UserService } from 'app/services/user.service';
import { Router } from '@angular/router';
import { User, Student, Professor, Agent } from 'app/models/user';
import { ProfessorService } from 'app/services/professor.service';
import { StudentService } from 'app/services/student.service';
import { AgentService } from 'app/services/agent.service';
import { NotificationService } from 'app/services/notification.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  updateUser: FormGroup;
  user: any;

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private router: Router,
    private professorService: ProfessorService,
    private studentService: StudentService,
    private agentService: AgentService,
    private notificationService: NotificationService

  ) {
    this.user = JSON.parse(localStorage.getItem('user'));
    this.updateUser = this.formBuilder.group({
      email: [this.user.email, [
        Validators.required,
        Validators.pattern(
          // tslint:disable-next-line: max-line-length
          /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        ),
      ]],
      firstName: [this.user.firstName, Validators.required],
      lastName: [this.user.lastName, Validators.required],
      id: new FormControl(this.user.id),
      grade: new FormControl(this.user.grade),
      role: new FormControl(this.user.role),
      numCarteEtudiant: new FormControl(this.user.numCarteEtudiant),
      subject: new FormControl(this.user.subject),
      agentRole: new FormControl(this.user.agentRole),
    });
  }

  ngOnInit() {
    switch (this.user.role) {
      case 'student':
        this.studentService.updateStudent(this.user).subscribe(
          student => {
            this.user = student;
            localStorage.removeItem('user');
            localStorage.setItem('user', JSON.stringify(student));
          },
        );
        break;
      case 'agent':
        this.agentService.updateAgent(this.user).subscribe(
          agent => {
            this.user = agent;
            localStorage.removeItem('user');
            localStorage.setItem('user', JSON.stringify(agent));
          },
        );
        break;
      case 'professor':
        this.professorService.updateProfessor(this.user).subscribe(
          professor => {
            this.user = professor;
            localStorage.removeItem('user');
            localStorage.setItem('user', JSON.stringify(professor));
          },
        );
        break;

      default:
        break;
    }
  }

  update(): void {
    this.user = this.updateUser.value
    switch (this.user.role) {
      case 'student':
        this.studentService.updateStudent(this.user).subscribe(
          student => {
            this.notificationService.showSuccessNotification('updated successfully');
            this.user = student;
            localStorage.removeItem('user');
            localStorage.setItem('user', JSON.stringify(student));
          },
          err => {
            this.notificationService.showWarningNotification(err.error);
          }
        );
        break;
      case 'agent':
        this.agentService.updateAgent(this.user).subscribe(
          agent => {
            this.notificationService.showSuccessNotification('Updated successfully');
            this.user = agent;
            localStorage.removeItem('user');
            localStorage.setItem('user', JSON.stringify(agent));
          },
          err => {
            this.notificationService.showWarningNotification(err.error);
          }
        );
        break;
      case 'professor':
        this.notificationService.showSuccessNotification('Updated successfully');
        this.professorService.updateProfessor(this.user).subscribe(
          professor => {
            this.user = professor;
            localStorage.removeItem('user');
            localStorage.setItem('user', JSON.stringify(professor));
          },
          err => {
            this.notificationService.showWarningNotification(err.error);
          }
        );
        break;

      default:
        break;
    }
  }

}
