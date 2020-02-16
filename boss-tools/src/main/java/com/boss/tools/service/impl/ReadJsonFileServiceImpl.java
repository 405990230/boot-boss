package com.boss.tools.service.impl;

import com.alibaba.fastjson.JSON;
import com.boss.tools.common.GetFileList;
import com.boss.tools.common.WriteCsv;
import com.boss.tools.service.ReadJsonFileService;
import com.boss.tools.vo.ExportBean;
import com.boss.tools.vo.ReadJsonBean;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
@Service("readJsonFileService")
public class ReadJsonFileServiceImpl implements ReadJsonFileService {
    @Autowired
    private ObjectMapper objectMapper;

    public void readJsonList(String sourceFolderPath, String targetFolderPath, String[] headers) {
        //String path = readJson.class.getResource("data.csv").getPath();
        List<File> fileList = GetFileList.getFileList(sourceFolderPath, "json");
        for (File file : fileList) {
            log.info("------" + file.getAbsolutePath());
            try {
                String input = FileUtils.readFileToString(file, "UTF-8");
                ReadJsonBean readJsonBean = JSON.parseObject(input, ReadJsonBean.class);
                List<List<String>> arrayList = readJsonBean.getTables().get(0).getRows();
                log.info("-------数据长度：" + arrayList.size());
                targetFolderPath = targetFolderPath + file.getName().replace(".json", ".csv");
                WriteCsv.writeCSV(targetFolderPath, arrayList, headers);
                // 利用set集合唯一性去重
//                arrayList = removeDuplicateOrder(arrayList);
//                System.out.println(arrayList.size());
//                ListIterator<List<String>> iterator = arrayList.listIterator();
//                while(iterator.hasNext()){
//                    List<String>  str = iterator.next();
//                    if("1.7.12".equals(str.get(1))){
//                        iterator.remove();
//                        continue;
//                    }
//                }
//                System.out.println(arrayList.size());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public ExportBean readJson(String sourceJsonPath, String targetCsvPath, String[] headers) {
        List<List<String>> listVin_list = new ArrayList<>();
        List<String> listVin = new ArrayList<>();
        InputStream inputStream = null;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        try {
            inputStream = new FileInputStream(sourceJsonPath);
            ReadJsonBean readJsonBean = objectMapper.readValue(inputStream, ReadJsonBean.class);
            for (List<String> list : readJsonBean.getTables().get(0).getRows()) {
                listVin.add(list.get(0));
                listVin_list.add(list);
            }
            //导出文件
            WriteCsv.writeCSV(targetCsvPath, listVin_list, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExportBean exportBean = new ExportBean();
        exportBean.setList(listVin);
        exportBean.setLists(listVin_list);
        return exportBean;
    }


    /**
     * 去重
     *
     * @param orderList
     * @return
     * @author jqlin
     */
    private static List<List<String>> removeDuplicateOrder(List<List<String>> orderList) {
        Set<List<String>> set = new TreeSet<List<String>>(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> a, List<String> b) {
                // 字符串则按照asicc码升序排列
                return a.get(0).compareTo(b.get(0));
            }
        });

        set.addAll(orderList);
        return new ArrayList<List<String>>(set);
    }

    /**
     * 创建Excel.xls
     *
     * @param lists  需要写入xls的数据
     * @param titles 列标题
     * @param name   文件名
     * @return
     * @throws IOException
     */
    public static Workbook creatExcel(List<List<String>> lists, String[] titles, String name) throws IOException {
        System.out.println(lists);
        //创建新的工作薄
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(name);
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int i = 0; i < titles.length; i++) {
            sheet.setColumnWidth((int) i, (int) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((int) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for (int i = 0; i < titles.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(cs);
        }
        if (lists == null || lists.size() == 0) {
            return wb;
        }
        //设置每行每列的值
        for (int i = 1; i <= lists.size(); i++) {
            try {
                // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
                // 创建一行，在页sheet上
                Row row1 = sheet.createRow((int) i);
                for (int j = 0; j < titles.length; j++) {
                    // 在row行上创建一个方格
                    Cell cell = row1.createCell(j);
                    cell.setCellValue(lists.get(i - 1).get(j));
                    cell.setCellStyle(cs2);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("有问题的： " + i);
            }

        }
        return wb;
    }

}
