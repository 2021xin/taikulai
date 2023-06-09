package com.learn.health.controller;


import com.learn.health.constant.MessageConstant;
import com.learn.health.entity.Result;
import com.learn.health.service.MemberService;
import com.learn.health.service.ReportService;
import com.learn.health.service.SetMealService;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Data 2022/10/14
 * @Time 17:17
 * @Author Yan Taixin
 */
@RestController
@RequestMapping("/admin/report")
public class ReportController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private SetMealService setMealService;
    @Autowired
    private ReportService reportService;

    /**
     * 返回最近一年会员数量折线图的相关数据
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getMemberReport")
    public Result getMemberReport() throws Exception {
        try {
            HashMap<String, Object> map = new HashMap<>();
            // 获取日历对象
            Calendar calendar = Calendar.getInstance();
            List<String> date = new ArrayList<>();
            // 计算过去12个月
            calendar.add(Calendar.MONTH, -12);
            for (int i = 0; i < 12; i++) {
                calendar.add(Calendar.MONTH, 1);
                date.add(new SimpleDateFormat("yyyy-MM").format(calendar.getTime()));
            }
            List<Integer> memberNumber = memberService.findMemberNumberByDate(date);
            map.put("months", date);
            map.put("memberCount", memberNumber);
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
        }
    }

    /**
     * 返回套餐预约饼图数据
     *
     * @return
     */
    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport() {
        try {
            Map<String, Object> map = new HashMap<>();
            ArrayList<String> setMealNames = new ArrayList<>();
            ArrayList<Map<String, Object>> setMealCount = setMealService.findSetmealCount();
            // 获取数据标签名
            for (Map<String, Object> data : setMealCount) {
                setMealNames.add((String) data.get("name"));
            }
            map.put("setmealNames", setMealNames);
            map.put("setmealCount", setMealCount);
            return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }
    }

    /**
     * 获取运营数据
     *
     * @return
     */
    @RequestMapping("/getBusinessReportData")
    public Result getBusinessReportData() {
        try {
            Map<String, Object> map = reportService.getBusinessReport();
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }

    /**
     * 以excel文件的方式导出运营数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/exportBusinessReport")
    public Result exportBusinessReport(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> result = reportService.getBusinessReport();
            // 取出返回结果数据，准备将报表数据写入到Excel文件中
            String reportDate = (String) result.get("reportDate");
            Integer todayNewMember = (Integer) result.get("todayNewMember");
            Integer totalMember = (Integer) result.get("totalMember");
            Integer thisWeekNewMember = (Integer) result.get("thisWeekNewMember");
            Integer thisMonthNewMember = (Integer) result.get("thisMonthNewMember");
            Integer todayOrderNumber = (Integer) result.get("todayOrderNumber");
            Integer thisWeekOrderNumber = (Integer) result.get("thisWeekOrderNumber");
            Integer thisMonthOrderNumber = (Integer) result.get("thisMonthOrderNumber");
            Integer todayVisitsNumber = (Integer) result.get("todayVisitsNumber");
            Integer thisWeekVisitsNumber = (Integer) result.get("thisWeekVisitsNumber");
            Integer thisMonthVisitsNumber = (Integer) result.get("thisMonthVisitsNumber");
            List<Map> hotSetmeal = (List<Map>) result.get("hotSetmeal");

            ClassPathResource classPathResource = new ClassPathResource("static" + File.separator + "template" + File.separator + "report_template.xlsx");
            // 基于提供的Excel模板文件在内存中创建一个Excel表格
            XSSFWorkbook excel = new XSSFWorkbook(classPathResource.getInputStream());
            // 读取工作表
            XSSFSheet sheet = excel.getSheetAt(0);
            // 获取行，设置日期
            XSSFRow row = sheet.getRow(2);
            row.getCell(5).setCellValue(reportDate);

            row = sheet.getRow(4);
            // 新增会员数（本日）
            row.getCell(5).setCellValue(todayNewMember);
            // 总会员数
            row.getCell(7).setCellValue(totalMember);

            row = sheet.getRow(5);
            // 本周新增会员数
            row.getCell(5).setCellValue(thisWeekNewMember);
            // 本月新增会员数
            row.getCell(7).setCellValue(thisMonthNewMember);

            row = sheet.getRow(7);
            // 今日预约数
            row.getCell(5).setCellValue(todayOrderNumber);
            // 今日到诊数
            row.getCell(7).setCellValue(todayVisitsNumber);

            row = sheet.getRow(8);
            // 本周预约数
            row.getCell(5).setCellValue(thisWeekOrderNumber);
            // 本周到诊数
            row.getCell(7).setCellValue(thisWeekVisitsNumber);

            row = sheet.getRow(9);
            // 本月预约数
            row.getCell(5).setCellValue(thisMonthOrderNumber);
            // 本月到诊数
            row.getCell(7).setCellValue(thisMonthVisitsNumber);

            int rowNum = 12;
            // 热门套餐
            for (Map map : hotSetmeal) {
                String name = (String) map.get("name");
                Long setmeal_count = (Long) map.get("setmeal_count");
                BigDecimal proportion = (BigDecimal) map.get("proportion");
                row = sheet.getRow(rowNum++);
                // 套餐名称
                row.getCell(4).setCellValue(name);
                // 预约数量
                row.getCell(5).setCellValue(setmeal_count);
                // 占比
                row.getCell(6).setCellValue(proportion.doubleValue());
            }

            //通过输出流进行文件下载
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename=report.xlsx");
            excel.write(out);
            out.flush();
            out.close();
            excel.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }

    /**
     * 以PDF文件的方式导出运营数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/exportBusinessReportPDF")
    public Result exportBusinessReportPDF(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> result = reportService.getBusinessReport();
            // 取出返回结果数据，准备将报表数据写入到PDF文件中
            List<Map> hotSetmeal = (List<Map>) result.get("hotSetmeal");

            // 动态获取模板文件的真实位置
            ClassPathResource jrxml = new ClassPathResource("static" + File.separator + "template" + File.separator + "health_business3.jrxml");
            ClassPathResource jasper = new ClassPathResource("static" + File.separator + "template" + File.separator + "health_business3.jasper");
            String jrxmlPath = jrxml.getFile().getAbsolutePath();
            String jasperPath = jasper.getFile().getAbsolutePath();

            // 编译模板
            JasperCompileManager.compileReportToFile(jrxmlPath,jasperPath);

            // 填充数据
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath, result, new JRBeanCollectionDataSource(hotSetmeal));

            //通过输出流进行文件下载
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/pdf");
            response.setHeader("content-Disposition", "attachment;filename=report.pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }
}
