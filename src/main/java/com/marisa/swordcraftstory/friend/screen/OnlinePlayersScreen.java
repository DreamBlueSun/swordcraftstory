package com.marisa.swordcraftstory.friend.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.friend.btn.FriendsAddBtn;
import com.marisa.swordcraftstory.friend.net.pack.FriendsDataPack;
import com.marisa.swordcraftstory.friend.pojo.PlayerVO;
import com.marisa.swordcraftstory.net.Networking;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 在线玩家列表Screen
 */

public class OnlinePlayersScreen extends Screen {

    ResourceLocation resourceLocation = new ResourceLocation(Story.MOD_ID, "textures/gui/friend/window.png");

    private final List<PlayerVO> onlinePlayer;
    private final static int PAGE_SIZE = 5;
    private int pageMax = 0;
    private int page = 0;

    private List<FriendsAddBtn> btnList;

    protected OnlinePlayersScreen(List<PlayerVO> onlinePlayer) {
        super(new TextComponent("online_players_screen"));
        this.onlinePlayer = onlinePlayer;
    }

    private void update() {
        if (this.onlinePlayer != null) {
            this.pageMax = this.onlinePlayer.size() % PAGE_SIZE == 0 ? Math.max(this.onlinePlayer.size() / PAGE_SIZE - 1, 0) : this.onlinePlayer.size() / PAGE_SIZE;
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
        //移除上一页按钮
        int count = 0;
        if (this.btnList != null && this.btnList.size() > 0) {
            this.btnList.forEach(this::removeWidget);
        }
        //渲染玩家名称
        this.btnList = new ArrayList<>();
        for (int i = PAGE_SIZE * this.page; i < this.onlinePlayer.size(); i++) {
            drawString(matrixStack, this.font, this.onlinePlayer.get(i).getName(), x, y + 6, 0x00FF7F);
            this.btnList.add(new FriendsAddBtn(x + 70, y, 32, 20, new TextComponent("加好友"), this.onlinePlayer.get(i).getUuid()));
            y += 22;
            if (++count == PAGE_SIZE) {
                break;
            }
        }
        //添加按钮
        if (this.btnList.size() > 0) {
            this.btnList.forEach(this::addRenderableWidget);
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

    public static void open(List<PlayerVO> onlinePlayer) {
        Minecraft.getInstance().setScreen(new OnlinePlayersScreen(onlinePlayer));
    }

}
