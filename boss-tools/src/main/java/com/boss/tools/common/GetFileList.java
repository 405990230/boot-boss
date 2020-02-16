package com.boss.tools.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GetFileList {
    public static List<File> getFileList(String strPath, String typeOfFile) {
        List<File> filelist = new ArrayList<>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath(), typeOfFile); // 获取文件绝对路径
                } else if (fileName.endsWith(typeOfFile)) { // 判断文件名是否以.avi结尾
                    //String strFileName = files[i].getAbsolutePath();
                    //System.out.println("---" + strFileName);
                    filelist.add(files[i]);
                } else {
                    continue;
                }
            }

        }
        filelist = sortFileByName(filelist, "asc");
        return filelist;
    }


    /**
     * @param files
     * @param orderStr 排序:asc,des,不区分大小写
     * @return
     */
    public static List<File> sortFileByName(List<File> files, final String orderStr) {
        if (!orderStr.equalsIgnoreCase("asc") && orderStr.equalsIgnoreCase("desc")) {
            return files;
        }
        File[] files1 = files.toArray(new File[0]);
        Arrays.sort(files1, new Comparator<File>() {
            public int compare(File o1, File o2) {
                int n1 = extractNumber(o1.getName());
                int n2 = extractNumber(o2.getName());
                if (orderStr == null || orderStr.length() < 1 || orderStr.equalsIgnoreCase("asc")) {
                    return n1 - n2;
                } else {
                    //降序
                    return n2 - n1;
                }
            }
        });
        return new ArrayList<>(Arrays.asList(files1));
    }

    private static int extractNumber(String name) {
        int i;
        try {
            String number = name.replaceAll("[^\\d]", "");
            i = Integer.parseInt(number);
        } catch (Exception e) {
            i = 0;
        }
        return i;
    }
}
