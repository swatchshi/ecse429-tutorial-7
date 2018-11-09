import android.widget.Button;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
import ca.mcgill.ecse321.eventregistration.HttpUtils;
import ca.mcgill.ecse321.eventregistration.MainActivity;
import ca.mcgill.ecse321.eventregistration.R;

import static com.google.common.truth.Truth.assertThat;

@RunWith(MockitoJUnitRunner.class)


@SmallTest
public class InstrumentationTest {

    private class ParticipantsAnswer implements Answer<JSONArray> {
        @Override
        public JSONArray answer(InvocationOnMock invocation) throws JSONException {
            Object[] arguments = invocation.getArguments();
            JsonHttpResponseHandler h = (JsonHttpResponseHandler) arguments[2];
            h.onSuccess(200, null, new JSONArray()
                    .put(new JSONObject().put("name", "P1"))
                    .put(new JSONObject().put("name", "P2"))
                    .put(new JSONObject().put("name", "P3")));
            return null;
        }
    }


    @Mock
    AsyncHttpClient httpClient;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void aTest() throws Throwable {
        final MainActivity mainActivity = activityTestRule.getActivity();
        mainActivity.setHttpUtils(new HttpUtils(httpClient));

        Mockito.when(httpClient.get(Mockito.anyString(), Mockito.<RequestParams>anyObject(), Mockito.<ResponseHandlerInterface>anyObject())).thenAnswer(new ParticipantsAnswer());

        final ListView participantList = mainActivity.findViewById(R.id.participant_list);
        final int numberOfParticipants = participantList.getAdapter().getCount();

        assertThat(numberOfParticipants).isEqualTo(0);

      /*  Button button = (Button) mainActivity.findViewById(R.id.button);
        button.performClick();

        int numberOfParticipants2 = participantList.getAdapter().getCount();
        assertThat(numberOfParticipants2).isEqualTo(3); */

        activityTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Button button = (Button) mainActivity.findViewById(R.id.button);
                button.performClick();
                int numberOfParticipants2 = participantList.getAdapter().getCount();
                assertThat(numberOfParticipants2).isEqualTo(3);
            }
        });


    }


}