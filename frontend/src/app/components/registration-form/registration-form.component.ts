import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { StudentService } from 'src/app/services/student.service';
import {UserDetails} from '../../models/user-details';
import { Student } from '../../models/student'
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent implements OnInit {

  userDetails: UserDetails;
  form: FormGroup;
  service: StudentService;
  router: Router;

  constructor(service: StudentService, router: Router) {
    this.service = service;
    this.router = router;
   }

  ngOnInit(): void {
    this.form = new FormGroup({
      name: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      ssn: new FormControl('', [Validators.required, Validators.minLength(9), Validators.maxLength(9), Validators.pattern("^[0-9]*$")]),
      phone: new FormControl('', [Validators.required, Validators.pattern("[0-9]{3}-[0-9]{3}-[0-9]{4}")]),
      street: new FormControl('', [Validators.required]),
      city: new FormControl('', [Validators.required]),
      state: new FormControl('', [Validators.required]),
      zipcode: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(5), Validators.pattern("^[0-9]*$")]),
      schoolid: new FormControl('', [Validators.required]),

    })
  }

  submit() {
    let student: Student = this.form.value
    this.service.addStudent(student).subscribe(
      res =>  {
        console.log(res)
        this.router.navigate(['student/all'])
      },
      res => console.log(res)
    )
  }


}
