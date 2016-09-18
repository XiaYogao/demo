package com.demo.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.List;

/**
 * EXCEL表格导出工具类
 *
 * @author xiayongao@gmail.com
 * @date 2016/9/19 0019 上午 04:35
 * @since V1.0
 */
public final class ExcelExportUtil {


    /**
     * @param response
     * @param request
     * @param filename 导出的文件名
     * @param titles 标题列和列名的对应.column:列名,title标题名
     * @param records 记录
     */
    @SuppressWarnings("unchecked")
    public static <M> void exportByRecord(HttpServletResponse response, HttpServletRequest request,
                                          String filename, List<Pair> titles, List<M> records) {
        exportByRecord(response, request, filename, new SheetData<M>(titles, records));
    }

    /**
     * @param response
     * @param request
     * @param filename 导出的文件名
     * @param sheetDatas 产生一个sheet需要的数据
     */
    @SuppressWarnings("unchecked")
    public static <M> void exportByRecord(HttpServletResponse response, HttpServletRequest request,
            String filename, SheetData<M>... sheetDatas) {

        HSSFWorkbook wb = new HSSFWorkbook();

        // 标题行的style
        CellStyle titleCellStyle = wb.createCellStyle();
        titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 居中
        titleCellStyle.setWrapText(true); // 自动换行
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 加粗
        font.setFontName("微软雅黑");
        titleCellStyle.setFont(font);

        // 内容行的style
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
        cellStyle.setWrapText(true);
        Font font2 = wb.createFont();
        font2.setFontName("微软雅黑");
        cellStyle.setFont(font2);

        // 多张sheet
        for (SheetData<M> sheetData : sheetDatas) {
            List<Pair> titles = sheetData.titles;
            List<M> records = sheetData.records;

            HSSFSheet sheet = wb.createSheet();
            int rowIndex = 0, cellIndex = 0;

            HSSFCell cell;

            // 创建标题行
            HSSFRow row = sheet.createRow(rowIndex);
            row.setHeight((short) 450);
            rowIndex++;

            for (Pair pair : titles) {
                cell = row.createCell(cellIndex);
                cell.setCellStyle(titleCellStyle); // 设置样式
                cellIndex++;
                cell.setCellValue(pair.title);
            }

            // 处理每一行
            int titledSize = titles.size();
            for (M record : records) {
                row = sheet.createRow(rowIndex);

                for (int i = 0; i < titledSize; i ++) {
                    sheet.autoSizeColumn(i);// 设置列宽自动长度
                }

                row.setHeight((short) 450);
                rowIndex++;
                cellIndex = 0;

                Class<?> clz = record.getClass();

                try {
                    for (Pair pair : titles) {
                        cell = row.createCell(cellIndex);
                        cell.setCellStyle(cellStyle); // 设置样式
                        cellIndex++;
                        Object value = clz.getMethod("get" + captureName(pair.column)).invoke(record);

                        if (value != null) {
                            cell.setCellValue(value.toString());
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

        // 序号
        writeStream(filename, wb, response, request);
    }

    //首字母大写
    private static String captureName(String name) {
        char[] cs=name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static void main(String[] args) {
        System.out.println(captureName("ajsdhfk"));
    }

    /**
     * 写到输出流
     */
    private static void writeStream(String filename, HSSFWorkbook wb, HttpServletResponse response, HttpServletRequest request) {
        try {
            String agent = request.getHeader("USER-AGENT");
            filename += ".xls";
            filename.replaceAll("/", "-");

            if (agent.toLowerCase().indexOf("firefox") > 0) {
                filename = new String(filename.getBytes("utf-8"), "iso-8859-1");
            } else {
                filename = URLEncoder.encode(filename, "UTF-8");
            }

            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            response.setContentType("application/ms-excel;charset=UTF-8");//octet-stream
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 标题列和列名的对应
     */
    public static class Pair {
        public String column;
        public String title;

        public Pair(String column, String title) {
            this.column = column;
            this.title = title;
        }
    }

    /**
     * 创建一个sheet需要的数据
     */
    public static class SheetData<T> {
        public List<Pair> titles;
        public List<T> records;

        public SheetData(List<Pair> titles, List<T> records) {
            this.titles = titles;
            this.records = records;
        }
    }
}
