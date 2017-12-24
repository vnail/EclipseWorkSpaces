package entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * jdbc��ʱ��
 * java.sql���µ��������ͣ�
 * java.sql.Date ������
 * java.sql.Time ʱ����
 * java.sql.Timestamp  ������ʱ����
 * ���Ƕ���java.util.Date������*/

public class Cost implements Serializable {
	//����
	private Integer costId;
	//�ʷ���
	private String name;
	//����ʱ��
	private Integer baseDuration;
	//��������
	private Double baseCost;
	//��λ����
	private Double unitCost;
	//״̬:0-��ͨ��1-����
	private String status;
	//����
	private String descr;
	//����ʱ��
	private Timestamp createtime;
	//��ͨʱ��
	private Timestamp starttime;
	//�ʷ�����1-���£�2-�ײͣ�3-��ʱ��
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
