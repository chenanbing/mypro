package com.cab.bean.entity.domains;

import java.util.Date;

public class Domains {
    private Integer id;

    private Date createtime;

    private Integer createrid;

    private Integer dnseffectivedate;

    private String dnsprovider;

    private String domainname;

    private Integer modifierid;

    private Date modifytime;

    private String operator;

    private String outerparseip;

    private Integer principalid;

    private String remarks;

    private Integer usecompanyid;

    private Integer usedeptid;

    private Date validityend;

    private Date validitystart;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getCreaterid() {
        return createrid;
    }

    public void setCreaterid(Integer createrid) {
        this.createrid = createrid;
    }

    public Integer getDnseffectivedate() {
        return dnseffectivedate;
    }

    public void setDnseffectivedate(Integer dnseffectivedate) {
        this.dnseffectivedate = dnseffectivedate;
    }

    public String getDnsprovider() {
        return dnsprovider;
    }

    public void setDnsprovider(String dnsprovider) {
        this.dnsprovider = dnsprovider == null ? null : dnsprovider.trim();
    }

    public String getDomainname() {
        return domainname;
    }

    public void setDomainname(String domainname) {
        this.domainname = domainname == null ? null : domainname.trim();
    }

    public Integer getModifierid() {
        return modifierid;
    }

    public void setModifierid(Integer modifierid) {
        this.modifierid = modifierid;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getOuterparseip() {
        return outerparseip;
    }

    public void setOuterparseip(String outerparseip) {
        this.outerparseip = outerparseip == null ? null : outerparseip.trim();
    }

    public Integer getPrincipalid() {
        return principalid;
    }

    public void setPrincipalid(Integer principalid) {
        this.principalid = principalid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getUsecompanyid() {
        return usecompanyid;
    }

    public void setUsecompanyid(Integer usecompanyid) {
        this.usecompanyid = usecompanyid;
    }

    public Integer getUsedeptid() {
        return usedeptid;
    }

    public void setUsedeptid(Integer usedeptid) {
        this.usedeptid = usedeptid;
    }

    public Date getValidityend() {
        return validityend;
    }

    public void setValidityend(Date validityend) {
        this.validityend = validityend;
    }

    public Date getValiditystart() {
        return validitystart;
    }

    public void setValiditystart(Date validitystart) {
        this.validitystart = validitystart;
    }
}