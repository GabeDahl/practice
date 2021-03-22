import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.css']
})
export class StudentsListComponent implements OnInit {

  service: StudentService;
  students: Student[];
  isNoStudents: boolean;
  error: string;
  router: Router;

  constructor(service: StudentService, router: Router) {
    this.service = service
    this.router = router
   }

  ngOnInit(): void {
    this.service.getAllStudents().subscribe(
      res => {
        this.students = res
      },
      err => this.error = err
    )
  }

  navigate(e) {
    console.log(e)
    this.router.navigate(['student'], {queryParams: {id: e}})
  }

}
