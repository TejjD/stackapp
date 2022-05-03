package jhb.dvt.co.za.stackapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import jhb.dvt.co.za.stackapp.R;
import jhb.dvt.co.za.stackapp.models.Question;
import jhb.dvt.co.za.stackapp.models.Questions;
import jhb.dvt.co.za.stackapp.utils.DateUtils;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {

    private List<Question> questions;
    private Context mContext;

    public QuestionsAdapter(Context context, String result) {
        mContext = context;

        Gson gson = new Gson();
        Questions question = gson.fromJson(result, Questions.class);
        questions = question.getQuestions();
    }

    @Override
    public QuestionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.question_layout, parent, false);
        return new ViewHolder(view);
    }


    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(QuestionsAdapter.ViewHolder holder, int position) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder.tagsRecyclerView.setLayoutManager(layoutManager);


        holder.questionText.setText(questions.get(position).getTitle());
        holder.questionAnswers.setText(String.valueOf(questions.get(position).getAnswerCount()));

        if (questions.get(position).getIsAnswered()) {
            holder.questionAnswers.setBackground(mContext.getDrawable(R.drawable.circle_green_64));
        }

        TagsAdapter tagsAdapter = new TagsAdapter(mContext, questions.get(position).getTags());
        holder.tagsRecyclerView.setAdapter(tagsAdapter);

        String dates = DateUtils.getDifferenceInDates(DateUtils.convertUnixToDate((questions.get(position).getCreationDate())));

        holder.questionTime.setText(dates);
    }


    @Override
    public int getItemCount() {
        return questions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView questionAnswers;
        private TextView questionText;
        private RecyclerView tagsRecyclerView;
        private TextView questionTime;

        ViewHolder(View itemView) {
            super(itemView);

            questionAnswers = itemView.findViewById(R.id.questionAnswers);
            questionText = itemView.findViewById(R.id.questionText);
            questionTime = itemView.findViewById(R.id.questionTime);
            tagsRecyclerView = itemView.findViewById(R.id.tagsRecyclerView);
        }
    }
}