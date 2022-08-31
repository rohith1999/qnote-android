package com.mithran.qnote.viewModel;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mithran.qnote.model.Note;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class NoteViewModel extends BaseObservable {


    private Note note;
    private String successMessage = "data saved successfully";
    private RequestQueue requestQueue;

    @Bindable
    // string variable for
    // toast message
    private String toastMessage = null;

    public NoteViewModel(Context context){
         requestQueue = Volley.newRequestQueue( context);

        note=new Note("","");
    }

    public String getToastMessage() {
        return toastMessage;
    }

    public void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);

    }

    @Bindable
    public String getNoteId() {
        return note.getNoteId();
    }

    public void setNoteId(String noteId) {
        note.setNoteId(noteId);
        notifyPropertyChanged(BR.noteId);

    }

    @Bindable
    public String getNoteData() {
        return note.getNoteData();
    }

    public void setNoteData(String noteData) {
        note.setNoteData(noteData);
        notifyPropertyChanged(BR.noteData);

    }

    public void onSubmit() {

        if(!getNoteId().isEmpty()) {
            String URL = String.format("https://qnote-01.herokuapp.com/note/%1$s", getNoteId());

            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                    URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        Log.d("success_aaaaaa", response.getString("noteData"));
                        setNoteData(response.getString("noteData"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
                    error -> Log.d("error_aaaa", error.getMessage()));

            requestQueue.add(stringRequest);

        }
        else {
            setToastMessage("Route is empty");
        }


    }
}
