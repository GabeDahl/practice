import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegistrationFormComponent } from './components/registration-form/registration-form.component';
import { StudentCardComponent } from './components/student-card/student-card.component';
import { StudentsListComponent } from './components/students-list/students-list.component';

const routes: Routes = [
  {path: 'student/all', component: StudentsListComponent},
  {path: 'student', component: StudentCardComponent},
  {component: RegistrationFormComponent, path: 'registration'},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
