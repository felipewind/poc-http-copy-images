import { ImageService } from './image.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  imageUrl: string = '';
  
  constructor(private imageService: ImageService) {
  }

  loadImage(): void {

  }

}
