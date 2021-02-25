tellraw @s ["",{"text":"[","bold":true,"color":"dark_gray"},{"text":"Recalls","bold":true,"color":"blue"},{"text":"] ","bold":true,"color":"dark_gray"},{"text":"Help","color":"aqua"}]

tellraw @s ["",{"text":"/trigger rc_target set ","color":"red"},{"text":"## ","bold":true,"color":"dark_red"},{"text":"- Teleports you to a waystone. Put a number in the red space.","color":"aqua"},{"text":"\n"},{"text":"If you insert ","color":"aqua"},{"text":"-1 ","color":"dark_red"},{"text":"you will be teleported to a random location (If admins enabled it)","color":"aqua"},{"text":"\n"},{"text":"While recalling, you can call the same command with ","color":"aqua"},{"text":"0 ","color":"dark_red"},{"text":"to cancel.","color":"aqua"}]
tellraw @s ["",{"text":"[","bold":true,"color":"dark_gray"},{"text":"Recalls","bold":true,"color":"blue"},{"text":"] ","bold":true,"color":"dark_gray"},{"text":"----","color":"aqua"}]

scoreboard players reset @s rc_help