scoreboard players set @s rc_progress -1

execute as @s if score @s rc_cooldown matches 1.. run function dreamy_recalls:teleport/status/cooldown
execute as @s run function dreamy_recalls:teleport/check_sky

execute if score @s rc_target matches 1 unless entity @e[tag=waystone1,type=minecraft:armor_stand] run function dreamy_recalls:teleport/status/missing
execute if score @s rc_target matches 2 unless entity @e[tag=waystone2,type=minecraft:armor_stand] run function dreamy_recalls:teleport/status/missing
execute if score @s rc_target matches 3 unless entity @e[tag=waystone3,type=minecraft:armor_stand] run function dreamy_recalls:teleport/status/missing
execute if score @s rc_target matches 4 unless entity @e[tag=waystone4,type=minecraft:armor_stand] run function dreamy_recalls:teleport/status/missing
execute if score @s rc_target matches 5 unless entity @e[tag=waystone5,type=minecraft:armor_stand] run function dreamy_recalls:teleport/status/missing
execute if score @s rc_target matches 6 unless entity @e[tag=waystone6,type=minecraft:armor_stand] run function dreamy_recalls:teleport/status/missing
execute if score @s rc_target matches 7 unless entity @e[tag=waystone7,type=minecraft:armor_stand] run function dreamy_recalls:teleport/status/missing
execute if score @s rc_target matches 8 unless entity @e[tag=waystone8,type=minecraft:armor_stand] run function dreamy_recalls:teleport/status/missing
execute if score @s rc_target matches 9 unless entity @e[tag=waystone9,type=minecraft:armor_stand] run function dreamy_recalls:teleport/status/missing
execute if score @s rc_target matches 10 unless entity @e[tag=waystone10,type=minecraft:armor_stand] run function dreamy_recalls:teleport/status/missing

execute if score @s rc_target matches 11.. run function dreamy_recalls:teleport/status/missing

execute if score @s rc_target matches -1 unless score random rc_settings matches 1 run function dreamy_recalls:teleport/status/random_disabled