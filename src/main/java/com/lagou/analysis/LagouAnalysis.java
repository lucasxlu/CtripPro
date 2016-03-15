package com.lagou.analysis;

import com.lagou.entity.LagouJob;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LucasX on 2016/3/14.
 */
public class LagouAnalysis {

    private Logger logger = LogManager.getLogger();

    private List<LagouJob> readJson(String fileJobTypeDir) throws IOException {
        List<LagouJob> lagouJobList = new ArrayList<>();
        Files.list(Paths.get(fileJobTypeDir)).forEach(path -> {
            try {
                StringBuilder stringBuilder = new StringBuilder("{\"joblist\":");
                Files.readAllLines(path).forEach(s -> {
                    stringBuilder.append(s);

                });
                JSONObject jsonObject = (JSONObject) JSONValue.parse(stringBuilder.toString().replace("'", "\"").replace("False", "false").replace("None", "null") + "}");

                ((JSONArray) jsonObject.get("joblist")).forEach(o -> {
                    String education = ((JSONObject) o).get("education").toString();
                    String formatCreateTime = ((JSONObject) o).get("formatCreateTime").toString();
                    String companyId = ((JSONObject) o).get("companyId").toString();
                    String companyName = ((JSONObject) o).get("companyName").toString();
                    String industryField = ((JSONObject) o).get("industryField").toString();
                    String companyLabelList = ((JSONObject) o).get("companyLabelList").toString();
                    String workYear = ((JSONObject) o).get("workYear").toString();
                    String positionType = ((JSONObject) o).get("positionType").toString();
                    String salary = ((JSONObject) o).get("salary").toString();
                    String financeStage = ((JSONObject) o).get("financeStage").toString();
                    String city = ((JSONObject) o).get("city").toString();
                    String positionName = ((JSONObject) o).get("positionName").toString();
                    String jobNature = ((JSONObject) o).get("jobNature").toString();

                    LagouJob lagouJob = new LagouJob(formatCreateTime, companyId, companyName, industryField, companyLabelList, workYear, positionType, salary, financeStage, city, education, positionName, jobNature);
                    lagouJobList.add(lagouJob);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        return lagouJobList;
    }

    private void toCsv(List<LagouJob> lagouJobList, String fileDirName) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("发布时间,公司ID,公司名称,所属行业,工作时间,工作类型,薪资待遇,融资情况,城市,教育程度,职位名称,工作属性\r\n");
        lagouJobList.forEach(lagouJob -> {
            stringBuilder.append(lagouJob.getFormatCreateTime()).append(",").append(lagouJob.getCompanyId()).append(",").append(lagouJob.getCompanyName()).append(",").append(lagouJob.getIndustryField()).append(",").append(lagouJob.getWorkYear()).append(",").append(lagouJob.getPositionType()).append(",").append(lagouJob.getSalary()).append(",").append(lagouJob.getFinanceStage()).append(",").append(lagouJob.getCity()).append(",").append(lagouJob.getEducation()).append(",").append(lagouJob.getPositionName()).append(",").append(lagouJob.getJobNature()).append("\r\n");
        });

        Files.write(Paths.get("H:\\" + fileDirName.split("\\\\")[fileDirName.split("\\\\").length - 1] + ".csv"), stringBuilder.toString().getBytes("GBK"));

        logger.debug("Excel generates successfully!!");
    }

    public static void main(String[] args) {
        String param = "H:\\lagou\\C";
        LagouAnalysis lagouAnalysis = new LagouAnalysis();

        try {
            List<LagouJob> lagouJobList = lagouAnalysis.readJson(param);
            lagouAnalysis.toCsv(lagouJobList, param);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
