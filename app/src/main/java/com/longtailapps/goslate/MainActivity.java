package com.longtailapps.goslate;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.longtailapps.goslate.rest.Translate;


public class MainActivity extends Activity {

    private Button translateBtn;
    private Button switchBtn;
    private String url = "http://goslateapi-info.herokuapp.com/api/word/?format=json";
    private EditText input;
    private String language;
    private TextView to;
    private TextView from;
    private TextView translatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkblue)));
        translateBtn = (Button)findViewById(R.id.translateBtn);
        switchBtn= (Button)findViewById(R.id.switchBtn);
        translateBtn.setOnClickListener(onClickListener);
        switchBtn.setOnClickListener(onClickListener);
        input = (EditText)findViewById(R.id.editText);
        to =(TextView)findViewById(R.id.to);
        language = to.getText().toString();
        from = (TextView)findViewById(R.id.from);
        translatedText=(TextView)findViewById(R.id.translated);

    }


    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.translateBtn:
                    if(!input.getText().toString().equals("")) {
                        new Translate(translatedText).execute(url, input.getText().toString(), language);
                    }else {
                        Toast.makeText(getApplicationContext(),"Enter Text", Toast.LENGTH_SHORT ).show();
                    }
                    break;
                case R.id.switchBtn:
                    if(language.equals("DE")){
                            language = "ENG";
                            to.setText("ENG");
                            from.setText("DE");

                    }else if(language.equals("ENG")){
                        language = "DE";
                        to.setText("DE");
                        from.setText("ENG");
                    }
                    break;
            }
        }
    };

}
