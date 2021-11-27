package com.axowattle.extraspells.Spells;

import com.axowattle.extraspells.ExtraSpells;
import com.axowattle.extraspells.PassiveAbilities.CloudKillPassiveAbility;
import com.axowattle.extraspells.PassiveAbilities.DoubleJump;
import com.axowattle.extraspells.PassiveAbilities.PassiveAbility;
import com.axowattle.extraspells.PlayerClass;
import com.axowattle.extraspells.Quests.QuestsHandler;
import com.axowattle.extraspells.SpellSettings;
import com.axowattle.extraspells.Spells.ArceaneArcher.*;
import com.axowattle.extraspells.Spells.Cleric.*;
import com.axowattle.extraspells.Spells.Druid.*;
import com.axowattle.extraspells.Spells.Sorcerer.*;
import com.axowattle.extraspells.Spells.Cleric.LaunchSpell;
import com.axowattle.extraspells.Tasks.ManaRegenerate;
import com.axowattle.extraspells.Tasks.SpellCaster;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.*;

public class SpellHandler {
    private static List<PlayerClass> classes = new ArrayList<>();
    private static Integer[] spellSlots = new Integer[]{10,11,12,13,14,15,16,17,19,20,21,22,23,24,25,26};
    private static Integer[] selectedSpellSlots = new Integer[]{46,47,48,49,50,51,52,53};
    private static int spellcaster = 0;
    private static int manaregenerate = 0;
    public static final NamespacedKey wandIdKey = new NamespacedKey(ExtraSpells.getInstance(), "wand-id");
    public static final NamespacedKey manaMaxKey = new NamespacedKey(ExtraSpells.getInstance(), "mana-max");
    public static final NamespacedKey playerNameKey = new NamespacedKey(ExtraSpells.getInstance(), "player-owner");
    private static Map<Vector,Material> treeData = new HashMap<>();
    private static String[] names = new String[]{"Aaliyah","Abby","Abe","Abigail","Abu","Ace","Action","Adalyn","Addie","Addison","Adele","Adeline","Adella","Adler","Admiral","Adrian Monk","Aesop","Agatha","Agent 47","Aiden","Aigis","Aimee","Ajax","Akuma","Al Bundy","Aladdin","Alaska","Alcott","Aldous","Alexander","Alfonso","Alfred","Alice","Alistair","Allie","Alpha","Alton","Alucard","Amaterasu","Ambassador","Ambrosia","Amelia","Amelie","Amethyst","Amy Rose","Anastasia","Anastasio","Andrew","Angel","Angelica","Angus","Angus MacGyver","Anna","Annie","Anxious","Apache","Apollo","Apple","Aqua","Aragorn","Arbiter","Archer","Archibald","Archie Bunker","Argos","Aria","Arianna","Ariel","Arthur","Arturo","Arwen","Arya","Arya Stark","Ash","Asher","Ashford","Ashley","Ashton","Asia","Aspen","Astaroth","Atari","Athena","Atlas","Atom","Atta","Atticus","Attila","Aubrey","Audrey","Augie","Augustus","Aurora","Austin","Autumn","Ava","Avalanche","Avery","Avril","Ayane","Baby","Bad Boy","Bagheera","Bailey","Bald Bull","Baloo","Balrog","Balthier","Bambi","Bandit","Banjo","Banshee","Banzai","Baraka","Barclay","Barney","Barney Fife","Barney Rubble","Barney Stinson","Bart Simpson","Bartley","Bashful","Basil Fawlty","Batman","Baxter","Bayleaf","Baylor","Beagle","Beamer","Bean","Beans","Bear","Beast","Beau","Beckham","Beethoven","Bella","Belle","Bender","Benjamin","Benji","Benny","Bentley","Berkeley","Berlin","Bernard","Berry","Bess","Bessie","Beta","Betty Boop","Betty White","Beyonce","Bianca","Big Ben","Big Boss","Big Boy","Big Daddy","Big Foot","Bilbo","Bing","Binkie","Birdie","Birdo","Biscuit","Bit","Bitsy","Bitty","Bizzy","BlackBerry","Blackie","Blake","Blanco","Blanka","Blaze","Bling","Blondie","Blue","Blues","Bo","Boba Fett","Bobo","Body","Bogey","Bohemian","Bolt","Bond","Bongo","Bonkers","Bonnie","Boo","Boomer","Boone","Boston Beans","Bowen","Bowser","Brady","Brandy","Brayden","Breeze","Brently","Brian Griffin","Briar","Brie","Britton","Brock","Brody","Brooklyn","Brownie","Bruno","Brutus","Bubba","Buddy","Buffy","Buffy Summers","Bug","Bugs Bunny","Bullet","Bullseye","Bungee","Bunker","Burhbank","Buster","Buttercup","Butterfly","Buzz","Byron","Byte","C3PO","Cabernet","Caden","Cadillac","Cage","Cagney","Cairo","Calamity Jane","Caleb","Cali","Callaway","Callie","Callisto","Calvin","Cameron","Camilla","Cammy","Camry","Captain","Captain Falcon","Captain Olimar","Captain Qwark","Carlton","Carlton Banks","Carlyle","Carmen","Carmen Sandiego","Carol Peletier","Carter","Casey","Cash","Cassie Cage","Castle","Catcher","Celia","Cersei","Chalupa","Champ","Chance","Chandler","Chandler Bing","Chanel","Channing","Chantilly","Chaos","Charlie","Charlotte","Chase","Chaucer","Chauncey","Checkers","Chekov","Cherry","Chester","Chew Chew","Chewie","Chewy","Chico","Chili","China","Chip","Chloe","Chocolate","Christian","Christie","Christopher Robin","Chubbs","Chun-Li","Church","Churchill","CiCi","Cid","Cinder","Cisco","Clabourne","Clancy","Clara","Clarus","Claude","Cleo","Cleveland","Clifford","Clipper","Cloe","Cloud","Cobalt","Cobra","Cobra Bubbles","Coco","Coco Chanel","Cocoa","Cody","Cogsworth","Cole","Coleman Hawkins","Colette","Colonel","Columbo","Comet","Commander Shepard","Connor","Cookie","Cooper","Copper","Cormac","Cosmo Kramer","Crash Bandicoot","Creeper","Cricket","Crissie","Cromwell","Cruise","Crumble","Crush","Cuddles","Curly","Cyber","Cypher","Cyrano","Cyrax","Dab","Daenerys Targaryen","Daffy Duck","Dagger","Daisy","Daisy Duck","Dakota","Dale","Dallas","Dana Scully","Dancer","Danger","Daniel","Dante","Darcy","Dare","Darth Vader","Darwin","Daryl Dixon","Dash","Data","David","Dawson","Daxter","Dazzle","Dazzler","Dean Winchester","Deanna","Demi","Denali","Derek Morgan","Derek Venturi","Desdemona","Devil","Devin","Dewey","Dexter","Dexter Morgan","Diamond","Dickens","Diddy Kong","Diesel","Digger","Diode","Diva","Dixie","Doc","Doctor Eggman","Doctor Nefarious","Doctor Neo Cortex","Dollop","Don Draper","Donald","Donald Duck","Donkey Kong","Doodle","Doonesbury","Dora","Dory","Dot","Doug","Dozer","Dr. Bosconovitch","Dr. Gregory House","Dr. John Watson","Dr. Wily","Dracula","Drop","Drunkard","Dudley","Duffy","Duke","Dumbo","Dwight Schrute","Dylan","E. Honda","Eaglehurst Gillette","Earthworm Jim","Ebony","Echo","Eddard Stark","Eden","Edith Bunker","Edmund","Edsel","Eeyore","Elaine Benes","Eldridge","Elena","Elf","Eli","Eliana","Elijah","Elizabeth","Ella","Ellie","Eloise","Elsa","Elvis","Emily","Emma","Emmy","Emu","Ender","Enid","Enrage","Epona","Eric Cartman","Ermac","Ernie","Esme","Esmeralda","Ethan","Ethel Mertz","Eve","Evelyn","Evie","Evil Knevil","Ewok","Fa la","Faithful","Falco Lombardi","Faline","Fancy","Fandango","Fang","Faulkner","Fauna","Fawful","Fei Long","Feisty","Feller","Ferdie","Ferris","Fifi","Figaro","Finn","Fiver","Flash","Fletcher","Flora","Flounder","Fluffy","Flynn","Fonzie","Forester","Forrest","Fox McCloud","Fox Mulder","Foxy","Francesca","Frank Costanza","Frank West","Frankie","Frasier Crane","Fred Flintstone","Frederick","Frisco","Frisky","Frodo","Frou-Frou","Fry","Fulgore","Gabby","Gabriel","Gaia","Gamma","Gandalf","Gandhi","Garbo","Gaston","Gator","Gatsby","Gen","George","George Costanza","George Jefferson","Georgia","Geppetto","Ghenghis Khan","Ghost","Giant","Gibson","Gidget","Giga","Gigi","Gilligan","Ginger","Giselle","Gizmo","Gladiator","Glass Joe","Glen","Glenn Quagmire","Glitter","Godot","Goldie","Goldwin","Goody","Goofy","Google","Goomba","Goro","Gouken","Grace","Gracie","Gray Fox","Grayson","Great Tiger","Grits","Gucci","Guile","Gumball","Gunner","Gus","Gustavo Fring","Guy","Gwen","Gypsy","Hailey","Hamilton","Hamm","Han Solo","Handsome Jack","Hank","Hank Hill","Hannah","Happy","Harley","Harmon","Harper","Hasty","Hattie","Haunter","Hawkeye Pierce","Hayes","Hazel","Heart","Heathcliff Huxtable","Hector","Hecuba","Heidi","Henry","Her","Herman Munster","Hermione","Hi Jinx","Hidalgo","Hillary","Him","Hint","Hitomi","Hobbit","Hobbs","Holly","Homer Simpson","Honey","Honey Child","Hook","Hope","Hubert","Huey","Hug","Hugo","Hurley"," Reyes","Hula Girl","Hulk","Hunter","Hurricane","Hyde","Iago","Ibuki","Igor","Ike","Inca","India","Indo","Indy","Inez","Irena","Isaac","Isabella","Isadora","Isaiah","Itty","Itty Bitty","Ivan","Ivana","Ivory","Ivy","Izzy","J.R. Ewing","Jabba","Jack","Jack Bauer","Jackson","Jacob","Jade","Jafar","Jago","Jaime Lannister","Jak","Jake","Jambalaya","James","James ","Sawyer","Ford","James T. Kirk","Jamie","Jane","Jango","Jaq","Jasmine","Jasper","Java","Jax","Jayce","Jayden","Jazz","Jean-Luc Picard","Jeannie","Jelly","Jellybean","Jenna","Jerry Seinfeld","Jersey","Jesse Pinkman","Jet","Jethro","Jewel","Jiggs","Jim Rockford","Jiminy","Jinx","Jock","Joey","Joey Tribbiani","John","John Locke","Johnny Cage","JoJo","Joker","Jon Snow","Josie","Joss","Jot","Joy","Jules","Julian","Julianna","Juliette (Juliet)","Jumbo","Jumper","Juno","Juri","Jynx","Kaa","Kabal","Kain","Kali","Kane","Kano","Kara","Karma","Kasey","Kasper","Kasumi","Katie","Kayden (Kaeden)","Kaylee","Keane","Keaton","Kelly","Ken","Ken Masters","Kendrick","Kenny McCormick","Kenshi","Kenzi","Kermit","Kernal","Kevin Arnold","Khaleesi","Khan","Kiara","Killer","Kimmy","King","King Dedede","King Hippo","King Kole","King Timahoe","King Tut","Kipling","Kirby","Kiss","Kisses","Kit","Kitana","Kiva","Klingon","Knuckles","Kobe","Kocoum","Kodiak","Kola","Kona","Kong","Kung Lao","Kyle Broflovski","Kyra","Lacey","Lacie","Lacy","Laddie Boy","Lady","Lady Rover","Landon","Lara","Lara Kroft","Largo","Lark","Lars Alexandersson","Laser","Lassie","Later","Latte","Lauren","Layla","Lazarus","Le Beau","Leah","Legolas","Leia","Leifang","Lemon","Leo","Leonard Hofstadter","Leonard McCoy","Leprechaun","Leroy Jethro Gibbs","Leslie Knope","Lester","Levi","Lex Luthor","Lexa","Lexi","Lexie","Liam","Liberty","Licorice","Lightning","Lili","Lillian","Lilly","Lilo","Lily","Limerick","Lincoln","Link","Linux","Linx","Liquid Snake","Lisa","Lisa Simpson","Little Beagle","Liu Kang","Liz Lemon","Lodi","Logan","Loki","Lola","Lollipop","London","Loretta","Louie","Lt. Frank Drebin","Lucas","Lucky","Lucy","Lucy Ricardo","Luigi","Luka","Luke","Luke Skywalker","Lulu","Lumiere","Lumpy","Luna","Lupo","Lurch","Lyndon","Mac","Macbeth","Mackenzie","Macy","Maddie","Madelyn","Madison","Madonna","Mafia","Maggie","Magic","Majestic","Major","Mako","Makoto","Mal","Manchu","Marge Simpson","Maria","Marie","Mario","Mariya","Marlboro","Marley","Martin Crane","Mary","Mary Richards","Mason","Master Chief","Matisse","Matthew","Matzoball","Maui","Max","Max Factor","Max Payne","Maxi","Maxwell","Maya","Meatball","Meeko","Mega Man","Meggie","Mellow","Melody","Mercedes","Merlin","Merryweather","Meta Knight","Mia","Michael","Michael Scott","Mickey","Midna","Midnight","Mike","Mila","Mileena","Millie","Milo","Mimi","Mini","Minnie","Minuet","Miss Beazley","Miss Bianca","Miss Havisham","Missy","Mister Ed","Misty","Mite","Moana","Mobster","Mocha","Mochi","Mojo","Moki","Molly","Mona","Monet","Monica Geller","Monster","Moondoggie","Moose","Mopsey","Morgan","Mork","Morpheus","Morticia Addams","Morty","Motaro","Mother Brain","Mouse","Mowgli","Moxie","Mr. Bean","Mr. Beefy","Mr. Burns","Mr. Rogers","Muffin","Muhammad","Mulan","Munchkin","Muppet","Murphy","Nacho","Nadia","Nala","Nana","Nani","Napoleon","Nash","Natalie","Nathan","Navi","Nellie","Nelly","Nemo","Neo","Newbie","Newman","Nibbles","Nicholas","Nightwolf","Nikki","Niko Bellic","Niles","Niles Crane","Ninja","Nit Pik","Noah","Noob Saibot","Nora","Norm Peterson","Norman","Nova","Obi-Wan","Octavia","Old Boy","Olive","Oliver","Olivia","Olivia Benson","Ollie","Olympia","Omar Little","Ono","Onyx","Opal","Ophelia","Oprah","Oprah Winfree","Oracle","Oregon","Oreo","Oro","Oscar","Othello","Otis","Ounce","Owen","Pac-Man","Palo Alto","Papillon","Paris","Pascal","Pasha","Pat","Patrick","Paul Pry","Paxton","Peanut","Pebbles","Penny","Pepper","Percy","Perdita","Perla","Persephone","Pete","Peter Griffin","Peter Pan","Peyton","Pez","Phillop","Phoebe","Picasso","Piglet","Pinocchio","Piper","Pippi","Pipsqueak","Pixel","Pixie","Pluto","Pong","Pongo","Pooch","Pooh","Pookie","Popcorn","Popeye","Portia","President","Preston","Prince","Princess","Princess Daisy","Princess Peach","Princeton","Proto Man","Puddles","Pumpkin","Pusinka","Quan Chi","Quanika","Quantum","Quark","Quasar","Quby","Queen","Queenie","Quenby","Quimby","Quincy","Quinn","Quip","Quyen","R2D2","Rachel Green","Rafa","Raiden","Rain","Rajah","Ram","Ranger","Rascal","Rayman","Red","Red Forman","Redford","Redmond","Regal","Reptile","Rex","Rick Grimes","Ricky Ricardo","Ridley","Rikimaru","Riley","Ripley","Rita","Rival","Rob Roy","Robin Hood","Rocco","Rocky","Rolls","Romeo","Ron Swanson","Rory","Rosalina","Rosarita","Roscoe","Rose","Rose Nylund","Rosie","Ross Geller","Rouge the Bat","Rowdy","Roxie","Roxy","Royce","Ruby","Ruby Rough","Rudy","Rufus","Rupert Giles","Rusty","Rx","Ryan","Rynn","Ryu","Saber","Sabine","Sabrina","Sadie","Saffron","Sagat","Sailor Boy","Sally","Sam","Sam Malone","Sam Winchester","Sammy","Samson","Samurai","Sandy","Sansa","Sapphire","Sarah","Sarah Connor","Sarah Kerrigan","Sasha","Sassy","Satan","Scarlet","Scarlett","Scooby-Doo","Scooter","Scorpion","Scotty","Scout","Scrooge","Scuttle","Searcher","Sebastian","Sektor","Selina","Shade","Shadow","Shadow the Hedgehog","Shang Tsung","Shannon","Shao Kahn","Shark","Sheeva","Shelby","Sheldon Cooper","Shell","Shere Khan","Sheriff","Sheriff Andy Taylor","Sherlock Holmes","Shiloh","Siberia","Sid","Sidi","Sierra","Silver","Simba","Sinclair","Sindel","Siri","Skip","Skitty","Sky","Skye","Skywalker","Slater","Sleepy","Smidge","Smoke","Snip","Solid Snake","Sonic the Hedgehog","Sonnie","Sonya Blade","Sophia","Sophia Petrillo","Sophie","Spark","Sparky","Spencer","Spike","Spirit","Spock","SpongeBob SquarePants","Spot","Stallone","Stan Marsh","Stannis Barkratheon","Stedman","Stefan","Stella","Steve Urkel","Stewie Griffin","titch","Streaker","Stryker","Sub-Zero","Sugar","Suki","Sulton","Sulu","Sunny","Suzy","Sweet Tooth","Sweetlips","Sydney","Symphony","T. Hawk","Tails","Taki","Tamago","Tani","Tanin","Tank","Tardis","Taster","Tease","Teddy","Teeny","Terk","Tesla","Tessa","Theodora","Thomas Magnum","Thor","Thumper","Thurman Murman","Tiana","Tiara","Tiberius","Tickles","Tidus","Tiffany","Tiger Lilly","Tigger","Timo","Timothy","Tina","Tinker Bell","Tiny","Tiny Tim","Tipler","Tipsi","Tito","Toad","Toby","Tod","Todd","Token","Tony Soprano","Tori","Tramp","Trinity","Tripp","Trixie","Tron","Trooper","Tucker","Turk","Tyrande Whisperwind","Tyrion Lannister","Tyson","Tywin Lannister","Uber","Udele","Uhura","Ulrika","Ulyana","Ulysses","Uma","Upanova","Urbana","Ursula","Uru","Vader","Valentine","Vega","Vegas","Velocity","Veto","Vicky","Victoria","Violet","Vivian","Vulcan","Wags","Wallace","Walter White","Waluigi","Wario","Warlock","Washington","Weejie","Whiskey","White Tips","Wicket","Widget","Wiggles","Wile E. Coyote","Will Smith","Willa","William","Willow","Willow Rosenberg","Winks","Winnie","Winston","Winter","Wolf","Woody","Woz","Wyatt","X-Ray","Xander","Xena","Xoana","Xoco","Xolli","Xolo","Xonny","Xora","Xory","Xyla","Xyrus","Yappy","Yasmine","Yeller","Yoda","Yosemite Sam","Yoshi","Yuki","Yukon","Zeda","Zeke","Zelda","Zerp","Zeus","Ziggy","Zip","Zippy","Zoe","Zooey","Mikmik","Lovely",ChatColor.RED + "You don't have enough mana to do this spell"};
    private static Random random = new Random();


