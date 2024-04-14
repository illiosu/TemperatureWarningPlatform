package com.TW.utils;

import com.TW.common.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.UUID;

public class FileProcessingUtil {

    private static final String UPLOADED_FOLDER = "C:\\D\\Work\\Projects\\test\\";

    public static ResponseEntity processFile(MultipartFile uploadfile, String pythonScriptPath) {
        if (uploadfile.isEmpty()) {
            return new ResponseEntity<>(Result.fail("please select a file!"), HttpStatus.OK);
        }

        try {
            //生成唯一ID
            String uniqueID = UUID.randomUUID().toString();

            //获取上传文件的基本名字
            String originalFilename = uploadfile.getOriginalFilename();
            String baseName = originalFilename.substring(0, originalFilename.lastIndexOf("."));

            //构建output文件的路径
            String outputTif = UPLOADED_FOLDER + baseName + "_" + uniqueID + ".tif";
            String outputPng = UPLOADED_FOLDER + baseName + "_" + uniqueID + ".png";

            //创建临时文件
            File tempFile = File.createTempFile("temp", originalFilename.substring(originalFilename.lastIndexOf(".")));
            uploadfile.transferTo(tempFile);

            //调用python脚本处理文件
            String[] args1 = new String[]{"python", pythonScriptPath, tempFile.getAbsolutePath(), outputTif, outputPng};
            Process proc = Runtime.getRuntime().exec(args1);

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "gb2312"));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();

            //删除临时文件
            tempFile.delete();

        } catch (Exception e) {
            return new ResponseEntity<>(Result.fail("Failure"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Result.ok("Success"), HttpStatus.OK);
    }
}