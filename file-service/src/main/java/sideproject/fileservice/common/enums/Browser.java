package sideproject.fileservice.common.enums;

import java.io.IOException;
import java.net.URLEncoder;

public enum Browser implements EnumModel {
    MSIE("MSIE", EncodedFileName.MSIE),
    FIREFOX("Firefox", EncodedFileName.FIREFOX_OPERA),

    OPERA("Opera", EncodedFileName.FIREFOX_OPERA),
    CHROME("Chrome", EncodedFileName.CHROME);

    private final String value;
    private final EncodedFileName encodedFileName;


    Browser(String value, EncodedFileName encodedFileName) {
        this.value = value;
        this.encodedFileName = encodedFileName;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public String getEncodedFileName(String filename) throws Exception {
        return encodedFileName.encodedFilename(filename);
    }

    enum EncodedFileName {
        MSIE {
            @Override
            String encodedFilename(String filename) throws Exception {
                return URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
            }
        },
        FIREFOX_OPERA {
            @Override
            String encodedFilename(String filename) throws Exception {
                return "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
            }
        },
        CHROME {
            @Override
            String encodedFilename(String filename) {
                return filename.chars()
                        .mapToObj(c -> {
                            try {
                                if (c > '~')
                                    return URLEncoder.encode(String.valueOf((char) c), "UTF-8");
                                else {
                                    return String.valueOf((char) c);
                                }
                            } catch (IOException e) {
                                throw new RuntimeException();
                            }

                        })
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString();
            }
        };
        abstract String encodedFilename(String filename) throws Exception;
    }
}
