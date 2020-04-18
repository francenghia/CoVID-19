package com.example.covid_19.Retrofit;

import com.example.covid_19.Models.CountriesStat;
import com.example.covid_19.Models.WorldStat;

import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ICovidApiClient {

    @GET("coronavirus/cases_by_country.php")
    Flowable<List<CountriesStat>> getAllCountriesStat();

    @GET("coronavirus/worldstat.php")
    Flowable<WorldStat> getAllWorldStat();
}
