import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from '../models/student';

const API_URL = `http://localhost:8080/student`

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(http: HttpClient) {
    this.http = http;
   }

  http: HttpClient;

  getStudent(id: string): Observable<Student> {
    return this.http.get<Student>(`${API_URL}/${id}`)
  }

  getAllStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(`${API_URL}/all`)
  }

  addStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(API_URL, student);
  }

  updateStudent(student: Student) {
    return this.http.put(API_URL, student)
  }

  deleteStudent(student: Student) {
    return this.http.request('delete', API_URL, {body: student})
  }

}
