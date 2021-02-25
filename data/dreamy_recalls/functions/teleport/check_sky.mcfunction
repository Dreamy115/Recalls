scoreboard players set @s skyCheck 0
scoreboard players set @s skyCheckB 0
execute as @s at @s positioned ~ ~1 ~ run function dreamy_recalls:teleport/skycast

execute if score @s[scores={rc_progress=..0}] skyCheckB = @s skyCheck if score @s skyCheck matches 0.. unless score @s rc_target matches 0 run function dreamy_recalls:teleport/status/commence