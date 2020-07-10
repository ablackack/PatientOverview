package space.ablackack.PatientOverview.enums;

public enum RoomTypeEnm {
    PERMANENT("permanent building"),
    TENT("tent");

    private final String displayValue;

    RoomTypeEnm(String displayValue) {
        this.displayValue = displayValue;
    }

    public static RoomTypeEnm fromString(String text) {
        for (RoomTypeEnm room : RoomTypeEnm.values()) {
            if (room.getDisplayValue().equalsIgnoreCase(text)) {
                return room;
            }
        }
        return null;
    }

    public static RoomTypeEnm fromValueString(String string) {
        if (string != null) {
            try {
                return RoomTypeEnm.valueOf(string.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getAsString() {
        return this.toString();
    }

    public String getDisplayValue() {
        return this.displayValue;
    }
}
