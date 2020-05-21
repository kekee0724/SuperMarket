package org.kekee.entity;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

/**
 * @author cocoa
 */
@Data
public class User {
    private Integer id;
    private String userCode;
    private String userName;
    private String userPassword;
    private int gender;
    private Date birthday;
    private String phone;
    private Role role;
    private String address;
    private int createdBy;
    private Date creationDate;
    private int age;
    private int userRole;

    public int getAge() {
        Date birthday = this.getBirthday();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        Calendar c2 = Calendar.getInstance();
        c2.setTime(birthday);
        int age = c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
        return age;
    }
}
