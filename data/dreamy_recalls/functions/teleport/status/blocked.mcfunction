scoreboard players set @s rc_progress 0
scoreboard players set @s rc_target 0
scoreboard players set @s skyCheck -1

tellraw @s ["",{"text":"[","bold":true,"color":"dark_gray"},{"text":"Recalls","bold":true,"color":"blue"},{"text":"] ","bold":true,"color":"dark_gray"},{"text":"Teleport Blocked! You have to be in open air to recall!","color":"red"}]