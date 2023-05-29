package com.hum;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author Hassan Mahfuj
 */
@ManagedBean
public class Student {
    String id;
    String name;
    String email;
    String gender;
    String round;
    List<String> skills;
    
    ArrayList<Student> list;

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public List<String> getSkills() {
        return skills;
    }
    
    public String getSkillsAsString() {
        return String.join(", ", skills);
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public ArrayList<Student> getList() {
        show();
        return list;
    }

    public void setList(ArrayList<Student> list) {
        this.list = list;
    }
    
    public void show() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hum", "root", "admin123");
            PreparedStatement pst = con.prepareStatement("SELECT id, name, email, gender, round, skills FROM students");
            ResultSet rs = pst.executeQuery();
            list = new ArrayList<>();
            while(rs.next()) {
                Student std = new Student();
                std.setId(rs.getString(1));
                std.setName(rs.getString(2));
                std.setEmail(rs.getString(3));
                std.setGender(rs.getString(4));
                std.setRound(rs.getString(5));
                std.setSkills(Arrays.asList(rs.getString(6).split(", ")));
                list.add(std);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void insert() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hum", "root", "admin123");
            PreparedStatement pst = con.prepareStatement("INSERT INTO students (id, name, email, gender, round, skills) VALUES (?, ?, ?, ?, ?, ?)");
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, email);
            pst.setString(4, gender);
            pst.setString(5, round);
            pst.setString(6, String.join(", ", skills));
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void update() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hum", "root", "admin123");
            PreparedStatement pst = con.prepareStatement("UPDATE students SET name = ?, email = ?, gender = ?, round = ?, skills = ? WHERE id = ?");
            pst.setString(6, id);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, gender);
            pst.setString(4, round);
            pst.setString(5, String.join(", ", skills));
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void delete() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hum", "root", "admin123");
            PreparedStatement pst = con.prepareStatement("DELETE FROM students WHERE id = ?");
            pst.setString(1, id);
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
