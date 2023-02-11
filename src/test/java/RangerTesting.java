import exceptions.InvalidArmorException;
import exceptions.InvalidWeaponException;
import heroes.HeroAttributes;
import heroes.Ranger;
import items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RangerTesting {

    //add failing test to see if CI works
    @Test
    void testLevel() {
        var ranger = new Ranger("someName");
        assertEquals(1, ranger.getLevel());
    }

    @Test
    void testLevelUp() {
        var ranger = new Ranger("someName");
        ranger.levelUp();
        assertEquals(2, ranger.getLevel());
    }

    @Test
    void rangerCanEquipWeapon() throws InvalidWeaponException {
        var ranger = new Ranger("someName");
        var weapon = new Weapon("someBow", 1, EquipmentSlot.Weapon, 3, WeaponType.Bow);

        ranger.equip(weapon);
        assertNotEquals(null, ranger.getEquipment().get(EquipmentSlot.Weapon));
    }

    @Test
    void weaponDealsDamage() throws InvalidWeaponException {
        var ranger = new Ranger("someName");
        var weapon = new Weapon("someBow", 1, EquipmentSlot.Weapon, 10, WeaponType.Bow);

        ranger.equip(weapon);
        assertNotEquals(1, ranger.damage());
    }

    //Armor
    @Test
    void rangerCannotEquipArmor() {
        var ranger = new Ranger("someName");
        var armorAttributes = new HeroAttributes(2, 2, 1);
        var armor = new Armor("cloth", 1, EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);

        try {
            ranger.equip(armor);
        } catch (InvalidArmorException e) {
        }

        assertEquals(null, ranger.getEquipment().get(EquipmentSlot.Body));
    }

    @Test
    void tooLowLevelToEquipArmor() {
        var ranger = new Ranger("someName");
        var armorAttributes = new HeroAttributes(2, 2, 1);
        var armor = new Armor("mailArmor", 2, EquipmentSlot.Body, ArmorType.Mail, armorAttributes);

        try {
            ranger.equip(armor);
        } catch (InvalidArmorException e) {
        }

        assertEquals(null, ranger.getEquipment().get(EquipmentSlot.Body));
    }

    @Test
    void rangerCanEquipArmor() throws InvalidArmorException {
        var ranger = new Ranger("someName");
        var armorAttributes = new HeroAttributes(1, 1, 3);
        var armor = new Armor("mailArmor", 1, EquipmentSlot.Body, ArmorType.Mail, armorAttributes);

        ranger.equip(armor);
        assertNotEquals(null, ranger.getEquipment().get(EquipmentSlot.Body));
    }
}
