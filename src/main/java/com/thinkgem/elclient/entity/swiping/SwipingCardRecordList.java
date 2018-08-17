package com.thinkgem.elclient.entity.swiping;


/**
 * @author zhaoqingjie
 */
public class SwipingCardRecordList extends SwipingCardRecordVo{

    private String startTime;

    private String endTime;

    private String valveId;

    private String valveName;

    private String peasantryId;

    private String peasantryName;


    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getValveId() {
        return valveId;
    }
    public void setValveId(String valveId) {
        this.valveId = valveId;
    }

    public String getValveName() {
        return valveName;
    }
    public void setValveName(String valveName) {
        this.valveName = valveName;
    }

    public String getPeasantryId() {
        return peasantryId;
    }
    public void setPeasantryId(String peasantryId) {
        this.peasantryId = peasantryId;
    }

    public String getPeasantryName() {
        return peasantryName;
    }
    public void setPeasantryName(String peasantryName) {
        this.peasantryName = peasantryName;
    }

    @Override
    public String toString() {
        return "SwipingCardRecordList{" + super.toString() +
                "endTime=" + endTime +
                ", valveId='" + valveId + '\'' +
                ", valveName='" + valveName + '\'' +
                ", peasantryId='" + peasantryId + '\'' +
                ", peasantryName='" + peasantryName + '\'' +
                '}';
    }
}
