package jhb.dvt.co.za.stackapp.asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import jhb.dvt.co.za.stackapp.utils.HttpUtils;

public class QuestionsAsyncTask extends AsyncTask<Context, Void, String> {

    private final ProgressDialog dialog;
    private QuestionsResultListener mQuestionsResultListener;
    private String mUrl;

    public interface QuestionsResultListener {
        void setQuestionsResult(String result);
    }

    public QuestionsAsyncTask(QuestionsResultListener questionsResultListener, String url, Context context) {
        mQuestionsResultListener = questionsResultListener;
        mUrl = url;
        dialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("Stacking questions! Please wait...");
        dialog.show();
    }

    @Override
    protected String doInBackground(Context... contexts) {

        return HttpUtils.makeServiceCall(mUrl);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        mQuestionsResultListener.setQuestionsResult(s);
    }
}
