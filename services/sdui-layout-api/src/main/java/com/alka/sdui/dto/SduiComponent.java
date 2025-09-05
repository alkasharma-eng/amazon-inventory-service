package com.alka.sdui.dto;

import java.util.Map;

public class SduiComponent {
    private String type;       // "Card" | "Text" | "Button"
    private Map<String, Object> props;

    public SduiComponent() {}
    public SduiComponent(String type, Map<String, Object> props) {
        this.type = type;
        this.props = props;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Map<String, Object> getProps() { return props; }
    public void setProps(Map<String, Object> props) { this.props = props; }
}