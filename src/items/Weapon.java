package items;

public class Weapon extends Item {
    private int weaponDamage;
    private WeaponType weaponType;

    public Weapon() {
        super();
    }

    public Weapon(String name, int requiredLevel, EquipmentSlot equipmentSlot, int weaponDamage, WeaponType weaponType) {
        super(name, requiredLevel, equipmentSlot);
        this.weaponDamage = weaponDamage;
        this.weaponType = weaponType;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }
}
