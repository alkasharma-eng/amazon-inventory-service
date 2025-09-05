package com.alka.sdui.dto;

import java.util.ArrayList;
import java.util.List;

public class SduiResponse {
    private String screen;
    private int version = 1;
    private List<SduiComponent> components = new ArrayList<>();

    public String getScreen() { return screen; }
    public void setScreen(String screen) { this.screen = screen; }
    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }
    public List<SduiComponent> getComponents() { return components; }
    public void setComponents(List<SduiComponent> components) { this.components = components; }
}