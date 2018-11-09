package ca.mcgill.ecse321.eventregistration;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BlackholeHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


// ... other imports

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IntegrationTest {

    @Mock
    AsyncHttpClient httpClient;

    HttpUtils httpUtils;

    @Before
    public void setUp(){
        httpUtils = new HttpUtils(httpClient);
    }

    @Test
    public void testHttpPostCalled() {
        String url = "participants/";
        RequestParams params = new RequestParams();
        BlackholeHttpResponseHandler handler = new BlackholeHttpResponseHandler();

        // Call the SUT
        httpUtils.post(url, params, handler);

        // Verify interactions
        Mockito.verify(httpClient).post(HttpUtils.getBaseUrl() + url, params, handler);
    }
}