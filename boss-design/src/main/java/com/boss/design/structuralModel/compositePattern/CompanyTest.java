package com.boss.design.structuralModel.compositePattern;

/**
 * 设计模式 结构型模式 之 组合模式
 * 客户端测试类
 */
public class CompanyTest {
    public static void main(String[] args) {
        //研发部
        Dept kf = new Dept("开发组");
        Employee e = new Employee("开发A", "开发组", 20000);
        kf.add(e);
        e = new Employee("开发B", "开发组", 15000);
        kf.add(e);
        Dept test = new Dept("测试组");
        e = new Employee("测试C", "测试组", 13000);
        test.add(e);
        Dept yf = new Dept("研发部");
        yf.add(kf);
        yf.add(test);
        e = new Employee("研发经理D", "研发部", 13000);
        yf.add(e);

        //人事部
        Dept rsb = new Dept("人事部");
        e = new Employee("人事D", "人事部", 6000);
        rsb.add(e);
        e = new Employee("人事E", "人事部", 8000);
        rsb.add(e);

        //CEO
        e = new Employee("CEO", "公司", 60000);

        //公司
        Dept company = new Dept("公司");
        company.add(e);
        company.add(rsb);
        company.add(yf);

        //公司的人员
        company.describeEmployees();

        //公司的人员年总收入
        System.out.println("公司每月总付出：" + company.countMonthlyIncome());
    }
}
