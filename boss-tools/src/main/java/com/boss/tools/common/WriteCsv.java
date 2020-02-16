package com.boss.tools.common;

import com.csvreader.CsvWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@Slf4j
public class WriteCsv {
    public static void writeCSV(String path, List<List<String>> lists, String[] headers) {
        String csvFilePath = path;
        try {
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("UTF-8"));
            csvWriter.writeRecord(headers);
            for (List<String> list : lists) {
                String[] writeLine = list.toArray(new String[list.size()]);
                csvWriter.writeRecord(writeLine);
            }
            csvWriter.close();
            log.info("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCSVForOne(String path, List<String> list, String[] headers) {
        String csvFilePath = path;
        try {
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("UTF-8"));
            csvWriter.writeRecord(headers);
            for (String str : list) {
                csvWriter.write(str);
            }
            csvWriter.close();
            log.info("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
