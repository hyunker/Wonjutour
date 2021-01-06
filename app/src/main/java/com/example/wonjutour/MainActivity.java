package com.example.wonjutour;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.PathOverlay;

import android.support.v4.content.ContextCompat;
import android.speech.tts.TextToSpeech;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Button;

import java.util.Arrays;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private NaverMap mMap;
    private TextToSpeech tts;
    LatLng prev_LOC = null;
    LatLng curr_LOC;
    Marker mk = new Marker();
    Button button;
    SpeechRecognizer mRecognizer;
    TextView textView;
    Intent intent;
    ImageView iv;

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

        button = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.tv);
        iv = (ImageView)findViewById(R.id.iv);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");

        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mRecognizer.setRecognitionListener(recognitionListener);
    }

    public void recognizeVoice(View view) {
        button.setText("말하는 중...");
        mRecognizer.startListening(intent);
    }

    private RecognitionListener recognitionListener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle bundle) {
            button.setText("어디로 가시나요~~?");
            textView.setText("");
        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float v) {

        }

        @Override
        public void onBufferReceived(byte[] bytes) {

        }

        @Override
        public void onEndOfSpeech() {
            button.setText("원하시는 관광지를 말씀해주세요.");
        }

        @Override
        public void onError(int i) {
            textView.setText("다시 말씀해주세요.");
        }

        @Override
        public void onResults(Bundle bundle) {
            ArrayList<String> mResult = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);

            textView.setText(rs[0]);


            if (rs[0].equals("뮤지엄 산")) {
                iv.setImageResource(R.drawable.mu1);
                textView.setText(R.string.title01);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.415276, 127.823592));
                mMap.moveCamera(cameraUpdate);
                mMap.setMinZoom(14.5);
                mMap.setMaxZoom(14.5);
            } else if (rs[0].equals("출렁다리")) {
                iv.setImageResource(R.drawable.solt2);
                textView.setText(R.string.title02);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.363832, 127.833368));
                mMap.moveCamera(cameraUpdate);
                mMap.setMinZoom(14.5);
                mMap.setMaxZoom(14.5);
            } else if (rs[0].equals("치악산")) {
                iv.setImageResource(R.drawable.san3);
                textView.setText(R.string.title03);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.372064, 128.050338));
                mMap.moveCamera(cameraUpdate);
                mMap.setMinZoom(14.5);
                mMap.setMaxZoom(14.5);
            } else if (rs[0].equals("강원감영")) {
                iv.setImageResource(R.drawable.dudrka4);
                textView.setText(R.string.title04);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.347993, 127.950637));
                mMap.setMinZoom(14.5);
                mMap.setMaxZoom(14.5);
                mMap.moveCamera(cameraUpdate);
            }  else if (rs[0].equals("미로시장")) {
                iv.setImageResource(R.drawable.miro5);
                textView.setText(R.string.title05);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.349930, 127.949468));
                mMap.moveCamera(cameraUpdate);
                mMap.setMinZoom(14.5);
                mMap.setMaxZoom(14.5);
            } else if (rs[0].equals("수변공원")) {
                iv.setImageResource(R.drawable.tnqus6);
                textView.setText(R.string.title06);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.342499, 127.996175));
                mMap.moveCamera(cameraUpdate);
                mMap.setMinZoom(14.5);
                mMap.setMaxZoom(14.5);
            } else if (rs[0].equals("한지 파크")) {
                iv.setImageResource(R.drawable.hanji7);
                textView.setText(R.string.title07);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.334508, 127.935361));
                mMap.moveCamera(cameraUpdate);
                mMap.setMinZoom(14.5);
                mMap.setMaxZoom(14.5);
            } else if (rs[0].equals("문학공원")) {
                iv.setImageResource(R.drawable.park8);
                textView.setText(R.string.title08);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.322493, 127.959537));
                mMap.moveCamera(cameraUpdate);
                mMap.setMinZoom(14.5);
                mMap.setMaxZoom(14.5);
            } else if (rs[0].equals("태장동")) {
                iv.setImageResource(R.drawable.hi);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.376526, 127.951031));
                mMap.moveCamera(cameraUpdate);
            } else if (rs[0].equals("우산동")) {
                iv.setImageResource(R.drawable.hi);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.369966, 127.936388));
                mMap.moveCamera(cameraUpdate);
            } else if (rs[0].equals("학성동")) {
                iv.setImageResource(R.drawable.hi);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.356877, 127.939850));
                mMap.moveCamera(cameraUpdate);
            } else if (rs[0].equals("단계동")) {
                iv.setImageResource(R.drawable.hi);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.351935, 127.928573));
                mMap.moveCamera(cameraUpdate);
            } else if (rs[0].equals("무실동")) {
                iv.setImageResource(R.drawable.hi);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.335640, 127.929769));
                mMap.moveCamera(cameraUpdate);
            } else if (rs[0].equals("명륜동")) {
                iv.setImageResource(R.drawable.hi);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.334924, 127.944089));
                mMap.moveCamera(cameraUpdate);
            } else if (rs[0].equals("단구동")) {
                iv.setImageResource(R.drawable.hi);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.327786, 127.954108));
                mMap.moveCamera(cameraUpdate);
            } else if (rs[0].equals("반곡동")) {
                iv.setImageResource(R.drawable.hi);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.325227, 127.981654));
                mMap.moveCamera(cameraUpdate);
            } else if (rs[0].equals("관설동")) {
                iv.setImageResource(R.drawable.hi);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.310891, 127.975913));
                mMap.moveCamera(cameraUpdate);
            } else if (rs[0].equals("행구동")) {
                iv.setImageResource(R.drawable.hi);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.343598, 127.994701));
                mMap.moveCamera(cameraUpdate);
            } else if (rs[0].equals("개운동")) {
                iv.setImageResource(R.drawable.hi);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.338914, 127.955620));
                mMap.moveCamera(cameraUpdate);
            } else if (rs[0].equals("중앙동")) {
                iv.setImageResource(R.drawable.hi);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.352455, 127.950427));
                mMap.moveCamera(cameraUpdate);
            } else if (rs[0].equals("봉산동")) {
                iv.setImageResource(R.drawable.hi);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.344378, 127.965118));
                mMap.moveCamera(cameraUpdate);
            } else if (rs[0].equals("확인")){
                iv.setImageResource(R.drawable.hi);
                textView.setText(R.string.title09);
                mMap.setMinZoom(5);
                mMap.setMaxZoom(18);
            } else if (rs[0].equals("내 위치")){
                iv.setImageResource(R.drawable.hi);
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(curr_LOC);
                mMap.moveCamera(cameraUpdate);
                mMap.setMinZoom(5);
                mMap.setMaxZoom(18);
            }
        }

        @Override
        public void onPartialResults(Bundle bundle) {

        }

        @Override
        public void onEvent(int i, Bundle bundle) {

        }
    };

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        mMap = naverMap;

        LatLng latlng1 = new LatLng(37.415276, 127.823592);
        LatLng latlng2 = new LatLng(37.363832, 127.833368);
        LatLng latlng3 = new LatLng(37.372064, 128.050338);
        LatLng latlng4 = new LatLng(37.347993, 127.950637);
        LatLng latlng5 = new LatLng(37.349930, 127.949468);
        LatLng latlng6 = new LatLng(37.342499, 127.996175);
        LatLng latlng7 = new LatLng(37.334508, 127.935361);
        LatLng latlng8 = new LatLng(37.322493, 127.959537);
        LatLng latlng9 = new LatLng(37.344336, 127.930822); // 터미널
        LatLng latlng10 = new LatLng(37.345539, 127.927743); // AK
        LatLng latlng11 = new LatLng(37.347185, 127.931992); // 단계택지
        LatLng latlng12 = new LatLng(37.348942, 127.926692); // 봉화산택지
        LatLng latlng13 = new LatLng(37.373328, 127.950384); // 안흥숯불고기막국수(태장동)
        LatLng latlng14 = new LatLng(37.397885, 127.952957); // 치악기사식당
        LatLng latlng15 = new LatLng(37.383999, 127.951352); // 원포차
        LatLng latlng16 = new LatLng(37.357913, 127.957601); // 정환이네구이
        LatLng latlng17 = new LatLng(37.370094, 127.935204); // 돈내고돈먹기(우산동)
        LatLng latlng18 = new LatLng(37.369705, 127.936395); // 지뎅
        LatLng latlng19 = new LatLng(37.367997, 127.936649); // 마미손
        LatLng latlng20 = new LatLng(37.368358, 127.938828); // 나우구이집
        LatLng latlng21 = new LatLng(37.370348, 127.939887); // 돈까스식당
        LatLng latlng22 = new LatLng(37.369147, 127.936970); // 삼천냥삼겹살
        LatLng latlng23 = new LatLng(37.352633, 127.948651); // 고인돌(학성동)
        LatLng latlng24 = new LatLng(37.356576, 127.941073); // 동해막국수
        LatLng latlng25 = new LatLng(37.348700, 127.925792); // 민혁이네외국포차(단계동)
        LatLng latlng26 = new LatLng(37.347329, 127.930289); // 치악산맛집
        LatLng latlng27 = new LatLng(37.349599, 127.926319); // 순용이네곱창
        LatLng latlng28 = new LatLng(37.348004, 127.929645); // 저팔계
        LatLng latlng29 = new LatLng(37.346980, 127.927613); // 항아리보쌈
        LatLng latlng30 = new LatLng(37.347668, 127.932669); // 우성닭갈비
        LatLng latlng31 = new LatLng(37.333814, 127.930245); // 라무진(무실동)
        LatLng latlng32 = new LatLng(37.338547, 127.928141); // 우담
        LatLng latlng33 = new LatLng(37.333804, 127.929094); // 카쿠레라
        LatLng latlng34 = new LatLng(37.333598, 127.929824); // 규규
        LatLng latlng35 = new LatLng(37.338435, 127.951404); // 대관령갈비명가(명륜동)
        LatLng latlng36 = new LatLng(37.337598, 127.948226); // 향교막국수
        LatLng latlng37 = new LatLng(37.336902, 127.956194); // 까치둥지(단구동)
        LatLng latlng38 = new LatLng(37.323030, 127.959076); // 영순이네해물찜
        LatLng latlng39 = new LatLng(37.321056, 127.965119); // 예지현
        LatLng latlng40 = new LatLng(37.323668, 127.958020); // 땡벌해장국
        LatLng latlng41 = new LatLng(37.327967, 127.988150); // 고에이프(반곡동)
        LatLng latlng42 = new LatLng(37.316536, 127.959146); // 도화게장장어한정식(관설동)
        LatLng latlng43 = new LatLng(37.317331, 127.962174); // 삼부자집
        LatLng latlng44 = new LatLng(37.317335, 127.962173); // 신촌막국수
        LatLng latlng45 = new LatLng(37.317335, 127.962173); // 청정고을명가
        LatLng latlng46 = new LatLng(37.343268, 127.989999); // 홍익돈까스(행구동)
        LatLng latlng47 = new LatLng(37.342917, 127.990838); // 라뜰리에
        LatLng latlng48 = new LatLng(37.343613, 127.989836); // 베이커리 궁
        LatLng latlng49 = new LatLng(37.337627, 127.951372); // 수가성순두부(개운동)
        LatLng latlng50 = new LatLng(37.328062, 127.960455); // 화미당
        LatLng latlng51 = new LatLng(37.350431, 127.949077); // 신혼부부(중앙동)
        LatLng latlng52 = new LatLng(37.350431, 127.949077); // 아무리찾아도찾기힘든집
        LatLng latlng53 = new LatLng(37.350431, 127.949077); // 강릉집
        LatLng latlng54 = new LatLng(37.350431, 127.949077); // 예미
        LatLng latlng55 = new LatLng(37.343646, 127.963649); // 원주뼈대있는짬뽕(봉산동)
        LatLng latlng56 = new LatLng(37.342568, 127.967818); // 흑차돌
        LatLng latlng57 = new LatLng(37.342568, 127.967818); // 흙심흑염소


        // 1번
        Marker marker1 = new Marker();
        marker1.setPosition(latlng1);
        marker1.setMap(naverMap);
        marker1.setWidth(70);
        marker1.setHeight(100);
        marker1.setIconTintColor(Color.BLUE);

        InfoWindow infoWindow1 = new InfoWindow();
        infoWindow1.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "뮤지엄 산";

            }
        });
        infoWindow1.open(marker1);

        // 2번
        Marker marker2 = new Marker();
        marker2.setPosition(latlng2);
        marker2.setMap(naverMap);
        marker2.setWidth(70);
        marker2.setHeight(100);
        marker2.setIconTintColor(Color.BLUE);

        InfoWindow infoWindow2 = new InfoWindow();
        infoWindow2.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "원주 소금산 출렁다리\n간현 레일파크";
            }
        });
        infoWindow2.open(marker2);

        // 3번
        Marker marker3 = new Marker();
        marker3.setPosition(latlng3);
        marker3.setMap(naverMap);
        marker3.setWidth(70);
        marker3.setHeight(100);
        marker3.setIconTintColor(Color.BLUE);

        InfoWindow infoWindow3 = new InfoWindow();
        infoWindow3.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "치악산 국립공원";
            }
        });
        infoWindow3.open(marker3);

        // 4번
        Marker marker4 = new Marker();
        marker4.setPosition(latlng4);
        marker4.setMap(naverMap);
        marker4.setWidth(70);
        marker4.setHeight(100);
        marker4.setIconTintColor(Color.BLUE);

        InfoWindow infoWindow4 = new InfoWindow();
        infoWindow4.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "원주 강원감영";
            }
        });
        infoWindow4.open(marker4);

        // 5번
        Marker marker5 = new Marker();
        marker5.setPosition(latlng5);
        marker5.setMap(naverMap);
        marker5.setWidth(70);
        marker5.setHeight(100);
        marker5.setIconTintColor(Color.BLUE);

        InfoWindow infoWindow5 = new InfoWindow();
        infoWindow5.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "원주 미로예술시장";
            }
        });
        infoWindow5.open(marker5);

        // 6번
        Marker marker6 = new Marker();
        marker6.setPosition(latlng6);
        marker6.setMap(naverMap);
        marker6.setWidth(70);
        marker6.setHeight(100);
        marker6.setIconTintColor(Color.BLUE);

        InfoWindow infoWindow6 = new InfoWindow();
        infoWindow6.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "행구 수변공원\n원주기후변화홍보관";
            }
        });
        infoWindow6.open(marker6);

        // 7번
        Marker marker7 = new Marker();
        marker7.setPosition(latlng7);
        marker7.setMap(naverMap);
        marker7.setWidth(70);
        marker7.setHeight(100);
        marker7.setIconTintColor(Color.BLUE);

        InfoWindow infoWindow7 = new InfoWindow();
        infoWindow7.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "원주 한지테마파크";
            }
        });
        infoWindow7.open(marker7);

        // 8번
        Marker marker8 = new Marker();
        marker8.setPosition(latlng8);
        marker8.setMap(naverMap);
        marker8.setWidth(70);
        marker8.setHeight(100);
        marker8.setIconTintColor(Color.BLUE);

        InfoWindow infoWindow8 = new InfoWindow();
        infoWindow8.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "박경리 문학공원";
            }
        });
        infoWindow8.open(marker8);

        //9번
        Marker marker9 = new Marker();
        marker9.setPosition(latlng9);
        marker9.setMap(naverMap);
        marker9.setWidth(70);
        marker9.setHeight(100);

        InfoWindow infoWindow9 = new InfoWindow();
        infoWindow9.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "터미널";
            }
        });
        infoWindow9.open(marker9);

        //10번
        Marker marker10 = new Marker();
        marker10.setPosition(latlng10);
        marker10.setMap(naverMap);
        marker10.setWidth(70);
        marker10.setHeight(100);

        InfoWindow infoWindow10 = new InfoWindow();
        infoWindow10.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "AK백화점";
            }
        });
        infoWindow10.open(marker10);

        //11번
        Marker marker11 = new Marker();
        marker11.setPosition(latlng11);
        marker11.setMap(naverMap);
        marker11.setWidth(70);
        marker11.setHeight(100);

        InfoWindow infoWindow11 = new InfoWindow();
        infoWindow11.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "단계택지";
            }
        });
        infoWindow11.open(marker11);


        //12번
        Marker marker12 = new Marker();
        marker12.setPosition(latlng12);
        marker12.setMap(naverMap);
        marker12.setWidth(70);
        marker12.setHeight(100);

        InfoWindow infoWindow12 = new InfoWindow();
        infoWindow12.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "봉화산택지";
            }
        });
        infoWindow12.open(marker12);

        //13번
        Marker marker13 = new Marker();
        marker13.setPosition(latlng13);
        marker13.setMap(naverMap);
        marker13.setWidth(70);
        marker13.setHeight(100);

        InfoWindow infoWindow13 = new InfoWindow();
        infoWindow13.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "안흥숯불고기막국수";
            }
        });
        infoWindow13.open(marker13);

        //14번
        Marker marker14 = new Marker();
        marker14.setPosition(latlng14);
        marker14.setMap(naverMap);
        marker14.setWidth(70);
        marker14.setHeight(100);

        InfoWindow infoWindow14 = new InfoWindow();
        infoWindow14.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "치악기사식당";
            }
        });
        infoWindow14.open(marker14);

        //15번
        Marker marker15 = new Marker();
        marker15.setPosition(latlng15);
        marker15.setMap(naverMap);
        marker15.setWidth(70);
        marker15.setHeight(100);

        InfoWindow infoWindow15 = new InfoWindow();
        infoWindow15.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "원포차";
            }
        });
        infoWindow15.open(marker15);

        //16번
        Marker marker16 = new Marker();
        marker16.setPosition(latlng16);
        marker16.setMap(naverMap);
        marker16.setWidth(70);
        marker16.setHeight(100);

        InfoWindow infoWindow16 = new InfoWindow();
        infoWindow16.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "정환이네구이";
            }
        });
        infoWindow16.open(marker16);

        //17번
        Marker marker17 = new Marker();
        marker17.setPosition(latlng17);
        marker17.setMap(naverMap);
        marker17.setWidth(70);
        marker17.setHeight(100);

        InfoWindow infoWindow17 = new InfoWindow();
        infoWindow17.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "돈내고돈먹기";
            }
        });
        infoWindow17.open(marker17);

        //18번
        Marker marker18 = new Marker();
        marker18.setPosition(latlng18);
        marker18.setMap(naverMap);
        marker18.setWidth(70);
        marker18.setHeight(100);

        InfoWindow infoWindow18 = new InfoWindow();
        infoWindow18.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "지뎅";
            }
        });
        infoWindow18.open(marker18);

        //19번
        Marker marker19 = new Marker();
        marker19.setPosition(latlng19);
        marker19.setMap(naverMap);
        marker19.setWidth(70);
        marker19.setHeight(100);

        InfoWindow infoWindow19 = new InfoWindow();
        infoWindow19.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "마미손";
            }
        });
        infoWindow19.open(marker19);

        //20번
        Marker marker20 = new Marker();
        marker20.setPosition(latlng20);
        marker20.setMap(naverMap);
        marker20.setWidth(70);
        marker20.setHeight(100);

        InfoWindow infoWindow20 = new InfoWindow();
        infoWindow20.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "나우구이집";
            }
        });
        infoWindow20.open(marker20);

        //21번
        Marker marker21 = new Marker();
        marker21.setPosition(latlng21);
        marker21.setMap(naverMap);
        marker21.setWidth(70);
        marker21.setHeight(100);

        InfoWindow infoWindow21 = new InfoWindow();
        infoWindow21.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) { return "돈까스식당";
            }
        });
        infoWindow21.open(marker21);

        //22번
        Marker marker22 = new Marker();
        marker22.setPosition(latlng22);
        marker22.setMap(naverMap);
        marker22.setWidth(70);
        marker22.setHeight(100);

        InfoWindow infoWindow22 = new InfoWindow();
        infoWindow22.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) { return "삼천냥삼겹살";
            }
        });
        infoWindow22.open(marker22);

        //23번
        Marker marker23 = new Marker();
        marker23.setPosition(latlng23);
        marker23.setMap(naverMap);
        marker23.setWidth(70);
        marker23.setHeight(100);

        InfoWindow infoWindow23 = new InfoWindow();
        infoWindow23.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "고인돌";
            }
        });
        infoWindow23.open(marker23);

        //24번
        Marker marker24 = new Marker();
        marker24.setPosition(latlng24);
        marker24.setMap(naverMap);
        marker24.setWidth(70);
        marker24.setHeight(100);

        InfoWindow infoWindow24 = new InfoWindow();
        infoWindow24.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "동해막국수";
            }
        });
        infoWindow24.open(marker24);

        //25번
        Marker marker25 = new Marker();
        marker25.setPosition(latlng25);
        marker25.setMap(naverMap);
        marker25.setWidth(70);
        marker25.setHeight(100);

        InfoWindow infoWindow25 = new InfoWindow();
        infoWindow25.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "민혁이네외국포차";
            }
        });
        infoWindow25.open(marker25);

        //26번
        Marker marker26 = new Marker();
        marker26.setPosition(latlng26);
        marker26.setMap(naverMap);
        marker26.setWidth(70);
        marker26.setHeight(100);

        InfoWindow infoWindow26 = new InfoWindow();
        infoWindow26.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "치악산맛집";
            }
        });
        infoWindow26.open(marker26);

        //27번
        Marker marker27 = new Marker();
        marker27.setPosition(latlng27);
        marker27.setMap(naverMap);
        marker27.setWidth(70);
        marker27.setHeight(100);

        InfoWindow infoWindow27 = new InfoWindow();
        infoWindow27.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "순용이네곱창";
            }
        });
        infoWindow27.open(marker27);

        //28번
        Marker marker28 = new Marker();
        marker28.setPosition(latlng28);
        marker28.setMap(naverMap);
        marker28.setWidth(70);
        marker28.setHeight(100);

        InfoWindow infoWindow28 = new InfoWindow();
        infoWindow28.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "저팔계";
            }
        });
        infoWindow28.open(marker28);

        //29번
        Marker marker29 = new Marker();
        marker29.setPosition(latlng29);
        marker29.setMap(naverMap);
        marker29.setWidth(70);
        marker29.setHeight(100);

        InfoWindow infoWindow29 = new InfoWindow();
        infoWindow29.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "항아리보쌈";
            }
        });
        infoWindow29.open(marker29);

        //30번
        Marker marker30 = new Marker();
        marker30.setPosition(latlng30);
        marker30.setMap(naverMap);
        marker30.setWidth(70);
        marker30.setHeight(100);

        InfoWindow infoWindow30 = new InfoWindow();
        infoWindow30.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "우성닭갈비";
            }
        });
        infoWindow30.open(marker30);

        //31번
        Marker marker31 = new Marker();
        marker31.setPosition(latlng31);
        marker31.setMap(naverMap);
        marker31.setWidth(70);
        marker31.setHeight(100);

        InfoWindow infoWindow31 = new InfoWindow();
        infoWindow31.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "라무진";
            }
        });
        infoWindow31.open(marker31);

        //32번
        Marker marker32 = new Marker();
        marker32.setPosition(latlng32);
        marker32.setMap(naverMap);
        marker32.setWidth(70);
        marker32.setHeight(100);

        InfoWindow infoWindow32 = new InfoWindow();
        infoWindow32.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "우담";
            }
        });
        infoWindow32.open(marker32);

        //33번
        Marker marker33 = new Marker();
        marker33.setPosition(latlng33);
        marker33.setMap(naverMap);
        marker33.setWidth(70);
        marker33.setHeight(100);

        InfoWindow infoWindow33 = new InfoWindow();
        infoWindow33.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "카쿠레라";
            }
        });
        infoWindow33.open(marker33);

        //34번
        Marker marker34 = new Marker();
        marker34.setPosition(latlng34);
        marker34.setMap(naverMap);
        marker34.setWidth(70);
        marker34.setHeight(100);

        InfoWindow infoWindow34 = new InfoWindow();
        infoWindow34.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "규규";
            }
        });
        infoWindow34.open(marker34);

        //35번
        Marker marker35 = new Marker();
        marker35.setPosition(latlng35);
        marker35.setMap(naverMap);
        marker35.setWidth(70);
        marker35.setHeight(100);

        InfoWindow infoWindow35 = new InfoWindow();
        infoWindow35.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "대관령갈비명가";
            }
        });
        infoWindow35.open(marker35);

        //36번
        Marker marker36 = new Marker();
        marker36.setPosition(latlng36);
        marker36.setMap(naverMap);
        marker36.setWidth(70);
        marker36.setHeight(100);

        InfoWindow infoWindow36 = new InfoWindow();
        infoWindow36.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "향교막국수";
            }
        });
        infoWindow36.open(marker36);

        //37번
        Marker marker37 = new Marker();
        marker37.setPosition(latlng37);
        marker37.setMap(naverMap);
        marker37.setWidth(70);
        marker37.setHeight(100);

        InfoWindow infoWindow37 = new InfoWindow();
        infoWindow37.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "까치둥지";
            }
        });
        infoWindow37.open(marker37);

        //38번
        Marker marker38 = new Marker();
        marker38.setPosition(latlng38);
        marker38.setMap(naverMap);
        marker38.setWidth(70);
        marker38.setHeight(100);

        InfoWindow infoWindow38 = new InfoWindow();
        infoWindow38.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "영순이네해물찜";
            }
        });
        infoWindow38.open(marker38);

        //39번
        Marker marker39 = new Marker();
        marker39.setPosition(latlng39);
        marker39.setMap(naverMap);
        marker39.setWidth(70);
        marker39.setHeight(100);

        InfoWindow infoWindow39 = new InfoWindow();
        infoWindow39.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "예지현";
            }
        });
        infoWindow39.open(marker39);

        //40번
        Marker marker40 = new Marker();
        marker40.setPosition(latlng40);
        marker40.setMap(naverMap);
        marker40.setWidth(70);
        marker40.setHeight(100);

        InfoWindow infoWindow40 = new InfoWindow();
        infoWindow40.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "땡벌해장국";
            }
        });
        infoWindow40.open(marker40);

        //41번
        Marker marker41 = new Marker();
        marker41.setPosition(latlng41);
        marker41.setMap(naverMap);
        marker41.setWidth(70);
        marker41.setHeight(100);

        InfoWindow infoWindow41 = new InfoWindow();
        infoWindow41.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "고에이프";
            }
        });
        infoWindow41.open(marker41);

        //42번
        Marker marker42 = new Marker();
        marker42.setPosition(latlng42);
        marker42.setMap(naverMap);
        marker42.setWidth(70);
        marker42.setHeight(100);

        InfoWindow infoWindow42 = new InfoWindow();
        infoWindow42.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "도화게장한정식";
            }
        });
        infoWindow42.open(marker42);

        //43번
        Marker marker43 = new Marker();
        marker43.setPosition(latlng43);
        marker43.setMap(naverMap);
        marker43.setWidth(70);
        marker43.setHeight(100);

        InfoWindow infoWindow43 = new InfoWindow();
        infoWindow43.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "삼부자집";
            }
        });
        infoWindow43.open(marker43);

        //44번
        Marker marker44 = new Marker();
        marker44.setPosition(latlng44);
        marker44.setMap(naverMap);
        marker44.setWidth(70);
        marker44.setHeight(100);

        InfoWindow infoWindow44 = new InfoWindow();
        infoWindow44.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "신촌막국수";
            }
        });
        infoWindow44.open(marker44);

        //45번
        Marker marker45 = new Marker();
        marker45.setPosition(latlng45);
        marker45.setMap(naverMap);
        marker45.setWidth(70);
        marker45.setHeight(100);

        InfoWindow infoWindow45 = new InfoWindow();
        infoWindow45.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "청정고을명가";
            }
        });
        infoWindow45.open(marker45);

        //46번
        Marker marker46 = new Marker();
        marker46.setPosition(latlng46);
        marker46.setMap(naverMap);
        marker46.setWidth(70);
        marker46.setHeight(100);

        InfoWindow infoWindow46 = new InfoWindow();
        infoWindow46.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "홍익돈까스";
            }
        });
        infoWindow46.open(marker46);

        //47번
        Marker marker47 = new Marker();
        marker47.setPosition(latlng47);
        marker47.setMap(naverMap);
        marker47.setWidth(70);
        marker47.setHeight(100);

        InfoWindow infoWindow47 = new InfoWindow();
        infoWindow47.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "라뜰리에";
            }
        });
        infoWindow47.open(marker47);

        //48번
        Marker marker48 = new Marker();
        marker48.setPosition(latlng48);
        marker48.setMap(naverMap);
        marker48.setWidth(70);
        marker48.setHeight(100);

        InfoWindow infoWindow48 = new InfoWindow();
        infoWindow48.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "베이커리 궁";
            }
        });
        infoWindow48.open(marker48);

        //49번
        Marker marker49 = new Marker();
        marker49.setPosition(latlng49);
        marker49.setMap(naverMap);
        marker49.setWidth(70);
        marker49.setHeight(100);

        InfoWindow infoWindow49 = new InfoWindow();
        infoWindow49.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "수가성순두부";
            }
        });
        infoWindow49.open(marker49);

        //50번
        Marker marker50 = new Marker();
        marker50.setPosition(latlng50);
        marker50.setMap(naverMap);
        marker50.setWidth(70);
        marker50.setHeight(100);

        InfoWindow infoWindow50 = new InfoWindow();
        infoWindow50.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "화미당";
            }
        });
        infoWindow50.open(marker50);

        //51번
        Marker marker51 = new Marker();
        marker51.setPosition(latlng51);
        marker51.setMap(naverMap);
        marker51.setWidth(70);
        marker51.setHeight(100);

        InfoWindow infoWindow51 = new InfoWindow();
        infoWindow51.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "신혼부부";
            }
        });
        infoWindow51.open(marker51);

        //52번
        Marker marker52 = new Marker();
        marker52.setPosition(latlng52);
        marker52.setMap(naverMap);
        marker52.setWidth(70);
        marker52.setHeight(100);

        InfoWindow infoWindow52 = new InfoWindow();
        infoWindow52.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "아무리찾아도찾기힘든집";
            }
        });
        infoWindow52.open(marker52);

        //53번
        Marker marker53 = new Marker();
        marker53.setPosition(latlng53);
        marker53.setMap(naverMap);
        marker53.setWidth(70);
        marker53.setHeight(100);

        InfoWindow infoWindow53 = new InfoWindow();
        infoWindow53.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "강릉집";
            }
        });
        infoWindow53.open(marker53);

        //54번
        Marker marker54 = new Marker();
        marker54.setPosition(latlng54);
        marker54.setMap(naverMap);
        marker54.setWidth(70);
        marker54.setHeight(100);

        InfoWindow infoWindow54 = new InfoWindow();
        infoWindow54.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "예미";
            }
        });
        infoWindow54.open(marker54);

        //55번
        Marker marker55 = new Marker();
        marker55.setPosition(latlng55);
        marker55.setMap(naverMap);
        marker55.setWidth(70);
        marker55.setHeight(100);

        InfoWindow infoWindow55 = new InfoWindow();
        infoWindow55.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "뼈대있는짬뽕";
            }
        });
        infoWindow55.open(marker55);

        //56번
        Marker marker56 = new Marker();
        marker56.setPosition(latlng56);
        marker56.setMap(naverMap);
        marker56.setWidth(70);
        marker56.setHeight(100);

        InfoWindow infoWindow56 = new InfoWindow();
        infoWindow56.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "흑차돌";
            }
        });
        infoWindow56.open(marker56);

        //57번
        Marker marker57 = new Marker();
        marker57.setPosition(latlng57);
        marker57.setMap(naverMap);
        marker57.setWidth(70);
        marker57.setHeight(100);

        InfoWindow infoWindow57 = new InfoWindow();
        infoWindow57.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "흙심흑염소";
            }
        });
        infoWindow57.open(marker57);

        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                updateMap(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                alertStatus(provider);
            }

            public void onProviderEnabled(String provider) {
                alertProvider(provider);
            }

            public void onProviderDisabled(String provider) {
                checkProvider(provider);
            }
        };

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            return;
        }

        String locationProvider;
        locationProvider = LocationManager.GPS_PROVIDER;
        locationManager.requestLocationUpdates(locationProvider, 1, 1, locationListener);
        locationProvider = LocationManager.NETWORK_PROVIDER;
        locationManager.requestLocationUpdates(locationProvider, 1, 1, locationListener);
    }

    public void checkProvider(String provider) {
        Toast.makeText(this, provider + "에 의한 위치서비스가 꺼져 있습니다. 켜주세요...", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    public void alertProvider(String provider) {
        Toast.makeText(this, provider + "서비스가 켜졌습니다!", Toast.LENGTH_LONG).show();
    }

    public void alertStatus(String provider) {
        Toast.makeText(this, "위치서비스가 " + provider + "로 변경되었습니다!", Toast.LENGTH_LONG).show();
    }

    public void updateMap(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        curr_LOC = new LatLng(latitude, longitude);

        if (prev_LOC == null) {
            CameraUpdate cameraUpdate = CameraUpdate.scrollTo(curr_LOC);
            mMap.moveCamera(cameraUpdate);

            LocationOverlay locationOverlay = mMap.getLocationOverlay();
            locationOverlay.setVisible(true);
            locationOverlay.setPosition(curr_LOC);

            prev_LOC = curr_LOC;

        } else {
            CameraUpdate cameraUpdate1 = CameraUpdate.scrollTo(curr_LOC);
            mMap.moveCamera(cameraUpdate1);

            PathOverlay path = new PathOverlay();
            path.setCoords(Arrays.asList(
                    new LatLng(prev_LOC.latitude, prev_LOC.longitude),
                    new LatLng(curr_LOC.latitude, curr_LOC.longitude)
            ));
            path.setMap(mMap);
            path.setOutlineColor(Color.YELLOW);
            path.setColor(Color.GREEN);
            path.setWidth(15);

            LocationOverlay locationOverlay = mMap.getLocationOverlay();
            locationOverlay.setVisible(true);
            locationOverlay.setPosition(curr_LOC);

            prev_LOC = curr_LOC;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null)
            locationManager.removeUpdates(locationListener);
    }
}