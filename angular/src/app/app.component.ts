import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent { 
  
  imagesUrl: string[] = [];
  
  constructor() {
  }

  loadImage($event: string): void {
    console.log(`search image=${$event}`);
    this.imagesUrl.unshift($event);
  }

}
