package com.epicgui.test;

import com.epicgui.lib.EpicGUIException;
import com.epicgui.lib.EpicGUILib;
import com.epicgui.lib.components.ButtonComponent;
import com.epicgui.lib.components.StaticComponent;
import com.epicgui.lib.components.TextComponent;
import com.epicgui.lib.gui.EpicGUI;
import net.kyori.adventure.text.Component;
import net.minestom.server.command.builder.Command;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.*;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.instance.block.Block;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.world.biomes.Biome;
import java.util.Arrays;
import java.util.List;

public class MainTest {

    public static void main(String[] args) throws EpicGUIException {
        MinecraftServer minecraftServer = MinecraftServer.init();
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();

        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();
        instanceContainer.setChunkGenerator(new GeneratorDemo());
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
            Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(0, 42, 0));
        });

        EpicGUILib.init();

        MinecraftServer.getCommandManager().register(new GUICommand());

        minecraftServer.start("0.0.0.0", 25565);
    }

    private static class GUICommand extends Command {

        public GUICommand() {
            super("gui");

            setDefaultExecutor((sender, context) -> {
                EpicGUI gui = new EpicGUI(net.kyori.adventure.text.Component.text("Example"), InventoryType.CHEST_6_ROW);

                ButtonComponent testComponent = new ButtonComponent(0, 0, 2, 2, ItemStack.of(Material.STONE_HOE));
                testComponent.setComponentClickListener(e -> {
                    e.cancel();
                    e.getPlayer().sendMessage("Enter something in chat:");
                    e.promptPlayerInChat(event -> {
                        event.getPlayer().sendMessage("You sent: " + event.getResult());
                    });
                });
                gui.addComponent(testComponent);

                gui.addComponent(new TextComponent(0, 5, Material.ACACIA_BOAT, Component.text("Info"), Component.text("Press here for more info.")).canPlayerTakeOutOfInventory(false));
                gui.addComponent(new StaticComponent(2, 0, 2, 2, ItemStack.of(Material.GOLD_BLOCK)).canPlayerTakeOutOfInventory(false));

                gui.open(sender.asPlayer());
            });
        }
    }

    private static class GeneratorDemo implements ChunkGenerator {
        @Override
        public void generateChunkData(ChunkBatch batch, int chunkX, int chunkZ) {
            // Set chunk blocks
            for (byte x = 0; x < Chunk.CHUNK_SIZE_X; x++) {
                for (byte z = 0; z < Chunk.CHUNK_SIZE_Z; z++) {
                    for (byte y = 0; y < 40; y++) {
                        batch.setBlock(x, y, z, Block.STONE);
                    }
                }
            }
        }

        @Override
        public List<ChunkPopulator> getPopulators() {
            return null;
        }
    }
}
