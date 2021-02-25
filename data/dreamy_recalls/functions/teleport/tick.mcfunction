scoreboard players add @s rc_progress 1
function dreamy_recalls:teleport/check_sky

function dreamy_recalls:teleport/effects/teleporting

execute if score @s rc_hit matches 1.. run function dreamy_recalls:teleport/status/cancelled