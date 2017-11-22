package com.tianjian.hsp.bean;

import java.io.Serializable;

// Referenced classes of package com.tianjian.hsp.bean:
//            HspDeptBaseinfoId

public class HspDeptBaseinfo implements Serializable{

    public HspDeptBaseinfo()
    {
    }



	public HspDeptBaseinfo(HspDeptBaseinfoId id, Long seqNo, String deptAttrCode)
    {
        this.id = id;
        this.seqNo = seqNo;
        this.deptAttrCode = deptAttrCode;
    }

    public HspDeptBaseinfoId getId()
    {
        return id;
    }

    public HspDeptBaseinfo(HspDeptBaseinfoId id, Long seqNo, String deptAttrCode, String deptAttrName, String inputCode, String comments, String hspConfigBaseinfoName, 
            String deptName, String healthBureauCode, String socialSecurityBureauCode, String tenantId,String deptIoAttrDictCode,String deptIsAttrDictCode)
    {
        this.id = id;
        this.seqNo = seqNo;
        this.deptAttrCode = deptAttrCode;
        this.deptAttrName = deptAttrName;
        this.inputCode = inputCode;
        this.comments = comments;
        this.hspConfigBaseinfoName = hspConfigBaseinfoName;
        this.deptName = deptName;
        this.healthBureauCode = healthBureauCode;
        this.socialSecurityBureauCode = socialSecurityBureauCode;
        this.deptIoAttrDictCode = deptIoAttrDictCode;
        this.deptIsAttrDictCode = deptIsAttrDictCode;
        this.tenantId = tenantId;
    }

    public void setId(HspDeptBaseinfoId id)
    {
        this.id = id;
    }

    public Long getSeqNo()
    {
        return seqNo;
    }

    public void setSeqNo(Long seqNo)
    {
        this.seqNo = seqNo;
    }

    public String getDeptAttrCode()
    {
        return deptAttrCode;
    }

    public void setDeptAttrCode(String deptAttrCode)
    {
        this.deptAttrCode = deptAttrCode;
    }

    public String getDeptAttrName()
    {
        return deptAttrName;
    }

    public void setDeptAttrName(String deptAttrName)
    {
        this.deptAttrName = deptAttrName;
    }

    public String getInputCode()
    {
        return inputCode;
    }

    public void setInputCode(String inputCode)
    {
        this.inputCode = inputCode;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public String getHspConfigBaseinfoName()
    {
        return hspConfigBaseinfoName;
    }

    public void setHspConfigBaseinfoName(String hspConfigBaseinfoName)
    {
        this.hspConfigBaseinfoName = hspConfigBaseinfoName;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getHealthBureauCode()
    {
        return healthBureauCode;
    }

    public void setHealthBureauCode(String healthBureauCode)
    {
        this.healthBureauCode = healthBureauCode;
    }

    public String getSocialSecurityBureauCode()
    {
        return socialSecurityBureauCode;
    }

    public void setSocialSecurityBureauCode(String socialSecurityBureauCode)
    {
        this.socialSecurityBureauCode = socialSecurityBureauCode;
    }

    public String getTenantId()
    {
        return tenantId;
    }

    public void setTenantId(String tenantId)
    {
        this.tenantId = tenantId;
    }

    public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getDeptIoAttrDictCode() {
		return this.deptIoAttrDictCode;
	}

	public void setDeptIoAttrDictCode(String deptIoAttrDictCode) {
		this.deptIoAttrDictCode = deptIoAttrDictCode;
	}

	public String getDeptIsAttrDictCode() {
		return this.deptIsAttrDictCode;
	}

	public void setDeptIsAttrDictCode(String deptIsAttrDictCode) {
		this.deptIsAttrDictCode = deptIsAttrDictCode;
	}
    private HspDeptBaseinfoId id;
    private Long seqNo;
    private String deptAttrCode;
    private String deptAttrName;
    private String inputCode;
    private String comments;
    private String hspConfigBaseinfoName;
    private String deptName;
    private String healthBureauCode;
    private String socialSecurityBureauCode;
    private String deptIoAttrDictCode;
	private String deptIsAttrDictCode;
    private String tenantId;
    private String itemCode;
}


