package com.common.skill;

import java.util.ArrayList;

public class Skill {
	public String name = "", PrimaryStat,// the stat that's based on how fast
											// you learn a skill
			Parentskill,// the skill you have to be decent in to learn this
						// current skill
			Explanation = "", TeacherID = "",// Teacher names
			Restricted,// What races are restricted ex:
						// Restricted=" Badger Rat ";
			desc = "", language = "";
	public int costincrease = 0, Magic = 0;

	public static Skill[] allSkills() {
		int amount = 0;
		ArrayList<Skill> skills = new ArrayList<Skill>();
		String[] classes = new String[] { "Acting", "Archery", "Bash", "bashaa",
				"bevish", "BlackMagic", "Blacksmithing", "BlueMagic", "Brewing",
				"Climbing", "Compounding", "Cooking", "CripplingTouch",
				"CrystalMagic", "Daggers", "Detection", "Disarm", "Disguise",
				"Dodging", "farran", "fauxens", "Filching", "Flinging",
				"ForestCraft", "GrayMagic", "GreenMagic", "haarr", "Hammers",
				"Herbalism", "Hiding", "Impersonation", "Imprinting", "IronFur",
				"IronPaw", "IronWill", "Jewelry", "Kicking", "Leaping", "Medicine",
				"Mining", "NervePinch", "OrangeMagic", "Parrying", "PawArts",
				"PawFighting", "PawMastery", "Perseverence", "pinian",
				"PressurePoints", "PurpleMagic", "Quills", "ratses", "RedMagic",
				"ResistSpells", "Resourcefulness", "rodens", "Running", "scenanes",
				"scranga", "Scrounging", "ShieldSkill", "Skill", "Skullduggery",
				"Spines", "squirens", "Staff", "Swords", "TailWhip", "Tenacity",
				"Thumping", "Tracking", "TwoHandedSword", "Viciousness", "wesel",
				"WhiteMagic", "WoodArmory", "Woodworking", "Writing", "YellowMagic" };
		for (String skillName : classes) {
			try {
				Skill skill = Skill.getSkill(skillName);
				if (skill.PrimaryStat != null && skill.PrimaryStat.length() > 2) {
					skills.add(skill);
					amount++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Object[] list = skills.toArray();
		Skill[] toReturn = new Skill[amount];
		for (int x = 0; x < amount; x++) {
			Skill s = (Skill) list[x];
			toReturn[x] = s;
		}
		return toReturn;
	}

	public static Skill getSkill(String n) {
		try {
			n = n.replaceAll(" ", "");
			ClassLoader cl = Skill.class.getClassLoader();
			Class<?> classDefinition = Class.forName("com.common.skill." + n, false,
					cl);
			Object object = classDefinition.newInstance();
			return (Skill) object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Skill() {
	}
}

class Blacksmithing extends Skill {
	public Blacksmithing() {
		name = "Blacksmithing";
		TeacherID = "Minshten";
		PrimaryStat = "Strength";
		desc = "Blacksmithing is the ability to forge various weapons, tools, and utensils from metal using a hammer and anvil.";
		Explanation = "Blacksmithing can only be done next to an anvil. Having a hammer equipped helps you forge items. To forge items, click on an anvil and a screen will popup and you can make a selection of what you want to forge.";
	}
}

class Scrounging extends Skill {
	public Scrounging() {
		name = "Scrounging";
		PrimaryStat = "Luck";
		TeacherID = "Talina Salys Korin Sally";
		desc = "Scrounging is the ability to find food and materials for trades such as Forest Crafting and Woodworking, among other things.";
		Explanation = "Scrounging allows for you to search for food and other items in the trees and grass. F3 is the default macro to scrounge for food and F4 is the default amcro to scrounge for materials. The more trees and grass are near you, the higher chance you have of finding things.";
	}
}

class Tracking extends Skill {
	public Tracking() {
		name = "Tracking";
		PrimaryStat = "Awareness";
		TeacherID = "Talina ";
		desc = "The ability to sense critters in your surrounding area and the direction they are in.";
		;
	}
}

class Meditation extends Skill {
	public Meditation() {
		name = "Meditation";
		PrimaryStat = "Intelligence;Awareness";
		desc = "The ability to recover as if you were sleeping, while being awake.";
	}
}

class Woodworking extends Skill {
	public Woodworking() {
		name = "Woodworking";
		PrimaryStat = "Nimbleness";
		desc = "Woodworking is a refined version of Forest Crafting that allows one to create items of even greater use from wooden and/or other natural materials.";
		TeacherID = "Elian";
		// Parentskill="Forest Craft";
		Explanation = "Woodworking allows you to create furniture and other useful items out of wood.";
	}
}

class WoodArmory extends Skill {
	public WoodArmory() {
		name = "Wood Armory";
		PrimaryStat = "Nimbleness";
		TeacherID = "Elian";
		Parentskill = "Woodworking";
		desc = "Wood Armory is a version of wood working that allows a critter to create weapons and armors, by collecting logs and than cutting them into planks.";
		Explanation = "Wood armory is for making armor and weapons out of wood.";
	}
}

class Archery extends Skill {
	public Archery() {
		name = "Archery";
		PrimaryStat = "Nimbleness";
		desc = "Allows you to shoot arrows at victims at a rapid but weak rate.";
		TeacherID = "Elian Jerih";
		Explanation = "This skill depends on the accuracy of your arrows shot with a bow. This skill is automatically used in combat.";
	}
}

class Hiding extends Skill {
	public Hiding() {
		name = "Hiding";
		PrimaryStat = "Craft";
		desc = "Allows you to stay invisible for a small amount of time.";
		TeacherID = "Talina Yeris";
		Explanation = "Hiding allows you to sneak past harmful players without beeing seen.";
	}
}

class Disguise extends Skill {
	public Disguise() {
		name = "Disguise";
		PrimaryStat = "Craft";
		desc = "Disguise allows its user to take on the appearance of another species of animal other than their own.";
		TeacherID = "Talina";
		Explanation = "Disguise lets you turn yourself into a different critter and fool other critters into thinking you really are that different critter.";
	}
}

class Impersonation extends Skill {
	public Impersonation() {
		name = "Impersonation";
		PrimaryStat = "Craft";
		Parentskill = "Disguise";
		desc = "Impersonation allows its user to take a disguise one step further and take on the name of another animal until they either shed the disguise, take on a new one, or are revealed.";
		TeacherID = "Talina Yeris";
	}
}

class Filching extends Skill {
	public Filching() {
		name = "Filching";
		PrimaryStat = "Craft";
		TeacherID = "Talina Yeris";
		desc = "Filching is the ability to 'pick pockets' so to speak, by attempting to take an item or stack of items from its victim's inventory. Marginal success and extreme failure will alert the victim and all nearby to your actions.";
	}
}

class CrystalMagic extends Skill {
	public CrystalMagic() {
		// costincrease=1;
		Magic = 1;
		name = "Crystal Magic";
		PrimaryStat = "Magic";
		desc = "While not required to learn the other magic types, crystal magic will allow you to use it without damaging or destroying the crystal used to cast said magic.";
		TeacherID = "Manin";
	}
}

class BlueMagic extends Skill {
	public BlueMagic() {
		costincrease = 1;
		Magic = 1;
		name = "Blue Magic";
		PrimaryStat = "Magic";
		desc = "Blue Magic is the magic of communication and truth. They will be able to send messages on the wind to a select recipient and temporarily bless themselves and others with the ability to see only the truth.";
		TeacherID = "Manin";
	}
}

class WhiteMagic extends Skill {
	public WhiteMagic() {
		name = "White Magic";
		costincrease = 1;
		Magic = 1;
		PrimaryStat = "Magic";
		desc = "White Magic is the magic of healing and protection. Such spells include basic healing, transfers of positive physical status to another, and the removal of hexes associated with its opposite color.";
		TeacherID = "Manin";
	}
}

class BlackMagic extends Skill {
	BlackMagic() {
		name = "Black Magic";
		costincrease = 1;
		Magic = 1;
		PrimaryStat = "Magic";
		desc = "Black Magic is that of curses and hexes, such as temporarily inflicting a victim with lethargy, or jinxing their successes to a degree. While the curses will last for a fair amount of time on their own, they can be removed by one knowledgeable and prepared in White Magic.";
		TeacherID = "Manin";
	}
}

class OrangeMagic extends Skill {
	OrangeMagic() {
		name = "Orange Magic";
		PrimaryStat = "Magic";
		Magic = 1;
		costincrease = 1;
		desc = "Orange Magic controls the forces of nature, fire, wind, lightning, et cetera. Mild and slow weather control combined with the calling of, well, fire and lightning upon foes. (and, if wielded carelessly, friends or even the user!)";
		TeacherID = "Manin";
	}
}

class YellowMagic extends Skill {
	YellowMagic() {
		name = "Yellow Magic";
		PrimaryStat = "Magic";
		Magic = 1;
		costincrease = 1;
		desc = "The body is the domain of yellow magic. Yellow mages may satiate themselves and others with just a press of the yellow crystal, and even boost or slow metabolism in the same manner.";
		TeacherID = "Manin";
	}
}

class GrayMagic extends Skill {
	GrayMagic() {
		name = "Gray Magic";
		PrimaryStat = "Magic";
		Magic = 1;
		costincrease = 1;
		desc = "Gray Magic covers the entirety of magic itself. They may use 'blank' (quartz, normally) crystals to convert into batteries for spare mental energy, and may draw from them at any time, at which they may either use or give it to another, in addition to temporarily boosting and sealing the magic of themselves and others.";
		TeacherID = "Manin";
	}
}

class RedMagic extends Skill {
	RedMagic() {
		name = "Red Magic";
		Magic = 1;
		PrimaryStat = "Magic";
		costincrease = 1;
		desc = "Red Magic covers weapons and the ability to use them, in a way. They may enhance a weapon and alter its weight to suit it better for its intended user. Inexperienced mages, however, risk damaging or outright destroying the weapon as a poor crystal mage would their crystal.";
		TeacherID = "Manin";
	}
}

class GreenMagic extends Skill {
	GreenMagic() {
		name = "Green Magic";
		Magic = 1;
		PrimaryStat = "Magic";
		costincrease = 1;
		desc = "Green Magery covers simply that of enhancement. They may take common tools and turn them into either a boost for everyday life or a deadly iron rock to be tossed at victims.";
		TeacherID = "Manin";
	}
}

class PurpleMagic extends Skill {
	PurpleMagic() {
		name = "Purple Magic";
		PrimaryStat = "Magic";
		Magic = 1;
		costincrease = 1;
		desc = "Purple Magic covers movement, and teleporting. It is also used for summoning critters to your location.";
		TeacherID = "Manin";
	}
}

class ResistSpells extends Skill {
	ResistSpells() {
		name = "Resist Spells";
		PrimaryStat = "Magic";
		desc = "Resist Spells covers the ability to avoid entirely the effects of any harmful magic, including outright damage and subtle hexes.";
		TeacherID = "Manin Thwick";
	}
}

class Quills extends Skill {
	Quills() {
		name = "Quills";
		PrimaryStat = "Fortitude";
		desc = "Quills is the ability for a hedgehog to use their spines defensively. A clumsy or even skilled attacker will feel a backlash of sorts depending on the skills of both attacker and defender.";
		Restricted = "Badger Mouse Rabbit Rat Fox Squirrel Shrew Weasel Beaver Ferret Skunk";
		TeacherID = "Thwick";
	}
}

class Spines extends Skill {
	Spines() {
		PrimaryStat = "Prowess";
		name = "Spines";
		Restricted = "Badger Mouse Rabbit Rat Fox Squirrel Shrew Weasel Beaver Ferret Skunk";
		desc = "Spines allows its user to offensively turn around and leap backward toward a foe in an attempt to stick their victim with one or more of their spines.";
		TeacherID = "Thwick";
	}
}

class Dodging extends Skill {
	Dodging() {
		name = "Dodging";
		PrimaryStat = "Luck";
		desc = "The ability to dodge allows a critter to capitalize on the good fortune of not being where a blow is going to land by moving closer to such a position.";
		TeacherID = "Salys";
	}
}

class Tenacity extends Skill {
	Tenacity() {
		PrimaryStat = "Fortitute";
		desc = "Tenacious critters, especially the ones very much so, are often the ones finding themselves shrugging off a blow that would otherwise lay them flat, and continue fighting the good (or bad) fight.";
		TeacherID = "Marian";
		name = "Tenacity";
	}
}

class Viciousness extends Skill {
	Viciousness() {
		PrimaryStat = "Prowess";
		desc = "Viciousness is a rather cruel skill that can often leave its victims with more than just a few scrapes and bruises. The owner will often inflict a larger amount of serious wounds, which will hinder growth, or, in the case of a medical condition, coninue to bleed until they are fully healed, naturally or otherwise.";
		TeacherID = "Marian Ishten";
		name = "Viciousness";
	}
}

class Parrying extends Skill {
	Parrying() {
		PrimaryStat = "Prowess";
		desc = "If a shield fails or isn't present, use your weapon. Parrying allows the usage of the critter's weapon to try and block an attack. A clumsy, though successful parry will damage the weapon, however! One's bare paws could also be used, but a clumsy parry still results in some damage.";
		TeacherID = "Marian Methira Ishten";
		name = "Parrying";
	}
}

class Herbalism extends Skill {
	Herbalism() {
		PrimaryStat = "Intelligence";
		desc = "Herbalism is the ability to gather herbs from the wilderness, or, if looking for a specific herb, certain plants. This works in a way similar to scrounging, but one really needs to know what they're looking for.";
		TeacherID = "Lance Talina Korin";
		name = "Herbalism";
	}
}

class Cooking extends Skill {
	Cooking() {
		PrimaryStat = "Craft";
		desc = "Cooking will increase how much food will prevent hunger.";
		TeacherID = "Lance Korin";
		name = "Cooking";
	}
}

class Medicine extends Skill {
	Medicine() {
		PrimaryStat = "Intelligence";
		desc = "Medicine is the use of herbs or herb compounds to apply beneficial effects to the one that said herb/compound is being applied to.";
		Parentskill = "Herbalism";
		TeacherID = "Lance Talina Korin";
		name = "Medicine";
	}
}

class Brewing extends Skill {
	Brewing() {
		PrimaryStat = "Intelligence";
		desc = "The ability to brew drinks from various usually-food substances into a held container. This may be combined with medicine or skullduggery to mix in an herb, positive or negative depending on the other skill used, as an alternate method of applying medicine.";
		TeacherID = "Lance Korin";
		name = "Brewing";
	}
}

class Flinging extends Skill {
	Flinging() {
		name = "Flinging";
		PrimaryStat = "Luck";
		desc = "Flinging is the art of hurling small objects, such as nuts or pebbles (or, with the aid of a green mage, ironnuts) at a victim. One skilled at this art may have a somewhat easy time knocking out an opponent at range.";
		TeacherID = "Salys";
	}
}

class Swords extends Skill {
	Swords() {
		name = "Swords";
		PrimaryStat = "Prowess";
		desc = "Simply put, the ability to use a sword meant for use in a single paw efficiently and with less risk to said weapon. (As well as oneself)";
		TeacherID = "Marian Ishten";
	}
}

class TwoHandedSword extends Skill {
	TwoHandedSword() {
		name = "Two-Handed Swords";
		PrimaryStat = "Prowess";
		desc = "One's prowess with bulky two-handed weapons.";
		TeacherID = "Marian Minshten Ishten";
	}
}

class Staff extends Skill {
	Staff() {
		name = "Staff";
		PrimaryStat = "Prowess";
		desc = "The ability to use a staff reasonably well.";
		TeacherID = "Marian Manin Ishten";
	}
}

class Hammers extends Skill {
	Hammers() {
		name = "Hammers";
		PrimaryStat = "Prowess";
		desc = "The ability to swing a hammer, blacksmith's or otherwise, efficiently.";
		TeacherID = "Marian Minshten Ishten";
	}
}

class Daggers extends Skill {
	Daggers() {
		name = "Daggers";
		PrimaryStat = "Prowess";
		desc = "The weapon of choice for the smaller or weaker critters. While not the absolute best for parrying, they can be fairly efficient in sticking a foe somewhat annoyingly and repeatedly.";
		TeacherID = "Marian Salys Ishten";
	}
}

class Imprinting extends Skill {
	Imprinting() {
		name = "Imprinting";
		PrimaryStat = "Craft";
		desc = "Imprinting involved fusing a gem (read: not crystal) with a weapon, thus increasing its strength, or tampering with in in other, similar ways.";
		TeacherID = "Minshten Manin";
	}
}

class Skullduggery extends Skill {
	Skullduggery() {
		name = "Skullduggery";
		PrimaryStat = "Craft";
		Parentskill = "Herbalism";
		desc = "Skullduggery is the ability to maliciously use poisonous herbs against victims.";
		TeacherID = "Talina Yeris";
	}
}

class Acting extends Skill {
	Acting() {
		name = "Acting";
	}
}

class ForestCraft extends Skill {
	ForestCraft() {
		name = "Forest Craft";
		PrimaryStat = "Craft;Nimbleness";
		desc = "Forest Craft is the ability to use some of the otherwise useless materials in the woods and grasslands to make useful objects such as paper, pens, blankets, and the like.";
		TeacherID = "Elian Korin";
	}
}

class IronWill extends Skill {
	IronWill() {
		name = "Iron Will";
		PrimaryStat = "Fortitude";
		desc = "Iron Will, simply put, is an increase in a critter's ability to withstand punishment.";
		TeacherID = "Thwick Isaac";
	}
}

class Perseverence extends Skill {
	Perseverence() {
		name = "Perseverence";
		PrimaryStat = "Fortitute";
		desc = "Perseverence, the willpower to keep at whatever it is you're doing. Those with this skill an increase to their maximum stamina, allowing for continued physical activity, combat or otherwise.";
		TeacherID = "Thwick Marian";
	}
}

class Resourcefulness extends Skill {
	Resourcefulness() {
		name = "Resourcefulness";
		PrimaryStat = "Craft";
		desc = "The ability to gather one's mind for the task at hand. A boost equal to the skill in one's mind points.";
		TeacherID = "Talina Yeris";
	}
}

class PressurePoints extends Skill {
	PressurePoints() {
		name = "Pressure Points";
		PrimaryStat = "Awareness";
		desc = "Pressure Points is a branch from the mid-level Paw Arts that allows one to soothe or harm another through the knowledge of various pressure points.";
		Parentskill = "Paw Arts";
		TeacherID = "Ardamu";
	}
}

class scenanes extends Skill {
	scenanes() {
		name = "Scenanes";
		PrimaryStat = "Intelligence";
		desc = "Lets you understand the language of the Skunks.";
		TeacherID = "Yeris Lance";
		language = "Skunk";
	}
}

class farran extends Skill {
	farran() {
		name = "Farran";
		PrimaryStat = "Intelligence";
		desc = "Lets you understand the language of the Ferrets.";
		TeacherID = "Jerih Lance";
		language = "Ferret";
	}
}

class rodens extends Skill {
	rodens() {
		name = "Rodens";
		PrimaryStat = "Intelligence";
		desc = "Lets you understand the language of the Mice.";
		TeacherID = "Lance";
		language = "Mouse";
	}
}

class haarr extends Skill {
	haarr() {
		name = "Haarr";
		PrimaryStat = "Intelligence";
		desc = "Lets you understand the language of the Rabbits.";
		TeacherID = "Trem Lance";
		language = "Rabbit";
	}
}

class pinian extends Skill {
	pinian() {
		name = "Pinian";
		PrimaryStat = "Intelligence";
		desc = "Lets you understand the language of the Hedgehogs.";
		TeacherID = "Lance Thwick";
		language = "Hedgehog";
	}
}

class ratses extends Skill {
	ratses() {
		name = "Ratses";
		PrimaryStat = "Intelligence";
		desc = "Lets you understand the language of the Rats.";
		TeacherID = "Lance Marian";
		language = "Rat";
	}
}

class fauxens extends Skill {
	fauxens() {
		name = "Fauxens";
		PrimaryStat = "Intelligence";
		desc = "Lets you understand the language of the Foxes.";
		TeacherID = "Lance Talina";
		language = "Fox";
	}
}

class bashaa extends Skill {
	bashaa() {
		name = "Bashaa";
		TeacherID = "Lance Minshten";
		PrimaryStat = "Intelligence";
		desc = "Lets you understand the language of the Badgers.";
		language = "Badger";
	}
}

class scranga extends Skill {
	scranga() {
		name = "Scranga";
		TeacherID = "Lance Korin Salys";
		PrimaryStat = "Intelligence";
		desc = "Lets you understand the language of the Shrews.";
		language = "Shrew";
	}
}

class squirens extends Skill {
	squirens() {
		name = "Squirens";
		TeacherID = "Lance Elian";
		PrimaryStat = "Intelligence";
		desc = "Lets you understand the language of the Squirrels.";
		language = "Squirrel";
	}
}

class bevish extends Skill {
	bevish() {
		name = "Bevish";
		TeacherID = "Lance Isaac";
		PrimaryStat = "Intelligence";
		desc = "Lets you understand the language of the Beavers.";
		language = "Beaver";
	}
}

class wesel extends Skill {
	wesel() {
		name = "Wesel";
		TeacherID = "Lance Ishten";
		language = "Weasel";
		desc = "Lets you understand the language of the Weasels.";
		PrimaryStat = "Intelligence";
	}
}

class Disarm extends Skill {
	Disarm() {
		name = "Disarm";
		desc = "A quick strike to the solar plexus that will frequently open up one or both of the victim's paws, causing them to drop whatever it is they're holding. However, there may be certain measures to prevent such an attack.";
		PrimaryStat = "Prowess";
	}
}

class CripplingTouch extends Skill {
	CripplingTouch() {
		name = "Crippling Touch";
		PrimaryStat = "Awareness";
		desc = "With a precision strike, one may slow an opponent's movement down temporarily by 'roughly tapping' a somewhat important nerve.";
		Parentskill = "Pressure Points";
		TeacherID = "Ardamu";
	}
}

class Kicking extends Skill {
	Kicking() {
		name = "Kicking";
		PrimaryStat = "Prowess";
		desc = "With one swift thrust of the user's hindlegs, the user can bash an opponent, damaging them and sometimes sending them sprawling.";
		TeacherID = "Methira Ishten";
	}
}

class Bash extends Skill {
	Bash() {
		name = "Bash";
		PrimaryStat = "Strength";
		desc = " A powerful blow from either one's head or clenched fist(s) that will sometimes knock on opponent over, keeping them from moving for a little.";
		TeacherID = "Minshten";
	}
}

class NervePinch extends Skill {
	NervePinch() {
		PrimaryStat = "Awareness";
		name = "Nerve Pinch";
		Parentskill = "Crippling Touch";
		desc = "Along the same lines as Crippling Touch, this particular pressure point skill will put an opponent to sleep for a short time, after a short time.";
		TeacherID = "Ardamu";
	}
}

class Leaping extends Skill {
	Leaping() {
		name = "Leaping";
		PrimaryStat = "Nimbleness";
		desc = "The talent to sometimes leap forward a step and make you run a lot faster than you normaly would.";
		// TeacherID="Talina";
	}
}

class Mining extends Skill {
	Mining() {
		name = "Mining";
		PrimaryStat = "Intelligence";
		desc = "The talent to search through rocks for any specific mineral is what mining is all about. The presence of more rocks will provide semi-noticable increases in results.";
		TeacherID = "Minshten Salys";
	}
}

class ShieldSkill extends Skill {
	ShieldSkill() {
		name = "Shield Skill";
		desc = "Simply put, one's ability to defensively use a shield.";
		TeacherID = "Marian Ishten";
		PrimaryStat = "Prowess";
	}
}

class Writing extends Skill {
	Writing() {
		name = "Writing";
		PrimaryStat = "Intelligence";
		desc = "The literate ability of a creature. This skill is checked when both reading AND writing a scroll, book, or whatever. Meaning, those that lack this skill are otherwise illiterate.";
		TeacherID = "Lance Korin";
	}
}

class Running extends Skill {
	Running() {
		PrimaryStat = "Fortitude";
		name = "Running";
		desc = "The ability to increase one's speed, without causing any physical or mental drain. This is checked with each step while running, and if successful, no drain will be applied.";
		TeacherID = "Thwick Isaac";
	}
}

class Compounding extends Skill {
	Compounding() {
		name = "Compounding";
		PrimaryStat = "Intelligence";
		Parentskill = "Herbalism";
		desc = "With the knowledge of Compounding, one is able to carefully grind any herb into a more potent essence, which can then be applied directly or mixed into (cooked) food or drink.";
		TeacherID = "Talina Lance";
	}
}

class Jewelry extends Skill {
	Jewelry() {
		name = "Jewelry";
		PrimaryStat = "Nimbleness";
		desc = "The ability to affix jewels to an otherwise unadorned ring. Depending on the jewel and material of the ring, this can have varied bonuses granted to those that wear the product.";
		TeacherID = "Elian Jerih";
	}
}

class Detection extends Skill {
	Detection() {
		name = "Detection";
		PrimaryStat = "Awareness";
		desc = "Detection is the ability to catch those sneaking around or hiding, or those in disguise/impersonating another and expose them for what they truly are.";
		TeacherID = "Trem Talina";
	}
}

class Thumping extends Skill {
	Thumping() {
		name = "Thumping";
		PrimaryStat = "Awareness";
		TeacherID = "Trem Marian";
		desc = "A method of communication in which one taps one's paws or tail against the ground in a code-like pattern, in which all others with the skill can receive the message, or, if desired, just the intended recipient. (Specified after the message.)";
	}
}

class TailWhip extends Skill {
	TailWhip() {
		name = "Tail Whip";
		PrimaryStat = "Prowess";
		desc = "A combat skill that allows its owner to swing their tail at an opponent, damaging and possibly tripping them in the process. Badgers and Rabbits, having the improper type of tails, are unable to do this. Foxes will have some difficulty using this.";
		Restricted = "Rabbit Badger Hedgehog";
	}
}

class PawFighting extends Skill {
	PawFighting() {
		name = "Paw Fighting";
		PrimaryStat = "Prowess";
		desc = "One's ability to fight without a weapon in either paw. This may be further enhanced by Paw Arts.";
		TeacherID = "Methira";
	}
}

class PawArts extends Skill {
	PawArts() {
		name = "Paw Arts";
		PrimaryStat = "Prowess";
		desc = "Paw Arts improves one's ability to actually land a blow even moreso than the Paw Fighting skill itself, while slightly improving damage.";
		Parentskill = "Paw Fighting";
		TeacherID = "Ardamu";
	}
}

class PawMastery extends Skill {
	PawMastery() {
		name = "Paw Mastery";
		PrimaryStat = "Prowess";
		desc = "The highest level of basic paw arts without branching out into any other schools, requiring quite the dedication to the the arts itself.";
		Parentskill = "Paw Arts";
		TeacherID = "Methiren";
	}
}

class IronFur extends Skill {
	IronFur() {
		name = "Iron Fur";
		PrimaryStat = "Fortitude";
		desc = "Iron Fur allows the Paw Master to concentrate some sort of energy in such a manner that their fur alone can damage an unskilled user's weapon.";
		Parentskill = "Paw Mastery";
		TeacherID = "Methiren";
	}
}

class IronPaw extends Skill {
	IronPaw() {
		name = "Iron Paw";
		PrimaryStat = "Strength";
		desc = "Iron Paw works along the same lines as iron fur, except that it turns the wielder's paw into a deadly weapon in its own right, capable of shattering any defenses and inflicting quite serious wounds.";
		Parentskill = "Paw Mastery";
		TeacherID = "Methiren";
	}
}

class Climbing extends Skill {
	Climbing() {
		name = "Climbing";
		PrimaryStat = "Nimbleness";
		desc = "Your ability to climb without falling or hurting yourself.";
		TeacherID = "Elian";
	}
}