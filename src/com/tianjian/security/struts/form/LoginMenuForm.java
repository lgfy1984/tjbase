/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   LoginMenuForm.java

package com.tianjian.security.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.*;

public class LoginMenuForm extends ActionForm
{

    public String[] getPicPath()
    {
        return picPath;
    }

    public void setPicPath(String picPath[])
    {
        this.picPath = picPath;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
        return null;
    }

    public LoginMenuForm()
    {
        verbId = "";
        message = "";
        staffId = "";
        staffCode = "";
        selectedPublicId = "";
    }

    public String getVerbId()
    {
        return verbId;
    }

    public void setVerbId(String verbId)
    {
        this.verbId = verbId;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getSelectedPublicId()
    {
        return selectedPublicId;
    }

    public void setSelectedPublicId(String selectedPublicId)
    {
        this.selectedPublicId = selectedPublicId;
    }

    public String[] getRolesId()
    {
        return rolesId;
    }

    public void setRolesId(String rolesId[])
    {
        this.rolesId = rolesId;
    }

    public String[] getPublicId()
    {
        return publicId;
    }

    public void setPublicId(String publicId[])
    {
        this.publicId = publicId;
    }

    public String[] getMenuId()
    {
        return menuId;
    }

    public void setMenuId(String menuId[])
    {
        this.menuId = menuId;
    }

    public String[] getMenuName()
    {
        return menuName;
    }

    public void setMenuName(String menuName[])
    {
        this.menuName = menuName;
    }

    public String[] getPublicName()
    {
        return publicName;
    }

    public void setPublicName(String publicName[])
    {
        this.publicName = publicName;
    }

    public String[] getParentMenuId()
    {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId[])
    {
        this.parentMenuId = parentMenuId;
    }

    public String getStaffCode()
    {
        return staffCode;
    }

    public void setStaffCode(String staffCode)
    {
        this.staffCode = staffCode;
    }

    public String getSelectedPublicClassId()
    {
        return selectedPublicClassId;
    }

    public void setSelectedPublicClassId(String selectedPublicClassId)
    {
        this.selectedPublicClassId = selectedPublicClassId;
    }

    public String getStaffId()
    {
        return staffId;
    }

    public void setStaffId(String staffId)
    {
        this.staffId = staffId;
    }

    public String[] getPublicReasonName()
    {
        return publicReasonName;
    }

    public void setPublicReasonName(String publicReasonName[])
    {
        this.publicReasonName = publicReasonName;
    }

    private static final long serialVersionUID = -7601433867678734463L;
    private String verbId;
    private String message;
    private String staffCode;
    private String staffId;
    private String selectedPublicId;
    private String selectedPublicClassId;
    private String rolesId[];
    private String publicId[];
    private String menuId[];
    private String parentMenuId[];
    private String menuName[];
    private String publicName[];
    private String publicReasonName[];
    private String picPath[];
    //20130717 add
    private String selectedPublicName;
    private String selectedPublicClassName;
    private String parentPublicClassId;
    private String parentPublicClassName;
	public String getSelectedPublicName() {
		return selectedPublicName;
	}

	public void setSelectedPublicName(String selectedPublicName) {
		this.selectedPublicName = selectedPublicName;
	}

	public String getSelectedPublicClassName() {
		return selectedPublicClassName;
	}

	public void setSelectedPublicClassName(String selectedPublicClassName) {
		this.selectedPublicClassName = selectedPublicClassName;
	}

	public String getParentPublicClassId() {
		return parentPublicClassId;
	}

	public void setParentPublicClassId(String parentPublicClassId) {
		this.parentPublicClassId = parentPublicClassId;
	}

	public String getParentPublicClassName() {
		return parentPublicClassName;
	}

	public void setParentPublicClassName(String parentPublicClassName) {
		this.parentPublicClassName = parentPublicClassName;
	}
}


/*
	DECOMPILATION REPORT

	Decompiled from: D:\Workspaces6\EHRPProject_20130625\WebRoot\WEB-INF\lib\tj-security.jar
	Total time: 188 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/