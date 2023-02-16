# RPG_Heroes

## Continous integration 
Each time a push to the master branch is done, a github action will be run, that builds the project and checks all the results from the junit unit tests. The test results will be shown in a test report and if there are failing unit tests, the build will fail and the failed tests will be shown. 

The continous integration can be seen on the "Actions" tab.

## Techniques used
* Java 
* Maven
* JUnit

## Contributors 
* Seth Öberg

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



