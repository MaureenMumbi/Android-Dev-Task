package com.busara.android.smartduka.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.busara.android.smartduka.R;
import com.busara.android.smartduka.ui.LoginActivity;
import com.busara.android.smartduka.utils.ApiEndPointsInterface;
import com.busara.android.smartduka.utils.Locations;
import com.busara.android.smartduka.utils.RetrofitClient;
import com.busara.android.smartduka.utils.UserList;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserFragment extends Fragment {
    private static final String ALL_LOCATIONS = "All_Locations";
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.firstname)
    EditText firstname;
    @BindView(R.id.lastname)
    EditText lastname;
    @BindView(R.id.is_active)
    CheckBox is_active;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.is_staff)
    CheckBox is_staff;
    @BindView(R.id.is_trainer)
    CheckBox is_trainer;
    @BindView(R.id.trainees)
    EditText trainees;
    @BindView((R.id.groups))
    EditText groups;
    @BindView(R.id.phone_number)
    EditText phone_number;
    @BindView(R.id.date_joined)
    EditText date_joined;
    @BindView(R.id.is_superuser)
    CheckBox is_superuser;
    @BindView(R.id.location)
    Spinner spinnerlocations;
    @BindView((R.id.registerbtn))
    Button registerBtn;
    Bundle locationsBundle = new Bundle();
    Calendar myCalendar;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_register_user, container, false);


        Intent intent = getActivity().getIntent();
        ButterKnife.bind(this, rootView);
        fetchLocations();

        myCalendar = Calendar.getInstance();

        // EditText edittext= (EditText) findViewById(R.id.Birthday);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        date_joined.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Clicked ", Toast.LENGTH_LONG);


                String username = email.getText().toString();
                String pwd = password.getText().toString();
                String first_name = firstname.getText().toString();
                String last_name = lastname.getText().toString();
                Boolean isactive = is_active.isChecked();
                Boolean isstaff = is_staff.isChecked();
                Boolean issuperuser = is_superuser.isChecked();
                Boolean istrainer = is_trainer.isChecked();

                Integer trainee = 1;
                if (!trainees.getText().toString().equals("")) {
                    trainee = Integer.parseInt(trainees.getText().toString());
                }
                List<Integer> trainees = new ArrayList<Integer>();
                trainees.add(trainee);

                Integer group = 0;
                if (!groups.getText().toString().equals("")) {
                    group = Integer.parseInt(groups.getText().toString());
                }
                List<Integer> groups = new ArrayList<Integer>();
                groups.add(group);
                String phonenumber = phone_number.getText().toString();
                String joindate = date_joined.getText().toString();


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");

                Calendar calendar = new GregorianCalendar(myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                String joineddate = sdf.format(calendar.getTime());


                Long location = spinnerlocations.getSelectedItemId();
                UserList userList;

                Register(username, first_name, last_name, isactive, isstaff, issuperuser, istrainer, trainees, groups, phonenumber, joineddate, location, pwd);
            }
        });


        return rootView;
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";//"yyyy-MM-dd HH:mm:ss.SSS"; //In which you need put here MM/dd/yy
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        date_joined.setText(sdf.format(myCalendar.getTime()));
    }

    private void Register(String username, String first_name, String last_name, Boolean isactive, Boolean isstaff, Boolean issuperuser, Boolean istrainer, List<Integer> trainee, List<Integer> group, String phonenumber, String joindate, Long location, String pwd) {

        List<Integer> groups = new ArrayList<Integer>();

        ApiEndPointsInterface apiService = RetrofitClient.getApiService(getContext());
        Map<String, Object> jsonParams = new ArrayMap<>();
        jsonParams.put("email", username);
        jsonParams.put("first_name", first_name);
        jsonParams.put("last_name", last_name);
        jsonParams.put("is_active", isactive);
        jsonParams.put("is_staff", isstaff);
        jsonParams.put("is_superuser", issuperuser);
        jsonParams.put("groups", groups);
        jsonParams.put("phone_number", phonenumber);
        jsonParams.put("is_trainer", false);
        jsonParams.put("trainees", trainee);
        jsonParams.put("date_joined", joindate);
        jsonParams.put("location", location);
        jsonParams.put("password", pwd);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());


        Call<ResponseBody> api = apiService.createUser(body);

        api.enqueue(new Callback<ResponseBody>()

        {
            @Override
            public void onResponse
                    (Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    Integer statusCode = response.code();

                    Toast.makeText(getContext(), "Registered SuccessFully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
                Toast.makeText(getContext(), "Error, check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void fetchLocations() {

        ApiEndPointsInterface api = RetrofitClient.getApiService(getContext());


        Call<Locations> locations = api.getLocations();
        locations.enqueue(new Callback<Locations>()

        {
            @Override
            public void onResponse
                    (Call<Locations> call, Response<Locations> response) {
                if (response.isSuccessful()) {

                    Integer statusCode = response.code();

                    ArrayList Results = response.body().getResults();
                    ArrayList<String> names = new ArrayList<>();
                    for (int y = 0; y < Results.size(); y++) {
                        names.add(response.body().getResults().get(y).getname());

                    }


                    locationsBundle.putParcelableArrayList(ALL_LOCATIONS, Results);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item, names);

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerlocations.setAdapter(adapter);
                    // spinnerlocations.setOnItemSelectedListener(this);
                }

            }


            @Override
            public void onFailure(Call<Locations> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
                Toast.makeText(getContext(), "Errorg fetching locations, check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
