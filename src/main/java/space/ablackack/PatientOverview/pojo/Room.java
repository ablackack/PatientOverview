package space.ablackack.PatientOverview.pojo;

import space.ablackack.PatientOverview.enums.RoomTypeEnm;

import java.io.Serializable;

public class Room implements Serializable {
    private String roomName;
    private Integer maxPatientsGreen;
    private Integer maxPatientsYellow;
    private Integer maxPatientsRed;
    private Integer maxPatientsTotal;
    private Boolean hasDoctor;
    private RoomTypeEnm roomType;

    public Room(String roomName, Boolean hasDoctor, RoomTypeEnm roomType) {
        this.roomName = roomName;
        this.hasDoctor = hasDoctor;
        this.roomType = roomType;
    }

    public Room(String roomName) {
        this(roomName, false, RoomTypeEnm.TENT);
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getMaxPatientsGreen() {
        return maxPatientsGreen;
    }

    public void setMaxPatientsGreen(Integer maxPatientsGreen) {
        this.maxPatientsGreen = maxPatientsGreen;
    }

    public Integer getMaxPatientsYellow() {
        return maxPatientsYellow;
    }

    public void setMaxPatientsYellow(Integer maxPatientsYellow) {
        this.maxPatientsYellow = maxPatientsYellow;
    }

    public Integer getMaxPatientsRed() {
        return maxPatientsRed;
    }

    public void setMaxPatientsRed(Integer maxPatientsRed) {
        this.maxPatientsRed = maxPatientsRed;
    }

    public Integer getMaxPatientsTotal() {
        return maxPatientsTotal;
    }

    public void setMaxPatientsTotal(Integer maxPatientsTotal) {
        this.maxPatientsTotal = maxPatientsTotal;
    }

    public Boolean getHasDoctor() {
        return hasDoctor;
    }

    public void setHasDoctor(Boolean hasDoctor) {
        this.hasDoctor = hasDoctor;
    }

    public RoomTypeEnm getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeEnm roomType) {
        this.roomType = roomType;
    }
}
