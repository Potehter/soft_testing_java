package ru.mantis.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;

public class RestHelper {
    private final ApplicationManager app;
    public RestHelper(ApplicationManager app) {
        this.app = app;
        RestAssured.authentication = RestAssured.basic(app.getProperty("rest.apiKey"), "");
    }

    public boolean isIssueOpen(int issueId) {
        String json = RestAssured.get(app.getProperty("rest.baseUrl") + "/issues/"+ issueId+ ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement status = parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0).getAsJsonObject().get("state");
        Integer state = new Gson().fromJson(status, Integer.class);
        return state == 0;
    }
}
