package audioapk.com.example.android.farmertofarmer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailProcess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_process);

        TextView title = findViewById(R.id.titleDetail);

        ImageView image = findViewById(R.id.processImageDetail);

        // Set the text from the Intent extra.
        title.setText(getIntent().getStringExtra("title"));
        Toast.makeText(this,title.getText().toString(),Toast.LENGTH_SHORT).show();

        // Load the image using the Glide library and the Intent extra.
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0))
                .into(image);
    }

}
