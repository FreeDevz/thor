package io.awijaya.test;

import java.util.List;

/**
 * Imagine you are the team that maintains the Atlassian employee directory.
 * At Atlassian - there are multiple groups, and each can have one or more groups. Every employee is part of a group.
 * You are tasked with designing a system that could find the closest common parent group given a target set of employees in the organization.
 *
 * -            Company
 *           /           \
 *         HR               Engg
 *       /   \          /          \
 *      Mona  Springs  BE          FE
 *                     /  \        / \  \
 *                   Alice Bob  Lisa Marley John
 *
 *  query input: can be more than 2
 */
public class EmployeeDirectoryUtil {

    public static String findClosestCommonParentGroup(Directory root, List<String> employees) {
        List<Directory> childDepartments = root.getChildDepartments();

        for (String employee: employees) {

        }

        return "";
    }

    public List<Directory> addDepartment(Directory directory, String employee, List<Directory> result) {
        if (directory.getEmployees() != null && directory.getEmployees().size() > 0) {
            if (directory.getEmployees().contains(employee)) {

            }
        }
        return null;
    }



    static class Directory {
        private String departmentName;
        private Directory parentDepartment;
        private List<Directory> childDepartments;
        private List<String> employees;

        Directory(String departmentName) {
            this.departmentName = departmentName;
        }

        Directory(String departmentName, Directory parentDepartment, List<Directory> childDepartments, List<String> employees) {
            this.departmentName = departmentName;
            this.parentDepartment = parentDepartment;
            this.childDepartments = childDepartments;
            this.employees = employees;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public Directory getParentDepartment() {
            return parentDepartment;
        }

        public void setParentDepartment(Directory parentDepartment) {
            this.parentDepartment = parentDepartment;
        }

        public List<Directory> getChildDepartments() {
            return childDepartments;
        }

        public void setChildDepartments(List<Directory> childDepartments) {
            this.childDepartments = childDepartments;
        }

        public List<String> getEmployees() {
            return employees;
        }

        public void setEmployees(List<String> employees) {
            this.employees = employees;
        }
    }

    public static void main(String[] args) {
        Directory atlassian = new Directory("Atlassian");
        Directory engineering = new Directory("ENG");
        Directory hr = new Directory("HR", atlassian, null, List.of("HR", "Engg"));
        Directory backendDirectory = new Directory("BE", engineering, null, List.of("Alice", "Bob"));
        Directory frontEndDirectory = new Directory("FE", engineering, null, List.of("Lisa", "Marley", "John"));

        engineering.setParentDepartment(atlassian);
        engineering.setChildDepartments(List.of(backendDirectory, frontEndDirectory));

        atlassian.setChildDepartments(List.of(hr, engineering));

        EmployeeDirectoryUtil.findClosestCommonParentGroup(atlassian, List.of("Alice", "Lisa")); // BE

//        EmployeeDirectoryUtil.findClosestCommonParentGroup(List.of("Alice", "Bob", "Lisa"), List.of(backendDirectory, backendDirectory, frontEndDirectory)); // ENG

//        EmployeeDirectory test1 = new EmployeeDirectory();
    }
}
