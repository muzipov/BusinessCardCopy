package university.u.businesscardcopy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NewsDetailsActivity extends AppCompatActivity {
        ImageView imageDetails;
        TextView titleDetails;
        TextView textDetails;
        TextView dateDetails;

    private Toolbar toolbar;



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_news_details);

            imageDetails = findViewById(R.id.image_details);
            titleDetails = findViewById(R.id.title_details);
            textDetails = findViewById(R.id.text_details);
            dateDetails = findViewById(R.id.date_details);

            Intent intent = getIntent();

            setDataFromIntent(intent);
            setCategoryTitle(intent);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.app_name);

    }

        private void setDataFromIntent(Intent intent) {
            titleDetails.setText(intent.getStringExtra("TITLE_KEY"));
            textDetails.setText(intent.getStringExtra("TEXT_KEY"));
            dateDetails.setText(intent.getStringExtra("DATE_KEY"));
            String imageUrl = intent.getStringExtra("IMGURL_KEY");
            Glide.with(this).load(imageUrl).into(imageDetails);


        }

        private void setCategoryTitle(Intent intent) {
            String category = intent.getStringExtra("CATEGORY_KEY");
            setTitle(category);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about_activity) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        return true;
    }



}