package ca.mcgill.ecse321.eventregistration;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LocalUITest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    public AsyncHttpClient httpClient;

    @Test
    public void aTest() {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.setHttpUtils(new HttpUtils(httpClient));
        mainActivity.addParticipant(null);
        Mockito.verify(httpClient).post(Mockito.anyString(),Mockito.<RequestParams>any(),Mockito.<ResponseHandlerInterface>any());
    }

}