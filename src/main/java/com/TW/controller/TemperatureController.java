package com.TW.controller;

import com.TW.common.Result;
import com.TW.utils.FileProcessingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/Temperature")
public class TemperatureController {
    private static String UPLOADED_FOLDER = "C:\\D\\Work\\Projects\\test\\";



    @PostMapping("/processNDVI")
    public ResponseEntity<Result> processNDVI(@RequestParam("test") MultipartFile uploadfile) {
        return FileProcessingUtil.processFile(uploadfile, "C:\\D\\Work\\Projects\\test\\ndvi\\ndvi.py");
    }
    @PostMapping("/processBR")
    public ResponseEntity<Result> processBR(@RequestParam("test") MultipartFile uploadfile) {
        return FileProcessingUtil.processFile(uploadfile, "C:\\D\\Work\\Projects\\test\\ndvi\\br.py");

    }
    @PostMapping("/processSRR")
    public ResponseEntity<Result> processSRR(@RequestParam("test") MultipartFile uploadfile) {

        return FileProcessingUtil.processFile(uploadfile, "C:\\D\\Work\\Projects\\test\\ndvi\\srr.py");
    }
    @PostMapping("/processTEM")
    public ResponseEntity<Result> processTEM(@RequestParam("test") MultipartFile uploadfile) {
        return FileProcessingUtil.processFile(uploadfile, "C:\\D\\Work\\Projects\\test\\ndvi\\tem.py");

    }
    @PostMapping("/processVEG")
    public ResponseEntity<Result> processVEG(@RequestParam("test") MultipartFile uploadfile) {
        return FileProcessingUtil.processFile(uploadfile, "C:\\D\\Work\\Projects\\test\\ndvi\\veg.py");

    }
}
