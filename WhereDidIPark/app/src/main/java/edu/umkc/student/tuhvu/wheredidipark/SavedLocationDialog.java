package edu.umkc.student.tuhvu.wheredidipark;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by Sil3nz3r on 12/01/2015.
 */
public class SavedLocationDialog extends DialogFragment {
    private SharedPreferences mLocationSharedPreferences;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mLocationSharedPreferences = getActivity().getSharedPreferences(getString(R.string.location_shared_preferences_name), Context.MODE_PRIVATE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Saved Location")
                .setItems(new String[]{"Location 1", "Location 2", "Location 3"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int index) {
                                retrieveLocationFromSharedPreferences(index);
                            }
                        });
        return builder.create();
    }

    private void retrieveLocationFromSharedPreferences(int index) {
        Location location = new Location(String.valueOf(R.string.shared_preferences_service));

        if (mLocationSharedPreferences.contains(String.valueOf(index) + "IsLocationExist")) {

            location.setLatitude(Double.longBitsToDouble(mLocationSharedPreferences.getLong(String.valueOf(index) + "Latitude", 0L)));
            location.setLongitude(Double.longBitsToDouble(mLocationSharedPreferences.getLong(String.valueOf(index) + "Longitude", 0L)));
            location.setTime(mLocationSharedPreferences.getLong(String.valueOf(index) + "LastUpdateTime", 0L));
            ((MainActivity)getActivity()).setCurrentLocation(location);
        } else {
            Toast.makeText(getActivity(), "Saved location is empty.", Toast.LENGTH_SHORT).show();
        }
    }
}
