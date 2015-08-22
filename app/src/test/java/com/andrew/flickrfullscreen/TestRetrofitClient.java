package com.andrew.flickrfullscreen;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedString;

@Singleton
public class TestRetrofitClient implements Client {

    private List<Response> responses = new ArrayList<>();

    @Inject
    public TestRetrofitClient() {

    }

    @Override
    public Response execute(Request request) throws IOException {
        Response response = responses.get(0);
        responses.remove(0);
        return response;
    }

    public void addResponse(Response response) {
        if (response != null) {
            responses.add(response);
        }
    }

    public static Response createResponse(int statusCode, Object jsonModel) {
        String body;
        if (jsonModel instanceof String) {
            body = (String) jsonModel;
        } else {
            body = new Gson().toJson(jsonModel);
        }
        return new Response("", statusCode, "", new ArrayList<Header>(), new TypedString(body));
    }
}
