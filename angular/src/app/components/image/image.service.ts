import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Observable } from 'rxjs';
import { Image } from './image.model';

const baseUrl = `http://localhost:8080/image`;

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private httpClient: HttpClient, private sanitizer: DomSanitizer) { }

  getBase64(imageUrl: string): Observable<Image> {
    const urlBase64 = `${baseUrl}/base64/${encodeURIComponent(imageUrl)}`;
    console.log(`${urlBase64}`);
    return this.httpClient.get<Image>(urlBase64);
  }

  getSanitizedUrl(image: Image): SafeResourceUrl {
    return this.sanitizer.bypassSecurityTrustResourceUrl(`data:${image.mediaType};base64,${image.encodedImage}`);
  }

  getUrlCopy(imageUrl: string): string {
    return `${baseUrl}/copy/${encodeURIComponent(imageUrl)}`;
  }

}