    public static NamespacedKey getWandIdKey() {
        return wandIdKey;
    }

    public static NamespacedKey getManaMaxKey() {
        return manaMaxKey;
    }

    public static String getRandomName(){
        return names[random.nextInt(names.length)];
    }

    public static void initialize(){
        classes.add(new PlayerClass("cleric",500,createItem(Material.HONEY_BOTTLE,"&6Cleric"),Arrays.asList(new Healing(), new FireBoltSpell(),new FireBoltSurroundSpell(),new WitherThornsSpell(),new LightningSpell(),new VampireBloodSpell(),new LaunchSpell(),new ShutterSpell()),Arrays.asList()));
        classes.add(new PlayerClass("sorcerer",500,createItem(Material.DIAMOND_SWORD,"&9Sorcerer"), Arrays.asList(new MagicMissileSpell(),new TeleportSpell(),new InvisibilitySpell(),new ExplosionSpell(),new FreezeRaySpell(),new AcidSplashSpell(),new AnvilRainSpell(),new AIRemovalSpell(),new ReachOverride(),new FireballSpell()),Arrays.asList()));
        classes.add(new PlayerClass("druid",500,createItem(Material.OAK_SAPLING,"&6Druid"),Arrays.asList(new BuffSpell(),new TreeSpell(),new ObsidianWallSpell(),new SpikesSpell(),new WolfSpell(),new DurabilitySpell(),new HealthAverageSpell(),new IdentityTheftSpell(),new KnockbackAttackSpell(),new SpikesBallSpell(),new CloudKillSpell()),Arrays.asList(new DoubleJump(),new CloudKillPassiveAbility())));
        classes.add(new PlayerClass("arcane_archer",500,createItem(Material.BOW,"&6Arcane Archer"),Arrays.asList(new ArrowDamageSpell(),new ArrowProtectionSpell(),new ArrowStealHealthSpell(),new DamageReduceSpell(),new ArrowSwitchSpell()),Arrays.asList()));

        PlayerData.playersDataConfig.setup();
        PlayerData.playersDataConfig.get().options().copyDefaults(true);
        PlayerData.playersDataConfig.save();
        for (String player : PlayerData.playersDataConfig.get().getKeys(false)){
            ConfigurationSection conf = PlayerData.playersDataConfig.get().getConfigurationSection(player);
            PlayerData.playersData.add(new PlayerData(conf));
        }

        spellcaster = Bukkit.getScheduler().scheduleSyncRepeatingTask(ExtraSpells.getInstance(),new SpellCaster(),1,1);
        manaregenerate = Bukkit.getScheduler().scheduleSyncRepeatingTask(ExtraSpells.getInstance(),new ManaRegenerate(),2L,2L);
        storeData();
        QuestsHandler.initialize();
    }

