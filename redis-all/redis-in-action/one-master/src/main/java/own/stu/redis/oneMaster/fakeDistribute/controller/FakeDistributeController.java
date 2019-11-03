package own.stu.redis.oneMaster.fakeDistribute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RequestMapping("distribute")
@Controller
public class FakeDistributeController {

    // 获取不到yml文件中的值，(ps : 有配置中心之后再说）
    @Value("${spring.servlet.multipar.location:/Users/my/Desktop/file-temp}")
    private String tempDir;

    @Autowired
    private Environment environment;

    @RequestMapping("test")
    @ResponseBody
    public String test() {
        System.out.println(tempDir);
        System.out.println(environment.getProperty("spring.servlet.multipar.location", "default .... "));
        return "success";
    }

    @RequestMapping("upload")
    @ResponseBody
    public String uploadFileHandler(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 文件存放服务端的位置
                String rootPath = tempDir;
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();
                // 写文件到服务器
                File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                file.transferTo(serverFile);
                return "You successfully uploaded file=" + file.getOriginalFilename();
            } catch (Exception e) {
                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
        }
    }

    @RequestMapping("download")
    @ResponseBody
    public String download(@RequestParam("filename") String fileName) throws IOException {
        String filePath = tempDir + File.separator + "tmpFiles" + File.separator + fileName;

        File serverFile = new File(filePath);
        if (!serverFile.exists()) {
            return fileName + "not exist";
        }

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();

        FileCopyUtils.copy(new FileInputStream(serverFile), response.getOutputStream());

        return "SUCCESS";
    }
}