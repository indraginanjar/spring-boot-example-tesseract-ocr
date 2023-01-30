package com.indraginanjar.ocr;

import java.util.ArrayList;
import java.util.List;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

class TextImage
{
    public String read(String imageFileName, String dpi, int roiWidth, int roiHeight, String language) {
        Mat image = Imgcodecs.imread(imageFileName);
        List<Rect> rois = new ArrayList<>();
        rois.add(new Rect(0, 0, roiWidth, roiHeight));
        ITesseract tesseract = new Tesseract();
        tesseract.setLanguage(language);
        StringBuilder text = new StringBuilder();

        for (Rect roi : rois) {
            try {
                Mat roiImage = new Mat(image, roi);
                BufferedImage bufferedImage = new BufferedImage(roiImage.width(), roiImage.height(), BufferedImage.TYPE_3BYTE_BGR);
                WritableRaster raster = bufferedImage.getRaster();
                DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
                byte[] data = dataBuffer.getData();
                roiImage.get(0, 0, data);
                tesseract.setTessVariable("user_defined_dpi", dpi);
                text.append(tesseract.doOCR(bufferedImage));
            } catch (TesseractException e) {
                e.printStackTrace();
            }
        }

        return text.toString();
    }
}