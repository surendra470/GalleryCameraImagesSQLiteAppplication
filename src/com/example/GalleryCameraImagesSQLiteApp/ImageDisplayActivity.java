package com.example.GalleryCameraImagesSQLiteApp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by SURENDRA REDDY on 10/14/2015.
 */
public class ImageDisplayActivity extends Activity {
    Button btnDelete;
    ImageView imageDetail;
    int imageId;
    Bitmap theImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        imageDetail = (ImageView) findViewById(R.id.imageView1);
        /**
         * getting intent data from search and previous screen
         */
        Intent intnt = getIntent();
        theImage = (Bitmap) intnt.getParcelableExtra("imagename");
        imageId = intnt.getIntExtra("imageid", 20);
        Log.d("Image ID:****", String.valueOf(imageId));
        imageDetail.setImageBitmap(theImage);
        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /**
                 * create DatabaseHandler object
                 */
                DataBaseHandler db = new DataBaseHandler(
                        ImageDisplayActivity.this);
                /**
                 * Deleting records from database
                 */
                Log.d("Delete Image: ", "Deleting.....");
                db.deleteContact(new Contact(imageId));
                // /after deleting data go to main page
                Intent i = new Intent(ImageDisplayActivity.this,
                       MyActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
