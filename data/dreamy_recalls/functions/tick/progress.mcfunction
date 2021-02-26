execute unless score @s rc_disabled matches 1 if score @s rc_progress <= teleport rc_settings at @s run function dreamy_recalls:teleport/tick

execute unless score @s rc_disabled matches 1 if score @s rc_progress > teleport rc_settings at @s run function dreamy_recalls:teleport/status/finalized
execute unless score @s rc_target matches 1.. if score @s rc_cooldown matches ..0 at @s run function dreamy_recalls:teleport/status/cancelled
execute unless score @s rc_target matches ..-1 if score @s rc_cooldown matches ..0 at @s run function dreamy_recalls:teleport/status/cancelled
execute if score @s rc_disabled matches 1 at @s run function dreamy_recalls:teleport/status/disabled

execute if score @s rc_progress matches 2 at @s run playsound minecraft:entity.ender_dragon.growl player @a ~ ~ ~ 2 1