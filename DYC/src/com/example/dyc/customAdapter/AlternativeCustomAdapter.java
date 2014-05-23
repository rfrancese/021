package com.example.dyc.customAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.example.dyc.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AlternativeCustomAdapter extends BaseAdapter {

	private LayoutInflater inflater = null;
    private Context context = null;
    ImageView thumb_image = null;
    private ProgressDialog progressbar = null;

    ArrayList<String> items;
    ArrayList<String> thumb_url;
     public AlternativeCustomAdapter(Context c, ArrayList<String> list, ArrayList<String> thumb) 
     {
         this.context = c;
         this.items = list;
         this.thumb_url = thumb;
         progressbar = new ProgressDialog(c);
         Log.d("Testing", "talk of town adapter constructor   "+items.size());
         inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

     }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_layout, null);
            Log.d("Testing", "before creating holder object");
            holder = new ViewHolder();
            holder.headlineView = (TextView) convertView.findViewById(R.id.title);
            holder.duration = (TextView)convertView.findViewById(R.id.secondLine);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            Log.d("Testing", "image view created ::::::::   ");
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.d("Testing", "text:::   "+items.get(position));
        holder.headlineView.setText(items.get(position));
        Log.d("Testing", "settting the text   ");
        holder.duration.setText("22/09/1987");

        if (thumb_url.get(position) != null) {
            Log.d("Testing", "getting the image  "+thumb_url.get(position));
            new ImageDownloaderTask(holder.imageView).execute(thumb_url.get(position));
        }

        return convertView;
    }

    static class ViewHolder {
        TextView headlineView;
        TextView duration;
        ImageView imageView;
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.d("Testing", "image loaded  "+myBitmap);
//          myBitmap = Bitmap.createBitmap(100, 50, Config.ARGB_8888);
            myBitmap = Bitmap.createScaledBitmap(myBitmap,(int)(myBitmap.getWidth()), (int)(myBitmap.getHeight()), true);
            return myBitmap;
        } catch (IOException e) {
            Log.d("Testing", "exception is getting the image "+e.toString());
            e.printStackTrace();
            return null;
        }
    }
    public void startProgress()
    {
        progressbar.setMessage("Please wait");
        progressbar.show();
    }

    public void stopProgress()
    {
        progressbar.dismiss();
    }

    class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;

        public ImageDownloaderTask(ImageView imageView) {
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        // Actual download method, run in the task thread
        protected Bitmap doInBackground(String... params) {
            // params comes from the execute() call: params[0] is the url.
            return getBitmapFromURL(params[0]);
        }

        @Override
        // Once the image is downloaded, associates it to the imageView
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            if (imageViewReference != null) {
                ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    } else {
                        imageView.setImageDrawable(imageView.getContext().getResources()
                                .getDrawable(R.drawable.logo2));
                    }
                }

            }
        }
    }

}
