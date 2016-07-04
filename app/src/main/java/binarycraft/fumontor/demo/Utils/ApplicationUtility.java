package binarycraft.fumontor.demo.Utils;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by User on 29-Mar-16.
 */
public class ApplicationUtility {

    /*public static Bundle getFacebookData(JSONObject object, AccessToken accessToken) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");
            String accesstokenUrl = StaticData.FACEBOOK_ACCESSTOKEN_URL + accessToken.getToken() + "&format=json";
            Log.e(StaticData.ACCESSTOKEN_URL, accesstokenUrl);
            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=200");
                Log.e("profile_pic", profile_pic + "");
                bundle.putString(StaticData.USER_IMAGE, profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
            bundle.putString(StaticData.USER_ID, id);
            if (object.has(StaticData.USER_FIRST_NAME))
                bundle.putString(StaticData.USER_FIRST_NAME, object.getString(StaticData.USER_FIRST_NAME));
            if (object.has(StaticData.USER_MIDDLE_NAME))
                bundle.putString(StaticData.USER_MIDDLE_NAME, object.getString(StaticData.USER_MIDDLE_NAME));
            if (object.has(StaticData.USER_LAST_NAME))
                bundle.putString(StaticData.USER_LAST_NAME, object.getString(StaticData.USER_LAST_NAME));
            if (object.has(StaticData.USER_EMAIL))
                bundle.putString(StaticData.USER_EMAIL, object.getString(StaticData.USER_EMAIL));
            if (object.has(StaticData.USER_GENDER))
                bundle.putString(StaticData.USER_GENDER, object.getString(StaticData.USER_GENDER));
            bundle.putString(StaticData.ACCESSTOKEN_URL, accesstokenUrl);
            return bundle;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bundle getGoogleAuthData(GoogleSignInAccount account) {
        Bundle bundle = new Bundle();
        bundle.putString(StaticData.USER_IMAGE, account.getPhotoUrl().toString());
        bundle.putString(StaticData.USER_ID, account.getId());
        bundle.putString(StaticData.USER_FIRST_NAME, getSeperatedName(account.getDisplayName(), true));
        bundle.putString(StaticData.USER_MIDDLE_NAME, "");
        bundle.putString(StaticData.USER_LAST_NAME, getSeperatedName(account.getDisplayName(), false));
        bundle.putString(StaticData.USER_EMAIL, account.getEmail());
        bundle.putString(StaticData.ACCESSTOKEN_URL, account.getIdToken());
        return bundle;
    }

    private static String getSeperatedName(String fullName, boolean isFirstname){
        String[] parts = fullName.split(" ");
        if(isFirstname){
            return parts[0];
        }else{
            return parts[1];
        }
    }*/

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(android.util.Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    /*public static String getSignUpJSONParam(Bundle bundle, String authType, String fcmToken) {
        String user_id = bundle.getString(StaticData.USER_ID);
        String email = bundle.getString(StaticData.USER_EMAIL);
        String first_name = bundle.getString(StaticData.USER_FIRST_NAME);
        String last_name = bundle.getString(StaticData.USER_LAST_NAME);
        String jSONParam = "{\"" + StaticData.USER_ID + "\":\"" + user_id + "\",\"auth_type\":\"" + authType + "\",\"" + StaticData.USER_EMAIL + "\":\"" + email + "\",\"" +
                StaticData.USER_FIRST_NAME + "\":\"" + first_name + "\",\"" + StaticData.USER_LAST_NAME + "\":\"" + last_name + "\",\"" + StaticData.USER_ADDRESS + "\":\"\",\"" +
                StaticData.USER_CONTACT + "\":\"\",\"" + StaticData.REG_ID + "\":\"" + fcmToken + "\"}";
        return jSONParam;
    }

    public static Bundle getResponseBundle(SignUpResponse response) {
        Bundle bundle = new Bundle();
        User aUser = response.getUser();
        Log.e("User_Id", aUser.getUser_id());
        bundle.putString(StaticData.USER_ID, aUser.getUser_id());
        bundle.putString(StaticData.USER_FIRST_NAME, aUser.getFirst_name());
        bundle.putString(StaticData.USER_LAST_NAME, aUser.getLast_name());
        bundle.putString(StaticData.USER_EMAIL, aUser.getEmail());
        bundle.putString(StaticData.CHURCH_ID, aUser.getChurch_id());
        bundle.putString(StaticData.ID, aUser.getId());
        bundle.putString(StaticData.USER_IMAGE, aUser.getImage());
        bundle.putString(StaticData.USER_TYPE, aUser.getType());
        bundle.putString(StaticData.USER_CONTACT, aUser.getContact_no());
        bundle.putString(StaticData.USER_ADDRESS, aUser.getAddress());

        return bundle;

    }

    public static boolean checkInternet(Context context) {
        ConnectivityManager check = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = check.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    *//*public static void openNetworkDialog(final AppCompatActivity activity, final int requestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(activity.getString(R.string.con_message))
                .setTitle(activity.getString(R.string.con_title)).setCancelable(false).setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
            }
        }).setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        }).create();
        builder.show();
        FragmentManager fm = activity.getSupportFragmentManager();
        NetworkDialogFragment networkDialogFragment = NetworkDialogFragment.newInstance(requestCode);
        networkDialogFragment.setCancelable(false);
        networkDialogFragment.show(fm, "fragment_edit_name");
    }*//*

    public static Date formatDate(String inputDate, SimpleDateFormat sdFormat) {
        Date dt = null;
        try {
            dt = sdFormat.parse(inputDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dt;
    }

    public static void hideKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void openScreenshotDialog(String imgUrl, Context con) {
        final Dialog dialog = new Dialog(con, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.screenshot_dialog);

        TouchImageView image = (TouchImageView) dialog.findViewById(R.id.ivScreenshot);
        Picasso.with(con).load(imgUrl).placeholder(R.drawable.progress_animation).into(image);

        ImageView ivCross = (ImageView) dialog.findViewById(R.id.ivCross);
        // if button is clicked, close the custom dialog
        ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static String getPath(Uri uri, Activity activity) {

        if (uri == null) {
            return null;
        }

        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }

        return uri.getPath();
    }

    public static String getImageInBase64(Bitmap pic) {
        String resultStr = "";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Log.e("IMAGE", "OrigH: " + pic.getHeight() + " OrigW: " + pic.getWidth());
        Dimension aDimension = getDimenAccordingToAspectRatio(pic.getHeight(), pic.getWidth());
        Log.e("IMAGE", "H: " + aDimension.getdHeight() + " W: " + aDimension.getdWidth());
        pic = Bitmap.createScaledBitmap(pic, aDimension.getdWidth(), aDimension.getdHeight(), true);
        // byte array output streams
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        pic.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        // byte array
        byte[] ba = baos.toByteArray();

        // byte to base64 encoded string
        resultStr = Base64.encodeBytes(ba);
        Log.e("Bas64 Image Test", resultStr);
        return resultStr;
    }

    public static Bitmap getOrientedImage(Uri imgUri, String imgPath, Context context) {
        Bitmap bitmap = createBitmapFromPath(imgUri, context);
        try {
            int orientation = getExifOrientation(imgPath);
            Log.e("ORIENTATION", orientation + "");
            if (orientation == 1) {
                return bitmap;
            }

            Matrix matrix = new Matrix();
            switch (orientation) {
                case 2:
                    matrix.setScale(-1, 1);
                    break;
                case 3:
                    matrix.setRotate(ExifInterface.ORIENTATION_ROTATE_180);
                    break;
                case 4:
                    matrix.setRotate(ExifInterface.ORIENTATION_ROTATE_180);
                    matrix.postScale(-1, 1);
                    break;
                case 5:
                    matrix.setRotate(ExifInterface.ORIENTATION_ROTATE_90);
                    matrix.postScale(-1, 1);
                    break;
                case 6:
                    matrix.setRotate(ExifInterface.ORIENTATION_ROTATE_90);
                    break;
                case 7:
                    matrix.setRotate(ExifInterface.ORIENTATION_ROTATE_270);
                    matrix.postScale(-1, 1);
                    break;
                case 8:
                    matrix.setRotate(-90);
//                    matrix.postScale(-1, 1);
                    break;
                default:
                    return bitmap;
            }

            try {
                Bitmap oriented = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                bitmap.recycle();
                return oriented;
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    private static int getExifOrientation(String src) throws IOException {
        int orientation = 1;

        if (Build.VERSION.SDK_INT >= 5) {
            ExifInterface exif = new ExifInterface(src);
            orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        }

        return orientation;
    }

    public static Bitmap createBitmapFromPath(Uri imgPath, Context context) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imgPath);
//            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(imgPath, false);
//            bitmap = decoder.decodeRegion(new Rect(10, 10, 50, 50), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        File sd = Environment.getExternalStorageDirectory();
//        File image = new File(imgPath, "hojoborolo.jpg");
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
//        bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
        return bitmap;
    }

    *//**
     * Must prepare this conditional logic to implement image resizing. Put one static maximum dimensional value in int
     * Check conditions according to that..
     *
     * @param oldHeight
     * @param oldWidth
     * @return
     *//*

    private static Dimension getDimenAccordingToAspectRatio(float oldHeight, float oldWidth) {
        Dimension aDimension = new Dimension();
        if (oldHeight > oldWidth) {
            float aspectRatio = (oldWidth / oldHeight);
            if (oldHeight >= StaticData.MAX_IMG_HEIGHT) {
                float newWidth = (StaticData.MAX_IMG_HEIGHT * aspectRatio);
                aDimension.setdHeight(StaticData.MAX_IMG_HEIGHT);
                aDimension.setdWidth(newWidth);
            } else {
                if (oldWidth >= StaticData.MAX_IMG_WIDTH) {
                    int newHeight = (int) (StaticData.MAX_IMG_WIDTH / aspectRatio);
                    aDimension.setdHeight(newHeight);
                    aDimension.setdWidth(StaticData.MAX_IMG_WIDTH);
                } else {
                    aDimension.setdHeight(oldHeight);
                    aDimension.setdWidth(oldWidth);
                }
            }
            return aDimension;
        } else if (oldHeight == oldWidth) {
            if (oldHeight >= StaticData.MAX_SQUARE_LENGTH) {
                aDimension.setdWidth(StaticData.MAX_SQUARE_LENGTH);
                aDimension.setdHeight(StaticData.MAX_SQUARE_LENGTH);
            } else {
                aDimension.setdHeight(oldHeight);
                aDimension.setdWidth(oldWidth);
            }
            return aDimension;
        } else {
            float aspectRatio = (oldHeight / oldWidth);
            if (oldWidth >= StaticData.MAX_IMG_HEIGHT) {
                int newHeight = (int) (StaticData.MAX_IMG_HEIGHT * aspectRatio);
                aDimension.setdWidth(StaticData.MAX_IMG_HEIGHT);
                aDimension.setdHeight(newHeight);
            } else {
                if (oldHeight >= StaticData.MAX_IMG_WIDTH) {
                    int newWidth = (int) (StaticData.MAX_IMG_WIDTH / aspectRatio);
                    aDimension.setdWidth(newWidth);
                    aDimension.setdHeight(StaticData.MAX_IMG_WIDTH);
                } else {
                    aDimension.setdHeight(oldHeight);
                    aDimension.setdWidth(oldWidth);
                }
            }
            return aDimension;
        }
    }

    public static boolean checkPermissions(Context context) {
        int resultWritePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int resultReadPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        int resultCameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (resultWritePermission == PackageManager.PERMISSION_GRANTED && resultCameraPermission == PackageManager.PERMISSION_GRANTED
                && resultReadPermission == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public static void requestPermission(Activity activity) {
        *//**
         * Previous denials will warrant a rationale for the user to help convince them.
         *//*
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                && ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)
                && ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(activity, "Christendom needs to access your external storage and camera to show images to select.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, StaticData.PERMISSION_REQUEST_CODE);
        }
    }

    public static void getImageFromDevice(Activity activity) {
        Intent intentt = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    *//*
                     * intentt.setType("image*//*");
					 * intentt.setAction(Intent.ACTION_GET_CONTENT);
					 *//*
        activity.startActivityForResult(
                Intent.createChooser(intentt, "Select Picture"),
                StaticData.SELECT_PICTURE);
    }

    public static boolean isOnlyBlankSpaces(String text) {
        if (text.trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void showSnackBar(CoordinatorLayout coordinatorLayout, final AppCompatActivity activity, final int requestCode) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_INDEFINITE)
                .setAction("SETTINGS", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activity.startActivityForResult(new Intent(Settings.ACTION_SETTINGS), requestCode);
                    }
                });

        snackbar.setActionTextColor(Color.RED);
// Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    private static Cache getCache(Context context) {
        File httpCacheDir;
        Cache cache = null;
        try {
            httpCacheDir = new File(context.getCacheDir(), "http");
            httpCacheDir.setReadable(true);
            long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
            HttpResponseCache.install(httpCacheDir, httpCacheSize);
            cache = new Cache(httpCacheDir, httpCacheSize);
            Log.i("HTTP Caching", "HTTP response cache installation success");
        } catch (IOException e) {
            Log.i("HTTP Caching", "HTTP response cache installation failed:" + e);
        }
        return cache;
    }

    public static OkHttpClient getClient(final Context context) {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .cache(getCache(context)) // 10 MB
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        if (ApplicationUtility.checkInternet(context)) {
                            request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
                        } else {
                            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                        }
                        return chain.proceed(request);
                    }
                })
                .build();
        return client;
    }*/

}
