tellraw @a ["",{"text":"[","bold":true,"color":"dark_gray"},{"text":"Recalls","bold":true,"color":"blue"},{"text":"] ","bold":true,"color":"dark_gray"},{"text":"Loading... v1.2.0","color":"aqua"}]

function dreamy_recalls:load/scoreboards
schedule function dreamy_recalls:tasks 1t

tellraw @a ["",{"text":"[","bold":true,"color":"dark_gray"},{"text":"Recalls","bold":true,"color":"blue"},{"text":"] ","bold":true,"color":"dark_gray"},{"text":"Loaded!","color":"aqua"}]
tellraw @a ["",{"text":"[","bold":true,"color":"dark_gray"},{"text":"Recalls","bold":true,"color":"blue"},{"text":"] ","bold":true,"color":"dark_gray"},{"text":"Usage: ","color":"aqua"},{"text":"/trigger rc_help ","color":"red","clickEvent":{"action":"run_command","value":"/trigger rc_help"}}]
tellraw @a ["",{"text":"[","bold":true,"color":"dark_gray"},{"text":"Recalls","bold":true,"color":"blue"},{"text":"] ","bold":true,"color":"dark_gray"},{"text":">Settings<","bold":true,"color":"green","clickEvent":{"action":"run_command","value":"/function dreamy_recalls:cmd/settings"},"hoverEvent":{"action":"show_text","contents":{"text":"Click to customize!"}}}]
