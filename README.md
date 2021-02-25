# [Recalls]
Vanilla warps for Minecraft.

### Versions
Native: 1.16.4  
Supported: 1.14.x-1.16.x

## Warps
### Adding a warp
Every warp is an invincible, invisible armor stand with a specific tag. To create a warp, summon an armor stand:  
`/summon minecraft:armor_stand ~ ~ ~ {NoGravity:1,Invisible:1,Invincible:1}`  
Then, assign it a `waystone#` tag where # is a number. Ie:  
`/tag @e[type=minecraft:armor_stand,limit=1,sort=nearest,distance=..5] add waystone1`  
The command above will give one nearest armor stand the tag 'waystone1'

### Removing a warp
It's as simple as killing the armor stand:  
`/kill @e[type=minecraft:armor_stand,tag=waystone1]`  
The command above will remove all armor stands from the first waystone

#### Note
You can have more than one of the same waystone. If a player attempts a recall to them, they will be teleported to the nearest.

## Teleportation
Players can use `/trigger rc_target set #` (where # is the warp number again) to recall to a specific warp. You can temporarily disable a player's ability to recall setting their `rc_disabled` score to **exactly 1**. Beware, a player has to be in open air (access to sky with no blocks inbetween, even transparent ones) in order to perform a recall. The recall is cancelled if the player takes damage or the sky is blocked within the **delay** time period.

### Admin Configuration

#### Delay
The delay is the time between a successful recall **initiation** to it's **completion** in **ticks**. You can set the value on the player `teleport` in scoreboard `rc_settings` to any **positive** value (`/scoreboard players set teleport rc_settings 140`). This feature defaults to **7 seconds** (140 ticks)

#### Cooldown
This is the delay a player has to wait after a **successful** recall to initiate another one. It defaults to **3 seconds** (60 ticks) and can be set on player `cooldown` in board `rc_settings` (`/scoreboard players set cooldown rc_settings 60)`)

#### RandomTP
Players can teleport to a random location using `/trigger rc_target set -1`. To disable this feature, simply set the `rc_settings` value of a player `random` to **0** (`/scoreboard players set random rc_settings 0`). This feature is **enabled** by default!