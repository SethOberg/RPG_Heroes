import exceptions.InvalidArmorException;
import exceptions.InvalidWeaponException;
import heroes.HeroAttributes;
import heroes.Rogue;
import heroes.Warrior;
import items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestWarrior {

    @Test
    public void testLevel() {
        var warrior = new Warrior("someName");
        assertEquals(1, warrior.getLevel());
    }

    @Test
    public void testWarriorBaseStats() {
        var warrior = new Warrior("someName");
        var warriorBaseStats = warrior.getLevelAttributes();

        var warriorBaseStatsOk = warriorBaseStats.getStrength() == 5
                && warriorBaseStats.getDexterity() == 2
                && warriorBaseStats.getIntelligence() == 1 ;

        assertEquals(true, warriorBaseStatsOk);
    }

    @Test
    public void testWarriorStatsLevelUp() {
        var warrior = new Warrior("someName");
        warrior.levelUp();
        var warriorBaseStats = warrior.getLevelAttributes();

        var warriorBaseStatsOk = warriorBaseStats.getStrength() == 8
                && warriorBaseStats.getDexterity() == 4
                && warriorBaseStats.getIntelligence() == 2 ;

        assertEquals(true, warriorBaseStatsOk);
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

        assertDoesNotThrow(() -> warrior.equip(weapon));
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

        assertThrows(InvalidArmorException.class, () -> warrior.equip(armor));
    }

    @Test
    public void testTooLowLevelToEquipArmor() {
        var warrior = new Warrior("someName");
        var armorAttributes = new HeroAttributes(2, 2, 1);
        var armor = new Armor("plateArmor", 2, EquipmentSlot.Body, ArmorType.Plate, armorAttributes);

        assertThrows(InvalidArmorException.class, () -> warrior.equip(armor));
    }

    @Test
    public void testWarriorCanEquipArmor() {
        var warrior = new Warrior("someName");
        var armorAttributes = new HeroAttributes(1, 1, 3);
        var armor = new Armor("plateArmor", 1, EquipmentSlot.Body, ArmorType.Plate, armorAttributes);

        assertDoesNotThrow(() -> warrior.equip(armor));
    }
}
