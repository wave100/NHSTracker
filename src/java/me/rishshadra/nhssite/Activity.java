/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhssite;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rish
 */
public class Activity {
    
    private int id;
    private float hours;
    private String projdesc, obsname, obsemail;
    private boolean approved, groupproj;
    
    public Activity(int i, float h, String p, String on, String oe, boolean a, boolean g) {
        id = i;
        hours = h;
        projdesc = p;
        obsname = on;
        obsemail = oe;
        approved = a;
        groupproj = g;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public String getProjdesc() {
        return projdesc;
    }

    public void setProjdesc(String projdesc) {
        this.projdesc = projdesc;
    }

    public String getObsname() {
        return obsname;
    }

    public void setObsname(String obsname) {
        this.obsname = obsname;
    }

    public String getObsemail() {
        return obsemail;
    }

    public void setObsemail(String obsemail) {
        this.obsemail = obsemail;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isGroupproj() {
        return groupproj;
    }

    public void setGroupproj(boolean groupproj) {
        this.groupproj = groupproj;
    }
    
    public void update() {
        try {
            new Reader().addActivity(this);
        } catch (SQLException ex) {
            Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
