package com.example.viewmodelexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// 뷰모델에 관해서 공부하기 위해 만든 예제 프로젝트다.
//뷰모델은 안드로이드에서 UI 관련 데이터를 관리해주는 역할을 한다. 액티비티, 프레그먼트의 생명주기에서 뷰와 관련된 데이터를 처리하는 것 보다
//뷰모델에서 관리하는게 더 편하다.
// 가로세로 전환할 때 마다 UI와 관련된 데이터가 초기화 되는 것을 확인할 수 있는데, 이를 saveInstanceState 를 통해서 해결할 수 있다.
//하지만 이 경우는 공식문서에서 50kb 미만의 데이터로 하기를 권장하고 있다. 그리고 onCreate 에서는 이런 작업까지 추가되면 사용자에게 화면을
//보여주는 시간이 더욱 지체된다.
//뷰모델은 안드로이드 측에서 제공해주는 JetPack 라이브러리중 하나다.

//예제 사용법
// '더하기' 버튼을 누를 때 마다 TextView 에 데이터가 1 씩 추가 된다.
//만약 ViewModel 을 쓰지 않았다면 화면 방향을 변환 했을 때 TextView 의 데이터가 다시 1이 되지만 그렇지 않다.
//ViewModel 을 쓰지 않았을때가 궁금한 경우가 있을 수 있으니 코드의 가장 아래쪽에 쓰지 않은 경우의 MainActivity 코드를 주석처리해서 남기겠다.

public class MainActivity extends AppCompatActivity{

    public static String TAG="MainActivity";

    TextView textView;
    Button button;

    NameViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);

        //뷰모델 객체를 생성
        viewModel = new ViewModelProvider(this, new NameViewModelFactory()).get(NameViewModel.class);

        //옵저버 정의 - 데이터가 변하는 것을 상시로 확인한다. 람다 형식으로 선언되어 있다.
        Observer<String> observer = newName -> textView.setText(newName);

        //뷰모델에 옵저버 등록
        viewModel.getCurrent_data().observe(this, observer);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(String.valueOf(Integer.parseInt(textView.getText().toString())+1));
                Log.v(TAG,"textView "+textView.getText().toString());

                String inputName = textView.getText().toString();
                viewModel.getCurrent_data().setValue(inputName);
            }
        });

    }

}


//// ViewModel 을 쓰지 않았을때를 분석하고 싶은 경우를 위해 남겨둔 코드.
//public class MainActivity extends AppCompatActivity{
//
//    public static String TAG="MainActivity";
//
//    TextView textView;
//    Button button;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        textView=findViewById(R.id.textView);
//        button=findViewById(R.id.button);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                textView.setText(String.valueOf(Integer.parseInt(textView.getText().toString())+1));
//                Log.v(TAG,"textView "+textView.getText().toString());
//            }
//        });
//
//    }
//
//}