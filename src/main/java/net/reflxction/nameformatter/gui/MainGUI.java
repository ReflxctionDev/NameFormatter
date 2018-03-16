/*
 * * Copyright 2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.reflxction.nameformatter.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ChatComponentText;
import net.reflxction.nameformatter.ChatColor;
import net.reflxction.nameformatter.NameFormatter;
import net.reflxction.nameformatter.Reference;

import java.io.IOException;

public class MainGUI extends GuiScreen {

    private GuiTextField textField;

    private Player p = new Player();

    private void clearTextField() {
        textField.setText("");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        this.textField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void closeGUI() {
        Minecraft.getMinecraft().displayGuiScreen(null);
    }

    @Override
    public void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            NameFormatter.setFormat(textField.getText());
            p.sendMessage(Reference.PREFIX + ChatColor.GREEN + "Format has been set to " + ChatColor.AQUA + textField.getText());
            clearTextField();
            closeGUI();
        }
        super.actionPerformed(button);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(1, 235, 150, 150, 20, ChatColor.GREEN + "Set format"));
        this.textField = new GuiTextField(2, fontRendererObj, 100, 200, 450, 20);
    }

    @Override
    protected void keyTyped(char par1, int par2) {
        try {
            super.keyTyped(par1, par2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.textField.textboxKeyTyped(par1, par2);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.textField.updateCursorCounter();
    }

    @Override
    protected void mouseClicked(int x, int y, int btn) {
        try {
            super.mouseClicked(x, y, btn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.textField.mouseClicked(x, y, btn);
    }

    // Class for ease access to the EntityPlayerSP instance
    private class Player {

        void sendMessage(String message) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColor.translateAlternateColorCodes(message)));
        }

    }

}
