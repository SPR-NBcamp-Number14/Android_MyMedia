# Android_MyMedia

## 앱 설명

### 실행 방법

local.properties 에서 변수를 선언해줘야합니다.

          api.key = "YOUTUBE V3 API KEY"

### Homefragment

youtube data v3 api의 videos & categories 사용하였습니다.

addOnScrollListener를 사용하여 무한 스크롤 구현되었습니다.

상단 카테고리 클릭 시 카테고리에 맞게 영상 리스트 수정됩니다.

motionlayout을 이용하여 우측 하단 버튼 클릭 시 상단 하단 이동 버튼 생성됩니다.

viewmodel 및 repository가 사용되었습니다.

repository를 통해 api를 받아옵니다.


### SearchFragment

youtube data v3 api 의 search & categories 사용하였습니다.

edittext 좌측의 버튼으로 검색이 가능합니다.

edittext 우측 x버튼으로 검색어 제거가 가능합니다.

viewmodel 및 repository가 사용되었습니다.

repository를 통해 api를 받아옵니다.



### DetailActivity

모든 fragment에서 접근하기 쉽도록 Activity로 설계되었습니다.

pierfrancescosoffritti 를 사용하여 youtube영상 재생합니다.

like 버튼을 이용하여 room 직접적으로 데이터를 넣고 제거합니다.

motionlayout을 사용하여 스크롤 시 영상 부분을 안보이게 합니다.


### MyVideoFragment

room 데이터와 viewmodel을 연결하여 recyclearview를 그려줍니다.

viewmodel 및 repository가 사용되었습니다.


## 사용 gradle

### sdk

        minSdk 26
        targetSdk 33

### 사용 코틀린 및 자바 버전

  

        sourceCompatibility JavaVersion.VERSION_17
        targetCompatibility JavaVersion.VERSION_17

        
    

    
    kotlinOptions {

    
        jvmTarget = '17'

        
    }

### gradle




    //
    implementation'io.coil-kt:coil:2.4.0'
    //youtube v3 data
    implementation 'com.google.apis:google-api-services-youtube:v3-rev20230904-2.0.0'
    //gson
    implementation 'com.google.code.gson:gson:2.10.1'

    //glide
    implementation 'com.github.bumptech.glide:glide:4.16.0'

    //retrofit2
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

    //okhttp
    implementation 'com.squareup.okhttp3:okhttp:4.10.0'

    // viewmodel 및 lifecycle
    // 유튜브 api
    implementation 'com.android.identity:identity-credential:20230420'


    //activityViewmodel
    implementation 'androidx.activity:activity-ktx:1.7.2'
    implementation 'androidx.fragment:fragment-ktx:1.6.1'

    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:"2.5.1"'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-compose:"2.5.1"'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:"2.5.1"'

    implementation 'androidx.core:core-ktx:1.9.0'

    implementation 'androidx.core:core-ktx:1.8.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.5.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'

    //room
    def room_version = "2.5.1"
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    testImplementation "androidx.room:room-testing:$room_version"

    //youtube player
    dependencies {
        implementation 'com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0'
    }

    //Motionlayout
    implementation 'androidx.constraintlayout:constraintlayout:2.0.0-beta3'
