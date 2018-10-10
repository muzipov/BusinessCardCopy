package university.u.businesscardcopy;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class NewsListActivity extends AppCompatActivity {

    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setAdapter(new NewsRecyclersAdapter(this, DataUtils.generateNews()));

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else{
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        toolbar.inflateMenu(R.menu.menu);
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


