package com.example.covid_19.Models;

public class CountriesStat {
    private String country_name;
    private int cases;
    private int deaths;
    private String region;
    private int total_recovered;
    private int new_deaths;
    private int new_cases;
    private int serious_critical;
    private int active_cases;
    private int total_cases_per_1m_population;
    private int deaths_per_1m_population;
    private int total_tests;
    private int tests_per_1m_population;

    public CountriesStat() {
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getTotal_recovered() {
        return total_recovered;
    }

    public void setTotal_recovered(int total_recovered) {
        this.total_recovered = total_recovered;
    }

    public int getNew_deaths() {
        return new_deaths;
    }

    public void setNew_deaths(int new_deaths) {
        this.new_deaths = new_deaths;
    }

    public int getNew_cases() {
        return new_cases;
    }

    public void setNew_cases(int new_cases) {
        this.new_cases = new_cases;
    }

    public int getSerious_critical() {
        return serious_critical;
    }

    public void setSerious_critical(int serious_critical) {
        this.serious_critical = serious_critical;
    }

    public int getActive_cases() {
        return active_cases;
    }

    public void setActive_cases(int active_cases) {
        this.active_cases = active_cases;
    }

    public int getTotal_cases_per_1m_population() {
        return total_cases_per_1m_population;
    }

    public void setTotal_cases_per_1m_population(int total_cases_per_1m_population) {
        this.total_cases_per_1m_population = total_cases_per_1m_population;
    }

    public int getDeaths_per_1m_population() {
        return deaths_per_1m_population;
    }

    public void setDeaths_per_1m_population(int deaths_per_1m_population) {
        this.deaths_per_1m_population = deaths_per_1m_population;
    }

    public int getTotal_tests() {
        return total_tests;
    }

    public void setTotal_tests(int total_tests) {
        this.total_tests = total_tests;
    }

    public int getTests_per_1m_population() {
        return tests_per_1m_population;
    }

    public void setTests_per_1m_population(int tests_per_1m_population) {
        this.tests_per_1m_population = tests_per_1m_population;
    }
}
