package com.service;

import java.util.ArrayList;
import java.util.List;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.PlaceAutocompleteRequest.SessionToken;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceAutocompleteType;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.FacesComponent;

@FacesComponent
@ApplicationScoped
public class GooglePlacesAutocompleteApiService {
	
    public List<String> getPredictions(String input) {
        List<String> places = new ArrayList<>();

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAy_i1TSaLaFpcoiaFGCVsREJAIRWTKiyQ") // hide before merging
                .build();

        //Call Autocomplete API
        try {
            SessionToken token = new SessionToken();
            AutocompletePrediction[] predictions = PlacesApi.placeAutocomplete(context,input, token).types(PlaceAutocompleteType.CITIES).await();

            for (AutocompletePrediction prediction : predictions) {
                places.add(prediction.description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return places;
    }


}
