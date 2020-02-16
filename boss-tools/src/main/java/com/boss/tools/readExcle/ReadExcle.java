//package com.boss.tools.readExcle;
//
//import com.boss.tools.common.WriteCsv;
//import com.boss.tools.contants.Contant;
//import com.boss.tools.service.ReadJsonFileService;
//import com.boss.tools.vo.ExportBean;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.*;
//
//@Component
//@Slf4j
//public class ReadExcle {
//    final static String EXCELPATH = "/Users/qxr4383/Documents/work/logger/prod/omc/data_new/evo.xlsx";
//    @Autowired
//    ReadJsonFileService readJson3;
//
//    public ExportBean readExcle(){
//        List<List<String>> lists_list = new ArrayList<>();
//        List<String> list = new ArrayList<>();
//        try {
//            File excel = new File(EXCELPATH);
//            if (excel.isFile() && excel.exists()) {   //判断文件是否存在
//                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
//                Workbook wb;
//                //根据文件后缀（xls/xlsx）进行判断
//                if ( "xls".equals(split[1])){
//                    wb = new HSSFWorkbook( new FileInputStream(excel));
//                }else if ("xlsx".equals(split[1])){
//                    wb = new XSSFWorkbook( new FileInputStream(excel));
//                }else {
//                    System.out.println("文件类型错误!");
//                    return null;
//                }
//                //开始解析
//                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
//
//                int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
//                int lastRowIndex = sheet.getLastRowNum();
//                System.out.println("firstRowIndex: "+firstRowIndex);
//
//                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
//                    List<String> listRow = new ArrayList<>();
//                    Row row = sheet.getRow(rIndex);
//                    if (row != null) {
//                        int firstCellIndex = row.getFirstCellNum();
//                        list.add(row.getCell(firstCellIndex).toString());
//                        int lastCellIndex = row.getLastCellNum();
//                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
//                            Cell cell = row.getCell(cIndex);
//                            if (cell != null) {
//                                listRow.add(cell.toString());
//                            }
//                        }
//                        lists_list.add(listRow);
//                    }
//                }
//                System.out.println("lastRowIndex: "+lastRowIndex);
//            }else {
//                System.out.println("找不到指定的文件");
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        ExportBean exportBean = new ExportBean();
//        exportBean.setList(list);
//        exportBean.setLists(lists_list);
//        return exportBean;
//    }
//
//
//    /**
//     * 实用 只有vin
//     */
//    public List<String> exportVin(){
//        String path = "/Users/qxr4383/Documents/work/logger/prod/omc/evo-NoRequest.csv";
//        List<String> vin_zhang = readExcle().getList();
//        System.out.println("张帆提供的vin: "+vin_zhang.size());
//
//        List<String> vin_request = readJson3.readJson().getList();
//        System.out.println("OMC后台接收的vin: "+vin_request.size());
//
//        List<String> vin_total = new ArrayList<>();
//        vin_total.addAll(vin_zhang);
//        vin_total.addAll(vin_request);
//        Collections.sort(vin_total);
//
//        System.out.println("首次合并后的总vin: "+vin_total.size());
//
//        List<String> vin_new = new ArrayList<>();
//        List<String> vin_repeat = new ArrayList<>();
////
////        for(int i =1;i<vin_total.size();i++){
////            if(!vin_total.get(i).equals(vin_new.get(newVin.size()-1))){
////                vin_new.add(vin_total.get(i));
////            }else {
////                chongfu_Vin.add(newVin.get(vin_new.size()-1));
////                vin_new.remove(vin_new.size()-1);
////            }
////        }
////        newVin.remove(0);
//
//        int count = 0;
//        String tem = vin_total.get(0);
//        vin_total.add("VIN");
//        for (int i =1;i<vin_total.size()-1;i++){
//            count = vin_total.get(i).equals(tem)?++count:(count = 0);
//            tem = vin_total.get(i);
//            if(!vin_total.get(i).equals(vin_total.get(i+1))&&count==0){
//                vin_new.add(tem);
//            }else if(count==1){
//                vin_repeat.add(tem);
//            }
//        }
//
//        System.out.println("张帆和OMC重复的VIN: " +vin_repeat.size());
//        System.out.println("第一次合并去除后的vin: " +vin_new.size());
//
//
//        //2次去重
//        List<String> listVin_total2 = new ArrayList<>();
//        listVin_total2.addAll(vin_zhang);
//        listVin_total2.addAll(vin_repeat);
//        Collections.sort(listVin_total2);
//
//        System.out.println("二次合并后的总vin: "+listVin_total2.size());
//
//        List<String> newVin2 = new ArrayList<>();
//        newVin2.add("VIN");
//        newVin2.add(listVin_total2.get(0));
//        for(int i =1;i<listVin_total2.size();i++){
//            if(!listVin_total2.get(i).equals(newVin2.get(newVin2.size()-1))){
//                newVin2.add(listVin_total2.get(i));
//            }else {
//                newVin2.remove(newVin2.size()-1);
//            }
//        }
//        newVin2.remove(0);
//        System.out.println("第二次总数中没有有请求的部分VIN: " +newVin2.size());
//
//        WriteCsv.writeCSVForOne(path,newVin2, Contant.HEADERS_VIN);
//        return newVin2;
//    }
//
//    final static String PATH_EVO_NoRequest_detail = "/Users/qxr4383/Documents/work/logger/prod/omc/evo_NoRequest_detail.csv";
//    final static String PATH_EVO_REPEAT_ID56 = "/Users/qxr4383/Documents/work/logger/prod/omc/evo_repeat_id56.csv";
//    final static String PATH_EVO_NOT_IN_ZHANG = "/Users/qxr4383/Documents/work/logger/prod/omc/evo_not_in_zhang.csv";
//
//    /**
//     * 实用  -- vin号并带有详细信息的
//     */
//    public void exportVinDetail(){
//        String path = "/Users/qxr4383/Documents/work/logger/prod/omc/evo-NoRequest_detail.csv";
//        List<List<String>> vin_zhang = readExcle().getLists();
//        List<List<String>> vin_request = readJson3.readJson().getLists();
//        String[] headers = {"vin_long","device_unit_type","pu"};
//
//
//        //第一次去重
//        ExportBean exportBean = duplicateVin(vin_request,headers);
//        System.out.println("张帆提供的vin: "+vin_zhang.size());
//        System.out.println("OMC后台接收的vin: "+vin_request.size());
//        System.out.println("id5和id6都有vin: " +exportBean.getVin_repeat().size());
//        readJson3.writeCSV(PATH_EVO_REPEAT_ID56,exportBean.getVin_repeat());
//
//        //第一次合并
//        List<List<String>> vin_total = new ArrayList<>();
//        vin_total.addAll(vin_zhang);
//        vin_total.addAll(exportBean.getVin_duplicate());
//        vin_total.addAll(exportBean.getVin_repeat());
//        System.out.println("首次合并后的总vin: "+vin_total.size());
//
//        //第二次去重
//        ExportBean exportBean2 = duplicateVin(vin_total,headers);
//        List<List<String>>  vin_duplicate = exportBean2.getVin_duplicate();
//        List<List<String>>  vin_repeat = exportBean2.getVin_repeat();
//        vin_duplicate.remove(0);
//        System.out.println("第一次合并去去重的vin: " +vin_duplicate.size());
//        System.out.println("张帆和OMC中重复的VIN: " +vin_repeat.size());
//
//
//        //第3次去重
//        List<List<String>> vin_total3 = new ArrayList<>();
//        vin_total3.addAll(vin_zhang);
//        vin_total3.addAll(vin_repeat);
//
//        ExportBean exportBean3 = duplicateVin(vin_total3,headers);
//        System.out.println("第三次合并后的总vin: "+vin_total3.size());
//
//        List<List<String>> vin_duplicate2 = exportBean3.getVin_duplicate();
//        vin_duplicate2.remove(0);
//        System.out.println("第三次总数中没有有请求的部分VIN: " +vin_duplicate2.size());
//        readJson3.writeCSV3(PATH_EVO_NoRequest_detail,vin_duplicate2,headers);
//
//
//        //第四次合并去重
//        List<List<String>> vin_total4 = new ArrayList<>();
//        vin_total4.addAll(exportBean.getVin_duplicate());
//        vin_total4.addAll(exportBean.getVin_repeat());
//        vin_total4.addAll(vin_repeat);
//        ExportBean exportBean4 = duplicateVin(vin_total4,headers);
//
//        readJson3.writeCSV(PATH_EVO_NOT_IN_ZHANG,exportBean4.getVin_duplicate());
//
//
//
//    }
//
//
//    public ExportBean duplicateVin( List<List<String>> list_total,String[] headers){
//        Collections.sort(list_total, new VinSort());
//        List<List<String>>  vin_repeat = new ArrayList<>();
//        List<List<String>> vin_duplicate = new ArrayList<>();
//        vin_duplicate.add(Arrays.asList(headers));
//        vin_duplicate.add(list_total.get(0));
//        for(int i =1;i<list_total.size();i++){
//            if(!list_total.get(i).get(0).equals(vin_duplicate.get(vin_duplicate.size()-1).get(0))){
//                vin_duplicate.add(list_total.get(i));
//            }else {
//                vin_repeat.add(vin_duplicate.get(vin_duplicate.size()-1));
//                vin_duplicate.remove(vin_duplicate.size()-1);
//            }
//        }
//        vin_duplicate.remove(0);
//        ExportBean exportBean = new ExportBean();
//        exportBean.setVin_repeat(vin_repeat);
//        exportBean.setVin_duplicate(vin_duplicate);
//        return exportBean;
//    }
//
//
//
//
//    public ExportBean duplicateVin2( List<List<String>> list1, List<List<String>> list2){
//        List<List<String>> vin_total = new ArrayList<>();
//        vin_total.addAll(list1);
//        vin_total.addAll(list2);
//
//        List<List<String>>  vin_duplicate = new ArrayList<>();
//        List<List<String>>  vin_repeat = new ArrayList<>();
//
//        int count = 0;
//        List<String> tem = vin_total.get(0);
//        vin_total.add(vin_total.get(0));
//        for (int i =1;i<vin_total.size()-1;i++){
//            count = vin_total.get(i).get(0).equals(tem.get(0))?++count:(count = 0);
//            tem = vin_total.get(i);
//            if(!vin_total.get(i).equals(vin_total.get(i+1))&&count==0){
//                vin_duplicate.add(tem);
//            }else if(count==1){
//                vin_repeat.add(tem);
//            }
//        }
//        ExportBean exportBean = new ExportBean();
//        exportBean.setVin_repeat(vin_repeat);
//        exportBean.setVin_duplicate(vin_duplicate);
//        return exportBean;
//    }
//
//
//
//    /**
//     * 逻辑可行，分析过大数据时太慢
//     */
//    public void judgeVin(){
//        String path = "/Users/qxr4383/Documents/work/logger/prod/omc/mgu.csv";
//        List<String> lists = readExcle().getList();
//        System.out.println("lists: "+lists.size());
//
//        List<String> listVin = readJson3.readJson().getList();
//        System.out.println("listVin: "+listVin.size());
//        ListIterator<String> iterator = listVin.listIterator();
//        while(iterator.hasNext()){
//            String str = iterator.next();
//            if(lists.contains(str)){
//                iterator.remove();
//                continue;
//            }
//        }
//        System.out.println("listVin last: "+listVin.size());
//        readJson3.writeCSV2(path,listVin);
//    }
//
//}
//
//
//class VinSort implements Comparator<List<String>>{
//    public int compare(List<String> l1,List<String> l2){
//        String vin1 = l1.get(0);
//        String vin2 = l2.get(0);
//        return vin1.compareTo(vin2);
//    }
//}
