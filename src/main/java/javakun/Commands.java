
package javakun;

import net.minecraft.event.*;
import net.minecraft.command.*;
import net.minecraft.util.*;
import java.util.*;

public class Commands implements ICommand
{
    private static final String name = "memorycleaner";
    private ClickEvent.Action command;
    private HoverEvent.Action Show_text;
    
    public Commands() {
        this.command = ClickEvent.Action.RUN_COMMAND;
        this.Show_text = HoverEvent.Action.SHOW_TEXT;
    }
    
    public String getCommandName() {
        return "memorycleaner";
    }
    
    public String getCommandUsage(final ICommandSender sender) {
        return "/memorycleaner help";
    }
    
    public void processCommand(final ICommandSender sender, final String[] args) {
        switch (args.length) {
            case 0: {
                this.sendHelpMessage(sender);
                break;
            }
            case 1: {
                if (args[0].equalsIgnoreCase("help")) {
                    String cmd = "/memorycleaner help";
                    cmd = "/memorycleaner clean";
                    sender.addChatMessage(new ChatComponentText(cmd).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD).setChatHoverEvent(new HoverEvent(this.Show_text, (IChatComponent)new ChatComponentText("Click for clear your memory"))).setChatClickEvent(new ClickEvent(this.command, cmd))));
                    cmd = "/memorycleaner delay [time in seconds]";
                    sender.addChatMessage(new ChatComponentText(cmd).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD).setChatHoverEvent(new HoverEvent(this.Show_text, (IChatComponent)new ChatComponentText("Set the delay timer to clear, 0 to not automatically clean\nNot specify the time delay to get the time currently delay setted"))).setChatClickEvent(new ClickEvent(this.command, cmd))));
                    break;
                }
                if (args[0].equalsIgnoreCase("clean")) {
                    Cleaner.clear();
                    sender.addChatMessage(new ChatComponentText("Memory cleaned").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.BLUE).setChatHoverEvent(new HoverEvent(this.Show_text, (IChatComponent)new ChatComponentText("Free memory: " + Cleaner.getFreeMemory() + "mb of " + Cleaner.getMaxMemory() + "| Used: " + Cleaner.getUsedMemory() + "mb")))));
                    break;
                }
                if (args[0].equalsIgnoreCase("delay")) {
                    sender.addChatMessage(new ChatComponentText(MemoryCleaner.delay + " seconds delay").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.BLUE)));
                    break;
                }
                this.sendHelpMessage(sender);
                break;
            }
            case 2: {
                if (args[0].equalsIgnoreCase("delay")) {
                    try {
                        final long time = Long.valueOf(args[1]);
                        if (time < 1L) {
                            MemoryCleaner.modOn = false;
                            sender.addChatMessage(new ChatComponentText("Mod disabled.").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.BLUE)));
                        }
                        else {
                            MemoryCleaner.modOn = true;
                            sender.addChatMessage(new ChatComponentText("Delay time setted to " + time + " seconds.").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.BLUE)));
                        }
                        MemoryCleaner.delay = time;
                        MemoryCleaner.configs.syncConfigs();
                    }
                    catch (NumberFormatException e) {
                        sender.addChatMessage(new ChatComponentText("You need to use a number.").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                    }
                    break;
                }
                this.sendHelpMessage(sender);
                break;
            }
            case 4: {
                if (args[0].equalsIgnoreCase("delay") && args[1].equalsIgnoreCase("[time") && args[2].equalsIgnoreCase("in") && args[3].equalsIgnoreCase("seconds]")) {
                    sender.addChatMessage(new ChatComponentText(MemoryCleaner.delay + " seconds delay").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.BLUE)));
                    break;
                }
                break;
            }
            default: {
                this.sendHelpMessage(sender);
                break;
            }
        }
    }
    
    private void sendHelpMessage(final ICommandSender sender) {
        sender.addChatMessage(new ChatComponentText("/memorycleaner help").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN).setBold(Boolean.valueOf(true)).setChatHoverEvent(new HoverEvent(this.Show_text, (IChatComponent)new ChatComponentText("Click for show help"))).setChatClickEvent(new ClickEvent(this.command, "/memorycleaner help"))));
        sender.addChatMessage(new ChatComponentText("All commands are clickable").setChatStyle(new ChatStyle().setItalic(Boolean.valueOf(true)).setColor(EnumChatFormatting.GREEN)));
    }
    
    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }
    
    public int compareTo(final Object paramT) {
        return 0;
    }
    
    public List getCommandAliases() {
        return new ArrayList();
    }
    
    public List addTabCompletionOptions(final ICommandSender p_71516_1_, final String[] p_71516_2_) {
        return new ArrayList();
    }
    
    public boolean isUsernameIndex(final String[] p_82358_1_, final int p_82358_2_) {
        return false;
    }
}
