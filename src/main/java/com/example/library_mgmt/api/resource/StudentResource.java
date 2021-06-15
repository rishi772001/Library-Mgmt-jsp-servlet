package com.example.library_mgmt.api.resource;

import com.example.library_mgmt.config.Connect;
import com.example.library_mgmt.models.Student;
import com.example.library_mgmt.servlets.SignupServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentResource {
    public List<Student> getAllStudents() throws SQLException {
        ResultSet rs = Connect.executeSelect("select * from student");
        List<Student> listOfStudents = new ArrayList<>();
        if (rs != null) {
            while(rs.next()){
                Student student = new Student();
                student.setRollno(rs.getInt("stud_rollNo"));
                student.setName(rs.getString("stud_name"));
                student.setPassword(rs.getString("stud_password"));
                listOfStudents.add(student);
            }
        }
        return listOfStudents;
    }

    public Student getStudent(int id) throws SQLException {
        ResultSet rs = Connect.executeSelect("select * from student where stud_rollno = '"+id+"'");
        Student student = new Student();
        if(rs != null && rs.next()){
            student.setRollno(rs.getInt("stud_rollNo"));
            student.setName(rs.getString("stud_name"));
            student.setPassword(rs.getString("stud_password"));
        }
        return student;
    }

    public boolean addStudent(Student student) throws SQLException {
        return SignupServlet.signup_student(student);
    }

    public boolean updateStudent(int id, Student student){
        String updateStudent = "update student set stud_rollno = '"+student.getRollno()+"', stud_name = '"+student.getName()+"', stud_password = '"+student.getPassword()+"' where stud_rollno = '"+id+"'";
        try {
            Connect.execute(updateStudent);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteStudent(int id){
        String updateStudent = "delete from student where stud_rollno = '"+id+"'";
        try {
            Connect.execute(updateStudent);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
