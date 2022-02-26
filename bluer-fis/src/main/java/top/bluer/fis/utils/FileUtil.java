package top.bluer.fis.utils;

/**
 * @program: Bluer-Tool
 * @description: 文件工具类
 * @author: bluer
 * @date: 2022-02-18 14:25
 * codes: 扁鹊
 **/
public class FileUtil {

    /**
     * @description: 判断文件名是否带盘符，重新处理
     * @date: 2022/2/18 14:25
     * @codes: 扁鹊
     **/
    public static String getFileName(String fileName) {
        // 判断是否带有盘符信息
        // Check for Unix-style path
        int unixSep = fileName.lastIndexOf('/');
        // Check for Windows-style path
        int winSep = fileName.lastIndexOf('\\');
        // Cut off at latest possible point
        int pos = (Math.max(winSep, unixSep));
        if (pos != -1) {
            // Any sort of path separator found...
            fileName = fileName.substring(pos + 1);
        }
        // 替换上传文件名字的特殊字符
        fileName = fileName.replace("=", "").replace(",", "").replace("&", "");
        return fileName;
    }


    public static boolean isNotEmpty(Object object) {
        return object != null && !object.equals("") && !object.equals("null");
    }
}
