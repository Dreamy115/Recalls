scoreboard players add @s skyCheck 1

execute if block ~ ~ ~ minecraft:air positioned ~ ~1 ~ unless score @s skyCheckB = @s skyCheck run function dreamy_recalls:teleport/skycast
execute unless block ~ ~ ~ minecraft:air run function dreamy_recalls:teleport/status/blocked

scoreboard players operation @s skyCheckB = @s skyCheck