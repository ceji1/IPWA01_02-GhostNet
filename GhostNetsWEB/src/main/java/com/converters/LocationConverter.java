package com.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import com.entities.Location;

@FacesConverter(forClass = Location.class)
public class LocationConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (Location) uic.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object instanceof Location) {
            Location loc = (Location) object;
            if (loc != null && loc instanceof Location && loc.getId() != null) {
                uic.getAttributes().put(loc.getId().toString(), loc);
                return loc.getId().toString();
            }
        }
        return "";
    }
}
