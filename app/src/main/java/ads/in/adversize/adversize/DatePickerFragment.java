package ads.in.adversize.adversize;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by suryamurugan on 21/4/18.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
       // return new DatePickerDialog(getActivity(), (AddMediaFragment)  , year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
       // Toast.makeText(getContext(), ""+year, Toast.LENGTH_SHORT).show();
    //    ((TextView) getActivity().findViewById(R.id.sel)).setText("Date = " +day+" "+month+" "+ year);
        ((Button) getActivity().findViewById(R.id.avaibaleFrom)).setText("Date = " +day+" "+month+" "+ year);


    }
}