    public static void disable(){
        for(PlayerData playerData : PlayerData.playersData){
            playerData.save(PlayerData.playersDataConfig.get());
        }
        PlayerData.playersDataConfig.save();
        Bukkit.getScheduler().cancelTask(spellcaster);
        Bukkit.getScheduler().cancelTask(manaregenerate);

        for (Player player : Bukkit.getOnlinePlayers()){
            for (PassiveAbility passiveAbility : PlayerData.getPlayerData(player).playerClass.getAvailablePassiveAbilities(player)) {
                passiveAbility.onDisable(player);
            }
        }
    }

    public static void storeData() {
        treeData.put(new Vector(0,0,0),Material.OAK_LOG);
        treeData.put(new Vector(0,1,0),Material.OAK_LOG);
        treeData.put(new Vector(0,2,0),Material.OAK_LOG);
        treeData.put(new Vector(0,3,0),Material.OAK_LOG);

        treeData.put(new Vector(0,4,0),Material.OAK_LEAVES);
        treeData.put(new Vector(0,3,1),Material.OAK_LEAVES);
        treeData.put(new Vector(0,3,-1),Material.OAK_LEAVES);
        treeData.put(new Vector(1,3,0),Material.OAK_LEAVES);
        treeData.put(new Vector(-1,3,0),Material.OAK_LEAVES);
        treeData.put(new Vector(0,2,1),Material.OAK_LEAVES);
        treeData.put(new Vector(0,2,-1),Material.OAK_LEAVES);
        treeData.put(new Vector(1,2,0),Material.OAK_LEAVES);
        treeData.put(new Vector(-1,2,0),Material.OAK_LEAVES);
    }

