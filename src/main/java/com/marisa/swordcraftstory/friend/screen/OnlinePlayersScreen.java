package com.marisa.swordcraftstory.friend.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.friend.btn.FriendsAddBtn;
import com.marisa.swordcraftstory.friend.net.pack.FriendsDataPack;
import com.marisa.swordcraftstory.net.Networking;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 在线玩家列表Screen
 */

public class OnlinePlayersScreen extends Screen {

    ResourceLocation resourceLocation = new ResourceLocation(Story.MOD_ID, "textures/gui/friend/window.png");

    private List<AbstractClientPlayer> players;
    private final static int PAGE_SIZE = 5;
    private int pageMax = 0;
    private int page = 0;

    protected OnlinePlayersScreen() {
        super(new TextComponent("online_players_screen"));
    }

    private void update() {
        if (Minecraft.getInstance().level != null) {
            this.players = Minecraft.getInstance().level.players();
            this.pageMax = players.size() % PAGE_SIZE == 0 ? Math.max(players.size() / PAGE_SIZE - 1, 0) : players.size() / PAGE_SIZE;
            if (this.page > this.pageMax) {
                this.page = this.pageMax;
            }
            if (this.page < 0) {
                this.page = 0;
            }
        }
    }

    @Override
    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float particleTick) {
        this.update();
        this.renderBackground(matrixStack);
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.setShaderTexture(0, resourceLocation);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        minecraft.getProfiler().push("online_players");
        blit(matrixStack, this.width / 2 - 60, this.height / 2 - 81, 0, 0, 120, 163, 120, 163);
        //页签
        Button btnPlayers = new Button(this.width / 2 - 53, this.height / 2 - 77, 53, 20, new TextComponent("在线列表"), (btn) -> {
        });
        btnPlayers.active = false;
        this.addRenderableWidget(btnPlayers);
        this.addRenderableWidget(new Button(this.width / 2 + 1, this.height / 2 - 77, 53, 20, new TextComponent("好友列表"), (btn) -> {
            Networking.FRIENDS_DATA.sendToServer(new FriendsDataPack("online.friends.screen.open"));
        }));
        //列表
        int x = this.width / 2 - 51;
        int y = this.height / 2 - 53;
        for (int i = PAGE_SIZE * this.page; i < this.players.size(); i++) {
            drawString(matrixStack, this.font, this.players.get(i).getDisplayName().getString(), x, y + 6, 0x00FF7F);
            this.addRenderableWidget(new FriendsAddBtn(x + 70, y, 32, 20, new TextComponent("加好友"), this.players.get(i).getStringUUID()));
            y += 22;
        }
        //上一页
        Button btnLastPage = new Button(x - 2, this.height / 2 + 58, 53, 20, new TextComponent("上一页"), (btn) -> {
            if (this.page > 0) {
                this.page--;
            }
        });
        if (this.page == 0) {
            btnLastPage.active = false;
        }
        this.addRenderableWidget(btnLastPage);
        //下一页
        Button btnNextPage = new Button(x + 52, this.height / 2 + 58, 53, 20, new TextComponent("下一页"), (btn) -> {
            if (this.page < this.pageMax) {
                this.page++;
            }
        });
        if (this.page == this.pageMax) {
            btnNextPage.active = false;
        }
        this.addRenderableWidget(btnNextPage);
        minecraft.getProfiler().pop();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        super.render(matrixStack, mouseX, mouseY, particleTick);
    }

    public static void open() {
        Minecraft.getInstance().setScreen(new OnlinePlayersScreen());
    }

}
