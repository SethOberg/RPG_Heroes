import exceptions.InvalidArmorException;
import exceptions.InvalidWeaponException;
import heroes.HeroAttributes;
import heroes.Warrior;
import items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestWarrior {

    @Test
    public void testLevel() {
        var warrior = new Warrior("someName");
        assertEquals(1, warrior.getLevel());
    }

    @Test
    public void testLevelUp() {
        var warrior = new Warrior("someName");
        warrior.levelUp();
        assertEquals(2, warrior.getLevel());
    }
    @Test
    public void testWarriorCanEquipWeapon() throws InvalidWeaponException {
        var warrior = new Warrior("someName");
        var weapon = new Weapon("someAxe", 1, EquipmentSlot.Weapon, 3, WeaponType.Axe);

        warrior.equip(weapon);
        assertNotEquals(null, warrior.getEquipment().get(EquipmentSlot.Weapon));
    }

    @Test
    public void testWeaponDealsDamage() throws InvalidWeaponException {
        var warrior = new Warrior("someName");
        var weapon = new Weapon("someAxe", 1, EquipmentSlot.Weapon, 10, WeaponType.Axe);

        warrior.equip(weapon);
        assertNotEquals(1, warrior.damage());
    }

    //Armor
    @Test
    public void testWarriorCannotEquipArmor() {
        var warrior = new Warrior("someName");
        var armorAttributes = new HeroAttributes(2, 2, 1);
        var armor = new Armor("clothArmor", 1, EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);

        try {
            warrior.equip(armor);
        } catch (InvalidArmorException e) {
        }

        assertEquals(null, warrior.getEquipment().get(EquipmentSlot.Body));
    }

    @Test
    public void testTooLowLevelToEquipArmor() {
        var warrior = new Warrior("someName");
        var armorAttributes = new HeroAttributes(2, 2, 1);
        var armor = new Armor("plateArmor", 2, EquipmentSlot.Body, ArmorType.Plate, armorAttributes);

        try {
            warrior.equip(armor);
        } catch (InvalidArmorException e) {
        }

        assertEquals(null, warrior.getEquipment().get(EquipmentSlot.Body));
    }

    @Test
    public void testWarriorCanEquipArmor() throws InvalidArmorException {
        var warrior = new Warrior("someName");
        var armorAttributes = new HeroAttributes(1, 1, 3);
        var armor = new Armor("plateArmor", 1, EquipmentSlot.Body, ArmorType.Plate, armorAttributes);

        warrior.equip(armor);
        assertNotEquals(null, warrior.getEquipment().get(EquipmentSlot.Body));
    }
}
