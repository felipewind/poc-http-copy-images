package org.acme;

public class EncodedImageDto {

    private String mediaType;
    private String encodedImage;
    
    public EncodedImageDto(String mediaType, String encodedImage) {
        this.mediaType = mediaType;
        this.encodedImage = encodedImage;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }    
    
}
