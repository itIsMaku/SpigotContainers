# SpigotContainers
Container system for dividing players into individual instances

## Usage
To put the player in the container, we first need to create the instance of the container itself.
We have two options. We can create a classic container or a container with world restrictions.
```java
Containers.createContainer(Container container);

new Container(String id, List<ContaineredPlayers> players)
new WorldRestrictedContainer(String id, List<ContaineredPlayers> players, List<World> worlds)
```
Now we can find container by id and join player.
```java
Container container = Containers.getContainer(String id);
container.join(Player plyer);
```
Now we have player in the container. The player can now only see players in his container and players from other containers cannot see him and he them. Players that are not in any container are able to see all players in all containers, but they don't.
You can also find player in what container he is.
```java
Optional<ContaineredPlayer> opt = Containers.findPlayerInContainers(Player player);
```
Player also can leave container.
```java
Container container = Containers.getContainer(String id);
Containers.findPlayerInContainers(Player player)
        .ifPresent(containeredPlayer -> containeredPlayer.leave());
```
Container object contains methods for getting players, restricted worlds and ID.

## Dependencies
- [Mommons (Maku's Commons)](https://github.com/itIsMaku/Mommons)
