package com.skullybunny.medinoteservices.medinote.helpers;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skullybunny.medinoteservices.medinote.R;
import com.skullybunny.medinoteservices.medinote.webdata.errors.WebErrorResIdContainer;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Response;

/**
 * Created by Martin on 5/9/2017.
 */

public class RetrofitHelper
{
    public static JSONObject getErrorBodyAsJSONObject(ResponseBody errorBody)
    {
        JSONObject errorJSONObject = new JSONObject();
        if (errorBody != null)
        {
            try
            {
                BufferedSource source = errorBody.source();

                if (source != null)
                {
                    source.request(Long.MAX_VALUE); // Buffer the entire body.
                    Buffer buffer = source.buffer();

                    Charset charset = Charset.defaultCharset();
                    MediaType contentType = errorBody.contentType();
                    if (contentType != null)
                    {
                        charset = contentType.charset(Charset.defaultCharset());
                    }

                    String errorBodyString = buffer.clone().readString(charset);

                    if (!TextUtils.isEmpty(errorBodyString))
                    {
                        errorJSONObject = new JSONObject(errorBodyString);
                    }
                }

            }
            catch (Exception e)
            {
                Log.d("RetrofitHelper", "Impossible to get StatusError stream", e);
            }
        }


        return errorJSONObject;
    }

    public static <T> void showDialogFromErrorResponse(Response<T> response, Context context)
    {
        JSONObject errorBodyJSON = getErrorBodyAsJSONObject(response.errorBody());
        try
        {
            int errorStringResId = WebErrorResIdContainer.getStringResIdError(errorBodyJSON.getInt("CustomErrorCode"));
            DialogHelper.showDialog(context.getString(errorStringResId), context);
        }
        catch (JSONException | Resources.NotFoundException e)
        {
            e.printStackTrace();
            DialogHelper.showDialog(context.getString(R.string.problem_when_communicating_with_server), context);
        }
    }
}
