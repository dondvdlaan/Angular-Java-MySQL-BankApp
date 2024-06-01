import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {

  condition = false
  count = 0
  @Input() name = ""
  message = "Yes, the server is running"

  @Output() incrementCountEvent = new EventEmitter<number>();

  onClick() {
    this.count++;
    console.log("User onClick: ", this.count)
    this.incrementCountEvent.emit(this.count);
  }

}
