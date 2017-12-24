package entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * jdbc下时间
 * java.sql包下的日期类型：
 * java.sql.Date 年月日
 * java.sql.Time 时分秒
 * java.sql.Timestamp  年月日时分秒
 * 它们都是java.util.Date的子类*/

public class Cost implements Serializable {
	//主键
	private Integer costId;
	//资费名
	private String name;
	//基本时长
	private Integer baseDuration;
	//基本费用
	private Double baseCost;
	//单位费用
	private Double unitCost;
	//状态:0-开通；1-禁用
	private String status;
	//描述
	private String descr;
	//创建时间
	private Timestamp createtime;
	//开通时间
	private Timestamp starttime;
	//资费类型1-包月；2-套餐；3-计时；
	private String costType;
	
	public Integer getCostId() {
		return costId;
	}
	public void setCostId(Integer costId) {
		this.costId = costId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBaseDuration() {
		return baseDuration;
	}
	public void setBaseDuration(Integer baseDuration) {
		this.baseDuration = baseDuration;
	}
	public Double getBaseCost() {
		return baseCost;
	}
	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}
	public Double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	@Override
	public String toString() {
		return "Cost [costId=" + costId + ", name=" + name + ", baseDuration=" + baseDuration + ", baseCost=" + baseCost
				+ ", unitCost=" + unitCost + ", status=" + status + ", descr=" + descr + ", createtime=" + createtime
				+ ", starttime=" + starttime + ", costType=" + costType + "]";
	}
	
	

}
