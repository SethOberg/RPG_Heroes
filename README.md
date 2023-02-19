# RPG_Heroes

## Continous integration 
Each time a push to the master branch is done, a github action will be run, that builds the project and checks all the results from the JUnit unit tests. The test results will be published in a test report and if there are failing unit tests, the build will fail. 

The continous integration can be seen on the [Actions tab](https://github.com/SethOberg/RPG_Heroes/actions).

## Techniques used
* Java 
* Maven
* JUnit

## Contributors 
* Seth Öberg

## UML class diagrams 

### Heroes class diagram
(some attributes and methods were skipped to make diagrams more compact)
![heroes_class_diagram](https://user-images.githubusercontent.com/48513637/219433094-e6fb6f6b-20ae-4bb5-a80b-88c5888093a1.png)

### Items class diagram 
(some attributes and methods were skipped to make diagrams more compact)
![items_class_diagram](https://user-images.githubusercontent.com/48513637/219433128-d55dd2c4-d1a1-41e8-975c-eed1b6714be8.png)

## UML Sequence diagrams

### Create Mage object
![Create_Mage_Diagram](https://user-images.githubusercontent.com/48513637/219955293-8dee72ce-077a-4037-a8ed-9449d2333053.jpg)


### Create Armor object
![Armor_Create_Diagram](https://user-images.githubusercontent.com/48513637/219615433-edfe64b3-2654-4881-827f-7e7de787b30a.jpg)


## Project
RPGHeroes is a project consisting of different hero types, they all inherit from the base class hero, the 4 different types are: 
* Mage
* Ranger
* Rogue
* Warrior

They all have stats consisting of: 
* Strength
* Dexterity
* Intelligence 

Each hero-type has different base stats and also gain different amounts in each stat when levelling up depending on the hero type. For example mages gain more intelligence, while Rangers gain more dexterity. 

Each hero can equip weapons and armor, each hero has different types of weapons and armor they can equip depending on their type. 

The Weapon types are:
* Axe 
* Sword 
* Hammer 
* Bow 
* Dagger 

The armor types are: 
* Cloth
* Leather 
* Mail
* Plate 

Each hero has 4 different slots they can equip items on, these are: 
* Legs
* Body
* Head 
* Weapon
