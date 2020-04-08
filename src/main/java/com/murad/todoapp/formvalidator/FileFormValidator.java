package com.murad.todoapp.formvalidator;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
@Component
public class FileFormValidator {

    public void validate(MultipartFile file, BindingResult bindingResult) throws IOException {
        BufferedImage bimg = ImageIO.read(file.getInputStream());
        if (bimg.getWidth() >= 200 || bimg.getHeight() >= 80) {
            bindingResult.rejectValue("name", "hr.image.message");
        }
        


    }}