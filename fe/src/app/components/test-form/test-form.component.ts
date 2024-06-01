import { Component } from '@angular/core';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';

import { Rekening } from '../../models/Rekening';


@Component({
  selector: 'app-test-form',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule
  ],
  templateUrl: './test-form.component.html',
  styleUrl: './test-form.component.css'
})
export class TestFormComponent {

  myRekening = new Rekening(1000)

  onSubmit(contactForm: any ) {
    console.log(contactForm.value);
  }

}
