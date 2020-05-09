package com.sjlexpress.wl.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserInfoExcelView extends AbstractXlsView {

    public CellStyle cellStyle;

    @Override
    protected void buildExcelDocument(Map<String, Object> map,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        String excelName = map.get("name").toString() + ".xls";
        System.err.println("导出数据："+ JSONObject.toJSONString(map));
        String agent = request.getHeader("User-Agent");
        if (null != agent) {
            agent = agent.toLowerCase();
            if (agent.indexOf("firefox") != -1) {
                response.setHeader("content-disposition", String.format("attachment;filename*=utf-8'zh_cn'%s", URLEncoder.encode(excelName, "utf-8")));
            } else {
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(excelName, "utf-8"));
            }
        }
        response.setContentType("application/ms-excel; charset=UTF-8");

        this.setStyle(workbook);
        setRow(workbook, map);
    }


    public void setRow(Workbook workbook, Map<String, Object> map) {
        String[] headername = (String[]) map.get("headername");
        List<String[]> list = (List<String[]>) map.get("exclvar");
        int rowCount = 0;
        int yeshu = 0;
        Sheet sheet =null;
        for (String[] stringvar : list) {
            if (rowCount % 10000 == 0) {
                sheet = workbook.createSheet(yeshu++ + "");
                sheet.setDefaultColumnWidth(40);//设置列宽
                Row header = sheet.createRow(0);
                for (int i = 0; i < headername.length; i++) {
                    System.err.println("i:" + i + "。headername：" + headername[i]);
                    header.createCell(i).setCellValue(headername[i]);
                    header.getCell(i).setCellStyle(cellStyle);
                }
                rowCount = 1;
            }
            Row userRow = sheet.createRow(rowCount++);
            for (int i = 0; i < stringvar.length; i++) {
                if (stringvar[i] != null) {
                    userRow.createCell(i).setCellValue(stringvar[i]);
                }
            }
        }
    }

    //设置样式
    protected void setStyle(Workbook workbook) {
        //设置字体
        Font font = workbook.createFont();
        font.setFontName("Arial");//设置字体
        font.setBold(true);//字体是否加粗
        font.setColor(HSSFColor.WHITE.index);//字体颜色
        //设置表格
        cellStyle = workbook.createCellStyle();//创建样式
        cellStyle.setFont(font);//设置字体
        cellStyle.setFillForegroundColor(HSSFColor.BLUE.index);//设置前景色
        cellStyle.setFillPattern((short) 1);//填充图案
    }


    //获取excel表内容
    public static List<String[]> getAllByExcel(String fileName, InputStream inputStream) {
        List<String[]> list = new ArrayList<>();
        Workbook wookbook = null;
        System.err.println("fileName" + fileName);
        if (fileName.endsWith("xls")) {
            try {
                wookbook = new HSSFWorkbook(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }//得到工作簿
        } else if (fileName.endsWith("xlsx")) {
            try {
                wookbook = new XSSFWorkbook(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JSONObject data = new JSONObject();
        //得到一个工作表
        Sheet sheet = wookbook.getSheetAt(0);
        int totalRowNum = sheet.getLastRowNum();//获得数据的总行数
        int cellNum = 0;//数据的总列数
        //获得所有数据
        String[] lie;
        for (int a = 0; a <= totalRowNum; a++) {
            //获得第i行对象
            Row row = sheet.getRow(a);
            if (row == null) {
                continue;
            }
            cellNum = row.getLastCellNum();
            lie = new String[cellNum];
            for (int b = 0; b < cellNum; b++) {
                row.getCell(b).setCellType(Cell.CELL_TYPE_STRING);
                lie[b] = row.getCell(b).getStringCellValue();
                System.err.print(lie[b] + " ");
            }
            data.put("data", lie);
            list.add(lie);
        }
        data.put("data", list);
        return list;
    }

}