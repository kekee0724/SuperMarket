package org.kekee.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author cocoa
 */
@Data
public class Provider {
    private Integer id;
    private String proCode;
    private String proName;
    private String proContact;
    private String proPhone;
    private String proFax;
    private String proDesc;
    private String proAddress;
    private Date creationDate;
    private int createBy;
}
