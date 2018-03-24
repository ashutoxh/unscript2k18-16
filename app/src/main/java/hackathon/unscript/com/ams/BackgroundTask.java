package hackathon.unscript.com.ams;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String, Void, String> {

    static Toast backgroundToast;
    String loggedInUserName = "", loggedInUserId = "";
    private Context ctx;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }


    public static boolean connection(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() &&
                connectivityManager.getActiveNetworkInfo().isConnected())
            return true;
        else
            return false;
    }

    protected String doInBackground(String... params) {
        String log_url = "http://711d2044.ngrok.io/connection.php";
      String response_api = "";
//        String name = params[0];
        String username = params[0];
        String password = params[1];
        String errorMessage = "";

//            HttpURLConnection httpURLConnection;
//            if (connection(ctx)) {
//                try {
//                    Log.d("BG THREAD","User : " +username + " Pass : " +password);
//                    httpURLConnection = (HttpURLConnection) new URL(log_url).openConnection();
//
//                    httpURLConnection.setRequestMethod("POST");
//                    httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//                    httpURLConnection.setRequestProperty("Accept", "application/json; charset=UTF-8");

//                    httpURLConnection.setDoOutput(true);
//                    httpURLConnection.setDoInput(true);
//                    OutputStream os = new DataOutputStream(httpURLConnection.getOutputStream());
//                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//
//                    String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
//                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
//                    Log.d("DATA : ",data);
//
//                    bufferedWriter.write(data);
//                    bufferedWriter.flush();
//                    bufferedWriter.close();
//                    os.close();
//                    InputStream inputStream = httpURLConnection.getInputStream();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                    String data = "";
//                    String line = "";
//                    while (line != null) {
//                        line = bufferedReader.readLine();
//                        data += line;
//                    }
//                    Log.v("BG Thread(LOGIN) : ", "DATA : " + data);
//                    JSONObject JO = new JSONObject(data);
//                    Log.v("BG Thread(LOGIN) : ", "DATA : " + JO.toString());
//                    response_api = JO.getString("action");
//                    Log.v("BG Thread(LOGIN) : ", "Response : " + response_api);
//                    errorMessage = JO.getString("errorMessage");
//                    Log.v("BG Thread(LOGIN) : ", "Error Message : " + errorMessage);
//                    userId = JO.getString("name");
//                    Log.v("BG Thread(LOGIN) : ", "Name : " + name);
//                    username = JO.getString("userId");
//                    Log.v("BG Thread(LOGIN) : ", "UserId : " + username);
//                    bufferedReader.close();
//                    inputStream.close();
//                    httpURLConnection.disconnect();
//
//                }  catch (ProtocolException e) {
//                    e.printStackTrace();
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                if (response_api.equals("success")) {
////                    loggedInUserName = name;
////                    loggedInUserId = username;
//                    return "Login Successful";
//                }
//                else if (response_api.equals("failure"))
//                {
//                    if(errorMessage.equals("Incorrect password"))
//                        return "Incorrect password";
//                    else if(errorMessage.equals("Incorrect result size: expected 1, actual 0"))
//                        return "User doesn't exist";
//                    else if(errorMessage.equals("Incorrect result size: expected 1, actual 0"))
//                        return "User doesn't exist";
//                }
//                else
//                    return "Sorry, Our servers are facing some issues.Please try again later";
//            } else
//                return "Please check your Internet Connection";

            Log.d("BG THREAD", "User : " + username + " Password : " + password);
            if (username.equals("student") && password.equals("student")) {
//                loggedInUserName = "ASHUTOSH SINGH";
//                loggedInUserId = "ashu";
                return "Student Login Successful";
            }
            else if (username.equals("parent") && password.equals("parent")) {
//                loggedInUserName = "ASHUTOSH SINGH";
//                loggedInUserId = "ashu";
                return "Parent Login Successful";
            }
            else
                return "Invalid";
//        return null;
    }

    @Override
    protected void onPostExecute(String result) {

          backgroundToast = Toast.makeText(ctx, "Welcome " + loggedInUserName, Toast.LENGTH_SHORT);
//        if (LoginActivity.progressBar != null)
//            LoginActivity.progressBar.setVisibility(View.GONE);
//        if (Register.progressBar != null)
//            Register.progressBar.setVisibility(View.GONE);
//
        if (result.equals("Student Login Successful")) {
            Log.d("BG THREAD", "Result : " + loggedInUserName + " " + loggedInUserId);
//            LoginActivity.sharedPreferences.edit().putString("username", loggedInUserName).apply();
//            LoginActivity.sharedPreferences.edit().putString("userId", loggedInUserId).apply();
//            Log.d("TEST SHARED STATIC ", " " + LoginActivity.sharedPreferences.getString("username", null));
            Intent intent = new Intent(ctx, Student.class);
//            intent.putExtra("username", this.loggedInUserName);
//            intent.putExtra("userId", this.loggedInUserId);
            ctx.startActivity(intent);
            backgroundToast.show();
        }
        else if(result.equals("Parent Login Successful")) {
            Log.d("BG THREAD", "Result : " + loggedInUserName + " " + loggedInUserId);
//            LoginActivity.sharedPreferences.edit().putString("username", loggedInUserName).apply();
//            LoginActivity.sharedPreferences.edit().putString("userId", loggedInUserId).apply();
//            Log.d("TEST SHARED STATIC ", " " + LoginActivity.sharedPreferences.getString("username", null));
            Intent intent = new Intent(ctx, Parent.class);
//            intent.putExtra("username", this.loggedInUserName);
//            intent.putExtra("userId", this.loggedInUserId);
            ctx.startActivity(intent);
            backgroundToast.show();
        }else
         {
            backgroundToast = Toast.makeText(ctx, result, Toast.LENGTH_SHORT);
            backgroundToast.show();
        }


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
