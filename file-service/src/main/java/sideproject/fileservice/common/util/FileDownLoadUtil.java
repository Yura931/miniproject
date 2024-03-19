package sideproject.fileservice.common.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import sideproject.fileservice.common.dto.FileDto;
import sideproject.fileservice.common.enums.Browser;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Optional;

@Component
public class FileDownLoadUtil {

    public void fileDownLoad(FileDto dto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String filePath = dto.filePath();

        File file = new File(filePath);
        int fSize = (int)file.length();

        if (fSize <= 0) {
            throw new RuntimeException();
        }

        String mimetype = "application/x-msdownload";
        response.setContentType(mimetype);
        setDisposition(dto.originalFilename(), request, response);
        response.setContentLength(fSize);

        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        try {
            in = new BufferedInputStream(new FileInputStream(file));
            out = new BufferedOutputStream(response.getOutputStream());

            FileCopyUtils.copy(in, out);
            out.flush();
        } catch (Exception e) {

        } finally {
            if (in != null)
                    in.close();

            if (out != null)
                    out.close();
        }
    }

    private void setDisposition(String filename, HttpServletRequest request,HttpServletResponse response) throws Exception {
        final String browser = getBrowser(request);
        final String dispositionPrefix = "attachment; filename=";
        String encodedFileName = Browser.valueOf(browser.toUpperCase()).getEncodedFileName(filename);
        response.setHeader("Content-Disposition", dispositionPrefix + encodedFileName);
    }

    private String getBrowser(HttpServletRequest request) {
        String header = Optional.ofNullable(request.getHeader("User-Agent")).orElseThrow(RuntimeException::new);
        return Arrays.stream(Browser.values())
                .filter(browser -> header.indexOf(browser.getValue()) > -1)
                .findFirst()
                .orElse(Browser.MSIE)
                .getValue();
    }
}
