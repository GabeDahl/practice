import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-edit-student-dialog',
  templateUrl: './edit-student-dialog.component.html',
  styleUrls: ['./edit-student-dialog.component.css']
})
export class EditStudentDialogComponent implements OnInit {
  service: StudentService;
  student: Student;

  constructor(
    public dialogRef: MatDialogRef<EditStudentDialogComponent>,
      @Inject(MAT_DIALOG_DATA) public data: Student,
      service: StudentService
    ) {
      this.student = data;
      this.service = service;
    }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {
    this.service.updateStudent(this.student).subscribe(
      res => console.log(res)
    );
  }

  ngOnInit(): void {
  }

}
