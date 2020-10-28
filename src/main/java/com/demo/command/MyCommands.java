package com.demo.command;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.File;
import java.util.List;

@ShellComponent
@Slf4j
public class MyCommands {

    @ShellMethod("Add two integers together.")
    public void start() {
        log.info("读去download.txt");
        FileReader fileReader = new FileReader("download.txt");
        List<String> strings = fileReader.readLines();
        File pwd = new File("");
        File output = FileUtil.mkdir("output");
        log.info("当前目录 {}", pwd.getAbsoluteFile());
        for (String s : strings) {
            JSONObject jsonObject = JSONUtil.parseObj(s);
            String uniqueId = jsonObject.getStr("uniqueId");
            String channelName = jsonObject.getStr("channelName");
            String programName = jsonObject.getStr("programName");
            File file = FileUtil.file(uniqueId);
            if (!file.isFile()) {
                log.info("当前目录下,无法找到 {}", file.toPath());
                continue;
            }
            File copyDir = FileUtil.mkdir(new File(output, channelName));
            File toFile = FileUtil.copyFile(file, new File(copyDir, programName));
            log.info("复制文件 {} to {}", file.getAbsoluteFile(), toFile.getAbsoluteFile());
            break;
        }
    }
}
