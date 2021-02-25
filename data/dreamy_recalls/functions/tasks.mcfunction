execute as @a unless score @s rc_progress matches -1.. run function dreamy_recalls:load/newplayer

scoreboard players enable @a[scores={rc_progress=0}] rc_target
scoreboard players enable @a rc_help

execute as @a[scores={rc_progress=0}] unless score @s rc_target matches 0 run function dreamy_recalls:teleport/init

execute as @a[scores={rc_help=1..}] run function dreamy_recalls:cmd/help

schedule function dreamy_recalls:tasks 1s