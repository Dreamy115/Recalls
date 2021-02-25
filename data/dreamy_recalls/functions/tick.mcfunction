execute as @a[scores={rc_progress=1..}] run function dreamy_recalls:tick/progress
execute as @a[scores={rc_cooldown=1..}] run scoreboard players remove @s rc_cooldown 1
scoreboard players set @a rc_hit 0