scoreboard objectives add rc_progress dummy 
scoreboard objectives add rc_target trigger {"text":"Recall To"}

scoreboard objectives add skyCheck dummy
scoreboard objectives add skyCheckB dummy

scoreboard objectives add rc_hit minecraft.custom:minecraft.damage_taken
scoreboard objectives add rc_settings dummy
scoreboard objectives add rc_cooldown dummy

scoreboard objectives add rc_help trigger {"text":"Recalls Help"}

scoreboard objectives add rc_disabled dummy {"text":"Recall Disabled"}

execute unless score teleport rc_settings matches 0.. run scoreboard players set teleport rc_settings 140
execute unless score cooldown rc_settings matches 0.. run scoreboard players set cooldown rc_settings 60
execute unless score random rc_settings matches 0.. run scoreboard players set random rc_settings 1