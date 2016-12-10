/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.controller;

import com.bounswe.group7.web.MultipartUtility;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ugurbor
 */
@Controller
public class UploadController {

    final String uploaderURL = "http://localhost:8090/upload";

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        MultipartUtility multipart = new MultipartUtility(uploaderURL, "UTF-8");

        multipart.addHeaderField("Authorization", (String) session.getAttribute("token"));
        multipart.addFormField("name", name);
        multipart.addFilePart("file", file.getInputStream());

        List<String> response = multipart.finish();

        System.out.println("SERVER REPLIED:");
        for (String line : response) {
            System.out.println(line);
        }
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

}
