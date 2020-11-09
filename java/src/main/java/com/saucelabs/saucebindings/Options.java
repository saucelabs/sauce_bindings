package com.saucelabs.saucebindings;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.MutableCapabilities;

import java.util.List;

public abstract class Options {
    @Setter protected MutableCapabilities capabilities = new MutableCapabilities();
    @Setter protected CapabilityManager capabilityManager;
    @Setter protected SystemManager systemManager;
    @Getter public final List<String> validOptions = null;
}
