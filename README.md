# SpigotContainers
Container system for dividing players into individual instances
## Usage
To put a player into a container, we first need to create an instance of the container itself.
We have two options. We can create a classic container or a container with world restrictions.
```java
Containers.createContainer(Container container);

new Container(String id, List<ContaineredPlayers> players)
new WorldRestrictedContainer(String id, List<ContaineredPlayers> players, List<World> worlds)
```
We can then look up the container by its ID and move the player into it:
```java
Container container = Containers.getContainer(String id);
container.join(Player plyer);
```
The player can now see only the players that are in the same container. Players that are not in any container are able to see all players in all containers.
You can also find in which container a certain player currently is:
```java
Optional<ContaineredPlayer> opt = Containers.findPlayerInContainers(Player player);
```
Player can also leave a container.
```java
Container container = Containers.getContainer(String id);
Containers.findPlayerInContainers(Player player)
        .ifPresent(containeredPlayer -> containeredPlayer.leave());
```
Container object contains methods for getting players, restricted worlds and ID.

## Dependencies
- [Mommons (Maku's Commons)](https://github.com/itIsMaku/Mommons)

## Add to your project
### Maven

Repository
```xml
<repository>
     <id>itismaku</id>
     <url>https://repo.repsy.io/mvn/itismaku/github/</url>
</repository>
```
Dependency
```xml
<dependency>
    <groupId>cz.maku</groupId>
    <artifactId>SpigotContainers</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
### Gradle
Repository
```groovy
repositories {
    maven { 
        url 'https://repo.repsy.io/mvn/itismaku/github/' 
    }
}
```
Dependency
```groovy
dependencies {
    implementation 'cz.maku:SpigotContainers:1.0-SNAPSHOT'
}
```