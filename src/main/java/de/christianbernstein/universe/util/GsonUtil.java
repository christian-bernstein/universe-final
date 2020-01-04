package de.christianbernstein.universe.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

    private static final Gson gson;

    public static Gson getGson() {
        return gson;
    }

    static {
        gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    }

}
