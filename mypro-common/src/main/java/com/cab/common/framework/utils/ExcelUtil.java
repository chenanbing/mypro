package com.cab.common.framework.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


public class ExcelUtil {

    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     * @param in,fileName
     * @return
     * @throws IOException
     */
    public static   Map<String ,List<List<Object>>> getExcelData(InputStream in,String fileName) throws Exception{
        if(in == null || StringUtils.isBlank(fileName)){
            return null;
        }
        //创建Excel工作薄
        Workbook work = getWorkbook(in,fileName);
        if(null == work){
            return null;
        }
        Map<String ,List<List<Object>>> titleAndDataMap = new HashMap<String ,List<List<Object>>>();
        List<List<Object>> titleList = new ArrayList<List<Object>>();
        List<List<Object>> rowList = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            Sheet sheet = work.getSheetAt(i);//excel中的sheet
            if(sheet==null){
                continue;
            }
            //遍历当前sheet中的所有行
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum =  sheet.getLastRowNum();
            for (int j = firstRowNum; j <= lastRowNum; j++) {
                Row row = sheet.getRow(j);//sheet中的行
                if(row==null){
                    continue;
                }
                //遍历所有的列
                int firstCellNum = row.getFirstCellNum();
                int lastCellNum =  row.getLastCellNum();
                List<Object> cellList = new ArrayList<Object>();
                for (int y = firstCellNum; y < lastCellNum; y++) {
                    Cell cell = row.getCell(y);//行中的列
                    cellList.add(getCellValue(cell));
                }
                if(i == 0 && j == firstRowNum){//第一个sheet的第一行为标题
                    titleList.add(cellList);
                }else{
                    rowList.add(cellList);//其他行为数据
                }
            }
        }
        titleAndDataMap.put("titles",titleList);
        titleAndDataMap.put("datas",rowList);
//        work.close();
        in.close();
        return titleAndDataMap;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public static   Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    public static boolean checkEnd(String fileName){
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            return true;
        }else if(excel2007U.equals(fileType)){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 描述：对表格中数值进行格式化
     * @param cell
     * @return
     */
    //解决excel类型问题，获得数值
    public static  String getCellValue(Cell cell) {
        String value = "";
        if(null==cell){
            return value;
        }
        switch (cell.getCellType()) {
            //数值型
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = format.format(date);;
                }else {// 纯数字
                    BigDecimal big=new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                    //解决1234.0  去掉后面的.0
                    if(null!=value&&!"".equals(value.trim())){
                        String[] item = value.split("[.]");
                        if(1<item.length&&"0".equals(item[1])){
                            value=item[0];
                        }
                    }
                }
                break;
            //字符串类型
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue().toString();
                break;
            // 公式类型
            case Cell.CELL_TYPE_FORMULA:
                //读公式计算值
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                    value = cell.getStringCellValue().toString();
                }
                break;
            // 布尔类型
            case Cell.CELL_TYPE_BOOLEAN:
                value = " "+ cell.getBooleanCellValue();
                break;
            // 空值
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            // 故障
            case Cell.CELL_TYPE_ERROR:
                value = "";
                break;
            default:
                value = cell.getStringCellValue().toString();
        }
        if("null".endsWith(value.trim())){
            value="";
        }
        return value;
    }


//    public static void main(String[] args) throws Exception{
//        File file = new File("D:\\idfa(2017-07-12).xlsx");
//        InputStream in = new FileInputStream(file);
//        getBankListByExcel(in,file.getName());
//
//
//
//    }

}