    public static boolean isWand(ItemStack item) {
        if (item == null)
            return false;
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null)
            return false;
        PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
        if (dataContainer.get(SpellHandler.getWandIdKey(), PersistentDataType.INTEGER) == null)
            return false;
        return dataContainer.get(SpellHandler.getWandIdKey(), PersistentDataType.INTEGER).intValue() >= 0;
    }

    public static boolean isSlotAllowed(int slot){
        return Arrays.asList(spellSlots).contains(slot) | Arrays.asList(selectedSpellSlots).contains(slot);
    }

    public static int getAdvancementAmount(String value){
        try{
            File file = new File("D:\\servers\\BigServer\\plugins\\adp.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.split(",")[0].equals(value)){
                    return Integer.parseInt(data.split(",")[1]);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static Map<String,Integer> getAdvancementPoints(){
        Map<String,Integer> out = new HashMap<>();
        try{
            File file = new File("D:\\servers\\BigServer\\plugins\\adp.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                out.put(data.split(",")[0],Integer.parseInt(data.split(",")[1]));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return out;
    }

    public static Integer[] getSelectedSpellSlots() {
        return selectedSpellSlots;
    }

    public static PlayerClass getPlayerClass(String name) {

        for(PlayerClass playerClass : SpellHandler.classes){
            if (playerClass.getName().equalsIgnoreCase(name)){
                return playerClass;
            }
        }

        return null;
    }

    public static void addNewPlayer(Player player){
        PlayerData.playersData.add(new PlayerData(player));
        int delay = Bukkit.getScheduler().scheduleSyncDelayedTask(ExtraSpells.getInstance(), new Runnable() {
            @Override
            public void run() {
                openClassSelectMenu(player);
            }
        },20L);
    }

    public static Map<Vector, Material> getTreeData() {
        return treeData;
    }

    public static void openSpellsSelectMenu(Player player){

        PlayerData playerData = PlayerData.getPlayerData(player);

        playerData.openInventory(1);

        Inventory inventory = Bukkit.createInventory(player,54,"Select your spells");

        inventory.setItem(0,createItem(Material.AMETHYST_SHARD,"&8Current points: &b" + playerData.point));


        if (playerData.point > SpellSettings.levelRequirement - 1){
            inventory.setItem(27,createItem(Material.DIAMOND,"&8Upgrade to level: &b" + (playerData.level+1)));
        }

        inventory.setItem(37,createItem(Material.OAK_SIGN,"&2L&8-&2L&8-&2L"));
        inventory.setItem(38,createItem(Material.OAK_SIGN,"&2R&8-&2L&8-&2L"));
        inventory.setItem(39,createItem(Material.OAK_SIGN,"&2L&8-&2R&8-&2L"));
        inventory.setItem(40,createItem(Material.OAK_SIGN,"&2R&8-&2R&8-&2L"));
        inventory.setItem(41,createItem(Material.OAK_SIGN,"&2L&8-&2L&8-&2R"));
        inventory.setItem(42,createItem(Material.OAK_SIGN,"&2R&8-&2L&8-&2R"));
        inventory.setItem(43,createItem(Material.OAK_SIGN,"&2L&8-&2R&8-&2R"));
        inventory.setItem(44,createItem(Material.OAK_SIGN,"&2R&8-&2R&8-&2R"));

        for (int i = 0;i<inventory.getSize();i++){
            if (inventory.getItem(i) == null & !Arrays.asList(spellSlots).contains(i) & !Arrays.asList(selectedSpellSlots).contains(i)){
                inventory.setItem(i,createItem(Material.GRAY_STAINED_GLASS_PANE," "));
            }
        }

        List<Spell> availableSpells = getAvailableSpells(player);
        for (int i = 0;i< availableSpells.size();i++){
            int slot = playerData.getSpellSlot(i);
            if (slot != -1){

                inventory.setItem(selectedSpellSlots[slot],availableSpells.get(i).getMenuItem(player));
            }else {
                inventory.addItem(availableSpells.get(i).getMenuItem(player));
            }
        }

        player.openInventory(inventory);
        player.updateInventory();
    }

    public static List<Spell> getAvailableSpells(Player player){
        PlayerData playerData = PlayerData.getPlayerData(player);
        List<Spell> allSpells = new ArrayList<>();
        if (playerData.level == 0) return allSpells;
        for (int i =1;i<playerData.level + 1;i++){
            for(Spell spell : playerData.playerClass.getSpells()){
                if (spell.getLevelRequired() == i){
                    allSpells.add(spell);
                }
            }
        }
        return allSpells;
    }

    public static int getSpellCount(Player player,Spell spell){
        if (spell == null)
            return -1;
        int i =0;
        List<Spell> availableSpells = getAvailableSpells(player);
        for(Spell currentSpell : availableSpells){
            if(currentSpell.getName().equals(spell.getName())){
                break;
            }
            i++;
        }
        return i;
    }

    public static void openClassSelectMenu(Player player){
        PlayerData.getPlayerData(player).selectedSpells = new Integer[]{-1,-1,-1,-1,-1,-1,-1,0};

        PlayerData.getPlayerData(player).openInventory(2);
        Inventory inventory = Bukkit.createInventory(player,9,"Select your class");
        for(PlayerClass playerClass : classes){
            inventory.addItem(playerClass.getItem());
        }

        player.openInventory(inventory);
        player.updateInventory();
    }

    public static ItemStack createItem(Material material, String name){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',name));
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DYE,ItemFlag.HIDE_DYE,ItemFlag.HIDE_POTION_EFFECTS);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static PlayerClass getPlayerClass(ItemStack item){
        for (PlayerClass playerClass : classes){
            if (playerClass.getItem().isSimilar(item)){
                return playerClass;
            }
        }
        return null;
    }

    public static Spell getSpell(PlayerClass playerClass,ItemStack item){
        if (item == null)
            return null;
        for (Spell spell : playerClass.getSpells()){
            if (spell.getName().equalsIgnoreCase(item.getItemMeta().getDisplayName())){
                return spell;
            }
        }
        return null;
    }
}

