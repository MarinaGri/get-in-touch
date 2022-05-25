package ru.itis.utils.helpers.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.exceptions.ConnectionLostException;
import ru.itis.dto.oauth.VkTokenDto;
import ru.itis.dto.oauth.VkUserDto;
import ru.itis.utils.helpers.OAuthHelper;

import java.io.IOException;
import java.util.Objects;

@Component
public class OAuthHelperImpl implements OAuthHelper {

    @Value("${oauth.vk.request-url.access-token}")
    private String requestUrlAccess;

    @Value("${oauth.vk.request-url.method}")
    private String requestUrlMethod;

    @Value("${oauth.vk.client-id}")
    private String clientId;

    @Value("${oauth.vk.redirect-url}")
    private String redirectUrl;

    @Value("${oauth.vk.client-secret}")
    private String clientSecret;

    @Value("${oauth.vk.version}")
    private String version;

    private final String GET_USER_INFO_METHOD = "users.get";

    @Override
    public VkUserDto getUserDtoByTokenDto(VkTokenDto dto) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(getUrlForMethod(dto))
                .build();

        Call call = client.newCall(request);

        try {
            Gson gson = new Gson();
            Response response = call.execute();
            String jsonString = Objects.requireNonNull(response.body()).string();
            JsonObject jsonObj = gson.fromJson(jsonString, JsonObject.class);
            JsonArray jsonArray = gson.fromJson(jsonObj.get("response"), JsonArray.class);
            return gson.fromJson(jsonArray.get(0), VkUserDto.class);
        } catch (IOException ex) {
            throw new ConnectionLostException("Can't access vk.com", ex);
        }
    }

    @Override
    public VkTokenDto getTokenDtoByCode(String code) {
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(getUrlForToken(code))
                .build();

        Call call = client.newCall(request);

        try {
            Gson gson = new Gson();
            Response response = call.execute();

            String str = Objects.requireNonNull(response.body()).string();

            return gson.fromJson(str, VkTokenDto.class);
        } catch (IOException ex) {
            throw new ConnectionLostException("Can't access vk.com", ex);
        }
    }

    private String getUrlForMethod(VkTokenDto dto) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(
                HttpUrl.parse(requestUrlMethod + "/" + GET_USER_INFO_METHOD)).newBuilder();
        urlBuilder.addQueryParameter("user_ids", String.valueOf(dto.getUserId()));
        urlBuilder.addQueryParameter("access_token", dto.getAccessToken());
        urlBuilder.addQueryParameter("v", version);

        return urlBuilder.toString();
    }

    private String getUrlForToken(String code) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(requestUrlAccess)).newBuilder();
        urlBuilder.addQueryParameter("client_id", clientId);
        urlBuilder.addQueryParameter("client_secret", clientSecret);
        urlBuilder.addQueryParameter("redirect_uri", redirectUrl);
        urlBuilder.addQueryParameter("code", code);

        return urlBuilder.toString();
    }
}
