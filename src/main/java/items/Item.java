package items;

public abstract class Item {
    private String name;
    private int requiredLevel;

    private EquipmentSlot equipmentSlot;

    public Item(String name, int requiredLevel, EquipmentSlot equipmentSlot) {
        this.name = name;
        this.requiredLevel = requiredLevel;
        this.equipmentSlot = equipmentSlot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }

    public void setEquipmentSlot(EquipmentSlot equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
    }
}
