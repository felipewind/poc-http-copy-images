import { Image } from './image.model';
import { Component, Input, OnInit } from '@angular/core';
import { SafeResourceUrl } from '@angular/platform-browser';
import { ImageService } from './image.service';

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.css']
})
export class ImageComponent implements OnInit {

  @Input() imageUrl: string;

  imageUrlCopy: string = '';
  imageUrlBase64: SafeResourceUrl = {};
  image: Image = { mediaType: '', encodedImage: '' };

  imageStatus: string = '';

  errorMessage: string = '';

  constructor(private imageService: ImageService) {
    this.imageUrl = '';
  }

  ngOnInit(): void {
    this.imageStatus = 'loading';
    console.log(`${this.imageUrl}`);
    this.imageService.getBase64(this.imageUrl).subscribe(
      (image) => {
        this.image = image;
        this.imageUrlCopy = this.imageService.getUrlCopy(this.imageUrl);        
        this.imageUrlBase64 = this.imageService.getSanitizedUrl(image);
        this.imageStatus = 'loaded';
      },
      (error) => {
        console.log(`${JSON.stringify(error)}`);        
        this.imageStatus = 'error';
        this.errorMessage = `status:${error.status} statusText:${error.statusText} message:${error.message}`;
      });
  }

}
