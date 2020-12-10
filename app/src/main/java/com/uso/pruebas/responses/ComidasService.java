package com.uso.pruebas.responses;

import com.google.gson.annotations.JsonAdapter;
import com.uso.pruebas.models.Comida;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ComidasService {
    @GET("comidas")
    Call<List<Comida>> getComidas();

    @GET("comidas/{id}")
    Call<Comida> getComida(@Path("id") String id );

    @FormUrlEncoded
    @POST("comidas")
    Call<Comida> insertcomidas(@Field("menu") String menu, @Field("precio") Double precio);

    @POST("comidas")
    Call<Comida> insertcomidas(@Body Comida comida);

    @FormUrlEncoded
    @POST("comidas")
    Call<Comida> insertcomidas(@FieldMap Map<String, Object> fields);

    @PUT("comidas/{id}")
    Call<ComidaResponse> updatecomidas(@Field("menu") String menu, @Field("precio") String precio);

    @PUT("comidas/{id}")
    Call<Comida> updatecomidas(@Path("id") String id, @Body Comida comida);

    @DELETE("comidas/{id}")
    Call<ComidaResponse> deletecomida(@Path("id") String id );
}
