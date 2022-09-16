package com.purple.handler;

import com.purple.model.WebImage;

import java.util.List;


public interface WebImageHandlerable {
    WebImage getWebImage(Long id);

    WebImage saveWebImage(WebImage webImage);
    WebImage getWebImageWithMeme(Long memeId);
    List<WebImage> getAllWebImages();
}
