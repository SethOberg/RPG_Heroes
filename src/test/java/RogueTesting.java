import exceptions.InvalidArmorException;
import exceptions.InvalidWeaponException;
import heroes.HeroAttributes;
import heroes.Rogue;
import items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RogueTesting {

    @Test
    void testLevel() {
        var rogue = new Rogue("someName");
        assertEquals(1, rogue.getLevel());
    }

    @Test
    void testLevelUp() {
        var rogue = new Rogue("someName");
        rogue.levelUp();
        assertEquals(2, rogue.getLevel());
    }
    @Test
    void rogueCanEquipWeapon() throws InvalidWeaponException {
        var rogue = new Rogue("someName");
        var weapon = new Weapon("someSword", 1, EquipmentSlot.Weapon, 3, WeaponType.Sword);

        rogue.equip(weapon);
        assertNotEquals(null, rogue.getEquipment().get(EquipmentSlot.Weapon));
    }

    @Test
    void weaponDealsDamage() throws InvalidWeaponException {
        var rogue = new Rogue("someName");
        var weapon = new Weapon("someSword", 1, EquipmentSlot.Weapon, 10, WeaponType.Sword);

        rogue.equip(weapon);
        assertNotEquals(1, rogue.damage());
    }

    //Armor
    @Test
    void rogueCannotEquipArmor() {
        var rogue = new Rogue("someName");
        var armorAttributes = new HeroAttributes(2, 2, 1);
        var armor = new Armor("clothArmor", 1, EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);

        try {
            rogue.equip(armor);
        } catch (InvalidArmorException e) {
        }

        assertEquals(null, rogue.getEquipment().get(EquipmentSlot.Body));
    }

    @Test
    void tooLowLevelToEquipArmor() {
        var rogue = new Rogue("someName");
        var armorAttributes = new HeroAttributes(2, 2, 1);
        var armor = new Armor("mailArmor", 2, EquipmentSlot.Body, ArmorType.Mail, armorAttributes);

        try {
            rogue.equip(armor);
        } catch (InvalidArmorException e) {
        }

        assertEquals(null, rogue.getEquipment().get(EquipmentSlot.Body));
    }

    @Test
    void rogueCanEquipArmor() throws InvalidArmorException {
        var rogue = new Rogue("someName");
        var armorAttributes = new HeroAttributes(1, 1, 3);
        var armor = new Armor("mailArmor", 1, EquipmentSlot.Body, ArmorType.Mail, armorAttributes);

        rogue.equip(armor);
        assertNotEquals(null, rogue.getEquipment().get(EquipmentSlot.Body));
    }
}
