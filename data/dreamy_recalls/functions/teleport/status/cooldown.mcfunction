scoreboard players set @s rc_target 0
scoreboard players set @s rc_progress 0

tellraw @s ["",{"text":"[","bold":true,"color":"dark_gray"},{"text":"Recalls","bold":true,"color":"blue"},{"text":"] ","bold":true,"color":"dark_gray"},{"text":"You're still on cooldown!","color":"red"}]