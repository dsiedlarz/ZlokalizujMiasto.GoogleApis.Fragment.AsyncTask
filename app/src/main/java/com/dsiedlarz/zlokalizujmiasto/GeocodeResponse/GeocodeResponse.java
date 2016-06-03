package com.dsiedlarz.zlokalizujmiasto.GeocodeResponse;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawid on 01.06.2016.
 */
public class GeocodeResponse {
    private String status;
    private List<Geocode> results = new ArrayList<Geocode>();

    protected GeocodeResponse(Parcel in) {
        status = in.readString();
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResults(List<Geocode> results) {
        this.results = results;
    }

    public List<Geocode> getResults() {
        return results;
    }


    public class Geocode {
        private ArrayList<String> types = new ArrayList<String>();
        private String formatted_address;
        private ArrayList<AddressComponent> address_components = new ArrayList<AddressComponent>();

        private boolean partialMatch;

        public ArrayList<String> getTypes() {
            return types;
        }

        public void setTypes(ArrayList<String> types) {
            this.types = types;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setAddress_components(ArrayList<AddressComponent> address_components) {
            this.address_components = address_components;
        }

        public ArrayList<AddressComponent> getAddress_components() {
            return address_components;
        }


    }

    public class AddressComponent {
        private String long_name;
        private String short_name;
        private ArrayList<String> types = new ArrayList<String>();

        protected AddressComponent(Parcel in) {
            long_name = in.readString();
            short_name = in.readString();
        }


        public String getLong_name() {
            return long_name;
        }

        public void setLong_name(String long_name) {
            this.long_name = long_name;
        }

        public String getShort_name() {
            return short_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }

        public ArrayList<String> getTypes() {
            return types;
        }

        public void setTypes(ArrayList<String> types) {
            this.types = types;
        }


    }
}
