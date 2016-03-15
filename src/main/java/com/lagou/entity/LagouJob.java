package com.lagou.entity;

/**
 * Created by LucasX on 2016/3/14.
 */
public class LagouJob {

    private String formatCreateTime;//发布时间
    private String companyId; //公司ID
    private String companyName; //公司名称
    private String industryField; //所属行业
    private String companyLabelList;//公司标签
    private String workYear;//工作时间
    private String positionType; //工作类型
    private String salary;//薪水
    private String financeStage; //融资情况
    private String city; //城市
    private String education;//教育程度
    private String positionName; //职位名称
    private String jobNature; //工作属性

    public LagouJob(String formatCreateTime, String companyId, String companyName, String industryField, String companyLabelList, String workYear, String positionType, String salary, String financeStage, String city, String education, String positionName, String jobNature) {
        this.formatCreateTime = formatCreateTime;
        this.companyId = companyId;
        this.companyName = companyName;
        this.industryField = industryField;
        this.companyLabelList = companyLabelList;
        this.workYear = workYear;
        this.positionType = positionType;
        this.salary = salary;
        this.financeStage = financeStage;
        this.city = city;
        this.education = education;
        this.positionName = positionName;
        this.jobNature = jobNature;
    }

    public String getFormatCreateTime() {
        return formatCreateTime;
    }

    public void setFormatCreateTime(String formatCreateTime) {
        this.formatCreateTime = formatCreateTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustryField() {
        return industryField;
    }

    public void setIndustryField(String industryField) {
        this.industryField = industryField;
    }

    public String getCompanyLabelList() {
        return companyLabelList;
    }

    public void setCompanyLabelList(String companyLabelList) {
        this.companyLabelList = companyLabelList;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getFinanceStage() {
        return financeStage;
    }

    public void setFinanceStage(String financeStage) {
        this.financeStage = financeStage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getJobNature() {
        return jobNature;
    }

    public void setJobNature(String jobNature) {
        this.jobNature = jobNature;
    }

    @Override
    public String toString() {
        return "LagouJob{" +
                "formatCreateTime='" + formatCreateTime + '\'' +
                ", companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", industryField='" + industryField + '\'' +
                ", companyLabelList='" + companyLabelList + '\'' +
                ", workYear='" + workYear + '\'' +
                ", positionType='" + positionType + '\'' +
                ", salary='" + salary + '\'' +
                ", financeStage='" + financeStage + '\'' +
                ", city='" + city + '\'' +
                ", education='" + education + '\'' +
                ", positionName='" + positionName + '\'' +
                ", jobNature='" + jobNature + '\'' +
                '}';
    }
}
