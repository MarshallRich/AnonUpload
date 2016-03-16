package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MacLap on 3/16/16.
 */

@RestController
public class AnonFileController {

    @Autowired
    AnonFileRepository files;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, HttpServletResponse response, String comment, boolean isKept) throws IOException {
        File dir = new File("public/files");
        dir.mkdirs();
        //the use of make dirs is mostly an example, it would usually be better to make a dummy file in the directory
        File f = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());

        AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename(), comment, isKept);
        files.save(anonFile);
        if (!anonFile.isKept()){
            ArrayList<AnonFile> filesTemp = (ArrayList<AnonFile>) files.findByIsKept(false);
            if (filesTemp.size() > 10) {
                AnonFile delFile = filesTemp.get(0);

                File del = new File("public/files/" + delFile.getFilename());
                del.delete();
                files.delete(delFile);
            }
        }

        response.sendRedirect("/");
    }

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<AnonFile> getFiles() {
        return (List<AnonFile>) files.findAll();
    }
}
