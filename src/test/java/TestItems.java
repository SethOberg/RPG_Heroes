import heroes.HeroAttributes;
import items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestItems {

    @Test
    public void testChangeArmorType() {
        var attributes = new HeroAttributes(1, 1, 1);
        var armor = new Armor("someArmor", 1, EquipmentSlot.Body, ArmorType.Mail, attributes);
        armor.setArmorType(ArmorType.Cloth);

        assertEquals(ArmorType.Cloth, armor.getArmorType());
    }

    @Test
    public void testChangeArmorAttributes() {
        var attributes = new HeroAttributes(1, 1, 1);
        var armor = new Armor("someArmor", 1, EquipmentSlot.Body, ArmorType.Mail, attributes);
        attributes.setAttributes(3, 3, 3);
        armor.setArmorAttributes(attributes);

        assertTrue(armor.getArmorAttributes().getStrength() == 3
                && armor.getArmorAttributes().getDexterity() == 3
                && armor.getArmorAttributes().getIntelligence() == 3);
    }

    @Test
    public void testCheckArmorAttributesCorrect() {
        var attributes = new HeroAttributes(4, 4, 4);
        var armor = new Armor("someArmor", 1, EquipmentSlot.Body, ArmorType.Mail, attributes);

        assertTrue(armor.getArmorAttributes().getStrength() == 4
                && armor.getArmorAttributes().getDexterity() == 4
                && armor.getArmorAttributes().getIntelligence() == 4);
    }

    @Test
    public void testCheckArmorName() {
        var attributes = new HeroAttributes(4, 4, 4);
        var armor = new Armor("someArmor", 1, EquipmentSlot.Body, ArmorType.Mail, attributes);

        assertTrue(armor.getName().equals("someArmor"));
    }

    @Test
    public void testChangeArmorName() {
        var attributes = new HeroAttributes(4, 4, 4);
        var armor = new Armor("someArmor", 1, EquipmentSlot.Body, ArmorType.Mail, attributes);
        armor.setName("mailArmor");

        assertTrue(armor.getName().equals("mailArmor"));
    }

    @Test
    public void testChangeEquipmentSlot() {
        var attributes = new HeroAttributes(4, 4, 4);
        var armor = new Armor("someArmor", 1, EquipmentSlot.Body, ArmorType.Mail, attributes);
        armor.setEquipmentSlot(EquipmentSlot.Head);

        assertEquals(EquipmentSlot.Head, armor.getEquipmentSlot());
    }

    @Test
    public void testChangeRequiredLevel() {
        var attributes = new HeroAttributes(4, 4, 4);
        var armor = new Armor("someArmor", 1, EquipmentSlot.Body, ArmorType.Mail, attributes);
        armor.setRequiredLevel(5);

        assertEquals(5, armor.getRequiredLevel());
    }

    @Test
    public void testChangeWeaponDamage() {
        var weapon = new Weapon("someHammer", 1, EquipmentSlot.Weapon, 2, WeaponType.Axe);
        weapon.setWeaponDamage(20);
        assertEquals(20, weapon.getWeaponDamage());
    }

    @Test
    public void testChangeWeaponType() {
        var weapon = new Weapon("someHammer", 1, EquipmentSlot.Weapon, 2, WeaponType.Axe);
        weapon.setName("someSword");
        weapon.setWeaponType(WeaponType.Sword);
        assertEquals(WeaponType.Sword, weapon.getWeaponType());
    }

}
