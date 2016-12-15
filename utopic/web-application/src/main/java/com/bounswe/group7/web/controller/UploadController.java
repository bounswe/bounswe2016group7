/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.controller;

import com.bounswe.group7.model.Users;
import com.bounswe.group7.web.MultipartUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ugurbor
 */
@RestController
public class UploadController {
    final String api = "http://localhost:8090/";
    final String uploaderURL = api + "upload";

    @RequestMapping(value = "/upload", method = RequestMethod.POST,
           produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public String handleFileUpload(RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Users user1 = (Users)session.getAttribute("user");
        long userId = user1.getId();
        MultipartUtility multipart = new MultipartUtility(uploaderURL, "UTF-8");
        Part file = request.getPart("file");
        String fileName = file.getSubmittedFileName();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String name = "user-" + userId + ".jpg";
        multipart.addHeaderField("Authorization", (String) session.getAttribute("token"));
        multipart.addFormField("name", name);
        multipart.addFilePart("file", file.getInputStream());
        List <String> res = multipart.finish();
        
        return api+"images/"+name;
    }
}
