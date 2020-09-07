package org.sheehan.fortune.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface PictureCurator {
    void CuratePicture(String[] args) throws IOException;

    void CurateRectanglePicture(String[] args);
}
