package audioapk.com.example.android.farmertofarmer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailProcess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_process);

        Bundle bundle = getIntent().getExtras();

        TextView title = findViewById(R.id.titleDetail),
                description = findViewById(R.id.process_description_detail),
                date = findViewById(R.id.dateDetail);
        assert bundle != null;
        title.setText(bundle.getString("title"));
        description.setText(bundle.getString("description"));
        date.setText(bundle.getString("date"));




    }

}
