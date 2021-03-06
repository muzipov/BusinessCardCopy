package university.u.businesscardcopy;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class AboutActivity extends AppCompatActivity {

    private static final String FB_LINK = "https://www.facebook.com";
    private static final String VK_LINK = "https://vk.com";
    private static final String[] EMAIL_ADRESS = {"muzipov@gmail.com"};

    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle(R.string.name_text);

//       Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.name_text);
//
//        // Add disclaimer from java code to bottom
//        LinearLayout mainLayout = findViewById(R.id.content);
//
//        TextView copyrightText = new TextView(this);
//        copyrightText.setText(R.string.copyright_text);
//
//        LinearLayout.LayoutParams mainLayoutParams = new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, // width
//                ViewGroup.LayoutParams.MATCH_PARENT); // height
//
//        mainLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
//
//        copyrightText.setLayoutParams(mainLayoutParams);
//
//        mainLayout.addView(copyrightText);

        //Send Email
        final TextView editText = findViewById(R.id.editText);

        final String subjectText = getString(R.string.subjectText) + getString(R.string.app_name);

        Button emailButton = findViewById(R.id.buttonSend);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = editText.getText().toString();
                composeEmail(EMAIL_ADRESS, subjectText, messageText);
            }
        });

        //Open Links
        ImageView fbButton = findViewById(R.id.fbIcon);
        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage(FB_LINK);
            }
        });

        ImageView vkButton = findViewById(R.id.vkIcon);
        vkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage(VK_LINK);
            }
        });
    }


    public void composeEmail(String[] address, String subject, String textBody) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, textBody);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Toast.makeText(this, R.string.noAppMessage, Toast.LENGTH_LONG).show();
        }
    }

    public void openWebPage(@NonNull String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Toast.makeText(this, R.string.noAppMessage, Toast.LENGTH_LONG).show();
        }
    }

}
