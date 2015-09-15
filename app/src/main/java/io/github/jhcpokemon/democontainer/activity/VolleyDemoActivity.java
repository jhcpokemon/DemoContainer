package io.github.jhcpokemon.democontainer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.jhcpokemon.democontainer.App;
import io.github.jhcpokemon.democontainer.R;

public class VolleyDemoActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.send_json_req_btn)
    Button sendJsonRequestButton;
    @Bind(R.id.post_req_btn)
    Button postRequestButton;
    @Bind(R.id.net_image_view)
    NetworkImageView networkImageView;
    @Bind(R.id.image_view)
    ImageView imageView;
    @Bind(R.id.text_view)
    TextView textView;
    String jsonObjTag = "json_obj_tag";
    String postTag = "post_tag";
    String imageUrl = "http://jakewharton.github.io/butterknife/static/logo.png";
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_demo);
        ButterKnife.bind(this);
        sendJsonRequestButton.setOnClickListener(this);
        postRequestButton.setOnClickListener(this);
        imageLoader = App.getAppInstance().getImageLoader();
        networkImageView.setImageUrl(imageUrl, imageLoader);
        imageLoader.get(imageUrl, ImageLoader.getImageListener(imageView, R.color.material_grey_300, R.color.accent_material_dark));
        textView.setText(getStringFromCache("https://www.v2ex.com/api/nodes/show.json?name=android"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_json_req_btn:
                String url = "https://www.v2ex.com/api/nodes/show.json?name=android";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Toast.makeText(getApplicationContext(), response.getString("header"), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Get Response Error", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                        try {
                            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
                        } catch (UnsupportedEncodingException | JSONException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
                App.getAppInstance().addToRequestQueue(jsonObjectRequest, jsonObjTag);
                break;
            case R.id.post_req_btn:
                String url1 = "https://www.v2ex.com/api/nodes/show.json";
                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, url1, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Toast.makeText(getApplicationContext(), response.getString("header"), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("name", "ruby");
                        return map;
                    }
                };
                App.getAppInstance().addToRequestQueue(jsonObjectRequest1, postTag);
                break;
            default:
                break;
        }
    }

    String getStringFromCache(String url) {
        Cache cache = App.getAppInstance().getRequestQueue().getCache();
        String header = "";
        try {
            Cache.Entry entry = cache.get(url);
            header = new String(entry.data, "utf-8");
            JSONObject jsonObject = new JSONObject(header);
            header = jsonObject.getString("header");
        } catch (NullPointerException | UnsupportedEncodingException | JSONException exception) {
            Log.e(App.TAG, exception.getMessage());
        }
        return header;
    }
}
