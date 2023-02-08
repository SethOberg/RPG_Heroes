package items;

import heroes.HeroAttributes;

public class Armor extends Item {
    private ArmorType armorType;
    private HeroAttributes armorAttributes;

    public Armor() {
        super();
    }

    public Armor(String name, int requiredLevel, EquipmentSlot equipmentSlot, ArmorType armorType, HeroAttributes armorAttributes) {
        super(name, requiredLevel, equipmentSlot);
        this.armorType = armorType;
        this.armorAttributes = armorAttributes;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    public HeroAttributes getArmorAttributes() {
        return armorAttributes;
    }

    public void setArmorAttributes(HeroAttributes armorAttributes) {
        this.armorAttributes = armorAttributes;
    }
}
