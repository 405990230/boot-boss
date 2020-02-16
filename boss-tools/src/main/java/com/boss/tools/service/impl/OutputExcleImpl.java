package com.boss.tools.service.impl;

import com.boss.tools.contants.Contant;
import com.boss.tools.service.OutputExcle;
import com.boss.tools.service.ReportHandleService;
import com.boss.tools.vo.ExportBean;
import com.boss.tools.vo.VinBean;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("outputExcle")
public class OutputExcleImpl implements OutputExcle {

    @Autowired
    ReportHandleService reportHandleService;

    /**
     * (2007 xlsx后缀 导出)
     *
     * @param
     * @return void 返回类型
     * @author xsw
     * @2016-12-7上午10:44:30
     */
    public void read2007Excel(InputStream in, String exportExcelPath, String month, String reportType) throws IOException {
        //读取excel模板
        XSSFWorkbook wb = new XSSFWorkbook(in);
        //读取了模板内所有sheet内容
        XSSFSheet sheet = wb.getSheetAt(0);
        //如果这行没有了，整个公式都不会有自动计算的效果的
        sheet.setForceFormulaRecalculation(true);

        List<ExportBean> list_export = reportHandleService.monthlyRequest(month, reportType);
        Integer[] cellNum = {1, 1, 2, 2, 3, 3, 4};
        for (int i = 0; i < list_export.size(); i++) {
            List<List<String>> list = list_export.get(i).getLists();
            for (int k = 0; k < list.size(); k++) {
                if (i == 0) {
                    sheet.getRow(k + 2).getCell(0).setCellValue(list.get(k).get(0).split("T")[0]);//第i+2行 第0列
                }
                Integer count = Integer.valueOf(list.get(k).get(1));
                sheet.getRow(k + 2).getCell(i + cellNum[i]).setCellValue(count);
            }
        }
        //在相应的单元格进行赋值
//        XSSFCell cell = sheet.getRow(11).getCell(6);//第12行 第7列
//        cell.setCellValue(1);
        //修改模板内容导出新模板
        exportExcelPath = exportExcelPath + month + "_" + reportType + ".xlsx";
        FileOutputStream out = new FileOutputStream(exportExcelPath);
        wb.write(out);
        out.close();
    }


    /**
     * @param @param  file
     * @param @return
     * @param @throws IOException
     * @return List<String> (excel每行拼接成List中的String)
     * @throws
     * @Title: readExcel
     * @Description: TODO(对外提供读取excel 的方法)
     */
    public void outputExcleForMonthRequest(String importFilePath, String exportFilePath, String month, String reportType) throws Exception {
        File file = new File(importFilePath);
        String fileName = file.getName();
        //根据其名称获取后缀
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
                .substring(fileName.lastIndexOf(".") + 1);
        if ("xlsx".equals(extension) || "xlsm".equals(extension) || "tmp".equals(extension)) {
            this.read2007Excel(new FileInputStream(file), exportFilePath, month, reportType);
        } else {
            throw new IOException("不支持的文件类型");
        }
    }

}