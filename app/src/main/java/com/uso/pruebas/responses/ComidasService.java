package com.uso.pruebas.responses;

import com.uso.pruebas.models.Comida;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ComidasService {
    //http://10.0.2.2:8000/api/comidas
    @GET("comidas")
    Call<List<Comida>> getComidas();

    //http://10.0.2.2:8000/api/comidas
    @GET("comidas/{id}")
    Call<ComidaResponse> getComidas(@Path("id") String id );

    //http://10.0.2.2:8000/api/insertcomida
    @POST("comidas")
    Call<ComidaResponse> insertcomidas(
            @Field("menu") String menu,
            @Field("precio") String precio);

    //http://10.0.2.2:8000/api/updatecomida
    @PUT("comidas/{id}")
    Call<ComidaResponse> updatecomidas(
            @Field("menu") String menu,
            @Field("precio") String precio);

    //http://10.0.2.2:8000/api/deletecomida
    @DELETE("comidas/{id}")
    Call<ComidaResponse> deletecomida(@Path("id") String id );
}
