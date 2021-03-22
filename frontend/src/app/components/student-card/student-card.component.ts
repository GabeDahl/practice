import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';
import { EditStudentDialogComponent } from '../edit-student-dialog/edit-student-dialog.component';

@Component({
  selector: 'app-student-card',
  templateUrl: './student-card.component.html',
  styleUrls: ['./student-card.component.css']
})
export class StudentCardComponent implements OnInit {
  service: StudentService;
  student: Student;
  route: ActivatedRoute;
  router: Router;
  id: string;

  constructor(service: StudentService, route: ActivatedRoute, public dialog: MatDialog, router: Router) {
    this.service = service;
    this.route = route;
    this.router = router;
   }

  ngOnInit(): void {
    this.id = this.route.snapshot.queryParamMap.get('id');
    console.log(this.id)
    this.service.getStudent(this.id).subscribe(
      res => this.student = res
    )
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(EditStudentDialogComponent, {
      width: '250px',
      data: this.student
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // this.service.updateStudent();
    });
  }

  delete() {
    this.service.deleteStudent(this.student).subscribe(
      res=> {
        console.log(res)
        this.router.navigateByUrl("http://localhost:4200/student/all")
      },
      res=>console.log(res)
    );
  }

}
