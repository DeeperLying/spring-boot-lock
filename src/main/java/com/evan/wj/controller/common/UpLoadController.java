package com.evan.wj.controller.common;

import com.alibaba.fastjson.JSON;
import com.evan.wj.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UpLoadController {

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(value = "api/upLoad")
    // TODO @PostMapping(name = "api/upLoad") name属性会导致静态资源没办法访问
    @ResponseBody
    public Result upLoad(@RequestParam("file")MultipartFile file) {
        if(file.isEmpty()) {
            return new Result(400, "请上传文件");
        }
        //总的路径
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        //String staticPath = null;
        try {
            staticPath = ResourceUtils.getURL("classpath:").getPath() + "static" + File.separator + "img" +File.separator;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(staticPath);
        File temp = new File(staticPath);
        if (!temp.exists()){
            temp.mkdirs();
        }
        //获取上传文件原来的名称
        String filename = file.getOriginalFilename();
        // String savePath = staticPath + File.separator + "images" + File.separator + filename;
        File saveFilePath = new File(staticPath+filename);

        try {
            file.transferTo(saveFilePath);
            Map<String, String> data = new HashMap<String, String>();
            // "/static" + File.separator + "Images/" 之所以这样写就是希望它在特殊环境报错
            String requestFilePath = File.separator + "img/" + filename;
            data.put("url", requestFilePath);
            return new Result(200, "success", data);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(400, "fail");
        }


    }
}
