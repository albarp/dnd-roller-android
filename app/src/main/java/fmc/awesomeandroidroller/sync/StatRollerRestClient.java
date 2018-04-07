package fmc.awesomeandroidroller.sync;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import fmc.awesomeandroidroller.interfaces.IApplicationRemoteCallHandler;

/**
 * Created by aarpini on 18/08/2017.
 */

public class StatRollerRestClient {
    private static final String BASE_URL = "https://stat-roller-express.herokuapp.com/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getDndDesign(final IApplicationRemoteCallHandler remoteCallHandler){

        getMethod("dnd/design", remoteCallHandler);
    }

    public static  void rollDndDefault(final IApplicationRemoteCallHandler remoteCallHandler){
        getMethod("roll/dnd/default", remoteCallHandler);
    }

    private static void getMethod(String methodName, final IApplicationRemoteCallHandler remoteCallHandler)
    {
        get(methodName, null, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response){

                remoteCallHandler.onCallResult(0, response);
            }

            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable t){
                remoteCallHandler.onCallResult(1, null);
            }
        });
    }

    private static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    private static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
