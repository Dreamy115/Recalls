scoreboard players set @s rc_progress 0
scoreboard players set @s rc_target 0
scoreboard players set @s skyCheck -1

tellraw @s ["",{"text":"[","bold":true,"color":"dark_gray"},{"text":"Recalls","bold":true,"color":"blue"},{"text":"] ","bold":true,"color":"dark_gray"},{"text":"Cannot find the waystone number you're looking for. Try a lower number!","color":"red"}]